/**
 * 
 */
package com.prwss.mis.pmm.dsrsewerage.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.pmm.dsrsewerage.DSRSewerageBean;


/**
 * @author pjha
 *
 */
public interface DSRSewerageDao {
	
	public List<DSRSewerageBean> findDSRSewerage(DSRSewerageBean dsrSewerageBean, List<String> statusList) throws DataAccessException;
	public List<DSRSewerageBean> findDSRSewerage(List<String> schemeId) throws DataAccessException;
    public String saveDSRSewerage(DSRSewerageBean dsrSewerageBean) throws DataAccessException;
	public boolean updateDSRSewerage(DSRSewerageBean dsrSewerageBean)	throws DataAccessException;
	public boolean updateDSRSewerage(List<DSRSewerageBean> dsrSewerageBeans) throws DataAccessException;
}
