package by.htp.ex.dao.impl;

import by.htp.ex.bean.News;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.service.ServiceException;

public class NewsDAO implements INewsDAO {

	private final static String SQL_QUERY_LATEST_LIST_NEWS = "SELECT * FROM news JOIN status_of_news ON "
			+ "news.status_of_news_id = status_of_news.id WHERE status_of_news.status_news = ? ORDER BY date_of_create DESC LIMIT ?";
	private final static String SQL_QUERY_LIST_ALL_NEWS_FOR_ADMIN = "SELECT * FROM news JOIN status_of_news ON"
			+ " news.status_of_news_id = status_of_news.id ORDER BY news.date_of_create DESC";
	/*private final static String SQL_QUERY_LIST_NEWS_FOR_USER = "SELECT * FROM news JOIN status_of_news ON "
			+ "news.status_of_news_id = status_of_news.id WHERE status_of_news.id = ? ORDER BY date_of_create DESC";*/
	private final static String SQL_QUERY_FETCH_NEWS = "SELECT * FROM news JOIN status_of_news ON"
			+ " news.status_of_news_id = status_of_news.id WHERE news.id = ?";
	private final static String SQL_QUERY_CHANGE_NEWS_STATUS_ON_REMOTE = "UPDATE news SET status_of_news_id=? WHERE id=?";
	private final static String SQL_QUERY_CHANGE_STATUS_OF_NEWS_ON_REMOTE = "INSERT INTO news_delete (news_id, user_id, date_of_deletion)"
			+ " VALUES (?, ?, ?)";
	private final static String SQL_QUERY_ADD_NEW_NEWS = "INSERT INTO news"
			+ " (title, brief, content, date_of_create, status_of_news_id, user_author_id)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";
	private final static String SQL_QUERY_EDIT_NEWS = "UPDATE news SET title=?, brief=?, content=?, status_of_news_id=? WHERE id=?";
	//private final static String SQL_QUERY_EDIT_NEWS = "UPDATE news SET title=? brief=? content=? status_of_news_id=?"
			//+ " WHERE id=?";
	private final static String SQL_QUERY_ADD_DATE_ABOUT_NEWS_UPDATE = "INSERT INTO news_update (news_id, user_id, date_of_update) VALUES (?, ?, ?)";
	
	private final static String DB_NEWS_TABLE_NEWS_COLUMN_ID = "id";
	private final static String DB_NEWS_TABLE_NEWS_COLUMN_TITLE = "title";
	private final static String DB_NEWS_TABLE_NEWS_COLUMN_BRIEF = "brief";
	private final static String DB_NEWS_TABLE_NEWS_COLUMN_CONTENT = "content";
	private final static String DB_NEWS_TABLE_NEWS_COLUMN_DATE_OF_CREATE = "date_of_create";
	private final static String DB_NEWS_TABLE_NEWS_COLUMN_STATUS_OF_NEWS = "status_of_news.status_news";
	//private final static String DB_NEWS_TABLE_NEWS_COLUMN_STATUS_OF_NEWS_ID = "status_of_news_id";
	//private final static String DB_NEWS_TABLE_NEWS_COLUMN_USER_AUTHOR_ID = "user_author_id";
	
