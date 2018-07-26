package com.prwss.mis.finance.gpwscregister.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface GpwscRegisterDao {
	
	public List<GpwscRegisterBean> findGPWSCRegister(GpwscRegisterBean gpwscRegisterBean, List<String> statusList) throws DataAccessException;

	public long saveGPWSCRegister(GpwscRegisterBean gpwscRegisterBean) throws DataAccessException;

	public boolean updateGPWSCRegister(GpwscRegisterBean gpwscRegisterBean)	throws DataAccessException;

}
