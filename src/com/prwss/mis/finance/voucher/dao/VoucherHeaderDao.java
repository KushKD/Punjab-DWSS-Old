package com.prwss.mis.finance.voucher.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.finance.voucher.VoucherHeaderBean;

public interface VoucherHeaderDao {
	public List<VoucherHeaderBean> findVoucher(VoucherHeaderBean voucherHeaderBean,List<String> statusList) throws DataAccessException;

	public long saveVoucher(VoucherHeaderBean voucherHeaderBean) throws DataAccessException;

	public boolean updateVoucher(VoucherHeaderBean voucherHeaderBean) throws DataAccessException;
}
