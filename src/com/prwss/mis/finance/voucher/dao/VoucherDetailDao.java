package com.prwss.mis.finance.voucher.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.finance.voucher.VoucherDetailBean;

public interface VoucherDetailDao {
	public List<VoucherDetailBean> findVoucherDetail(VoucherDetailBean voucherDetailBean, List<String> statusList) throws DataAccessException;

	public boolean saveOrUpdateVoucherDetail(Collection<VoucherDetailBean> voucherDetailBeans) throws DataAccessException;

}
