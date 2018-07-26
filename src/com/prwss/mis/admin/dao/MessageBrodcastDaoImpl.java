package com.prwss.mis.admin.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.admin.MessageBrodcastBean;
import com.prwss.mis.common.util.MisUtility;

public class MessageBrodcastDaoImpl extends HibernateDaoSupport implements MessageBrodcastDao{

	@Override
	public long saveBrodcastMessage(MessageBrodcastBean messageBrodcastBean)
			throws DataAccessException {
		 long status = 0;
		try {
			//System.out.println("Inside DAO IMPL");
			status = (Long) getHibernateTemplate().save(messageBrodcastBean);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		
		return status;
		 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageBrodcastBean> findMessageDeatil(
			MessageBrodcastBean messageBrodcastBean, List<String> statusList)
			throws DataAccessException {
		List<MessageBrodcastBean> messageBrodcastBeans = new ArrayList<MessageBrodcastBean>();
		try{
			if(MisUtility.ifEmpty(messageBrodcastBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(MessageBrodcastBean.class);
				
				if(MisUtility.ifEmpty(messageBrodcastBean.getMessageId())){
					criteria.add(Restrictions.eq("messageId", messageBrodcastBean.getMessageId()));
				}
			
				if(!MisUtility.ifEmpty(statusList)){
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				}
				
				messageBrodcastBeans = getHibernateTemplate().findByCriteria(criteria);
			}
				
			
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		
		return messageBrodcastBeans;
	}

	@Override
	public boolean updateBrodcastMessage(MessageBrodcastBean messageBrodcastBean)
			throws DataAccessException {
		 
		 try {
				System.out.println("Inside DAO IMPL update");
				   getHibernateTemplate().update(messageBrodcastBean);
			} catch (DataAccessException e) {
				e.printStackTrace();
				throw e;
			}
			
			return true;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<MessageBrodcastBean> findMessages() throws DataAccessException {
		List<MessageBrodcastBean> messageBrodcastBeans = new ArrayList<MessageBrodcastBean>();
		try{
			DetachedCriteria criteria = DetachedCriteria.forClass(MessageBrodcastBean.class);
			
			criteria.add(Expression.ge("expiryDate", new Date()));
			messageBrodcastBeans = getHibernateTemplate().findByCriteria(criteria);
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return messageBrodcastBeans;
	}

}
