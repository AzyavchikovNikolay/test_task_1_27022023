package by.htp.ex.service.impl;

import by.htp.ex.service.INewsService;
import java.util.List;
import by.htp.ex.bean.News;
import by.htp.ex.service.ServiceException;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.NewsDAOException;

public class NewsServiceImpl implements INewsService  {
	
	private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDao();
	
	@Override
	public void save(News news, int idUser) throws ServiceException {
		
		try {
				newsDAO.addNews(news, idUser);
			} catch (NewsDAOException e) {
				// TODO Auto-generated catch block
				throw new ServiceException(e);
			}
	}
	
	@Override
	public void find() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void update(News news, int idUser) throws ServiceException {
		
		try {
				newsDAO.updateNews(news, idUser);
			} catch (NewsDAOException e) {
				// TODO Auto-generated catch block
				throw new ServiceException(e);
			}
	}
	

	
	@Override
	public List<News> latestList(int count) throws ServiceException {
		
		try {
			return newsDAO.getLatestsList(5);
		} catch(NewsDAOException e) {
			throw new ServiceException(e);
			
		}
	}
	
	@Override
	public List<News> list() throws ServiceException {
		
		try {
			return newsDAO.getList();
		} catch(NewsDAOException e) {
			throw new ServiceException(e);
			
		}
	}
	
	@Override
	public News findById(int id) throws ServiceException {
		
		try {
			return newsDAO.fetchById(id);
		} catch(NewsDAOException e) {
			throw new ServiceException(e);
			
		}
	}
	
	@Override
	public void deleteNews(int idNews) throws ServiceException {
		
		try {
			newsDAO.deleteNews(idNews);
		} catch(NewsDAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public void deleteNewses(String[] idNewses) throws ServiceException {
		
		try {
			newsDAO.deleteNewses(idNewses);
		} catch(NewsDAOException e) {
			throw new ServiceException(e);
		}
	}
}
