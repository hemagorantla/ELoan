package com.iiht.evaluation.eloan.dao;

import java.util.List;

import com.iiht.evaluation.eloan.exception.AccessDeniedException;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;

public interface IAdminDao {
	
	List<LoanInfo> getLoans() throws AccessDeniedException;
	
	LoanInfo getLoanById(int loanId) throws AccessDeniedException;
	
	ApprovedLoan getApprovedLoan(int loanId) throws AccessDeniedException;
	
	boolean saveApprovedLoan(String loanId, double sanctionedAmount, int loanTerm,
			String paymentStartDate, String loanClosureDate, double emi) throws AccessDeniedException;
	
	boolean updateLoanStatus(String loanId, String status) throws AccessDeniedException;

}
