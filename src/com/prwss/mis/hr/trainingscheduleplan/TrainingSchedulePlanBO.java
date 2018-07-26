package com.prwss.mis.hr.trainingscheduleplan;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.hr.trainingscheduleplan.struts.TrainingScheduleForm;

public interface TrainingSchedulePlanBO {
	public List<TrainingScheduleHeaderBean> findTrainingSchedulePlan(TrainingScheduleForm trainingScheduleForm, List<String> statusList) throws MISException;
	
	public long saveTrainingSchedulePlan(TrainingScheduleForm trainingScheduleForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateTrainingSchedulePlan(TrainingScheduleForm trainingScheduleForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteTrainingSchedulePlan(TrainingScheduleForm trainingScheduleForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean postTrainingSchedulePlan(TrainingScheduleForm trainingScheduleForm, MISSessionBean misSessionBean) throws MISException;

}
