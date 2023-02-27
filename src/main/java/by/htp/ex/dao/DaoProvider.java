package by.htp.ex.dao;

import by.htp.ex.dao.impl.UserDAO;
import by.htp.ex.dao.impl.NewsDAO;

public final class DaoProvider {
	private static final DaoProvider instance = new DaoProvider();
	
	private final IUserDAO userDao = new UserDAO();
	private final INewsDAO newsDao = new NewsDAO();
	
	private DaoProvider() {	
	}
	
	public IUserDAO getUserDao() {
		return userDao;
	}
	
	public INewsDAO getNewsDao() {
		return newsDao;
	}
	
	public static DaoProvider getInstance() {
		return instance;
	}
	
}
