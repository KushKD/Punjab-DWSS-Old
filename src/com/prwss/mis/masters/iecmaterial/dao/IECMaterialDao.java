package com.prwss.mis.masters.iecmaterial.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface IECMaterialDao {
	
	public List<IECMaterialBean> findIECMaterial(IECMaterialBean iecMaterialBean, List<String> statusList) throws DataAccessException;
	
	public List<IECMaterialBean> findIECMaterial(List<String> iecMaterialIds) throws DataAccessException;

	public String saveIECMaterial(IECMaterialBean iecMaterialBean) throws DataAccessException;

	public boolean updateIECMaterial(IECMaterialBean iecMaterialBean)	throws DataAccessException;
	
	public boolean updateIECMaterial(List<IECMaterialBean> iecMaterialBeans) throws DataAccessException;

	public Set<IECMaterialBean> getDistinctIECMaterialId() throws DataAccessException;

}
