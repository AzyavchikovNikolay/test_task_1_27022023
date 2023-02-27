package by.htp.ex.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CommandProvider provider = new CommandProvider();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doCommand(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doCommand(request, response);
	}

	private void doCommand(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String commandName = request.getParameter("command");
		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}
}
