/**
 * 
 */
package com.prwss.mis.finance.loc.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.finance.loc.LOCHeaderBean;
import com.prwss.mis.finance.releaseloc.struts.LocNotReleasedBean;

/**
 * @author PJHA
 *
 */
public interface LOCHeaderDao {

	public List<LOCHeaderBean> findLOCHeader(LOCHeaderBean locHeaderBean,List<String> statusList) throws DataAccessException;
	public List<LOCRequestFromLocationBean> findDistinctrequestFromLocationId(LOCHeaderBean locHeaderBean,List<String> statusList) throws DataAccessException;
	public long saveLOCHeader(LOCHeaderBean locHeaderBean) throws DataAccessException;

	public boolean updateLOCHeader(LOCHeaderBean locHeaderBean) throws DataAccessException;
	public boolean updateLOCHeader(List<LOCHeaderBean> locHeaderBeans) throws DataAccessException;
	public List<LocNotReleasedBean> fetchReleaseLoc(
			LocNotReleasedBean locNotReleasedBean);
	
}
