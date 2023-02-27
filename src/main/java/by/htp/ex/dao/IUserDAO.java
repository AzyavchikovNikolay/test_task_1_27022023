package by.htp.ex.dao;

import by.htp.ex.bean.User;

public interface IUserDAO {
	
	String logination(String login, String password) throws DaoException;
	boolean registration(User user) throws DaoException;
	//String getRole(String login, String password) throws DaoException;
	int idUser(String login, String password) throws DaoException;

}
