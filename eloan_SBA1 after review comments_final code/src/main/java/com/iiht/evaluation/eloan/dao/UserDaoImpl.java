package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.eloan.exception.AccessDeniedException;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;

public class UserDaoImpl implements IUserDao {

	private ConnectionProvider connectionProvider;

	public UserDaoImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		connectionProvider = new ConnectionProvider(jdbcURL, jdbcUsername, jdbcPassword);
	}

	@Override
	public User saveAndGet(String username, String email, String password, String role, String phone)
			throws AccessDeniedException {
		try {
			Connection jdbcConnection = connectionProvider.connect();
			Statement st = jdbcConnection.createStatement();
			String sql = "INSERT INTO users (username, email, password,role,phone) VALUES( " + "'" + username + "'"
					+ "," + "'" + email + "'" + "," + "'" + password + "'" + "," + null + "," + "'" + phone + "'" + ")";
			int success = st.executeUpdate(sql);
			if (success == 1) {
				String sql2 = "select * from users where username=" + "'" + username + "'" + " and password=" + "'"
						+ password + "'" + "";
				ResultSet rs = st.executeQuery(sql2);
				User user = new User();
				if (rs.next()) {
					user.setUsername(rs.getString("username"));
					user.setId(rs.getString("id"));
				}
				return user;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new AccessDeniedException("User details could not be fetched");
		}

	}

	@Override
	public User getUserByNameAndPwd(String username, String password) throws AccessDeniedException {
		try {
			Connection jdbcConnection = connectionProvider.connect();
			Statement st = jdbcConnection.createStatement();
			String sql = "select * from users where username=" + "'" + username + "'" + " and password=" + "'"
					+ password + "'" + "";
			ResultSet rs = st.executeQuery(sql);
			User user = null;
			if (rs.next()) {
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setId(rs.getString("id"));
				user.setRole(rs.getString("role"));
			}

			return user;
		} catch (SQLException e) {
			throw new AccessDeniedException("User details could not be fetched");
		}
	}

	@Override
	public LoanInfo addLoan(String loanname, String loanamount, String businessstruture, String taxindiacator,
			String address1, String address2, String address3, String uid) throws AccessDeniedException {
		try {
			Connection jdbcConnection = connectionProvider.connect();
			Statement st = jdbcConnection.createStatement();
			String sql = "INSERT INTO loans (loanname, loanamount, businessstruture,taxindiacator, address1, address2, address3, id) "
					+ "VALUES( " + "'" + loanname + "'" + "," + "'" + loanamount + "'" + "," + "'" + businessstruture
					+ "'" + "," + "'" + taxindiacator + "'" + "," + "'" + address1 + "'" + "," + "'" + address2 + "'"
					+ "," + "'" + address3 + "'" + "," + "'" + uid + "'" + ")";
			int success = st.executeUpdate(sql);
			if (success == 1) {
				String sql1 = "select * from loans where loanname=" + "'" + loanname + "'" + " and loanamount=" + "'"
						+ loanamount + "'" + "";
				ResultSet rs = st.executeQuery(sql1);
				LoanInfo loan = new LoanInfo();
				if (rs.next()) {
					loan.setApplno(rs.getString("loanid"));
					loan.setAmtrequest(Integer.parseInt(rs.getString("loanamount")));
					loan.setBstructure(rs.getString("businessstruture"));
					loan.setDoa(rs.getString("loan_create_time"));
					loan.setBindicator(rs.getString("taxindiacator"));
					loan.setAddress(rs.getString("address1") + rs.getString("address2") + rs.getString("address3"));
					loan.setLoanname(rs.getString("loanname"));
				}
				return loan;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new AccessDeniedException("User could not add a new loan");
		}

	}

	@Override
	public LoanInfo getLoanByUserId(int userid) throws AccessDeniedException {
		try {
			Connection jdbcConnection = connectionProvider.connect();
			Statement st = jdbcConnection.createStatement();
			String sql = "select * from loans where loanid=" + userid;
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
		} catch (SQLException e) {
			throw new AccessDeniedException("User could not get loan details");
		}
	}

	@Override
	public List<LoanInfo> getLoanStatus(int userId) throws AccessDeniedException {
		try {
			Connection jdbcConnection = connectionProvider.connect();
			Statement st = jdbcConnection.createStatement();
			String sql = "select * from loans where id=" + userId;
			ResultSet rs = st.executeQuery(sql);

			List<LoanInfo> loans = new ArrayList<LoanInfo>();
			while (rs.next()) {
				LoanInfo loan = new LoanInfo();
				loan.setApplno(rs.getString("loanid"));
				String status;
				if (rs.getString("loanstatus") == null) {
					status = "In Review";
				} else {
					status = rs.getString("loanstatus");
				}
				loan.setStatus(status);
				loans.add(loan);
			}
			return loans;
		} catch (SQLException e) {
			throw new AccessDeniedException("User could not get loan status");
		}

	}

	@Override
	public boolean updateLoan(int loanId, String loanName, String loanAmount, String bs, String ti, String a1,
			String a2, String a3) throws AccessDeniedException {
		try {
			Connection jdbcConnection = connectionProvider.connect();
			Statement st = jdbcConnection.createStatement();

			String sql = "UPDATE loans SET loanname = " + "'" + loanName + "'" + "," + "loanamount = " + "'"
					+ loanAmount + "'" + "," + "businessstruture = " + "'" + bs + "'" + "," + "taxindiacator = " + "'"
					+ ti + "'" + "," + "address1 = " + "'" + a1 + "'" + "," + "address2 = " + "'" + a2 + "'" + ","
					+ "address3 = " + "'" + a3 + "'" + " WHERE loanid=" + loanId;
			int success = st.executeUpdate(sql);
			return success == 1;
		} catch (SQLException e) {
			throw new AccessDeniedException("User could not update the loan status");
		}
	}

	@Override
	public LoanInfo getLoanById(int loanId) throws AccessDeniedException {
		try {
			Connection jdbcConnection = connectionProvider.connect();
			Statement st = jdbcConnection.createStatement();
			String sql = "select * from loans where loanid=" + loanId;
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
		} catch (SQLException e) {
			throw new AccessDeniedException("User could not retrieve the loan details");
		}
	}

}
