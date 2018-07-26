/**
 * 
 */
package com.prwss.mis.pmm.dsrcanal.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.pmm.dsrcanal.DSRCanalBean;

/**
 * @author pjha
 *
 */
public interface DSRCanalDao {
	public List<DSRCanalBean> findDSRCanal(DSRCanalBean dsrCanalBean, List<String> statusList) throws DataAccessException;
	public List<DSRCanalBean> findDSRCanal(List<String> schemeId) throws DataAccessException;
    public String saveDSRCanal(DSRCanalBean dsrCanalBean) throws DataAccessException;
	public boolean updateDSRCanal(DSRCanalBean dsrCanalBean)	throws DataAccessException;
	public boolean updateDSRCanal(List<DSRCanalBean> dsrCanalBeans) throws DataAccessException;
	
}
