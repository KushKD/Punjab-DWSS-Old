package com.prwss.mis.daktask.inwarddak.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.prwss.mis.common.util.MisUtility;

public class InwardDakDaoImpl extends HibernateDaoSupport implements InwardDakDao{
	Logger log=Logger.getLogger(InwardDakDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<InwardDakBean> getInwardDakBeans(InwardDakBean inwardDakBean,
			List<String> statusList) throws DataAccessException {
	 log.debug("in inwarddakdaoimpl");
		
		List<InwardDakBean> InwardDakBeans=null;
		try{
			log.debug("daoimpl= "+inwardDakBean.toString());
			log.debug(" status==  "+MisUtility.ifEmpty(inwardDakBean));
			if(MisUtility.ifEmpty(inwardDakBean))
			{
				
				DetachedCriteria criteria = DetachedCriteria.forClass(InwardDakBean.class);
				if(MisUtility.ifEmpty(inwardDakBean.getLocationId()))
				{
					criteria.add(Restrictions.eq("locationId", inwardDakBean.getLocationId()));
					log.debug("set location id");
				}					
				
				if(MisUtility.ifEmpty(inwardDakBean.getDocumentNo()))
				{
					criteria.add(Restrictions.eq("documentNo", inwardDakBean.getDocumentNo()));
					log.debug("set document Number");
				}
				if(MisUtility.ifEmpty(inwardDakBean.getDocumentRefNo()))
				{
					criteria.add(Restrictions.eq("documentRefNo", inwardDakBean.getDocumentRefNo()));
					log.debug("set Doc ref Number");
				}
				if(MisUtility.ifEmpty(inwardDakBean.getReceiptDate()))
				{
					criteria.add(Restrictions.eq("receiptDate", inwardDakBean.getReceiptDate()));
					log.debug("set receipt date");
				}
				if(MisUtility.ifEmpty(inwardDakBean.getAssignTo()))
					criteria.add(Restrictions.eq("assignTo", inwardDakBean.getAssignTo()));
					
				InwardDakBeans = getHibernateTemplate().findByCriteria(criteria);
				log.debug(InwardDakBeans.size());
			}
		}catch(DataAccessException e)
		{
			throw e;
		}
		return InwardDakBeans;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean saveOrUpdateInwardDakBean(InwardDakBean inwardDakBean)
			throws DataAccessException {
	
		log.debug("daoimplSave= "+inwardDakBean.toString());
			try{
				if(MisUtility.ifEmpty(inwardDakBean.getLocationId()))
				{
					getHibernateTemplate().saveOrUpdate(inwardDakBean);
				}else
				{
					List<InwardDakBean> inbeans=null;
					DetachedCriteria criteria=DetachedCriteria.forClass(InwardDakBean.class);
					criteria.add(Restrictions.eq("documentNo", inwardDakBean.getDocumentNo()));
					inbeans=getHibernateTemplate().findByCriteria(criteria);
					inbeans.get(0).setAssignTo(inwardDakBean.getAssignTo());
					inbeans.get(0).setTargetDate(inwardDakBean.getTargetDate());
					inbeans.get(0).setCompletedDate(inwardDakBean.getCompletedDate());
					getHibernateTemplate().saveOrUpdate(inbeans.get(0));
				}
				
			}catch(DataAccessException e)
			{
				throw e;
			}
			
			return true;
		}	

}
