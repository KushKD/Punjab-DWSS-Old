package com.prwss.mis.procurement.workspackage.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.procurement.workspackage.PackageWorksBean;

public interface PackageWorksDao {
	
	public  List<PackageWorksBean> findWorksPackage(PackageWorksBean packageWorksBean ,List<String> statusList) throws DataAccessException;

	public boolean saveWorksPackage(PackageWorksBean packageWorksBean) throws DataAccessException;

	public boolean updateWorksPackage(PackageWorksBean packageWorksBean) throws DataAccessException;
	
	public Set<PackageWorksBean> getPackageIds(String schemeId)  throws DataAccessException;
}
