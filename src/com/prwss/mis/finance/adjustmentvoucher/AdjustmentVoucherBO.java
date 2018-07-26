/**
 * 
 */
package com.prwss.mis.finance.adjustmentvoucher;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.finance.adjustmentvoucher.struts.AdjustmentVoucherForm;

/**
 * @author PJHA
 *
 */
public interface AdjustmentVoucherBO {
	public List<AdjustmentVoucherBean> findAdjustmentVoucher(AdjustmentVoucherForm adjustmentVoucherForm,List<String> statusList) throws MISException;
	
	public long saveAdjustmentVoucher(AdjustmentVoucherForm adjustmentVoucherForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateAdjustmentVoucher(AdjustmentVoucherForm adjustmentVoucherForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteAdjustmentVoucher(AdjustmentVoucherForm adjustmentVoucherForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean postAdjustmentVoucher(AdjustmentVoucherForm adjustmentVoucherForm,  MISSessionBean misSessionBean) throws MISException;
	
}
