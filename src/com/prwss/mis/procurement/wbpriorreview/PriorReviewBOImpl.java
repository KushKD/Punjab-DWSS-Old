package com.prwss.mis.procurement.wbpriorreview;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderDao;
import com.prwss.mis.procurement.wbpriorreview.struts.PriorReviewPackageForm;

public class PriorReviewBOImpl implements PriorReviewBO {
	private Logger log = Logger.getLogger(PriorReviewBOImpl.class);
	private PackageHeaderDao packageHeaderDao;
	
	public void setPackageHeaderDao(PackageHeaderDao packageHeaderDao) {
		this.packageHeaderDao = packageHeaderDao;
	}

	@Override
	public List<PackageHeaderBean> findPriorReviewPackage(
			PriorReviewPackageForm priorReviewPackageForm,
			List<String> statusList) throws MISException {
		List<PackageHeaderBean> packageHeaderBeans = null;
		try {
			PackageHeaderBean packageHeaderBean = new PackageHeaderBean();
			packageHeaderBean.setPlanId(priorReviewPackageForm.getPlanId());
			packageHeaderBean.setLocationId(priorReviewPackageForm.getLocationId());
			packageHeaderBean.setPackageType(priorReviewPackageForm.getTypeOfProcurement());
			packageHeaderBean.setPackageId(priorReviewPackageForm.getPackageId());
			packageHeaderBean.setPostPriorStatus(MISConstants.PROCUREMENT_PACKAGE_PRIOR_REVIEW);
			packageHeaderBeans = packageHeaderDao.findPackageForPriorReview(packageHeaderBean, null);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return packageHeaderBeans;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public boolean updatePriorReviewPackage(
			PriorReviewPackageForm priorReviewPackageForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			PackageHeaderBean packageHeaderBean = new PackageHeaderBean();
			packageHeaderBean.setPackageId(priorReviewPackageForm.getPackageId());
			packageHeaderBean.setLocationId(priorReviewPackageForm.getLocationId());
			packageHeaderBean= packageHeaderDao.findPackage(packageHeaderBean, statusList).get(0);
			if(MisUtility.ifEmpty(packageHeaderBean)){
				packageHeaderBean.setWbNocDate(MisUtility.convertStringToDate(priorReviewPackageForm.getWbNocDate()));
				
				packageHeaderBean.setWbBidDocNocDate(MisUtility.convertStringToDate(priorReviewPackageForm.getWbBidDocNocDate()));
				packageHeaderBean.setWbContractAwardNocDate(MisUtility.convertStringToDate(priorReviewPackageForm.getWbContractAwardNocDate()));
				
				packageHeaderBean.setWbNumber(priorReviewPackageForm.getWbNumber().trim());
				packageHeaderBean.setWbRemarks(priorReviewPackageForm.getWbRemarks().trim());
				packageHeaderBean.setWbStatus(priorReviewPackageForm.getWbStatus());
				packageHeaderBean.setWbReviewSubmittedBy(misSessionBean.getEnteredBy());
				packageHeaderBean.setWbReviewEntDate(misSessionBean.getEnteredDate());
				status = packageHeaderDao.updatePackage(packageHeaderBean);
				if(!status){
					log.error(packageHeaderBean);
					throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Package World Bank Prior Review Details");
				}
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}

		return status;
	}

}
