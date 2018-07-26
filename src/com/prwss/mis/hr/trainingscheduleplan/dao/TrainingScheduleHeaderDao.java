package com.prwss.mis.hr.trainingscheduleplan.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.hr.trainingscheduleplan.TrainingScheduleHeaderBean;

public interface TrainingScheduleHeaderDao {
	
	public  List<TrainingScheduleHeaderBean> findTrainingScheduleHeader(TrainingScheduleHeaderBean trainingScheduleHeaderBean ,List<String> statusList) throws DataAccessException;

	public long saveTrainingScheduleHeader(TrainingScheduleHeaderBean trainingScheduleHeaderBean) throws DataAccessException;

	public boolean updateTrainingScheduleHeader(TrainingScheduleHeaderBean trainingScheduleHeaderBean) throws DataAccessException;
	

}
