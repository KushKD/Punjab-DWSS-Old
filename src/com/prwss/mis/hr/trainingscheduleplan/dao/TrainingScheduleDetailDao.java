package com.prwss.mis.hr.trainingscheduleplan.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface TrainingScheduleDetailDao {
	public List<TrainingScheduleDetailBean> findTrainingScheduleDetailBeans(TrainingScheduleDetailBean trainingScheduleDetailBean, List<String> statusList) throws DataAccessException;

	public boolean saveOrUpdateTrainingScheduleDetailBeans(Collection<TrainingScheduleDetailBean> trainingScheduleDetailBeans) throws DataAccessException;
}
