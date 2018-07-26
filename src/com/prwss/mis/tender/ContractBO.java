package com.prwss.mis.tender;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.tender.contract.dao.ContractHeaderBean;
import com.prwss.mis.tender.struts.ContractManagementForm;

public interface ContractBO {
	
	public List<ContractHeaderBean> findContract(ContractManagementForm contractManagementForm, List<String> statusList) throws MISException;
	
	public boolean updateContract(ContractManagementForm contractManagementForm, MISSessionBean misAuditBean) throws MISException;
	
}
