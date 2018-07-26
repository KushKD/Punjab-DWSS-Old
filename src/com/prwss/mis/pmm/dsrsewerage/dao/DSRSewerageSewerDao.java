/**
 * 
 */
package com.prwss.mis.pmm.dsrsewerage.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * @author pjha
 *
 */
public interface DSRSewerageSewerDao {
	public List<DSRSewerageSewerBean> findDSRCanalDistribution(DSRSewerageSewerBean dsrSewerageSewerBean, List<String> statusList) throws DataAccessException;

	public boolean saveOrUpdateDSRSewerageSewerBean(Collection<DSRSewerageSewerBean> dsrSewerageSewerBeans) throws DataAccessException;

}
