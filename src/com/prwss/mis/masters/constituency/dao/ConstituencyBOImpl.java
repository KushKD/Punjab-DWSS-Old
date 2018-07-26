package com.prwss.mis.masters.constituency.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.admin.struts.ConstituencyForm;
import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.district.dao.DistrictBean;

public class ConstituencyBOImpl implements ConstituencyBO{

	private Logger log = Logger.getLogger(ConstituencyBOImpl.class);
	private ConstituencyDao constituencyDao;
	
	
	public void setConstituencyDao(ConstituencyDao constituencyDao) {
		this.constituencyDao = constituencyDao;
	}

	@Override
	public List<ConstituencyBean> findConstituency(ConstituencyForm constituencyForm, List<String> statusList) throws MISException {
		List<ConstituencyBean> constituencyBeans = null;
		try {
			ConstituencyBean constituencyBean = new ConstituencyBean();
			constituencyBean.setConstituencyId(constituencyForm.getConstituencyId());
			
			DistrictBean district = new DistrictBean();
			district.setDistrictId(constituencyForm.getDistrictId());
			constituencyBean.setDistrictId(district.getDistrictId());
			constituencyBeans = constituencyDao.findConstituency(constituencyBean, statusList);
			System.out.println("----constituency Bean"+constituencyBeans.size());
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return constituencyBeans;
	}

	@Override
	public boolean saveConstituency(ConstituencyForm constituencyForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		List<ConstituencyBean> constituencyBeans = null;
		try {
			/*ConstituencyBean constituencyBean = populateConstituencyBean(constituencyForm);		
			constituencyBean.setConstituencyId(constituencyBean.getConstituencyId());
			String constituencyName = constituencyBean.getConstituencyName();
			constituencyBeans = constituencyDao.findConstituency(constituencyBean, null);
			if(!MisUtility.ifEmpty(constituencyBeans)){					
				StringBuffer message = new StringBuffer();
				message.append(" Constituency Id ").append(constituencyBean.getConstituencyId());
				if(constituencyName.equalsIgnoreCase(constituencyBeans.get(0).getConstituencyName())){
					message.append(" and Constituency Name ").append(constituencyName);
				}
				log.debug("Duplicate Entry for "+constituencyBean.getConstituencyId()+" and "+ constituencyBean.getConstituencyName());
				log.debug("Constituency Already exist \n"+constituencyBeans);
				throw new MISException(MISExceptionCodes.MIS001 , message.toString());
			}
			constituencyBean.setDistrictId(constituencyForm.getDistrictId());
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			constituencyBean.setMisAuditBean(misAuditBean);
			status = constituencyDao.saveConstituency(constituencyBean);*/
			ConstituencyBean constituencyBean = new ConstituencyBean();
			constituencyBean.setConstituencyId("");
			constituencyBean.setConstituencyName("");
			//constituencyBean.setDistrictId(constituencyForm.getDistrictId());
			ConstituencyBean constituencyBean2 = new ConstituencyBean();
			
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList.add(MISConstants.MASTER_STATUS_DELETED);
			constituencyBeans = constituencyDao.findConstituency(constituencyBean, statusList);
			if(!MisUtility.ifEmpty(constituencyBeans)){			
				for(ConstituencyBean constituencyBean3:constituencyBeans){
					if(constituencyForm.getConstituencyName().equalsIgnoreCase(constituencyBean3.getConstituencyName())){
						StringBuffer message = new StringBuffer();
						message.append(" Constituency Name ").append(constituencyForm.getConstituencyName());
						log.debug("Duplicate Entry for "+constituencyForm.getConstituencyId()+" and "+ constituencyForm.getConstituencyName());
						log.debug("Constituency Already exist \n"+constituencyBeans);
						throw new MISException(MISExceptionCodes.MIS001 , message.toString());		
					}
				}
			
				System.out.println("Size is "+constituencyBeans.size());
				ConstituencyBean bean = constituencyBeans.get(constituencyBeans.size()-1);
				if(constituencyBeans.size()%10==0){
					constituencyBean2.setConstituencyId(constituencyForm.getDistrictId()+"C0"+(constituencyBeans.size()+1));
				}
				else{
					String id = bean.getConstituencyId().substring(6);
					int lastId = Integer.parseInt(id)+1;
					System.out.println("String Id is "+id+" lastId Created "+lastId);
					
					if(lastId<10){
						constituencyBean2.setConstituencyId(constituencyForm.getDistrictId()+"C00"+lastId);
					}
					else{
						constituencyBean2.setConstituencyId(constituencyForm.getDistrictId()+"C0"+lastId);	
					}
				}
			}
		
			constituencyBean2.setConstituencyName(constituencyForm.getConstituencyName());			
			constituencyBean2.setDistrictId(constituencyForm.getDistrictId());
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			constituencyBean2.setMisAuditBean(misAuditBean);
			status = constituencyDao.saveConstituency(constituencyBean2);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} 
		
		return status;
		
		
	}

	@Override
	public boolean updateConstituency(ConstituencyForm constituencyForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		List<ConstituencyBean> constituencyBeans = null;
		try {
			ConstituencyBean constituencyBean = populateConstituencyBean(constituencyForm);		
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			constituencyBean.setMisAuditBean(misAuditBean);
			
			ConstituencyBean bean = new ConstituencyBean();
			bean.setConstituencyId("");
			bean.setConstituencyName("");
			
			List<String> statusList1 = new ArrayList<String>();
			statusList1.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList1.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList1.add(MISConstants.MASTER_STATUS_DELETED);
			constituencyBeans = constituencyDao.findConstituency(bean, statusList1);
			if(!MisUtility.ifEmpty(constituencyBeans)){	
				for(ConstituencyBean constituencyBean3:constituencyBeans){
					if(constituencyForm.getConstituencyName().equalsIgnoreCase(constituencyBean3.getConstituencyName())){
						StringBuffer message = new StringBuffer();
						message.append(" Constituency Name ").append(constituencyForm.getConstituencyName());
						log.debug("Duplicate Entry for "+constituencyForm.getConstituencyId()+" and "+ constituencyForm.getConstituencyName());
						log.debug("Constituency Already exist \n"+constituencyBeans);
						
						throw new MISException(MISExceptionCodes.MIS001 , message.toString());		
					}
				}
			}			
			status = constituencyDao.updateConstituency(constituencyBean);
		} catch (DataAccessException e) {
			throw new MISException(e);
		}	
		return status;
	}

	@Override
	public boolean deleteConstituency(ConstituencyForm constituencyForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false; 
		try {
			ConstituencyBean bean = new ConstituencyBean();
			bean.setConstituencyId(constituencyForm.getConstituencyId());
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			List<ConstituencyBean> constituencyBeans = constituencyDao.findConstituency(bean,statusList);
			for (ConstituencyBean constituencyBean : constituencyBeans) {
				MISAuditBean misAuditBean = new MISAuditBean();
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
				constituencyBean.setMisAuditBean(misAuditBean);
				}
			status = constituencyDao.updateConstituency(constituencyBeans);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}

		return status;
	}

	@Override
	public boolean postConstituency(ConstituencyForm constituencyForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false; 
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		ConstituencyBean bean = new ConstituencyBean();
		bean.setConstituencyId(constituencyForm.getConstituencyId());
		try {
			List<ConstituencyBean> constituencyBeans = constituencyDao.findConstituency(bean,statusList);
			for (ConstituencyBean constituencyBean : constituencyBeans) {
				MISAuditBean misAuditBean = new MISAuditBean();
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
				constituencyBean.setMisAuditBean(misAuditBean);
				}
			status = constituencyDao.updateConstituency(constituencyBeans);
			
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}

		return status;
	}
	
	private ConstituencyBean populateConstituencyBean(ConstituencyForm constituencyForm){
		
		ConstituencyBean constituencyBean = new ConstituencyBean();
		
		constituencyBean.setConstituencyId(constituencyForm.getConstituencyId());
		constituencyBean.setConstituencyName(constituencyForm.getConstituencyName());
		DistrictBean district = new DistrictBean();
		district.setDistrictId(constituencyForm.getDistrictId());
		constituencyBean.setDistrictId(district.getDistrictId());
		
		return constituencyBean;
	}

}
