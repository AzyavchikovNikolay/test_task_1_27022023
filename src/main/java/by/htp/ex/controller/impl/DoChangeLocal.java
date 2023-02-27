package by.htp.ex.controller.impl;

import by.htp.ex.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import by.htp.ex.service.ServiceException;

public class DoChangeLocal implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {	
			if(request.getSession()==null) {
				String local = request.getParameter("local");
				request.getSession(true).setAttribute("local", local);
				request.getSession().setAttribute("session_warning", "warning");
				response.sendRedirect("controller?command=go_to_base_page");
			}
			String prevGetRequest = request.getSession().getAttribute("prevServletPath")+"?"+
					request.getSession().getAttribute("prevQuery");			
			String local = request.getParameter("local");
			request.getSession().setAttribute("local", local);
			request.getSession().setAttribute("message", "repeat");
			response.sendRedirect(prevGetRequest);
		} catch(Exception e) {
			request.getRequestDispatcher("WEB-INF/pages/tiles/error.jsp").forward(request, response);
		}
	}
}
