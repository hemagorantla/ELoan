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

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;



@WebServlet("/")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
private ConnectionDao connDao;
	
	public void setConnDao(ConnectionDao connDao) {
		this.connDao = connDao;
	}
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action + " request servlet path ");
		
		String viewName = "";
		try {
			switch (action) {
			case "/registerNewUser":
				viewName=registernewuser(request,response);
				break;
			case "/validate":
				viewName=validate(request,response);
				break;
			case "/placeloan":
				viewName=placeloan(request,response);
				break;
			case "/application1":
				viewName=application1(request,response);
				break;
			case "/editLoanProcess"  :
				viewName=editLoanProcess(request,response);
				break;
			case "/registeruser":
				viewName=registerUser(request,response);
				break;
			case "/register":
				viewName = register(request, response);
				break;
			case "/application":
				viewName = application(request, response);
				break;
			case "/trackloan":
				viewName = trackloan(request, response);
				break;
			case "/editloan":
				viewName = editloan(request, response);
				break;	
			case  "/displaystatus" :
				viewName=displaystatus(request,response);
				break;
			case "/logout":
				viewName = userLogout(request, response);
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
	
	private String userLogout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();  
        session.invalidate(); 
        String errorMessage = "User successfully loggedout.";
		request.setAttribute("error", errorMessage);
		return "index.jsp";
	}
	
	private String validate(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		/* write the code to validate the user */
		
		return null;
	}
	private String placeloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write the code to place the loan information */
		
		return null;
	}
	private String application1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write the code to display the loan application page */
		/* write a code to return to trackloan page */		
		return "application.jsp";
	}
	private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to edit the loan info */
		if(request.getParameter("loanid") != null){
			int loanId= Integer.parseInt(request.getParameter("loanid"));
			LoanInfo loan = connDao.getUserLoan(loanId);
			request.setAttribute("loan", loan);
		}
		
		return "loanDetails.jsp";
	}
	private String registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to redirect page to read the user details */
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = connDao.getRegisterUser(username,password);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		if(user != null){
			if(user.getRole() != null && user.getRole().equals("admin")){
				return "adminhome1.jsp";
			}else{
				return "userhome.jsp";
			}
		}else{
			String errorMessage = "Please login with correct credentials";
			request.setAttribute("error", errorMessage);
			return "index.jsp";
		}
		
		
	}
	private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		/* write the code to create the new user account read from user 
		   and return to index page */
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		User user = connDao.getRegisterNewUser(username,email,password,phone, phone);
		String info;
		if(user != null){
			info = "User registered successfully. Please login.";
		}else{
			info = "User registration failed. Please try again.";
		}
		
		request.setAttribute("info", info);
		return "newuserui.jsp";
	}
	
	private String register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write the code to redirect to register page */
		
		return null;
	}
	private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code the display the loan status based on the given application
		   number 
		*/
		
		return null;
	}

	private String editloan(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		// TODO Auto-generated method stub
	/* write a code to return to editloan page */
		int loanId = Integer.parseInt(request.getParameter("loandidtoeditloand"));
		String loanname = request.getParameter("loanname");
		String loanamount = request.getParameter("loanamount");
		String businessstruture = request.getParameter("businessstruture");
		String taxindiacator = request.getParameter("taxindiacator");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address3 = request.getParameter("address3");
		LoanInfo loan =connDao.getUserLoan(loanId);
		HttpSession session = request.getSession();		
		User u1 = (User) session.getAttribute("user");
		int userid = Integer.parseInt(u1.getId());
		String msg;
		if(loan.getUserId() !=userid){
			msg = "Loan is not taken by this user. Please try with loan you requested.";
		}else if(loan.getStatus() == null){
			connDao.updateLoan(loanId, loanname,loanamount,businessstruture,taxindiacator,address1,address2,address3);
			msg = "Loan details updated successfully.";
		}else{
			msg = "Loan is already processed, edit is not allowed.";
		}
		
		request.setAttribute("message", msg);
		return "userhome.jsp";
	}

	private String trackloan(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		int userId = Integer.parseInt(request.getParameter("userid"));
		List<LoanInfo> loans = connDao.getLoanStatus(userId);
		request.setAttribute("loans", loans);
		return "trackloan.jsp";
	}

	private String application(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */		
		HttpSession session = request.getSession();		
		User u1 = (User) session.getAttribute("user");
		String loanname = request.getParameter("loanname");
		String loanamount = request.getParameter("loanamount");
		String businessstruture = request.getParameter("businessstruture");
		String taxindiacator = request.getParameter("taxindiacator");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address3 = request.getParameter("address3");

		LoanInfo loans = connDao.putLoanRequest(loanname,loanamount,businessstruture,taxindiacator,address1,address2,address3,u1.getId());
		if(loans != null){
			request.setAttribute("message", "Üser loan successfully submitted.");
			return "application.jsp";
		}else{
			return "notfound.jsp";
		}
		
	}
}