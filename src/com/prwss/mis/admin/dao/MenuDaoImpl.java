package com.prwss.mis.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class MenuDaoImpl extends HibernateDaoSupport implements MenuDao {

	@SuppressWarnings("unchecked")
	@Override
	public Set<MenuBean> getMenuIds() throws DataAccessException {
		Set<MenuBean> menuBeans = null; 
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(MenuBean.class);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			criteria.add(Restrictions.in("misAuditBean.status",statusList));
			menuBeans = new TreeSet<MenuBean>(getHibernateTemplate().findByCriteria(criteria));
			System.out.println("-----------Size of MenuIds"+menuBeans.size());
		} catch (DataAccessException e) {
			throw e;
		}
		
		return menuBeans;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuBean> getMenuNames(MenuBean menuBean)
			throws DataAccessException {
		System.out.println("Inside Dao Impl");
		 	List<MenuBean> menuBeans = null;
		 	try{
		 		if(MisUtility.ifEmpty(menuBean.getMenuId())){
		 			System.out.println("inside criteria");
		 			DetachedCriteria criteria = DetachedCriteria.forClass(MenuBean.class);
		 			criteria.add(Restrictions.eq("menuId",menuBean.getMenuId()));
		 			menuBeans =  new ArrayList<MenuBean>(getHibernateTemplate().findByCriteria(criteria));
		 		}
		 		
		 	}catch(DataAccessException e){
		 		throw e;
		 	}
		return menuBeans;
	}

}
