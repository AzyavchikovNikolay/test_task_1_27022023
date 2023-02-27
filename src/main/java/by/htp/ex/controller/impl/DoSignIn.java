package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSignIn implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();
	private static final String JSP_LOGIN_PARAM = "login";
	private static final String JSP_PASSWORD_PARAM = "password";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;
		login = request.getParameter(JSP_LOGIN_PARAM);
		password = request.getParameter(JSP_PASSWORD_PARAM);
		int idUser = 0;
		try {
			if (request.getSession() == null) {
				request.getSession(true).setAttribute("session_warning", "warning");
				response.sendRedirect("controller?command=go_to_base_page");
			}
			String role = service.signIn(login, password);
			idUser = service.findIdUser(login, password);
			if (!role.equals("guest")) {
				request.getSession().setAttribute("user", "active");
				request.getSession().setAttribute("welcome", login);
				request.getSession().setAttribute("role", role);
				request.getSession().setAttribute("idUser", idUser);
				response.sendRedirect("controller?command=go_to_news_list");
			} else {
				request.getSession().setAttribute("user", "not active");
				request.setAttribute("AuthenticationError", "local.registration.auth_error");
				request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			}
		} catch (ServiceException e) {
			request.getRequestDispatcher("/WEB-INF/pages/tiles/error.jsp").forward(request, response);
		}
	}
}
