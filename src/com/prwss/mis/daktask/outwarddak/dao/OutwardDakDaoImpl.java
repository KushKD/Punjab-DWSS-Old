package com.prwss.mis.daktask.outwarddak.dao;


import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.prwss.mis.common.util.MisUtility;

public class OutwardDakDaoImpl extends HibernateDaoSupport implements OutwardDakDao{
	Logger log=Logger.getLogger(OutwardDakDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<OutwardDakBean> getOutwardDakBeans(OutwardDakBean outwardDakBean,
			List<String> statusList) throws DataAccessException {
	
		
		List<OutwardDakBean> outwardDakBeans=null;
		try{
			log.debug("daoimpl= "+outwardDakBean.toString());
			if(MisUtility.ifEmpty(outwardDakBean))
			{
				DetachedCriteria criteria = DetachedCriteria.forClass(OutwardDakBean.class);
				
				if(MisUtility.ifEmpty(outwardDakBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", outwardDakBean.getLocationId()));
				if(MisUtility.ifEmpty(outwardDakBean.getDocumentRefNo()))
					criteria.add(Restrictions.eq("documentRefNo", outwardDakBean.getDocumentRefNo()));
				if(MisUtility.ifEmpty(outwardDakBean.getDocumentType()))
					criteria.add(Restrictions.eq("documentType", outwardDakBean.getDocumentType()));
				if(MisUtility.ifEmpty(outwardDakBean.getDispatchDate()))
					criteria.add(Restrictions.eq("dispatchDate", outwardDakBean.getDispatchDate()));
				outwardDakBeans = getHibernateTemplate().findByCriteria(criteria);				
			}
		}catch(DataAccessException e)
		{
			throw e;
		}
		return outwardDakBeans;
	}

	@Override
	public boolean saveOrUpdateOutwardDakBean(OutwardDakBean outwardDakBean)
			throws DataAccessException {
	
		log.debug("daoimplSave= "+outwardDakBean.toString());
			try{
				getHibernateTemplate().saveOrUpdate(outwardDakBean);
			}catch(DataAccessException e)
			{
				throw e;
			}
			
			return true;
		}

	@Override
	public boolean save(OutwardDakBean outwardDakBean) throws DataAccessException {
		
		
		try {
			getHibernateTemplate().save(outwardDakBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}	

}

