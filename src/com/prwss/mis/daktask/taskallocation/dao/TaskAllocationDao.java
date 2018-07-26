package com.prwss.mis.daktask.taskallocation.dao;

import java.util.Collection;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface TaskAllocationDao {
public List<TaskAllocationBean> getTaskAllocationBeans(TaskAllocationBean taskAllocationBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveOrUpdateTaskAllocationBean(Collection<TaskAllocationBean> taskAllocationBeans) throws DataAccessException;

}
