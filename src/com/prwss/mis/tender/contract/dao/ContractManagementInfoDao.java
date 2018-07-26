package com.prwss.mis.tender.contract.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface ContractManagementInfoDao {
	public List<ContractManagementInfoBean> getContractCodes(String locationId) throws DataAccessException;

}
