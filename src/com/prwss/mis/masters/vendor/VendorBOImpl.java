package com.prwss.mis.masters.vendor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.vendor.dao.VendorBean;
import com.prwss.mis.masters.vendor.dao.VendorDao;
import com.prwss.mis.masters.vendor.struts.VendorForm;
import com.prwss.mis.sd.document.dao.DocumentNumberBean;
import com.prwss.mis.sd.document.dao.DocumentNumberDAO;

public class VendorBOImpl implements VendorBO {
	
	private Logger log = Logger.getLogger(VendorBOImpl.class);
	private VendorDao vendorDao;
	private DocumentNumberDAO documentNumberDao;

	
	
	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
	}

	public void setVendorDao(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}

	@Override
	public List<VendorBean> findVendor(VendorForm vendorForm, List<String> statusList) throws MISException {
		List<VendorBean> vendorBeans = null;
		try {
			VendorBean vendorBean = new VendorBean();
			vendorBean.setVendorId(vendorForm.getVendorId());
			vendorBeans = vendorDao.findVendor(vendorBean, statusList);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return vendorBeans;
	}

	@Override
	public String saveVendor(VendorForm vendorForm, MISSessionBean misAuditBean) throws MISException {
		String id = null;
		boolean status = false;
		try {
			
			VendorBean vendorFindBean= new VendorBean();
			vendorFindBean.setPanNo(vendorForm.getPanNo().trim());
			List<VendorBean> beans = vendorDao.findVendor(vendorFindBean, null);
			if(!MisUtility.ifEmpty(beans)){
				throw new MISException(MISExceptionCodes.MIS001,"Vendor with Pan number: "+vendorForm.getPanNo()+". Please verify again.");
			}
			
			VendorBean vendorBean = populateVendorBean(vendorForm);	
			
			DocumentNumberBean documentNumebrBean=new DocumentNumberBean();
			
//			documentNumebrBean.setLocationId(inwardDakForm.getLocationId());
			
			documentNumebrBean.setDocumentType("VENDOR");
			
			DocumentNumberBean documentNumberBean = documentNumberDao.getDocumentNumber(documentNumebrBean).get(0);
			if(!(MisUtility.ifEmpty(documentNumberBean))){
				throw new MISException();
			}
			vendorBean.setVendorId(documentNumberBean.getLastNumber().toString());
			id = documentNumberBean.getLastNumber().toString();
			MISAuditBean misAuditBean2 = new MISAuditBean();
			misAuditBean2.setEntBy(misAuditBean.getEnteredBy());
			misAuditBean2.setEntDate(misAuditBean.getEnteredDate());
			misAuditBean2.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			vendorBean.setMisAuditBean(misAuditBean2);
			status = vendorDao.saveVendor(vendorBean);
			if(status){
				documentNumberDao.saveOrUpdateDocumentNumberBeans(documentNumberBean);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return id;
	}

	@Override
	public boolean updateVendor(VendorForm vendorForm, MISSessionBean misAuditBean,List<String> statusList) throws MISException {
		boolean status = false;
		try {
			VendorBean vendorBean = populateVendorBean(vendorForm);	
			MISAuditBean misAuditBean2 = new MISAuditBean();
			misAuditBean2.setEntBy(misAuditBean.getEnteredBy());
			misAuditBean2.setEntDate(misAuditBean.getEnteredDate());
			misAuditBean2.setStatus(statusList.get(0));
			vendorBean.setMisAuditBean(misAuditBean2);
			
			status = vendorDao.updateVendor(vendorBean);
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
	public boolean deleteVendor(VendorForm vendorForm, MISSessionBean misAuditBean) throws MISException {
		boolean result = false;
//		List<VendorBean> vendorBeans = vendorDao.findVendor(Arrays.asList(vendorForm.getVendorIds()));
		try {
			VendorBean vendorBean = populateVendorBean(vendorForm);
//			for (VendorBean vendorBean : vendorBeans) {
				MISAuditBean misAuditBean2 = new MISAuditBean();
				misAuditBean2.setEntBy(misAuditBean.getEnteredBy());
				misAuditBean2.setEntDate(misAuditBean.getEnteredDate());
				misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
				vendorBean.setMisAuditBean(misAuditBean2);
//			}
			result = vendorDao.updateVendor(vendorBean);
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
	public boolean postVendor(VendorForm vendorForm, MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
//		List<VendorBean> vendorBeans = vendorDao.findVendor(Arrays.asList(vendorForm.getVendorIds()));
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			VendorBean vendorBean = new VendorBean();
			vendorBean.setVendorId(vendorForm.getVendorId());
			VendorBean vendorBean2 = vendorDao.findVendor(vendorBean, statusList).get(0);
//			for (VendorBean vendorBean : vendorBeans) {
				MISAuditBean misAuditBean2 = new MISAuditBean();
				misAuditBean2.setEntBy(vendorBean2.getMisAuditBean().getEntBy());
				misAuditBean2.setEntDate(vendorBean2.getMisAuditBean().getEntDate());
				misAuditBean2.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean2.setAuthDate(misSessionBean.getEnteredDate());
				misAuditBean2.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				vendorBean2.setMisAuditBean(misAuditBean2);
//			}
			result = vendorDao.updateVendor(vendorBean2);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}
	
	private VendorBean populateVendorBean(VendorForm vendorForm){
		
		VendorBean vendorBean = new VendorBean();
		vendorBean.setVendorId(vendorForm.getVendorId());
		vendorBean.setVendorName(vendorForm.getVendorName());
		vendorBean.setAddress1(vendorForm.getAddress1());
		vendorBean.setAddress2(vendorForm.getAddress2());
		vendorBean.setCity(vendorForm.getCity());
		vendorBean.setEmail(vendorForm.getEmail());
		vendorBean.setPanNo(vendorForm.getPanNo().trim());
		vendorBean.setState(vendorForm.getState());
		vendorBean.setStreet(vendorForm.getStreet());
		vendorBean.setMobilePhone(vendorForm.getMobPhoneNo());
		vendorBean.setPin(vendorForm.getPinCode());
		vendorBean.setWorkPhone(vendorForm.getWorkPhoneNo());
		vendorBean.setVendorClass(vendorForm.getVendorClass());
		vendorBean.setVendorCategory(vendorForm.getVendorCategory());
		vendorBean.setTenderingLimit(vendorForm.getTenderingLimit());
		vendorBean.setEnlistmentAuthority(vendorForm.getEnlistmentAuthority());
		vendorBean.setDebarringAuthority(vendorForm.getDebarringAuthority());
		vendorBean.setRecovery(vendorForm.getRecovery());
		return vendorBean;
	}

	@Override
	public Set<VendorBean> getDistinctVendorCodes() throws MISException {
		
		Set<VendorBean> vendorBeans = null;
		try {
			vendorBeans = vendorDao.getDistinctVendorCodes();
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return vendorBeans;
	}

}
