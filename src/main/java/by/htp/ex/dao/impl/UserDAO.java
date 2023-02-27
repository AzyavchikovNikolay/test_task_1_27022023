package by.htp.ex.dao.impl;

import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.dao.DaoException;
import by.htp.ex.bean.User;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserDAO implements IUserDAO {

	private final static String SQL_QUERY_USER_LOGIN_PASSWORD = "SELECT * FROM users JOIN status_user ON "
			+ "users.status_user_id = status_user.id WHERE users.login = ? AND users.password = ? AND status_user.status_name = ?";
	private final static String SQL_QUERY_USER_ROLE = "SELECT * FROM users JOIN role ON "
			+ "users.role_id = role.id WHERE users.id = ?";
	private final static String SQL_QUERY_USER_LOGIN = "SELECT * FROM users WHERE login = ?";
	private final static String SQL_QUERY_REGISTRATION_NEW_USER = "INSERT INTO users (login, password, role_id, status_user_id) VALUES (?, ?, ?, ?)";
	private final static String SQL_QUERY_ADD_NEW_USER_DETAILS = "INSERT INTO user_details"
			+ " (user_id, name, surname, phone, email, date_of_creation, birthday)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

	private final static String DB_NEWS_TABLE_USERS_COLUMN_ID = "id";
	private final static String DB_NEWS_TABLE_USERS_COLUMN_LOGIN = "login";
	private final static String DB_NEWS_TABLE_USERS_COLUMN_PASSWORD = "password";
	private final static String DB_NEWS_TABLE_ROLE_COLUMN_ROLE_NAME = "role_name";
	
	private final static String SQL_ERROR = "SQL error";
	private final static String DATEBASE_CONNECTION_ERROR = "Datebase connection error";
	private final static String USER_ROLE_GUEST = "guest";
	
	@Override
	public String logination(String login, String password) throws DaoException{
	
		String userRole = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String statusUserName = "active";
		int idUser=0; 
		
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			try {
				statement = connection.prepareStatement(SQL_QUERY_USER_LOGIN_PASSWORD);
				statement.setString(1, login);
				statement.setString(2, password);
				statement.setString(3, statusUserName);
				resultSet = statement.executeQuery();
				while(resultSet.next()) {
					idUser = (int)resultSet.getInt(DB_NEWS_TABLE_USERS_COLUMN_ID);
				}
			} catch (SQLException e) {
				throw new DaoException(SQL_ERROR, e);
			} finally {
				try {
					if (resultSet != null) {
						resultSet.close();
					}
				} catch (SQLException e) {
					throw new DaoException(SQL_ERROR, e);
				}
				try {
					if (statement != null) {
					statement.close();
					}
				} catch (SQLException e) {
					throw new DaoException(SQL_ERROR, e);
				}
			}
			try {
				statement = connection.prepareStatement(SQL_QUERY_USER_ROLE);	
				statement.setInt(1, idUser);
				resultSet = statement.executeQuery();
				while(resultSet.next()) {
					userRole = resultSet.getString(DB_NEWS_TABLE_ROLE_COLUMN_ROLE_NAME);
				}
			} catch (SQLException e) {
				throw new DaoException(SQL_ERROR, e);
			}
		} catch (ConnectionPoolException e) {
			throw new DaoException(DATEBASE_CONNECTION_ERROR, e);
		} finally {
			ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
		}	
		if(userRole == null) {
			return USER_ROLE_GUEST;
		} else {
			return userRole;
		}
	}

	/*public String getRole(String login, String password) {
		return "guest";
	}*/
	
	@Override
	public boolean registration(User user) throws DaoException{
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean registrationResult = false;
		int idNewUser = 0;

		try {
			connection = ConnectionPool.getInstance().takeConnection();
			try {
				statement = connection.prepareStatement(SQL_QUERY_USER_LOGIN);
				statement.setString(1, user.getLogin());
				resultSet = statement.executeQuery();
				while(resultSet.next()) {
					return registrationResult;
				}
			} catch (SQLException e) {
				throw new DaoException(SQL_ERROR, e);
			} finally {
				try {
					if (resultSet != null) {
						resultSet.close();
					}
				} catch (SQLException e) {
					throw new DaoException(SQL_ERROR, e);
				}
				try {
					if (statement != null) {
					statement.close();
					}
				} catch (SQLException e) {
					throw new DaoException(SQL_ERROR, e);
				}
			}	
			try {
				statement = connection.prepareStatement(SQL_QUERY_REGISTRATION_NEW_USER);
				statement.setString(1, user.getLogin());
				statement.setString(2, user.getPassword());
				statement.setInt(3, 2);
				statement.setInt(4, 1);
				statement.executeUpdate();
			} catch (SQLException e) {
				throw new DaoException(SQL_ERROR, e);
			} finally {
				try {
					if (statement != null) {
					statement.close();
					}
				} catch (SQLException e) {
					throw new DaoException(SQL_ERROR, e);
				}
			}	
			try {
				statement = connection.prepareStatement(SQL_QUERY_USER_LOGIN);
				statement.setString(1, user.getLogin());
				resultSet = statement.executeQuery();
				while(resultSet.next()) {
					idNewUser = (int)resultSet.getInt(DB_NEWS_TABLE_USERS_COLUMN_ID);
				}
			} catch (SQLException e) {
				throw new DaoException(SQL_ERROR, e);
			} finally {
				try {
					if (resultSet != null) {
						resultSet.close();
					}
				} catch (SQLException e) {
					throw new DaoException(SQL_ERROR, e);
				}
				try {
					if (statement != null) {
					statement.close();
					}
				} catch (SQLException e) {
					throw new DaoException(SQL_ERROR, e);
				}
			}	
			try {
				
				Date dateOfCreateUser = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH*mm*ss");
				String formattedDateOfCreateUser = formatter.format(dateOfCreateUser);
				
				statement = connection.prepareStatement(SQL_QUERY_ADD_NEW_USER_DETAILS);
				statement.setInt(1, idNewUser);
				statement.setString(2, user.getName());
				statement.setString(3, user.getSurname());
				statement.setString(4, user.getPhone());
				statement.setString(5, user.getEmail());
				statement.setString(6, formattedDateOfCreateUser);
				statement.setString(7, user.getBirthday());
				statement.executeUpdate();
				registrationResult = true;
			} catch (SQLException e) {
				throw new DaoException(SQL_ERROR, e);
			}
		} catch (ConnectionPoolException e) {
			throw new DaoException(DATEBASE_CONNECTION_ERROR, e);
		} finally {
			ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
		}	
		return registrationResult;
	}
	
	@Override
	public int idUser(String login, String password) throws DaoException{
	
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String statusUserName = "active";
		int idUser=0; 
		
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			try {
				statement = connection.prepareStatement(SQL_QUERY_USER_LOGIN_PASSWORD);
				statement.setString(1, login);
				statement.setString(2, password);
				statement.setString(3, statusUserName);
				resultSet = statement.executeQuery();
				while(resultSet.next()) {
					idUser = (int)resultSet.getInt(DB_NEWS_TABLE_USERS_COLUMN_ID);
				}
			} catch (SQLException e) {
				throw new DaoException(SQL_ERROR, e);
			} finally {
				try {
					if (resultSet != null) {
						resultSet.close();
					}
				} catch (SQLException e) {
					throw new DaoException(SQL_ERROR, e);
				}
				try {
					if (statement != null) {
					statement.close();
					}
				} catch (SQLException e) {
					throw new DaoException(SQL_ERROR, e);
				}
			}
		} catch (ConnectionPoolException e) {
			throw new DaoException(DATEBASE_CONNECTION_ERROR, e);
		} finally {
			ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
		}	
		return idUser;
	}	
}
