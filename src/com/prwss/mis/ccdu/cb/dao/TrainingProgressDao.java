package com.prwss.mis.ccdu.cb.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface TrainingProgressDao {
	
	public List<TrainingProgressBean> findCBTrainingProgress(TrainingProgressBean trainingProgressBean, List<String> statusList) throws DataAccessException;
	
	public TrainingProgressBean loadCBTrainingProgress(long cbProgressId) throws DataAccessException;
	
	public long saveCBTrainingProgress(TrainingProgressBean trainingProgressBean) throws DataAccessException;

	public boolean updateCBTrainingProgress(TrainingProgressBean trainingProgressBean)	throws DataAccessException;
	
	//public Set<TrainingProgressBean> getTrainingProgressIds(String villageId) throws DataAccessException;
	public Set<TrainingProgressBean> getTrainingProgressIds(TrainingProgressBean trainingProgressBean) throws DataAccessException;
	
	
}
