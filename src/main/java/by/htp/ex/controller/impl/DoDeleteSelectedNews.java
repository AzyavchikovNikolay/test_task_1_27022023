package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoDeleteSelectedNews implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] idNewses = request.getParameterValues("idNews");
		List<News> latestNews;
		List<News> allNews;
		
		try {
			if (request.getSession() == null) {
				request.getSession(true).setAttribute("session_warning", "warning");
				response.sendRedirect("controller?command=go_to_base_page");
			}
			if(idNewses==null) {
				request.getSession().setAttribute("delete_selected_news", "warning");
				response.sendRedirect("controller?command=go_to_news_list");
			} else {
			newsService.deleteNewses(idNewses);
			latestNews = newsService.latestList(5);
			request.getSession().setAttribute("latestNews", latestNews);
			allNews = newsService.list();
			request.getSession().setAttribute("news", allNews);
			request.getSession().setAttribute("delete_selected_news", "success");
			response.sendRedirect("controller?command=go_to_news_list");
			}
		} catch (ServiceException e) {
			request.getRequestDispatcher("/WEB-INF/pages/tiles/error.jsp").forward(request, response);
		}
	}
}
