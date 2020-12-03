package com.iiht.evaluation.eloan.service;

import java.util.List;

import com.iiht.evaluation.eloan.dao.IUserDao;
import com.iiht.evaluation.eloan.dao.UserDaoImpl;
import com.iiht.evaluation.eloan.exception.AccessDeniedException;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;

public class UserServiceImpl implements IUserService{
	
	private IUserDao userDao;
	
	public UserServiceImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.userDao=new UserDaoImpl(jdbcURL, jdbcUsername, jdbcPassword);
	}

	@Override
	public User saveAndGet(String username, String email, String password, String role, String phone)
			throws AccessDeniedException {
		
		return userDao.saveAndGet(username, email, password, role, phone);
	}

	@Override
	public User getUserByNameAndPwd(String username, String password) throws AccessDeniedException {
		
		return userDao.getUserByNameAndPwd(username, password);
	}

	@Override
	public LoanInfo addLoan(String loanname, String loanamount, String businessstruture, String taxindiacator,
			String address1, String address2, String address3, String uid) throws AccessDeniedException {
		
		return userDao.addLoan(loanname, loanamount, businessstruture, taxindiacator, address1, address2, address3, uid);
	}

	@Override
	public LoanInfo getLoanByUserId(int userid) throws AccessDeniedException {
		
		return userDao.getLoanByUserId(userid);
	}

	@Override
	public List<LoanInfo> getLoanStatus(int userId) throws AccessDeniedException {
	
		return userDao.getLoanStatus(userId);
	}

	@Override
	public boolean updateLoan(int loanId, String loanName, String loanAmount, String bs, String ti, String a1,
			String a2, String a3) throws AccessDeniedException {
		
		return userDao.updateLoan(loanId, loanName, loanAmount, bs, ti, a1, a2, a3);
	}

	@Override
	public LoanInfo getLoanById(int loanId) throws AccessDeniedException {
		return userDao.getLoanById(loanId);
	}

}
