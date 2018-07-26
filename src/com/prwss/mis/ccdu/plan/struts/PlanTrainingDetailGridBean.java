package com.prwss.mis.ccdu.plan.struts;

import java.io.Serializable;

public class PlanTrainingDetailGridBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7206375704564427892L;
	private String trainingId;
	private long count;
	
	public String getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
}
