package by.htp.ex.service.impl;

import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.DaoProvider;

public class UserServiceImpl implements IUserService {

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
	
	@Override
	public String signIn(String login, String password) throws ServiceException{
		
		try {
			return userDAO.logination(login, password);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}	
	}
	
	@Override
	public int findIdUser(String login, String password) throws ServiceException{
		
		try {
			return userDAO.idUser(login, password);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}	
	}
	
	
	@Override
	public String registration(User user) throws ServiceException {
		
		try {
			if(userDAO.registration(user)) {
				return user.getRole();
			} else
				return "guest";
				
		} catch(DaoException e) {
			throw new ServiceException(e);
		}	
	}
}
