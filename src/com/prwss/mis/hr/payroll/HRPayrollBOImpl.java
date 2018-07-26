package com.prwss.mis.hr.payroll;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.finance.voucher.VoucherHeaderBean;
import com.prwss.mis.finance.voucher.dao.VoucherDetailDao;
import com.prwss.mis.finance.voucher.dao.VoucherHeaderDao;
import com.prwss.mis.hr.payroll.dao.HRPayrollDao;
import com.prwss.mis.hr.payroll.struts.HRPayrollForm;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

public class HRPayrollBOImpl implements HRPayrollBO {
	private Logger log = Logger.getLogger(HRPayrollBOImpl.class);
	private HRPayrollDao hrPayrollDao;
	private VoucherHeaderDao voucherHeaderDao;
	private VoucherDetailDao voucherDetailDao;
	
	

	public void setVoucherHeaderDao(VoucherHeaderDao voucherHeaderDao) {
		this.voucherHeaderDao = voucherHeaderDao;
	}

	public void setVoucherDetailDao(VoucherDetailDao voucherDetailDao) {
		this.voucherDetailDao = voucherDetailDao;
	}

	public void setHrPayrollDao(HRPayrollDao hrPayrollDao) {
	this.hrPayrollDao = hrPayrollDao;
	}

	@Override
	public List<HRPayrollBean> findHRPayroll(HRPayrollForm hrPayrollForm,
			List<String> statusList) throws MISException {
		List<HRPayrollBean> hrPayrollBeans = null;
		try {
			HRPayrollBean hrPayrollBean = new HRPayrollBean();
			EmployeeBean bean = new EmployeeBean();
			bean.setEmployeeId(hrPayrollForm.getEmployeeId());
			hrPayrollBean.setEmployeeBean(bean);
			hrPayrollBean.setPayrollMonth(hrPayrollForm.getPayrollMonth());
			hrPayrollBean.setPayrollYear(hrPayrollForm.getPayrollYear());
			hrPayrollBeans = hrPayrollDao.findHRPayroll(hrPayrollBean, statusList);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return hrPayrollBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public long saveHRPayroll(HRPayrollForm hrPayrollForm,
			MISSessionBean misSessionBean) throws MISException {
		long vocId = 0;
		boolean status = false;
		try {
			List<HRPayrollBean> hrPayrollBeans = null;
			HRPayrollBean hrPayrollBean = new HRPayrollBean();
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			hrPayrollBean.setLocationId(hrPayrollForm.getLocationId());
			hrPayrollBean.setPayrollMonth(hrPayrollForm.getPayrollMonth());
			hrPayrollBean.setPayrollYear(hrPayrollForm.getPayrollYear());
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setEmployeeId(hrPayrollForm.getEmployeeId());
			hrPayrollBean.setEmployeeBean(employeeBean);
			hrPayrollBeans = hrPayrollDao.findHRPayroll(hrPayrollBean, statusList);
			if(!MisUtility.ifEmpty(hrPayrollBeans)){
				throw new MISException(MISExceptionCodes.MIS001, "Payroll Voucher Generation Failed For Employee Id - "+hrPayrollForm.getEmployeeId()+"For The Specified Month and Year As Given. Entry Already Exist For This Period");
			}
			VoucherHeaderBean voucherHeaderBean = new VoucherHeaderBean();
			voucherHeaderBean.setAmount(new BigDecimal(hrPayrollForm.getTotalAmount()));
			voucherHeaderBean.setLocationId(hrPayrollForm.getLocationId());
			voucherHeaderBean.setPayeePayerType(MISConstants.FIN_PAYMENT_EMPLOYEE);
			voucherHeaderBean.setPayeePayerId(new Long(hrPayrollForm.getEmployeeId()).toString().trim());
			voucherHeaderBean.setNotes("Payroll Voucher");
			voucherHeaderBean.setVocDate(MisUtility.convertStringToDate(MisUtility.now("dd-MM-yyyy")));
			voucherHeaderBean.setPaymentMode("BANK");
			voucherHeaderBean.setTypeOfPayment("OTHER");
			voucherHeaderBean.setVocType(MISConstants.FIN_VOC_TYPE_PAYMENT);
			voucherHeaderBean.setMisAuditBean(misAuditBean);
			voucherHeaderBean.setProgramId(MISConstants.FIN_NOT_APPLICABLE);
			voucherHeaderBean.setCanModify(MISConstants.FIN_VOUCHER_CAN_MODIFY_NO);
			vocId = voucherHeaderDao.saveVoucher(voucherHeaderBean);
			if(MisUtility.ifEmpty(vocId)){
				HRPayrollBean hrPayrollBean2 = populateHrPayrollBean(hrPayrollForm, vocId);
				hrPayrollBean2.setMisAuditBean(misAuditBean);
				status = hrPayrollDao.saveHRPayroll(hrPayrollBean2);
				if(!status){
					throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Payroll Bean");
				}
			}
			
			// Still we need to add voucher details and Program Id from this method. TO DO When Information is available
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new MISException(e);
		}
		return vocId;
	}
	
	private HRPayrollBean populateHrPayrollBean(HRPayrollForm hrPayrollForm,long vocId){
		HRPayrollBean hrPayrollBean = new HRPayrollBean();
		EmployeeBean employeeBean = new EmployeeBean();
		employeeBean.setEmployeeId(hrPayrollForm.getEmployeeId());
		hrPayrollBean.setEmployeeBean(employeeBean);
		hrPayrollBean.setPayrollMonth(hrPayrollForm.getPayrollMonth());
		hrPayrollBean.setPayrollYear(hrPayrollForm.getPayrollYear());
		hrPayrollBean.setTotalAmount(hrPayrollForm.getTotalAmount());
		hrPayrollBean.setVocId(vocId);
		hrPayrollBean.setLocationId(hrPayrollForm.getLocationId());
		return hrPayrollBean;
	}

}
