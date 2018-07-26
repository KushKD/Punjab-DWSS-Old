package com.prwss.mis.daktask.taskallocation;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.daktask.inwarddak.dao.InwardDakBean;


public interface TaskAllocationBO {
	
	public List<InwardDakBean> find(InwardDakBean inwardDakBean,List <String> statusList) throws MISException;
	public boolean updateTaskAllocationDeatils(List <InwardDakBean> inwardDakBeans,MISSessionBean misSessionBean) throws MISException;

}
