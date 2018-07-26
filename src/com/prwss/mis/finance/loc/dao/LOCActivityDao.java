package com.prwss.mis.finance.loc.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface LOCActivityDao {
	
	public List<LOCActivityBean> findLOCActivity(LOCActivityBean locActivityBean, List<String> statusList) throws DataAccessException;

	public boolean saveLOCActivity(Collection<LOCActivityBean> locActivityBeans) throws DataAccessException;

	public boolean updateActivity(Collection<LOCActivityBean> locActivityBeans) throws DataAccessException;


}
