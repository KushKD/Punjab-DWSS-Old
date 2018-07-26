package com.prwss.mis.admin;

import java.util.List;

import com.prwss.mis.admin.struts.MessageBrodcastForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
 

public interface MessageBrodcastBO {
	
	public long saveBrodcastedMessage(MessageBrodcastBean messageBrodcastBean,MISSessionBean misSessionBean) throws MISException;
	public List<MessageBrodcastBean> findBrodcastedMessages(MessageBrodcastForm messageBrodcastForm,List<String> statusList)throws MISException;
	public boolean updateMesageBrodcast(MessageBrodcastForm messageBrodcastForm,MISSessionBean misSessionBean )throws MISException;

}
