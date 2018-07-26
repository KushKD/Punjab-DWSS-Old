package com.prwss.mis.procurement.nonconsultancypackage.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.procurement.nonconsultancypackage.PackageNonConsultancyBean;

public interface PackageNonConsultancyDao {
	public  List<PackageNonConsultancyBean> findNonConsltPackage(PackageNonConsultancyBean packageNonConsultancyBean ,List<String> statusList) throws DataAccessException;

	public boolean saveNonConsltPackage(PackageNonConsultancyBean packageNonConsultancyBean) throws DataAccessException;

	public boolean updateNonConsltPackage(PackageNonConsultancyBean packageNonConsultancyBean) throws DataAccessException;
}
