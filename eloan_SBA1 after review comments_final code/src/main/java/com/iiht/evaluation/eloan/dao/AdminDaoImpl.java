package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.eloan.exception.AccessDeniedException;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;

public class AdminDaoImpl implements IAdminDao{

	private ConnectionProvider connectionProvider;

	public AdminDaoImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		connectionProvider = new ConnectionProvider(jdbcURL, jdbcUsername, jdbcPassword);
	}

	@Override
	public List<LoanInfo> getLoans() throws AccessDeniedException {
		try {
			Connection jdbcConnection = connectionProvider.connect();
			Statement st = jdbcConnection.createStatement();
			String sql = "select * from loans";
			ResultSet rs = st.executeQuery(sql);
			List<LoanInfo> loans = new ArrayList<LoanInfo>();
			while(rs.next()){
				LoanInfo loan = new LoanInfo();
				loan.setApplno(rs.getString("loanid"));				
				loan.setAmtrequest(Integer.parseInt(rs.getString("loanamount")));
				loan.setBstructure(rs.getString("businessstruture"));
				loan.setDoa(rs.getString("loan_create_time"));
				loan.setBindicator(rs.getString("taxindiacator"));
				loan.setAddress(rs.getString("address1") + rs.getString("address2") + rs.getString("address3"));
				loan.setLoanname(rs.getString("loanname"));
				loans.add(loan);
			}
			
			
			return loans;
		
		} catch (SQLException e) {
			throw new AccessDeniedException("User could not get list of loans for admin");
		}
	}

	@Override
	public LoanInfo getLoanById(int loanId) throws AccessDeniedException {
		try {
			Connection jdbcConnection = connectionProvider.connect();
			Statement st = jdbcConnection.createStatement();
			String sql = "select * from loans where loanid="+loanId;
			ResultSet rs = st.executeQuery(sql);
			
			LoanInfo loan = new LoanInfo();
			if (rs.next()) {
				loan.setUserId(Integer.parseInt(rs.getString("id")));
				loan.setApplno(rs.getString("loanid"));				
				loan.setAmtrequest(Integer.parseInt(rs.getString("loanamount")));
				loan.setBstructure(rs.getString("businessstruture"));
				loan.setDoa(rs.getString("loan_create_time"));
				loan.setBindicator(rs.getString("taxindiacator"));
				loan.setAddress(rs.getString("address1") + rs.getString("address2") + rs.getString("address3"));
				loan.setLoanname(rs.getString("loanname"));
				loan.setStatus(rs.getString("loanstatus"));
			}
			return loan;
		}  catch (SQLException e) {
			throw new AccessDeniedException("User could not retrieve the loan details");
		}
	}

	@Override
	public ApprovedLoan getApprovedLoan(int loanId) throws AccessDeniedException {
		try {
			Connection jdbcConnection = connectionProvider.connect();
			Statement st = jdbcConnection.createStatement();
			String sql = "select * from approvedloans where loanid="+loanId;
			ResultSet rs = st.executeQuery(sql);
			
			ApprovedLoan approvedLoan = new ApprovedLoan();
			if (rs.next()) {
				approvedLoan.setApplno(rs.getString("loanid"));				
				approvedLoan.setAmotsanctioned(Integer.parseInt(rs.getString("amountsanctioned")));
				approvedLoan.setLoanterm(Integer.parseInt(rs.getString("loanterm")));
				approvedLoan.setPsd(rs.getString("psd"));
				approvedLoan.setLcd(rs.getString("lcd"));
				approvedLoan.setEmi(Integer.parseInt(rs.getString("emi")));
			}
			return approvedLoan;
		} catch (SQLException e) {
			throw new AccessDeniedException("Admin could not approve the lona...please check....");
		}
	}

	@Override
	public boolean saveApprovedLoan(String loanId, double sanctionedAmount, int loanTerm, String paymentStartDate,
			String loanClosureDate, double emi) throws AccessDeniedException {
		try {
			Connection jdbcConnection = connectionProvider.connect();
			Statement st = jdbcConnection.createStatement();
			String sql = "INSERT INTO approvedloans (amountsanctioned, loanterm, psd,lcd, emi, loanid) "
					+ "VALUES( " + "'" + sanctionedAmount + "'" + "," + "'" + loanTerm + "'" + "," + "'" + paymentStartDate + "'"
					+ "," +  "'" + loanClosureDate +  "'" + "," + "'" + emi + "'" +  "," + "'" + loanId
					+ "'" + ")";
			int success = st.executeUpdate(sql);
			return success == 1;
		} catch (SQLException e) {
			throw new AccessDeniedException("Admin is not able to save the approved loan details");
		}
	}

	@Override
	public boolean updateLoanStatus(String loanId, String status) throws AccessDeniedException {
		try {
			Connection jdbcConnection = connectionProvider.connect();
			Statement st = jdbcConnection.createStatement();
			String sql = "UPDATE loans SET loanstatus = "+ "'" + status + "'" +  " WHERE loanid="+loanId;
			int success = st.executeUpdate(sql);
			return success ==1;
		} catch (SQLException e) {
			throw new AccessDeniedException("Admin is not able to update the loan status");
		}
	}

}
