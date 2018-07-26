package com.prwss.mis.procurement.wbpriorreview;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderBean;
import com.prwss.mis.procurement.wbpriorreview.struts.PriorReviewPackageForm;

public interface PriorReviewBO {
    public List<PackageHeaderBean> findPriorReviewPackage(PriorReviewPackageForm priorReviewPackageForm, List<String> statusList) throws MISException;
	public boolean updatePriorReviewPackage(PriorReviewPackageForm priorReviewPackageForm, MISSessionBean misSessionBean) throws MISException;
}
