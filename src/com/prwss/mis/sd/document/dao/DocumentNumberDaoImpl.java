package com.prwss.mis.sd.document.dao;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.prwss.mis.common.util.MisUtility;


import org.apache.log4j.Logger;


public class DocumentNumberDaoImpl extends HibernateDaoSupport implements DocumentNumberDAO  {
	Logger log = Logger.getLogger(DocumentNumberDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentNumberBean> getDocumentNumberBeans(
			DocumentNumberBean documentNumberBean) throws DataAccessException {
		List <DocumentNumberBean> documentNumebrBeans=null;
		try {
			if(MisUtility.ifEmpty(documentNumberBean))
			{
				log.debug("location----- "+documentNumberBean.getLocationId());
				log.debug("type----- "+documentNumberBean.getDocumentType());
				DetachedCriteria criteria = DetachedCriteria.forClass(DocumentNumberBean.class);			 
				
				if(MisUtility.ifEmpty(documentNumberBean.getLocationId()))
				criteria.add(Restrictions.eq("locationId", documentNumberBean.getLocationId()));
				
				if(MisUtility.ifEmpty(documentNumberBean.getDocumentType()))
				criteria.add(Restrictions.eq("documentType",documentNumberBean.getDocumentType()));
				
				documentNumebrBeans=getHibernateTemplate().findByCriteria(criteria);			
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		
		return documentNumebrBeans;
	}

	@Override
	public boolean saveOrUpdateDocumentNumberBeans(
			 DocumentNumberBean DB)
			throws DataAccessException {
		try{
			DB.setLastNumber(DB.getLastNumber()+1);			
			getHibernateTemplate().saveOrUpdate(DB);
		}catch(DataAccessException e)
		{
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
			
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentNumberBean> getDocumentNumber(
			DocumentNumberBean documentNumberBean) throws DataAccessException {
		List <DocumentNumberBean> documentNumebrBeans=null;
		try {
			if(MisUtility.ifEmpty(documentNumberBean))
			{
//			log.debug("location----- "+documentNumberBean.getLocationId());
				log.debug("type----- "+documentNumberBean.getDocumentType());
				DetachedCriteria criteria = DetachedCriteria.forClass(DocumentNumberBean.class);			 
//			criteria.add(Restrictions.eq("locationId", documentNumberBean.getLocationId()));
				criteria.add(Restrictions.eq("documentType",documentNumberBean.getDocumentType() ));
				documentNumebrBeans=getHibernateTemplate().findByCriteria(criteria);	
				System.out.println("Document"+documentNumebrBeans);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		
		return documentNumebrBeans;
	}
	

}
