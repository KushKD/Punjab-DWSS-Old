/**
 * 
 */
package com.prwss.mis.pmm.dsrcanal.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * @author pjha
 *
 */
public interface DSRCanalInletDao {

	public List<DSRCanalInletBean> findDSRCanalDistribution(DSRCanalInletBean dsrCanalInletBean, List<String> statusList) throws DataAccessException;

	public boolean saveOrUpdateDSRCanalDistribution(Collection<DSRCanalInletBean> dsrCanalInletBeans) throws DataAccessException;

}
