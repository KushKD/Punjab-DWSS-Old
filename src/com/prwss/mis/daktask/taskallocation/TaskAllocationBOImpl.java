package com.prwss.mis.daktask.taskallocation;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.daktask.inwarddak.dao.InwardDakBean;
import com.prwss.mis.daktask.inwarddak.dao.InwardDakDao;


public class TaskAllocationBOImpl implements TaskAllocationBO {

	private InwardDakDao inwardDakDao;
	Logger log=Logger.getLogger(TaskAllocationBOImpl.class);

	public void setInwardDakDao(InwardDakDao inwardDakDao) {
		this.inwardDakDao = inwardDakDao;
	}

	@Override
	public List<InwardDakBean> find(InwardDakBean inwardDakBean,
			List<String> statusList) throws MISException {		
		log.debug("in taskallocationimpl-----"+inwardDakBean.toString());
		List<InwardDakBean> inwardDakBeans=null;
		try{
			
			inwardDakBeans = inwardDakDao.getInwardDakBeans(
				inwardDakBean, statusList);
			if(inwardDakBeans.size()<=0)
			{
				throw new MISException();
			}
		}catch (MISException e) {
			throw e;
		}

		return inwardDakBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateTaskAllocationDeatils(
			List<InwardDakBean> inwardDakBeans, MISSessionBean misSessionBean)
			throws MISException {
				log.debug("inside update BO");
			int count=0;
		
			MISAuditBean misAuditBean=new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			
			
		
		try{
			for(InwardDakBean inwardDakBean:inwardDakBeans)
			{	try{
				inwardDakBean.setMisAuditBean(misAuditBean);
				inwardDakDao.saveOrUpdateInwardDakBean(inwardDakBean);
				count++;
				}catch(DataAccessException e)
				{
					throw new MISException(++count+" Record contains error  "+e);
				}
			}
		}catch(MISException e)
		{
			throw e;
		}
		
		return true;
	}

}
