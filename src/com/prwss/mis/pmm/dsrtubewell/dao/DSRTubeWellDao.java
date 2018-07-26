/**
 * 
 */
package com.prwss.mis.pmm.dsrtubewell.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.pmm.dsrtubewell.DSRTubeWellBean;

/**
 * @author pjha
 *
 */
public interface DSRTubeWellDao {
	public List<DSRTubeWellBean> findDSRTubeWell(DSRTubeWellBean  dsrTubeWellBean, List<String> statusList) throws DataAccessException;
	public List<DSRTubeWellBean> findDSRTubeWell(List<String> schemeId) throws DataAccessException;
    public String saveDSRTubeWell(DSRTubeWellBean dsrTubeWellBean) throws DataAccessException;
	public boolean updateDSRTubeWell(DSRTubeWellBean dsrTubeWellBean)	throws DataAccessException;
	public boolean updateDSRTubeWell(List<DSRTubeWellBean> dsrTubeWellBeans) throws DataAccessException;
}