	private final static String SQL_ERROR = "SQL error";
	private final static String DATEBASE_CONNECTION_ERROR = "Datebase connection error";
	
	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException{
		List<News> result = new ArrayList<News>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String statusNamePublished = "published";
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			try {
				statement = connection.prepareStatement(SQL_QUERY_LATEST_LIST_NEWS);
				statement.setString(1, statusNamePublished);
				statement.setInt(2, count);
				resultSet = statement.executeQuery();
				while(resultSet.next()) {
					int news_id = (int)resultSet.getInt(DB_NEWS_TABLE_NEWS_COLUMN_ID);
					Date dateOfCreate = resultSet.getDate(DB_NEWS_TABLE_NEWS_COLUMN_DATE_OF_CREATE);				
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					String formattedDateOfCreate = formatter.format(dateOfCreate);
					result.add(new News(news_id, resultSet.getString(DB_NEWS_TABLE_NEWS_COLUMN_TITLE),
							resultSet.getString(DB_NEWS_TABLE_NEWS_COLUMN_BRIEF),
							resultSet.getString(DB_NEWS_TABLE_NEWS_COLUMN_CONTENT), formattedDateOfCreate,
							resultSet.getString(DB_NEWS_TABLE_NEWS_COLUMN_STATUS_OF_NEWS)));
				}
			} catch (SQLException e) {
				throw new NewsDAOException(SQL_ERROR, e);
			}
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(DATEBASE_CONNECTION_ERROR, e);
		} finally {
			ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
		}
		return result;
	}
	
	@Override
	public List<News> getList() throws NewsDAOException{
		
		List<News> result = new ArrayList<News>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			try {
				statement = connection.prepareStatement(SQL_QUERY_LIST_ALL_NEWS_FOR_ADMIN);
				resultSet = statement.executeQuery();
				while(resultSet.next()) {
					int news_id = (int)resultSet.getInt(DB_NEWS_TABLE_NEWS_COLUMN_ID);
					Date dateOfCreate = resultSet.getDate(DB_NEWS_TABLE_NEWS_COLUMN_DATE_OF_CREATE);				
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					String formattedDateOfCreate = formatter.format(dateOfCreate);
					result.add(new News(news_id, resultSet.getString(DB_NEWS_TABLE_NEWS_COLUMN_TITLE),
							resultSet.getString(DB_NEWS_TABLE_NEWS_COLUMN_BRIEF),
							resultSet.getString(DB_NEWS_TABLE_NEWS_COLUMN_CONTENT), formattedDateOfCreate,
							resultSet.getString(DB_NEWS_TABLE_NEWS_COLUMN_STATUS_OF_NEWS)));
				}
			} catch (SQLException e) {
				throw new NewsDAOException(SQL_ERROR, e);
			}
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(DATEBASE_CONNECTION_ERROR, e);
		} finally {
			ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
		}
		return result;
	}
	
	@Override
	public News fetchById(int id) throws NewsDAOException{
		
		News uRes=null;
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			try {
				statement = connection.prepareStatement(SQL_QUERY_FETCH_NEWS);
				statement.setInt(1, id);
				resultSet = statement.executeQuery();
				while(resultSet.next()) {
					int news_id = (int)resultSet.getInt(DB_NEWS_TABLE_NEWS_COLUMN_ID);
					Date dateOfCreate = resultSet.getDate(DB_NEWS_TABLE_NEWS_COLUMN_DATE_OF_CREATE);				
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					String formattedDateOfCreate = formatter.format(dateOfCreate);
					uRes = new News(news_id, resultSet.getString(DB_NEWS_TABLE_NEWS_COLUMN_TITLE),
							resultSet.getString(DB_NEWS_TABLE_NEWS_COLUMN_BRIEF),
							resultSet.getString(DB_NEWS_TABLE_NEWS_COLUMN_CONTENT), formattedDateOfCreate,
							resultSet.getString(DB_NEWS_TABLE_NEWS_COLUMN_STATUS_OF_NEWS));
				}
			} catch (SQLException e) {
				throw new NewsDAOException(SQL_ERROR, e);
			}
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(DATEBASE_CONNECTION_ERROR, e);
		} finally {
			ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
		}
		return uRes;
	}
	
	@Override
	public void addNews(News news, int idUser) throws NewsDAOException{
		
		Connection connection = null;
		PreparedStatement statement = null;
		int idNewsStatus = 0;

		try {
			connection = ConnectionPool.getInstance().takeConnection();
			try {
				Date dateOfCreateNews = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH*mm*ss");
				String formattedDateOfCreateNews = formatter.format(dateOfCreateNews);
				
				if(news.getNewsStatus().equals("new news")) {
					idNewsStatus = 1;
				}
				if(news.getNewsStatus().equals("published news")) {
					idNewsStatus = 2;
				}
				
				statement = connection.prepareStatement(SQL_QUERY_ADD_NEW_NEWS);
				statement.setString(1, news.getTitle());
				statement.setString(2, news.getBriefNews());
				statement.setString(3, news.getContent());
				statement.setString(4, formattedDateOfCreateNews);
				statement.setInt(5, idNewsStatus);
				statement.setInt(6, idUser);
				statement.executeUpdate();
			} catch (SQLException e) {
				throw new NewsDAOException(SQL_ERROR, e);
			}
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(DATEBASE_CONNECTION_ERROR, e);
		} finally {
			ConnectionPool.getInstance().closeConnection(connection, statement);
		}	
	}
	
	@Override
	public void updateNews(News news, int idUser) throws NewsDAOException{
		
		Connection connection = null;
		PreparedStatement statement = null;
		int idNewsStatus = 0;

		try {
			connection = ConnectionPool.getInstance().takeConnection();
			try {
				if(news.getNewsStatus().equals("new news")) {
					idNewsStatus = 1;
				}
				if(news.getNewsStatus().equals("published news")) {
					idNewsStatus = 2;
				}
				
				statement = connection.prepareStatement(SQL_QUERY_EDIT_NEWS);
				statement.setString(1, news.getTitle());
				statement.setString(2, news.getBriefNews());
				statement.setString(3, news.getContent());
				statement.setInt(4, idNewsStatus);
				statement.setInt(5, news.getIdNews());
				statement.executeUpdate();
			} catch (SQLException e) {
				throw new NewsDAOException(SQL_ERROR, e);
			} finally {
				try {
					if (statement != null) {
					statement.close();
					}
				} catch (SQLException e) {
					throw new NewsDAOException(SQL_ERROR, e);
				}
			}	
			try {
				Date dateOfEditNews = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH*mm*ss");
				String formattedDateOfEditNews = formatter.format(dateOfEditNews);
				
				statement = connection.prepareStatement(SQL_QUERY_ADD_DATE_ABOUT_NEWS_UPDATE);
				statement.setInt(1, news.getIdNews());
				statement.setInt(2, idUser);
				statement.setString(3, formattedDateOfEditNews);
				statement.executeUpdate();
			} catch (SQLException e) {
				throw new NewsDAOException(SQL_ERROR, e);
			} finally {
				try {
					if (statement != null) {
					statement.close();
					}
				} catch (SQLException e) {
					throw new NewsDAOException(SQL_ERROR, e);
				}
			}	
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(DATEBASE_CONNECTION_ERROR, e);
		} finally {
			ConnectionPool.getInstance().closeConnection(connection, statement);
		}	
	}
	
	@Override
	public void deleteNews(int idNews) throws NewsDAOException{
		
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			try {
				statement = connection.prepareStatement(SQL_QUERY_CHANGE_NEWS_STATUS_ON_REMOTE);
				statement.setInt(1, 3);
				statement.setInt(2, idNews);
				statement.executeUpdate();
			} catch (SQLException e) {
				throw new NewsDAOException(SQL_ERROR, e);
			} finally {
				try {
					if (statement != null) {
					statement.close();
					}
				} catch (SQLException e) {
					throw new NewsDAOException(SQL_ERROR, e);
				}
			}	
			try {
				
				int idOfNewsDeletionUser = 1; //
				Date dateOfNewsDeletion = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH*mm*ss");
				String formattedDateOfNewsDeletion = formatter.format(dateOfNewsDeletion);	
				statement = connection.prepareStatement(SQL_QUERY_CHANGE_STATUS_OF_NEWS_ON_REMOTE);
				statement.setInt(1, idNews);
				statement.setInt(2, idOfNewsDeletionUser);
				statement.setString(3, formattedDateOfNewsDeletion);
				statement.executeUpdate();
			} catch (SQLException e) {
				throw new NewsDAOException(SQL_ERROR, e);
			} finally {
				try {
					if (statement != null) {
					statement.close();
					}
				} catch (SQLException e) {
					throw new NewsDAOException(SQL_ERROR, e);
				}
			}	
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(DATEBASE_CONNECTION_ERROR, e);
		} finally {
			ConnectionPool.getInstance().closeConnection(connection, statement);	
		}
	}
	
	@Override
	public void deleteNewses(String[] idNewses) throws NewsDAOException{
		
		int idDeletedNews;
		try {
			for(String id:idNewses) {
				idDeletedNews = (int)Integer.parseInt(id);
				deleteNews(idDeletedNews);
			}
		} catch(NewsDAOException e) {
			throw new NewsDAOException(SQL_ERROR, e);
		}
	}
}
