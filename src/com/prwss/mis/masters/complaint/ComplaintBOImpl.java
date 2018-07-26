package com.prwss.mis.masters.complaint;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.complaint.dao.ComplaintBean;
import com.prwss.mis.masters.complaint.dao.ComplaintDao;
import com.prwss.mis.masters.complaint.struts.ComplaintForm;

public class ComplaintBOImpl implements ComplaintBO {
	
	private Logger log = Logger.getLogger(ComplaintBOImpl.class);
	
	private ComplaintDao complaintDao;

	public void setComplaintDao(ComplaintDao complaintDao) {
		this.complaintDao = complaintDao;
	}

	@Override
	public List<ComplaintBean> findComplaint(ComplaintForm complaintForm, List<String> statusList) throws MISException {
		List<ComplaintBean> complaintBeans = null;
		ComplaintBean complaintBean = null;
		try {
			if(!MisUtility.ifEmpty(complaintForm.getComplaintType())){
				complaintBean = new ComplaintBean();
				complaintBean.setComplaintType("");
			}
			else
				complaintBean = populateComplaintBean(complaintForm);
			
			complaintBeans = complaintDao.findComplaint(complaintBean, statusList);
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return complaintBeans;
	}

	@Override
	public boolean saveComplaint(ComplaintForm complaintForm, MISSessionBean misAuditBean) throws MISException {
		boolean status = false;
		try {
			
			ComplaintBean complaintBean = populateComplaintBean(complaintForm);
			
			String complaintType = complaintBean.getComplaintType();
			List<ComplaintBean> complaintBeans = complaintDao.findComplaint(complaintBean, null);
			if(!MisUtility.ifEmpty(complaintBeans)){					
				StringBuffer message = new StringBuffer();
				message.append(" Complaint Id ").append(complaintForm.getComplaintId());
				if(complaintType.equalsIgnoreCase(complaintBeans.get(0).getComplaintType())){
					message.append(" and Complaint Name ").append(complaintType);
				}
				log.debug("Duplicate Entry for "+complaintForm.getComplaintId()+" and "+ complaintForm.getComplaintType());
				log.debug("Complaint Already exist \n"+complaintBeans);
				throw new MISException(MISExceptionCodes.MIS001 , message.toString());
			}
			complaintBean.setComplaintId(complaintBeans.size()+1);
			complaintBean.setEnteredBy(misAuditBean.getEnteredBy());
			complaintBean.setEnteredDate(misAuditBean.getEnteredDate());
			complaintBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			
			status = complaintDao.saveComplaint(complaintBean);
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean updateComplaint(ComplaintForm complaintForm, MISSessionBean misAuditBean,List<String> statusList) throws MISException {
		boolean status = false;
		try {
			
			ComplaintBean complaintBean = populateComplaintBean(complaintForm);
			complaintBean.setEnteredBy(misAuditBean.getEnteredBy());
			complaintBean.setEnteredDate(misAuditBean.getEnteredDate());
			complaintBean.setStatus(statusList.get(0));
			
			status = complaintDao.updateComplaint(complaintBean);
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean deleteComplaint(ComplaintForm complaintForm, MISSessionBean misAuditBean) throws MISException {
		boolean result = false;
		List<ComplaintBean> complaintBeans = complaintDao.findComplaint(Arrays.asList(complaintForm.getComplaintIds()));
		try {
			for (ComplaintBean complaintBean : complaintBeans) {
				complaintBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				complaintBean.setEnteredBy(misAuditBean.getEnteredBy());
				complaintBean.setEnteredDate(misAuditBean.getEnteredDate());
			}
			result = complaintDao.updateComplaint(complaintBeans);
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}

	@Override
	public boolean postComplaint(ComplaintForm complaintForm, MISSessionBean misAuditBean) throws MISException {
		boolean result = false;
		List<ComplaintBean> complaintBeans = complaintDao.findComplaint(Arrays.asList(complaintForm.getComplaintIds()));
		try {
			for (ComplaintBean complaintBean : complaintBeans) {
				complaintBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				complaintBean.setAuthorizedBy(misAuditBean.getEnteredBy());
				complaintBean.setAuthorizedDate(misAuditBean.getEnteredDate());
			}
			result = complaintDao.updateComplaint(complaintBeans);
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}
	
	private ComplaintBean populateComplaintBean(ComplaintForm complaintForm){

		ComplaintBean complaintBean = new ComplaintBean();

		complaintBean.setComplaintId(complaintForm.getComplaintId());
		complaintBean.setComplaintType(complaintForm.getComplaintType());
		complaintBean.setComplaintDescription(complaintForm.getComplaintDescription());
		complaintBean.setLevel1EmployeeId(complaintForm.getLevel1EmployeeId());
		complaintBean.setLevel2EmployeeId(complaintForm.getLevel2EmployeeId());
		complaintBean.setLevel3EmployeeId(complaintForm.getLevel3EmployeeId());
		complaintBean.setLevel4EmployeeId(complaintForm.getLevel4EmployeeId());
		complaintBean.setLevel5EmployeeId(complaintForm.getLevel5EmployeeId());
		complaintBean.setLevel1RedressalDays(complaintForm.getLevel1RedressalDays());
		complaintBean.setLevel2RedressalDays(complaintForm.getLevel2RedressalDays());
		complaintBean.setLevel3RedressalDays(complaintForm.getLevel3RedressalDays());
		complaintBean.setLevel4RedressalDays(complaintForm.getLevel4RedressalDays());
		complaintBean.setLevel5RedressalDays(complaintForm.getLevel5RedressalDays());
		
		return complaintBean;	
	}

}
