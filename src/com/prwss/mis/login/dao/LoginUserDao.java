package com.prwss.mis.login.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface LoginUserDao {
	
	public LoginUserBean getUserDetails(String userId) throws DataAccessException, Exception;
	
	public List<LoginUserBean> findLoginUser(LoginUserBean loginUserBean, List <String> statusList) throws DataAccessException;

	public boolean saveLoginBean(LoginUserBean loginUserBean) throws DataAccessException;

	public boolean updateLoginBean(LoginUserBean loginUserBean)	throws DataAccessException;
	

}
