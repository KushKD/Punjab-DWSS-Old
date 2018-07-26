/**
 * 
 */
package com.prwss.mis.reports.performa.dao;

import java.util.List;
import java.util.ListIterator;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.reports.performa.PerformaFiveYearPlanBean;
import com.prwss.mis.reports.performa.PerformaMasterForm;

/**
 * @author bhsingh
 *
 */
public interface PerformaMasterDao {

	public List<PerformaMasterBean> findPerformaMaster(PerformaMasterForm performaMasterForm,List<String> statusList)throws DataAccessException;
	
	public List<PerformaMasterBean> findPerforma(PerformaMasterForm performaMasterForm,List<String> statusList)throws DataAccessException;


	public List<PerformaMasterBean> findVillage(PerformaMasterBean performaMasterBean,List<String> statusList)throws DataAccessException; 
	
	public boolean upadtePerformaHeader(List<VillagePerformaBean> performaBean)throws DataAccessException;
	
	public boolean upadtePerformaSource(List<SchemeSourcePerformaBean> performaBean)throws DataAccessException; 
	public boolean saveSchemeBean(List<SchemeBean1> schemeBean)throws DataAccessException; 
	
	public boolean saveFiveYearPlanBean(List<PerformaFiveYearPlanBean> performaFiveYearPlanBeans)throws DataAccessException; 
	public  List<PerformaMasterBean>  findSchemeFromVillage(
			PerformaMasterBean performaBean) throws DataAccessException;
	
	public List<SchemeSourcePerformaBean> findFiveYearPlanData(PerformaMasterForm performaMasterForm) throws DataAccessException;
	public List<SchemeBean1> findSchemeMaster(PerformaMasterForm performaMasterForm) throws DataAccessException;
	
	public boolean savePerformaMaster(FiveYearPerformaMaster fiveYearPerformaMaster)throws DataAccessException;


}
