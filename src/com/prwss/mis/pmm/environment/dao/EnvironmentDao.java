/**
 * 
 */
package com.prwss.mis.pmm.environment.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.pmm.environment.EnvironmentBean;

/**
 * @author pjha
 *
 */
public interface EnvironmentDao {
	public  List<EnvironmentBean> findEnvironmentBean(EnvironmentBean environmentBean ,List<String> statusList) throws DataAccessException;

	public boolean saveEnvironmentBean(EnvironmentBean environmentBean) throws DataAccessException;

	public boolean saveOrUpdateEnvironmentBean(EnvironmentBean environmentBean) throws DataAccessException;
}
