/**
 * 
 */
package com.prwss.mis.finance.adjustmentvoucher.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.finance.adjustmentvoucher.AdjustmentVoucherBean;

/**
 * @author PJHA
 *
 */
public interface AdjustmentVoucherDao {
	public List<AdjustmentVoucherBean> findAdjustmentVoucher(AdjustmentVoucherBean adjustmentVoucherBean,List<String> statusList) throws DataAccessException;

	public long saveAdjustmentVoucher(AdjustmentVoucherBean adjustmentVoucherBean) throws DataAccessException;

	public boolean updateAdjustmentVoucher(AdjustmentVoucherBean adjustmentVoucherBean) throws DataAccessException;
}
