package com.prwss.mis.common.contractCheck;

import java.util.List;

import com.prwss.mis.procurement.check.ContractCheckBean;
import com.prwss.mis.tender.dao.TenderHeaderBean;

public interface ContractNotificationDao {

	public List<ContractCheckBean> findAllContracts();
	public List<TenderHeaderBean> findAllContract(); 
		

}
