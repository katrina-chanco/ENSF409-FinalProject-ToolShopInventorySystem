package Server.Model;

import Server.Controller.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Suppliers management class for the shop.
 */
public class Suppliers extends DatabaseManager {
	/**
	 * Constructor for the suppliers manager.
	 */
	public Suppliers(Database database) {
		super(database);
	}

	/**
	 * Get suppliers array
	 * @return
	 */
	public LinkedList<Supplier> getSuppliers() {
		return getWholeTable();
	}

	/**
	 * Search for supplier by it's data.
	 * @param id Id to search for.
	 * @return Returns supplier object if it's found else null.
	 */
	public Supplier searchSupplierById (int id) {
		LinkedList<Supplier> supp = getSuppliers();
		for (Supplier s:supp) {
			if(s.getId() == id){
				return s;
			}
		}
		return null;
	}
	/**
	 * Search for supplier by it's data.
	 * Search using a provided list of suppliers to minimize DB calls.
	 * @param id Id to search for.
	 * @return Returns supplier object if it's found else null.
	 */
	public Supplier searchSupplierById (int id, LinkedList<Supplier> supp) {
		for (Supplier s:supp) {
			if(s.getId() == id){
				return s;
			}
		}
		return null;
	}

	/**
	 * Gets whole table of supplier from the database
	 * @return Generic type
	 */
	@Override
	public LinkedList<Supplier> getWholeTable() {
		LinkedList<Supplier> returnList = new LinkedList<>();
		try {
			Connection connection = database.getConnection();
			String sqlString = "SELECT * FROM suppliers";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			try {
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Supplier supplier = new Supplier(resultSet.getInt("supplierId"),
							resultSet.getString("companyName"),
							resultSet.getString("companyAddress"),
							resultSet.getString("salesContact"));
					returnList.add(supplier);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnList;
	}
}
