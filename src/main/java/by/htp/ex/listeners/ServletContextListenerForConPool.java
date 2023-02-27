package by.htp.ex.listeners;

import by.htp.ex.dao.impl.ConnectionPool;
import by.htp.ex.dao.impl.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ServletContextListenerForConPool implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			throw new RuntimeException("Connection pool initialization error", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.dispose();
	}
	
}
