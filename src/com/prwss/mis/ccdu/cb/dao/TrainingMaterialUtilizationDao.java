package com.prwss.mis.ccdu.cb.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface TrainingMaterialUtilizationDao {

	public List<TrainingMaterialUtilizationBean> findCBTrainingMaterialUtilization(TrainingMaterialUtilizationBean trainingMaterialUtilizationBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveOrUpdateCBTrainingMaterialUtilization(Collection<TrainingMaterialUtilizationBean> trainingMaterialUtilizationBeans) throws DataAccessException;

}
