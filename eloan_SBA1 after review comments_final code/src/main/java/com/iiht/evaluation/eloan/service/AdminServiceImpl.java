package com.iiht.evaluation.eloan.service;

import java.util.List;

import com.iiht.evaluation.eloan.dao.AdminDaoImpl;
import com.iiht.evaluation.eloan.dao.IAdminDao;
import com.iiht.evaluation.eloan.exception.AccessDeniedException;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;


public class AdminServiceImpl implements IAdminService
{

	private IAdminDao adminDao;
	
	public AdminServiceImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.adminDao=new AdminDaoImpl(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	@Override
	public List<LoanInfo> getLoans() throws AccessDeniedException {
		
		return adminDao.getLoans();
	}

	@Override
	public LoanInfo getLoanById(int loanId) throws AccessDeniedException {
		
		return adminDao.getLoanById(loanId);
	}

	@Override
	public ApprovedLoan getApprovedLoan(int loanId) throws AccessDeniedException {
		return adminDao.getApprovedLoan(loanId);
	}

	@Override
	public boolean saveApprovedLoan(String loanId, double sanctionedAmount, int loanTerm, String paymentStartDate,
			String loanClosureDate, double emi) throws AccessDeniedException {
		return adminDao.saveApprovedLoan(loanId, sanctionedAmount, loanTerm, paymentStartDate, loanClosureDate, emi);
	}

	@Override
	public boolean updateLoanStatus(String loanId, String status) throws AccessDeniedException {
		return adminDao.updateLoanStatus(loanId, status);
	}  
}
