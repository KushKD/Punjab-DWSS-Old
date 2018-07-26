package com.prwss.mis.masters.scheme.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.scheme.struts.SchemeVillagesBean;

public class SchemeHeaderDaoImpl extends HibernateDaoSupport implements SchemeHeaderDao {
	private HibernateTemplate hibernateTemplate;

	private Logger log = Logger.getLogger(SchemeHeaderDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<SchemeHeaderBean> findSchemeHeader(SchemeHeaderBean schemeHeaderBean , List<String> statusList)
			throws DataAccessException {
		List<SchemeHeaderBean> schemeHeaderBeans = new ArrayList<SchemeHeaderBean>();
		    System.out.println("scheme source===in dao="+schemeHeaderBean.getSchemeSource());
		    System.out.println("scheme name===in dao="+schemeHeaderBean.getSchemeName());
		    System.out.println("sw ws===in dao="+schemeHeaderBean.getWatersupply());
		    System.out.println("Scheme Upgraded===in dao="+schemeHeaderBean.getSchemeUpgraded());
		    
		    System.out.println("sub program===in dao="+schemeHeaderBean.getProgId());
		    
		    
		try {
			if(MisUtility.ifEmpty(schemeHeaderBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(SchemeHeaderBean.class);
				List<String> waterSupply = new ArrayList<String>();
				waterSupply.add("CANAL");
				waterSupply.add("TUBEWELL");
				waterSupply.add("TUBEWELLWITHRO");
				waterSupply.add("PERCULATIONWELL");
				waterSupply.add("LIFTINGOFWATERFROMRSDLAKE");
				waterSupply.add("DISTRIBUTION");
				waterSupply.add("HANDPUMP");
				waterSupply.add("PONDS");
				waterSupply.add("ROOFTOPRAINHARVESTING");
				if(MisUtility.ifEmpty(schemeHeaderBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", schemeHeaderBean.getSchemeId()));
				
				if(MisUtility.ifEmpty(schemeHeaderBean.getSchemeName()))
					criteria.add(Restrictions.eq("schemeName", schemeHeaderBean.getSchemeName()));
				
				if(MisUtility.ifEmpty(schemeHeaderBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", schemeHeaderBean.getLocationId()));
				
				if(MisUtility.ifEmpty(schemeHeaderBean.getDistrictId()))
					criteria.add(Restrictions.eq("districtId", schemeHeaderBean.getDistrictId()));
			
			
				if(MisUtility.ifEmpty(schemeHeaderBean.getSchemeSource())){
					if(schemeHeaderBean.getSchemeSource().equals("ws")){
						//System.out.println("-----Water Supply--------"+waterSupply);
						criteria.add(Restrictions.in("schemeSource",waterSupply));
					}
					else{
					System.out.println("----------Sewrage or Improvement-----"+schemeHeaderBean.getSchemeSource());
						criteria.add(Restrictions.eq("schemeSource", schemeHeaderBean.getSchemeSource()));
					}
				}
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				if(MisUtility.ifEmpty(schemeHeaderBean.getSchemeUpgraded())){
					System.out.println("schemeHeaderBean.getSchemeUpgraded()"+schemeHeaderBean.getSchemeUpgraded());
					 criteria.add(Restrictions.eq("schemeUpgraded", schemeHeaderBean.getSchemeUpgraded()));
				}
				
				criteria.addOrder(Property.forName("schemeName").asc() );
				schemeHeaderBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		//System.out.println("schemeHeaderBeans RRR-------"+schemeHeaderBeans );
		return schemeHeaderBeans;
	}

	@Override
	public boolean saveSchemeHeader(SchemeHeaderBean schemeHeaderBean)
			throws DataAccessException {
		try {
			hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.flush();
			getHibernateTemplate().save(schemeHeaderBean);
			hibernateTemplate.flush();
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean saveOrUpdateSchemeHeader(SchemeHeaderBean schemeHeaderBean)
			throws DataAccessException {
		try {
			hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.flush();
			
			System.out.println("scheme header dao----------");
			
			hibernateTemplate.saveOrUpdate(schemeHeaderBean);
			hibernateTemplate.flush();
			
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}
 
	@Override
	public boolean updateSchemeHeader(SchemeHeaderBean schemeHeaderBean)throws DataAccessException {
		try {
			hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.flush();
			hibernateTemplate.update(schemeHeaderBean);
			hibernateTemplate.flush();
			
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public List<SchemeVillagesBean> getSchemevillages(SchemeVillagesBean schemeVillagesBean) throws DataAccessException {
		// TODO Auto-generated method stub
		List<SchemeVillagesBean> schemeHeaderBeans = new ArrayList<SchemeVillagesBean>();

		try{
			if(MisUtility.ifEmpty(schemeVillagesBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(SchemeVillagesBean.class);
				criteria.add(Restrictions.eq("villageId", schemeVillagesBean.getVillageId()));
				criteria.add(Restrictions.eq("scheme_source", schemeVillagesBean.getScheme_source()));
				schemeHeaderBeans = getHibernateTemplate().findByCriteria(criteria);
			}
			
		}catch(DataAccessException e){
			throw e;
		}
		return schemeHeaderBeans;
	}
	
}
