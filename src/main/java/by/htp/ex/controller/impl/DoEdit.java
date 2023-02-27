package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.htp.ex.bean.News;

public class DoEdit implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	private static final String JSP_NEWS_TITLE_PARAM = "titleEdit";
	//private static final String JSP_NEWS_DATE_PARAM = "newsDateEdit";
	private static final String JSP_BRIEF_PARAM = "briefEdit";
	private static final String JSP_CONTENT_PARAM = "contentEdit";
	private static final String JSP_STATUS_PARAM = "statusNewsEdit";
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String titleEditNews = request.getParameter(JSP_NEWS_TITLE_PARAM);
		//String dateEditNews = request.getParameter(JSP_NEWS_DATE_PARAM);
		String briefEditNews = request.getParameter(JSP_BRIEF_PARAM);
		String contentEditNews = request.getParameter(JSP_CONTENT_PARAM);
		String statusEditNews = request.getParameter(JSP_STATUS_PARAM);
		int idUser = 0;
		
		try {
			if (request.getSession() == null) {
				request.getSession(true).setAttribute("session_warning", "warning");
				response.sendRedirect("controller?command=go_to_base_page");
			}
			int idEditNews = Integer.valueOf((String) request.getSession(true).getAttribute("idPre"));
			News newsEdit = new News(idEditNews, titleEditNews, briefEditNews, contentEditNews, "0", statusEditNews);
			String check = "";
			if (titleEditNews.equals(check) || briefEditNews.equals(check)
					|| contentEditNews.equals(check) || statusEditNews.equals(check)) {
				request.getSession().setAttribute("edit", "warning");
				request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			} else {
				idUser = (int) (request.getSession().getAttribute("idUser"));
				newsService.update(newsEdit, idUser);
				request.getSession().setAttribute("edit_result", "success");
				request.getSession(true).setAttribute("idPre", Integer.toString(idEditNews));
				response.sendRedirect("controller?command=go_to_view_news");
			}
		} catch (ServiceException e) {
			request.getRequestDispatcher("/WEB-INF/pages/tiles/error.jsp").forward(request, response);
		}
	}
}