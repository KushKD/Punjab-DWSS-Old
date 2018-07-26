package com.prwss.mis.ccdu.cb.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface TrainingOfficerDao {
	
	public List<TrainingOfficerBean> findTrainingOfficer(TrainingOfficerBean trainingOfficerBean, List<String> statusList) throws DataAccessException;
	
	public String saveTrainingOfficer(TrainingOfficerBean trainingOfficerBean) throws DataAccessException;

	public boolean updateTrainingOfficer(TrainingOfficerBean trainingOfficerBean)	throws DataAccessException;
	
	public boolean saveOrUpdateTrainingOfficer(List<TrainingOfficerBean> trainingOfficerBeans) throws DataAccessException;

}
