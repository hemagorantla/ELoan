package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.eloan.exception.AccessDeniedException;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.service.AdminServiceImpl;
import com.iiht.evaluation.eloan.service.IAdminService;


@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IAdminService adminService;
	
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		adminService = new AdminServiceImpl(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
		System.out.println(action);
		String viewName = "";
		try {
			switch (action) {
			case "listall" : 
				viewName = listall(request, response);
				break;
			case "process":
				viewName=process(request,response);
				break;
			case "callemi":
				viewName=calemi(request,response);
				break;
			case "updatestatus":
				viewName=updatestatus(request,response);
				break;
			case "logout":
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	private String updatestatus(HttpServletRequest request, HttpServletResponse response) throws AccessDeniedException {
		String status = request.getParameter("status");
		String loanid = request.getParameter("loandidtoupdate");
		String msg = null;
		if("accept".equalsIgnoreCase(status)){
			if(adminService.updateLoanStatus(loanid, "Aproved")){
				double loanSanctionedAmount = Double.parseDouble(request.getParameter("las"));
				int duration = Integer.parseInt(request.getParameter("duration"));
				String paymentStartDate = request.getParameter("psd");
				String loanClosureDate = request.getParameter("lcd");
				double emi = Double.parseDouble(request.getParameter("emi"));
				adminService.saveApprovedLoan(loanid, loanSanctionedAmount, duration, paymentStartDate, loanClosureDate, emi);
				msg = "Loan is Approved successfully.";
			}else{
				msg = "Failed to Approved loan.";
			}
			
		}else if("reject".equalsIgnoreCase(status)){
			if(adminService.updateLoanStatus(loanid, "Rejected")){
				msg = "Loan is Rejected successfully.";
			}else{
				msg = "Failed to Reject loan.";
			}
			
		}
		request.setAttribute("message", msg);
		return "adminhome1.jsp";
	}
	private String calemi(HttpServletRequest request, HttpServletResponse response) throws AccessDeniedException {
		double loanSanctionedAmount = Double.parseDouble(request.getParameter("las"));
		float rateOfInterest = Float.parseFloat(request.getParameter("roi"));
		int duration = Integer.parseInt(request.getParameter("duration"));
		String emi = String.valueOf(getCalculatedEmi(loanSanctionedAmount, rateOfInterest, duration));
		request.setAttribute("emi", emi);
		request.setAttribute("las",  String.valueOf(loanSanctionedAmount));
		request.setAttribute("duration",  String.valueOf(duration));
		request.setAttribute("roi",  String.valueOf(rateOfInterest));
		return "calemi.jsp";
	}
	
	private double getCalculatedEmi(double amountSanctioned, float rateOfInterest, int loanTerm){
		double termPayment =(amountSanctioned) *  Math.pow(1 + rateOfInterest/100, loanTerm);
		return termPayment/loanTerm;
	}
	
	
	public String process(HttpServletRequest request, HttpServletResponse response) throws AccessDeniedException {
		if(request.getParameter("loanid") != null){
			int loanId = Integer.parseInt(request.getParameter("loanid"));
			LoanInfo loan = adminService.getLoanById(loanId);
			request.setAttribute("loan", loan);
		}
		
		return  "process.jsp";
	}
	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();  
        session.invalidate(); 
        String errorMessage = "Admin user successfully loggedout.";
		request.setAttribute("error", errorMessage);
		return "index.jsp";
	}

	private String listall(HttpServletRequest request, HttpServletResponse response) throws AccessDeniedException {
		List<LoanInfo> loans = adminService.getLoans();
		request.setAttribute("loans", loans);
		return "listall.jsp";
	}

}