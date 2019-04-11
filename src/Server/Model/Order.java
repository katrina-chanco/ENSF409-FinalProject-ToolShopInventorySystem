package Server.Model;
// Nathan Darby - 30033588
// Katrina Chanco - 30037408
// Evan Krul - 30043180


import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

import Server.Controller.*;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Order managing class.
 */
public class Order extends DatabaseManager implements Constants{
	/**
	 * Constructor for order from list of orderLines.
	 * @param orderLines List of orderLines to be used to create the object.
	 */
	public Order(LinkedList<OrderLine> orderLines, Database database) {
		super(database);
	}

	/**
	 * Add an order by creating a new orderLine and writing it to a file.
	 * @param item Item to be ordered.
	 * @param quantity Quantity to be ordered.
	 */
	synchronized public void addOrder(Item item,int quantity)  {
		OrderLine orderLine = new OrderLine(item.getId(),quantity, LocalDate.now());
	//	orderLines.add(orderLine);
		item.setQuantity(item.getQuantity()+quantity,database);

		try {
			Connection connection = database.getConnection();
			String sqlString = "SELECT orderId, date FROM orders WHERE DATE=(SELECT MAX(date) FROM orders)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			try {
				ResultSet resultSet = preparedStatement.executeQuery();
				resultSet.first();

				ZoneId defaultZoneId = ZoneId.systemDefault();
				Instant instant = new Date().toInstant();
				int orderId;
				if (!(instant.atZone(defaultZoneId).toLocalDate().toString()).equals(resultSet.getString("date"))) {
					orderId = new Random().nextInt(99999) + 10000;
					String sqlStringDate = "INSERT INTO `toolShop`.`orders` (`orderId`,`date`) VALUES (?,?);";
					PreparedStatement preparedStatementDate = connection.prepareStatement(sqlStringDate);
					preparedStatementDate.setInt(1, orderId);
					preparedStatementDate.setDate(2,new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
					preparedStatementDate.execute();
				} else {
					orderId = resultSet.getInt("orderId");
				}

				String sqlStringInsertOrder = "INSERT INTO `toolShop`.`orderLines` (`itemId`, `orderId`, `quantityOrdered`) VALUES (?, ?, ?);";
				PreparedStatement preparedStatementInsertOrder = connection.prepareStatement(sqlStringInsertOrder);
				preparedStatementInsertOrder.setInt(1,item.getId());
				preparedStatementInsertOrder.setInt(2,orderId);
				preparedStatementInsertOrder.setInt(3,quantity);
				preparedStatementInsertOrder.execute();


			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	/**
	 * Gets a list of orderLines for a given date range.
	 * @param formattedDateStart start of date range(String in format yyyy-mm-dd cause SQL problems)
	 * @param formattedDateEnd end of date range(String in format yyyy-mm-dd cause SQL problems)
	 * @return json of orders with orderLines for a given date range.
	 */
	public JSONObject getOrdersForDate(String formattedDateStart, String formattedDateEnd) {
		try {
			Connection connection = database.getConnection();
			String sqlString = "SELECT orders.orderId,orders.date,orderLines.quantityOrdered,items.itemName,items.quantity,items.price,suppliers.supplierId,suppliers.companyName,suppliers.companyAddress,suppliers.salesContact  FROM orders\n" +
					" INNER JOIN orderLines ON orders.orderId = orderLines.orderId\n" +
					" INNER JOIN items ON orderLines.itemId = items.itemId \n" +
					" INNER JOIN suppliers ON items.supplier = suppliers.supplierId \n" +
					" WHERE DATE>=? AND  DATE<=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,formattedDateStart);
			preparedStatement.setString(2,formattedDateEnd);
			ResultSet resultSet = preparedStatement.executeQuery();
			JSONArray jsonOrders = new JSONArray();
			String currentDate = "";
			JSONArray currentOrder = new JSONArray();
			while (resultSet.next()) {
				JSONObject jsonOrderLine = new JSONObject();
				JSONObject jsonOrder = new JSONObject();
				jsonOrder.put("orderId",resultSet.getInt("orderId"));
				jsonOrder.put("orderDate",resultSet.getString("date"));
				jsonOrder.put("quantityOrdered",resultSet.getInt("quantityOrdered"));
				JSONObject jsonItem = new JSONObject();
				jsonItem.put("itemName",resultSet.getString("itemName"));
				jsonItem.put("quantity",resultSet.getString("quantity"));
				jsonItem.put("price",resultSet.getString("price"));
				JSONObject jsonSupplier = new JSONObject();
				jsonSupplier.put("companyName",resultSet.getString("companyName"));
				jsonSupplier.put("companyAddress",resultSet.getString("companyAddress"));
				jsonSupplier.put("salesContact",resultSet.getString("salesContact"));

				jsonOrderLine.put("order",jsonOrder);
				jsonOrderLine.put("item",jsonItem);
				jsonOrderLine.put("supplier",jsonSupplier);

				currentOrder.put(jsonOrderLine);
			}
			JSONObject cur = new JSONObject();
			cur.put("orders", currentOrder);
			return cur;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//	/**
//	 * Writes the orderLine to the file specified in Constants.
//	 * @param orderLine orderLine to write.
//	 * @throws java.io.IOException Throws error for file writing.
//	 */
//	public void writeOrderLine(OrderLine orderLine) throws java.io.IOException {
//		String fileOutput = "";
//		if(orderLines.indexOf(orderLine) == 0) {
//			fileOutput += orderLine.getHeader();
//		} else if(orderLines.get(orderLines.size() -2).getDateOrdered().compareTo(orderLines.get(orderLines.size() -1).getDateOrdered()) != 0) {
//			fileOutput += orderLine.getHeader();
//		}
//		fileOutput += orderLine.getBody();
//
//		File yourFile = new File(Constants.orderFileName);
//		yourFile.createNewFile(); // if file already exists will do nothing
//		FileOutputStream oFile = new FileOutputStream(yourFile, true);
//		oFile.write(fileOutput.getBytes());
//		oFile.flush();
//		oFile.close();
//	}

	/**
	 * Abstract generic method to get whole table. To be implanted by every instance of Database.
	 *
	 * @return Generic type
	 */
	@Override
	public Object getWholeTable() {
		LinkedList<Order> returnList = new LinkedList<>();
		try {
			Connection connection = database.getConnection();
			String sqlString = "SELECT * FROM orders";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			try {
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Order order = getOrderFromResultSet(resultSet);
					returnList.add(order);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnList;

	}

	/**
	 * Helper method that build an Order object from a resultSet
	 * @param resultSet resultSet to build order item from
	 * @return Order item
	 */
	private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
		LinkedList<OrderLine> orderLines = getOrderLines(resultSet.getInt("orderId"));
		Order order = new Order(orderLines,database);
		return order;
	}

	/**
	 * Returns a list of orderlines for an order
	 * @param orderId id of order
	 * @return
	 */
	public LinkedList<OrderLine> getOrderLines(int orderId) {
		LinkedList<OrderLine> returnList = new LinkedList<>();
		try {
			Connection connection = database.getConnection();
			String sqlString = "SELECT * FROM orderLines WHERE orderLineId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1,orderId);
			try {
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					ZoneId defaultZoneId = ZoneId.systemDefault();
					Instant instant = resultSet.getDate("date").toInstant();
					LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
					OrderLine order = getOrderLineFromResultSet(resultSet,localDate);
					returnList.add(order);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnList;
	}



	/**
	 * Helper method that build an orderLine from a resultSet.
	 * @param resultSet resultSet to build orderLine item from.
	 * @return orderLine.
	 */
	private OrderLine getOrderLineFromResultSet(ResultSet resultSet, LocalDate date) throws SQLException {
		return new OrderLine(resultSet.getInt("itemId"),resultSet.getInt("quantityOrdered"),date);
	}
}

//SELECT orders.orderId,orders.DATE,orderLines.quantityOrdered,items.itemName,items.quantity,items.price,suppliers.supplierId,suppliers.companyName,suppliers.companyAddress,suppliers.salesContact  FROM orders
// INNER JOIN orderLines ON orders.orderId = orderLines.orderId
// INNER JOIN items ON orderLines.itemId = items.itemId
// INNER JOIN suppliers ON items.supplier = suppliers.supplierId