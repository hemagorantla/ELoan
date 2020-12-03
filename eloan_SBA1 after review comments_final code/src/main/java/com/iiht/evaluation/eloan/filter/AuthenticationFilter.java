package com.iiht.evaluation.eloan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.eloan.model.User;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (request instanceof HttpServletRequest) {

			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;

			User user = (User) httpRequest.getSession().getAttribute("user");

			String incomingUrl = httpRequest.getServletPath();
			
			Status status = null;

			String[] allowedUrls = null;

			if (user == null) {
				allowedUrls = new String[] { 
						"/registerNewUser",
						"/registeruser",
						"/register",
						"index.jsp",
						"/home",
						"/newuserui.jsp"};
				for (String au : allowedUrls) {
					if (au.equals(incomingUrl)) {
						status = Status.AUTHORIZED;
						break;
					}
				}
				if (status == null) {
					status = Status.UN_AUTHENTICATED;
				}
			} else if (user.getRole().equalsIgnoreCase("USER")) {
				allowedUrls = new String[] { 
						"/placeloan", 
						"/application1", "/editLoanProcess",
						"/register","/application","/trackloan",
						"/editloan","/displaystatus","/logout","/index.jsp",
						"/application.jsp","/loanDetails.jsp","/userhome.jsp","/newuserui.jsp","/trackloan.jsp","/application.jsp","/notfound.jsp"};
				for (String au : allowedUrls) {
					if (au.equals(incomingUrl)) {
						status = Status.AUTHORIZED;
						break;
					}
				}
				if (status == null) {
					status = Status.UN_AUTHORIZED;
				}
			}else if (user.getRole().equalsIgnoreCase("ADMIN")) {
				status = Status.AUTHORIZED;				
			}

			if (status == Status.AUTHORIZED) {
				chain.doFilter(request, response);
			} else if (status == Status.UN_AUTHORIZED) {
				httpResponse.sendRedirect("errorPage.jsp");
			} else if (status == Status.UN_AUTHENTICATED) {
				httpResponse.sendRedirect("home");
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void destroy() {

	}

}

enum Status {
	UN_AUTHENTICATED, UN_AUTHORIZED, AUTHORIZED
}