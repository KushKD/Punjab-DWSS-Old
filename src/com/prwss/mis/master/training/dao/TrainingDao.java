package com.prwss.mis.master.training.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface TrainingDao {
	
	public List<TrainingBean> findTraining(TrainingBean trainingBean, List<String> statusList) throws DataAccessException;
	
	public List<TrainingBean> findTraining(List<String> trainingIds) throws DataAccessException;
	
	public String saveTraining(TrainingBean trainingBean) throws DataAccessException;

	public boolean updateTraining(TrainingBean trainingBean) throws DataAccessException;
	
	public boolean updateTraining(List<TrainingBean> trainingBeans) throws DataAccessException;

	public Set<TrainingBean> getDistinctTrainingIds(String trainingType) throws DataAccessException;

}
