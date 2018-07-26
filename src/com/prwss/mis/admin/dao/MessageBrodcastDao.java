package com.prwss.mis.admin.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.admin.MessageBrodcastBean;
 

public interface MessageBrodcastDao {

	public long saveBrodcastMessage(MessageBrodcastBean messageBrodcastBean) throws DataAccessException;
	public List<MessageBrodcastBean> findMessageDeatil(MessageBrodcastBean messageBrodcastBean, List<String> statusList)throws DataAccessException;
	public boolean updateBrodcastMessage(MessageBrodcastBean messageBrodcastBean)throws DataAccessException;
	public List<MessageBrodcastBean> findMessages()throws DataAccessException;
}
