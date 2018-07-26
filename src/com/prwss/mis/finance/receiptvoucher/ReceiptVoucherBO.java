/**
 * 
 */
package com.prwss.mis.finance.receiptvoucher;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.finance.receiptvoucher.struts.ReceiptVoucherForm;
import com.prwss.mis.finance.voucher.VoucherHeaderBean;

/**
 * @author PJHA
 *
 */
public interface ReceiptVoucherBO {
	
	public List<VoucherHeaderBean> findReceiptVoucher(ReceiptVoucherForm receiptVoucherForm, List<String> statusList) throws MISException;

	public long saveReceiptVoucher(ReceiptVoucherForm receiptVoucherForm, MISSessionBean misSessionBean) throws MISException;

	public boolean updateReceiptVoucher(ReceiptVoucherForm receiptVoucherForm, MISSessionBean misSessionBean) throws MISException;

	public boolean deleteReceiptVoucher(ReceiptVoucherForm receiptVoucherForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean postReceiptVoucher(ReceiptVoucherForm receiptVoucherForm, MISSessionBean misSessionBean) throws MISException;

	


}
