package by.htp.ex.service;

import by.htp.ex.bean.User;

public interface IUserService {

	String signIn(String login, String password) throws ServiceException;
	int findIdUser(String login, String password) throws ServiceException;
	String registration(User user) throws ServiceException;	
}
