package Server.Model;

import Server.Controller.Database;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class to manage user
 */
public class User {

	/**
	 * is valid user
	 */
	private boolean validUser;
	/**
	 * username
	 */
	private String userName;
	/**
	 * password
	 */
	private String password;
	/**
	 * access level
	 */
	private AccountType accessLevel;
	/**
	 * Database object
	 */
	Database database;

	/**
	 * Constructs a user object from the database.
	 * Sets validUser to false if the user is not found in the DB with the given password
	 * @param userName username
	 * @param password password
	 * @param database database
	 */
	public User(String userName, String password, Database database) {
		this.database = database;
		try {
			Connection connection = database.getConnection();
			String sqlString = "SELECT * FROM users JOIN userLevels ON userLevels.userLevelID = users.accessLevel " +
					"WHERE userName=? AND PASSWORD=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,userName);
			preparedStatement.setString(2,password);
			try {
				ResultSet resultSet = preparedStatement.executeQuery();
				if(!resultSet.next()) {
					validUser = false;
				} else {
					this.userName = userName;
					this.password = password;
					accessLevel = new AccountType(resultSet.getInt("accessLevel"),resultSet.getString("accessLevelName"));
					validUser = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}



	public String getUserName() {
		return userName;
	}

	public AccountType getAccessLevel() {
		return accessLevel;
	}


	public boolean isValidUser() {
		return validUser;
	}

	public ArrayList<AccountType> getAccountTypes() {
		ArrayList<AccountType> accountTypes = new ArrayList<>();
		try {
			Connection connection = database.getConnection();
			String sqlString = "SELECT * FROM userLevels";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			try {
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()){
					accountTypes.add(new AccountType(resultSet.getInt("userLevelID"),resultSet.getString("accessLevelName")));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountTypes;
	}

	/**
	 * Makes a new user
	 * @param userName username
	 * @param password md5 password
	 * @param level access level
	 * @return
	 */
	public boolean addUser(String userName, String password, int level) {

		try {
			Connection connection = database.getConnection();
			String sqlString = "SELECT * FROM users WHERE users.userName=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1,userName);
			try {
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					return false;
				}
				String sqlStringInsert = "INSERT INTO users (`userName`,`password`,`accessLevel`) VALUES (?,?,?)";
				PreparedStatement preparedStatementInsert = connection.prepareStatement(sqlStringInsert);
				preparedStatementInsert.setString(1,userName);
				preparedStatementInsert.setString(2,password);
				preparedStatementInsert.setInt(3,level);
				preparedStatementInsert.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
