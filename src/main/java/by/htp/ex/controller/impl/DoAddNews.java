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

public class DoAddNews implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	//private static final String JSP_NEWS_ID_PARAM = "newsId";
	private static final String JSP_NEWS_TITLE_PARAM = "titleEdit";
	//private static final String JSP_NEWS_DATE_PARAM = "newsDateEdit";
	private static final String JSP_BRIEF_PARAM = "briefEdit";
	private static final String JSP_CONTENT_PARAM = "contentEdit";
	private static final String JSP_STATUS_OF_NEWS = "statusNewsEdit";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//String newsId = request.getParameter(JSP_NEWS_ID_PARAM);
		String titleAddedNews = request.getParameter(JSP_NEWS_TITLE_PARAM);
		//String dateAddedNews = request.getParameter(JSP_NEWS_DATE_PARAM);
		String briefAddedNews = request.getParameter(JSP_BRIEF_PARAM);
		String contentAddedNews = request.getParameter(JSP_CONTENT_PARAM);
		String statusAddedNews = request.getParameter(JSP_STATUS_OF_NEWS);
		int idUser = 0;
		try {
			String check = "";
			if (request.getSession() == null) {
				request.getSession(true).setAttribute("session_warning", "warning");
				response.sendRedirect("controller?command=go_to_base_page");
			}
			if (titleAddedNews.equals(check) || briefAddedNews.equals(check) || contentAddedNews.equals(check) ||
					statusAddedNews.equals(check)) {
				request.getSession().setAttribute("add", "warning");
				response.sendRedirect("controller?command=go_to_add_news");
			} else {
				News newsA = new News(0, titleAddedNews, briefAddedNews,
						contentAddedNews, "0", statusAddedNews);
				
				idUser = (int) (request.getSession().getAttribute("idUser"));
				
				newsService.save(newsA, idUser);
				request.getSession().setAttribute("add_news", "success");
				response.sendRedirect("controller?command=go_to_news_list");
			}
		} catch (ServiceException e) {
			request.getRequestDispatcher("/WEB-INF/pages/tiles/error.jsp").forward(request, response);
		}
	}
}
