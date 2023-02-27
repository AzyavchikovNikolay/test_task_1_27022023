package by.htp.ex.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FilterRequest implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;	
		String method = req.getMethod();
		String SPath = req.getServletPath().toString().substring(1);
		String query = req.getQueryString();
		if (method.equals("GET")) {
			req.getSession().setAttribute("prevServletPath", req.getSession().getAttribute("curServletPath"));
			req.getSession().setAttribute("prevQuery", req.getSession().getAttribute("curQuery"));
			req.getSession().setAttribute("curServletPath", SPath);
			req.getSession().setAttribute("curQuery", query);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}
}
