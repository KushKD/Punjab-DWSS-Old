package com.prwss.mis.ccdu.iecactivity.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface IECActivityMaterialUtilizationDao {
	
	public List<IECActivityMaterialUtilizationBean> findCBIECActivityMaterialUtilization(IECActivityMaterialUtilizationBean iecActivityMaterialUtilizationBean, List<String> statusList) throws DataAccessException;

	public boolean saveOrUpdateCBIECActivityMaterialUtilization(Collection<IECActivityMaterialUtilizationBean> iecActivityMaterialUtilizationBeans) throws DataAccessException;

}
