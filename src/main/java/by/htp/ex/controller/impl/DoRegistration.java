package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.bean.User;

public class DoRegistration implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();

	private static final String JSP_LOGIN_REG_PARAM = "loginReg";
	private static final String JSP_PASSWORD_REG_PARAM = "passwordReg";
	private static final String JSP_ROLE_PARAM = "role";
	private static final String JSP_SURNAME_PARAM = "surname";
	private static final String JSP_NAME_PARAM = "name";
	private static final String JSP_PHONE_PARAM = "phone";
	private static final String JSP_EMAIL_PARAM = "email";
	private static final String JSP_BIRTHDAY_PARAM = "birthday";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String loginReg = request.getParameter(JSP_LOGIN_REG_PARAM);
		String passwordReg = request.getParameter(JSP_PASSWORD_REG_PARAM);
		String role = request.getParameter(JSP_ROLE_PARAM);
		String surname = request.getParameter(JSP_SURNAME_PARAM);
		String name = request.getParameter(JSP_NAME_PARAM);
		String phone = request.getParameter(JSP_PHONE_PARAM);
		String email = request.getParameter(JSP_EMAIL_PARAM);
		String birthday = request.getParameter(JSP_BIRTHDAY_PARAM);

		try {
			if (request.getSession() == null) {
				request.getSession(true).setAttribute("session_warning", "warning");
				response.sendRedirect("controller?command=go_to_base_page");
			}
			User u = new User(loginReg, passwordReg, role, surname, name, phone, email, birthday);
			String role1 = service.registration(u);
			String check = "";
			if (loginReg.equals(check) || passwordReg.equals(check) || role.equals(check) || surname.equals(check)
					|| name.equals(check) || phone.equals(check) || email.equals(check)|| birthday.equals(check)) {
				request.getSession().setAttribute("warningReg", "warning");
				response.sendRedirect("controller?command=go_to_registration_page");
			} else {
				if (!role1.equals("guest")) {
					request.getSession().setAttribute("registration", "success");
					request.getSession(true).setAttribute("user", "active");
					request.getSession(true).setAttribute("role", role1);
					response.sendRedirect("controller?command=go_to_news_list");
				} else {
					request.getSession().setAttribute("warningReg2", "warning");
					response.sendRedirect("controller?command=go_to_registration_page");
				}
			}
		} catch (ServiceException e) {
			request.getRequestDispatcher("/WEB-INF/pages/tiles/error.jsp").forward(request, response);
		}
	}
}