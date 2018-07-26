package com.prwss.mis.tender.contract.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.dao.DataAccessException;

public interface ContractDetailDao {
	
	public List<ContractDetailBean> getContractDetails(String contractId) throws DataAccessException;
	public boolean saveOrUpdateContractDetail(Collection<ContractDetailBean> contractDetailBeans) throws DataAccessException;
	public boolean saveOrUpdateContractDetail1(Collection<ContractDetailBean> contractDetailBeans) throws DataAccessException;
	public boolean saveOrUpdateContractDetail2(Collection<ContractDetailBean> contractDetailBeans) throws DataAccessException;

}
