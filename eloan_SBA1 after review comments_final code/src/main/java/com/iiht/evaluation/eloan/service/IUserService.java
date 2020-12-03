package com.iiht.evaluation.eloan.service;

import java.util.List;

import com.iiht.evaluation.eloan.exception.AccessDeniedException;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;

public interface IUserService {
	
	User saveAndGet(String username, String email, String password, String role, String phone)
			throws AccessDeniedException;

	User getUserByNameAndPwd(String username, String password) throws AccessDeniedException;

	LoanInfo addLoan(String loanname, String loanamount, String businessstruture, String taxindiacator, String address1,
			String address2, String address3, String uid) throws AccessDeniedException;

	LoanInfo getLoanByUserId(int userid) throws AccessDeniedException;

	List<LoanInfo> getLoanStatus(int userId) throws AccessDeniedException;

	boolean updateLoan(int loanId, String loanName, String loanAmount, String bs, String ti, String a1, String a2,
			String a3) throws AccessDeniedException;
	
	LoanInfo getLoanById(int loanId) throws AccessDeniedException;

}
