package com.prwss.mis.admin;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.admin.dao.MessageBrodcastDao;
import com.prwss.mis.admin.struts.MessageBrodcastForm;
import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class MessageBrodcastBOImpl implements MessageBrodcastBO {

	private MessageBrodcastDao messageBrodcastDao;
	
	public void setMessageBrodcastDao(MessageBrodcastDao messageBrodcastDao) {
		this.messageBrodcastDao = messageBrodcastDao;
	}
	
	@Override
	public long saveBrodcastedMessage(
			MessageBrodcastBean messageBrodcastBean,
			MISSessionBean misSessionBean) throws MISException {
		long status = 0;
		try {
			
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			messageBrodcastBean.setMisAuditBean(misAuditBean);
			status = messageBrodcastDao.saveBrodcastMessage(messageBrodcastBean);
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		 
		return status;
	}

	@Override
	public List<MessageBrodcastBean> findBrodcastedMessages(
			MessageBrodcastForm messageBrodcastForm, List<String> statusList)
			throws MISException {
			List<MessageBrodcastBean> messageBrodcastBeans = null;
			MessageBrodcastBean messageBrodcastBean1 = new MessageBrodcastBean();
			try{
			messageBrodcastBean1.setMessageId(messageBrodcastForm.getMessageId());
			messageBrodcastBeans = messageBrodcastDao.findMessageDeatil(messageBrodcastBean1,statusList);
			}catch (DataAccessException e) {
				 
				throw e;
			}
		return messageBrodcastBeans;
	}

	@Override
	public boolean updateMesageBrodcast(
			MessageBrodcastForm messageBrodcastForm,
			MISSessionBean misSessionBean) throws MISException {
		 boolean status = false;
		 try {
			
				MessageBrodcastBean messageBrodcastBean = new MessageBrodcastBean();
				messageBrodcastBean.setMessageId(messageBrodcastForm.getMessageId());
				messageBrodcastBean.setMessageDetail(messageBrodcastForm.getMessageDetail());
				messageBrodcastBean.setExpiryDate(MisUtility.convertStringToDate(messageBrodcastForm.getExpiryDate()));
				MISAuditBean misAuditBean = new MISAuditBean();
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
				messageBrodcastBean.setMisAuditBean(misAuditBean);
				status = messageBrodcastDao.updateBrodcastMessage(messageBrodcastBean);
				
			} catch (DataAccessException e) {
				e.printStackTrace();
				throw e;
			}
		 
		return status;
	}

}
