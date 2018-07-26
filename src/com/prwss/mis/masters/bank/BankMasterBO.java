package com.prwss.mis.masters.bank;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.bank.dao.BankMasterBean;
import com.prwss.mis.masters.bank.struts.BankMasterForm;

public interface BankMasterBO {

public List<BankMasterBean> findBank(BankMasterForm bankMasterForm, List<String> statusList) throws MISException;
	
	public long saveBank(BankMasterForm bankMasterForm, MISSessionBean misAuditBean) throws MISException;
	
	/*public boolean updateBank(BankMasterForm bankMasterForm, MISSessionBean misAuditBean) throws MISException;
	*/
	public boolean deleteBank(BankMasterForm bankMasterForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean postBank(BankMasterForm bankMasterForm, MISSessionBean misAuditBean) throws MISException;

	boolean updateBank(BankMasterForm bankMasterForm,
			MISSessionBean misSessionBean, List<String> statusList)
			throws MISException;
	
	
}
