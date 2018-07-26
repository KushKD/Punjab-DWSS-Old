/**
 * 
 */
package com.prwss.mis.pmm.dsrtubewell.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * @author pjha
 *
 */
public interface DSRTubeWellDistributionDao {
	public List<DSRTubeWellDistributionBean> findDSRTubeWellDistribution(DSRTubeWellDistributionBean dsrTubeWellDistributionBean, List<String> statusList) throws DataAccessException;

	public boolean saveOrUpdateDSRTubeWellDistribution(Collection<DSRTubeWellDistributionBean> dsrTubeWellDistributionBeans) throws DataAccessException;

}
