package by.htp.ex.controller.impl;

import by.htp.ex.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.ServletException;

public class DoSignOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int idUser = 0;
		try {
			if (request.getSession() == null) {
				request.getSession(true).setAttribute("session_warning", "warning");
				response.sendRedirect("controller?command=go_to_base_page");
			}
			request.getSession().setAttribute("user", "not active");
			request.getSession().setAttribute("welcome", "guest");
			request.getSession().setAttribute("idUser", idUser);
			response.sendRedirect("controller?command=go_to_base_page");
		} catch (Exception e) {
			request.getRequestDispatcher("/WEB-INF/pages/tiles/error.jsp").forward(request, response);
		}
	}
}
