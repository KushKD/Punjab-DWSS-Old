package com.prwss.mis.tender.contract.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface ContractDao {
	
	public List<ContractHeaderBean> findContracts(ContractHeaderBean contractHeaderBean, List<String> statusList) throws DataAccessException;	
	public String saveContract(ContractHeaderBean contractHeaderBean) throws DataAccessException;	
	public boolean updateContract(ContractHeaderBean contractHeaderBean) throws DataAccessException;
	public Set<ContractHeaderBean> getContractCodes(String locationId) throws DataAccessException;
	public Set<ContractHeaderBean> getContractCodes(ContractHeaderBean contractHeaderBean) throws DataAccessException;
//	public List<ContractHeaderBean> findTender(ContractHeaderBean contractHeaderBean1) throws DataAccessException;
}
