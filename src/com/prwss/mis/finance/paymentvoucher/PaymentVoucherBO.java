/**
 * 
 */
package com.prwss.mis.finance.paymentvoucher;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.finance.paymentvoucher.struts.PaymentVoucherForm;
import com.prwss.mis.finance.voucher.VoucherHeaderBean;

/**
 * @author PJHA
 *
 */
public interface PaymentVoucherBO {
	public List<VoucherHeaderBean> findPaymentVoucher(PaymentVoucherForm paymentVoucherForm, List<String> statusList) throws MISException;

	public long savePaymentVoucher(PaymentVoucherForm paymentVoucherForm, MISSessionBean misSessionBean) throws MISException;

	public boolean updatePaymentVoucher(PaymentVoucherForm paymentVoucherForm, MISSessionBean misSessionBean) throws MISException;

	public boolean deletePaymentVoucher(PaymentVoucherForm paymentVoucherForm, MISSessionBean misSessionBean) throws MISException;

	public boolean postPaymentVoucher(PaymentVoucherForm paymentVoucherForm, MISSessionBean misSessionBean) throws MISException;

}
