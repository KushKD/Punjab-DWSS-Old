package com.prwss.mis.masters.bank.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface BankMasterDao {
	
public List<BankMasterBean> findBank(BankMasterBean bankMasterBean, List<String> statusList) throws DataAccessException;
	
	public List<BankMasterBean> findBank(List<Long> bankMasterIds) throws DataAccessException;

	public long saveBank(BankMasterBean bankMasterBean) throws DataAccessException;

	public boolean updateBank(BankMasterBean bankMasterBean)	throws DataAccessException;
	
	public boolean updateBank(List<BankMasterBean> bankMasterBeans) throws DataAccessException;

	public Set<BankMasterBean> getBankMasterCodes() throws DataAccessException;

}
