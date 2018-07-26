package com.prwss.mis.tender.award.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface TenderSecurityDepositDao {
	
	public List<TenderSecurityDepositBean> getTenderSecurityDepositBeans(TenderSecurityDepositBean securityDepositBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveOrUpdateTenderSecurityDepositBeans(Collection<TenderSecurityDepositBean> securityDepositBeans) throws DataAccessException;

}
