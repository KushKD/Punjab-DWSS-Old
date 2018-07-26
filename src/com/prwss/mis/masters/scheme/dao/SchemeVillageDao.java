/**
 * 
 */
package com.prwss.mis.masters.scheme.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.scheme.struts.SchemeForm;

/**
 * @author pjha
 *
 */
public interface SchemeVillageDao {
public  List<SchemeVillageBean> findVillage(String villageId,List<String> status) throws DataAccessException ;

public  List<SchemeVillageBean> findWaterWorks(String schemeId,String villageId,List<String> status) throws DataAccessException ;
	
public List<SchemeVillageBean> findSchemeVillage(SchemeVillageBean schemeVillageBean ,List<String> statusList) throws DataAccessException;
	
	public boolean saveSchemeVillage(List<SchemeVillageBean> schemeVillageBeans) throws DataAccessException,MISException;
	public boolean updateSchemeVillages(List<SchemeVillageBean> schemeVillageBean) throws DataAccessException, MISException;

	boolean updateSchemeVillage(SchemeVillageBean schemeVillageBean)
			throws DataAccessException;

	public List<SchemeVillageBean> findSchemeVillage(
			SchemeVillageBean schemeVillageBean, List<String> statusList,
			String[] arr) throws DataAccessException;

	List<SchemeVillageBean> findSchemevillage(SchemeVillageBean form,
			List<String> statusList) throws DataAccessException;
	
	
	
}
