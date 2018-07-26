package com.prwss.mis.hr.trainingmanagent.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.hr.trainingmanagent.HRTrainingManagementBean;

public interface HRTrainingManagementDao {
	
	public List<HRTrainingManagementBean> findHRTrainingManagementBeans(HRTrainingManagementBean hrTrainingManagementBean, List<String> statusList) throws DataAccessException;

	public boolean saveOrUpdateHRTrainingManagementBeans(Collection<HRTrainingManagementBean> hrTrainingManagementBeans) throws DataAccessException;


}
