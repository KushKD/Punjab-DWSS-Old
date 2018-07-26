package com.prwss.mis.pmm.RoScreenDao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.RoScreen.RoScreenBean;
import com.prwss.mis.pmm.RoScreen.VillageInfoBean;
import com.prwss.mis.pmm.RoScreenStruts.RoScreenDto;

public class RoScreenDaoImpl extends HibernateDaoSupport implements RoScreenDao{
	
	private Logger log = Logger.getLogger(RoScreenDaoImpl.class);
	
//---------------------------------------------------------------------------------------------------------------------------------------------
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoScreenDto> getDivisionNameAndId() throws DataAccessException {

		List<RoScreenDto> roScreenDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(RoScreenBean.class);
			criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)));

			criteria.setProjection(Projections.projectionList()
					.add(Projections.distinct(Projections.property("division")),"division")
					.add(Projections.property("divisionName"),"divisionName"));

			criteria.setResultTransformer(Transformers.aliasToBean(RoScreenDto.class));
			roScreenDto = getHibernateTemplate().findByCriteria(criteria);
			
			System.out.println(roScreenDto.toString());

		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return roScreenDto;
	}
//---------------------------------------------------------------------------------------------------------------------------------------------
	
		@SuppressWarnings("unchecked")
		@Override
		public List<RoScreenDto> getSubDivisionNameAndId(String divisionId) throws DataAccessException {

			List<RoScreenDto> roScreenDto = null;
			try {
				DetachedCriteria criteria = DetachedCriteria.forClass(RoScreenBean.class);
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)))
						.add(Restrictions.eq("division", divisionId));

				criteria.setProjection(Projections.projectionList()
						.add(Projections.distinct(Projections.property("subDivision")),"subDivision")
						.add(Projections.property("subDivisionName"),"subDivisionName"));
				
				criteria.setResultTransformer(Transformers.aliasToBean(RoScreenDto.class));
				roScreenDto = getHibernateTemplate().findByCriteria(criteria);
				
				System.out.println(roScreenDto.toString());

			} catch (DataAccessException e) {
				e.printStackTrace();
				log.error(e.getLocalizedMessage(), e);
				throw e;
			}
			return roScreenDto;
		}
//---------------------------------------------------------------------------------------------------------------------------------------------
		
		@SuppressWarnings("unchecked")
		@Override
		public List<RoScreenDto> getVillageNameAndId(String divisionId,String subDivisionId) throws DataAccessException {

			List<RoScreenDto> roScreenDto = null;
			try {
				DetachedCriteria criteria = DetachedCriteria.forClass(RoScreenBean.class);
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)))
						.add(Restrictions.eq("division", divisionId))
						.add(Restrictions.eq("subDivision", subDivisionId));

				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("village"),"village")
						.add(Projections.property("villageName"),"villageName"));
				
				criteria.setResultTransformer(Transformers.aliasToBean(RoScreenDto.class));
				roScreenDto = getHibernateTemplate().findByCriteria(criteria);
				
				System.out.println(roScreenDto.toString());

			} catch (DataAccessException e) {
				e.printStackTrace();
				log.error(e.getLocalizedMessage(), e);
				throw e;
			}
			return roScreenDto;
		}
//---------------------------------------------------------------------------------------------------------------------------------------------
		
		@SuppressWarnings("unchecked")
		@Override
		public String getCapacityOfRO(String divisionId, String subDivisionId, String villageId) throws DataAccessException {
			
			List<RoScreenDto> roScreenDto = null;
			
			try {
				DetachedCriteria criteria = DetachedCriteria.forClass(RoScreenBean.class);
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)))
						.add(Restrictions.eq("division", divisionId))
						.add(Restrictions.eq("subDivision", subDivisionId))
						.add(Restrictions.eq("village", villageId)); 
			
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("capacityOfRO"),"capacityOfRO"));
				
				criteria.setResultTransformer(Transformers.aliasToBean(RoScreenDto.class));
				roScreenDto = getHibernateTemplate().findByCriteria(criteria);
				
				System.out.println(roScreenDto.toString());

			} catch (DataAccessException e) {
				e.printStackTrace();
				log.error(e.getLocalizedMessage(), e);
				throw e;
			}
			return roScreenDto.get(0).getCapacityOfRO();
		}
//---------------------------------------------------------------------------------------------------------------------------------------------

		@SuppressWarnings("unchecked")
		@Override
		public String getSeasonalRO(String divisionId, String subDivisionId, String villageId) throws DataAccessException {
			
			List<RoScreenDto> roScreenDto = null;
			
			try {
				DetachedCriteria criteria = DetachedCriteria.forClass(RoScreenBean.class);
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)))
						.add(Restrictions.eq("division", divisionId))
						.add(Restrictions.eq("subDivision", subDivisionId))
						.add(Restrictions.eq("village", villageId)); 
			
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("seasonalRO"),"seasonalRO"));
				
				criteria.setResultTransformer(Transformers.aliasToBean(RoScreenDto.class));
				roScreenDto = getHibernateTemplate().findByCriteria(criteria);
				
				System.out.println(roScreenDto.toString());

			} catch (DataAccessException e) {
				e.printStackTrace();
				log.error(e.getLocalizedMessage(), e);
				throw e;
			}
			return roScreenDto.get(0).getSeasonalRO();
		}
//---------------------------------------------------------------------------------------------------------------------------------------------
		
		@SuppressWarnings("unchecked")
		@Override
		public String getProgram(String divisionId, String subDivisionId, String villageId) throws DataAccessException {
			
			List<RoScreenDto> roScreenDto = null;
			
			try {
				DetachedCriteria criteria = DetachedCriteria.forClass(RoScreenBean.class);
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)))
						.add(Restrictions.eq("division", divisionId))
						.add(Restrictions.eq("subDivision", subDivisionId))
						.add(Restrictions.eq("village", villageId)); 
			
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("headProgramme"),"headProgramme"));
				
				criteria.setResultTransformer(Transformers.aliasToBean(RoScreenDto.class));
				roScreenDto = getHibernateTemplate().findByCriteria(criteria);
				
				System.out.println(roScreenDto.toString());

			} catch (DataAccessException e) {
				e.printStackTrace();
				log.error(e.getLocalizedMessage(), e);
				throw e;
			}
			return roScreenDto.get(0).getHeadProgramme();
		}
//---------------------------------------------------------------------------------------------------------------------------------------------
		
		@SuppressWarnings("unchecked")
		@Override
		public String getExecutingAgency(String divisionId, String subDivisionId, String villageId) throws DataAccessException {
			
			List<RoScreenDto> roScreenDto = null;
			
			try {
				DetachedCriteria criteria = DetachedCriteria.forClass(RoScreenBean.class);
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)))
						.add(Restrictions.eq("division", divisionId))
						.add(Restrictions.eq("subDivision", subDivisionId))
						.add(Restrictions.eq("village", villageId)); 
			
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("executingAgency"),"executingAgency"));
				
				criteria.setResultTransformer(Transformers.aliasToBean(RoScreenDto.class));
				roScreenDto = getHibernateTemplate().findByCriteria(criteria);
				
				System.out.println(roScreenDto.toString());

			} catch (DataAccessException e) {
				e.printStackTrace();
				log.error(e.getLocalizedMessage(), e);
				throw e;
			}
			return roScreenDto.get(0).getExecutingAgency();
		}
//---------------------------------------------------------------------------------------------------------------------------------------------

		/*@SuppressWarnings("unchecked")
		@Override
		public String getOnMDate(String divisionId, String subDivisionId, String villageId) throws DataAccessException {
			
			List<RoScreenDto> roScreenDto = null;
			
			try {
				DetachedCriteria criteria = DetachedCriteria.forClass(RoScreenBean.class);
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)))
						.add(Restrictions.eq("division", divisionId))
						.add(Restrictions.eq("subDivision", subDivisionId))
						.add(Restrictions.eq("village", villageId)); 
			
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("dateCompOandM"),"dateCompOandM"));
				
				criteria.setResultTransformer(Transformers.aliasToBean(RoScreenDto.class));
				roScreenDto = getHibernateTemplate().findByCriteria(criteria);
				
				System.out.println(roScreenDto.toString());

			} catch (DataAccessException e) {
				e.printStackTrace();
				log.error(e.getLocalizedMessage(), e);
				throw e;
			}
			return String.valueOf(roScreenDto.get(0).getDateCompOandM());
		}*/
//---------------------------------------------------------------------------------------------------------------------------------------------

		/*@SuppressWarnings("unchecked")
		@Override
		public String getAdminApproveCost(String divisionId, String subDivisionId, String villageId) throws DataAccessException {
			
			List<RoScreenDto> roScreenDto = null;
			
			try {
				DetachedCriteria criteria = DetachedCriteria.forClass(RoScreenBean.class);
				criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)))
						.add(Restrictions.eq("division", divisionId))
						.add(Restrictions.eq("subDivision", subDivisionId))
						.add(Restrictions.eq("village", villageId)); 
			
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("adminAppCost"),"adminAppCost"));
				
				criteria.setResultTransformer(Transformers.aliasToBean(RoScreenDto.class));
				roScreenDto = getHibernateTemplate().findByCriteria(criteria);
				
				System.out.println(roScreenDto.toString());

			} catch (DataAccessException e) {
				e.printStackTrace();
				log.error(e.getLocalizedMessage(), e);
				throw e;
			}
			return String.valueOf(roScreenDto.get(0).getAdminAppCost());
		}*/
//---------------------------------------------------------------------------------------------------------------------------------------------
		
		@SuppressWarnings("unchecked")
		public List<RoScreenBean> fetchRoConnectionBean(String division, String subDivision, String village) throws DataAccessException {
			
			List<RoScreenBean> roScreenBean = null;
			try{
				DetachedCriteria criteria = DetachedCriteria.forClass(RoScreenBean.class);
					criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)))
							.add(Restrictions.eq("division", division))
							.add(Restrictions.eq("subDivision", subDivision))
							.add(Restrictions.eq("village", village)); 
					
					roScreenBean = getHibernateTemplate().findByCriteria(criteria);
						
			}catch (DataAccessException e) {
				e.printStackTrace();
				log.error(e.getLocalizedMessage(), e);
				throw e;
			}
			return roScreenBean;
		}
//---------------------------------------------------------------------------------------------------------------------------------------------		
	
		public boolean saveRoConnectionDetails(RoScreenBean roScreenBean) throws DataAccessException {
			
			try{
				
				if(MisUtility.ifEmpty(roScreenBean)){
					getHibernateTemplate().save(roScreenBean);
				}
			}catch(DataAccessException e){
				e.printStackTrace();
				log.error(e.getLocalizedMessage(),e);
				throw e;
			}	
				return true;
		}
//---------------------------------------------------------------------------------------------------------------------------------------------		

		public int update(RoScreenBean roScreenBean, Integer enteredBy) throws DataAccessException {
			Session session=null;
			int result=0;
			try{
				System.out.println("hcskhdfugduhdj-----------");
				System.out.println("roScreenBean----------------------->"+roScreenBean.toString());
					//getHibernateTemplate().update(roScreenBean);
				session=getSession();
				
				String hql="UPDATE RoScreenBean e set  e.activeFlag=:active_flag WHERE e.roConnectionId = :ro_connection_id";
				
				Query query = session.createQuery(hql);
				query.setParameter("active_flag", 0);
				//query.setParameter("lst_updated_user", Long.parseLong(Integer.toString(enteredBy)));
				//query.setParameter("lst_updated_date", MisUtility.now("yyyy-mm-dd"));
				query.setParameter("ro_connection_id",roScreenBean.getRoConnectionId());
				
				 result=query.executeUpdate();
				
				
			}catch(DataAccessException e){
				e.printStackTrace();
				log.error(e.getLocalizedMessage(),e);
				throw e;
			}	
				return result;
		}
//---------------------------------------------------------------------------------------------------------------------------------------------		

		@SuppressWarnings("unchecked")
		@Override
		public String getHouseholds(String villageId) throws DataAccessException {
			
			List<RoScreenDto> roScreenDto = null;
			
			try {
				DetachedCriteria criteria = DetachedCriteria.forClass(VillageInfoBean.class);
				criteria.add(Restrictions.eq("villageId", villageId)); 
			
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("totalHouseholds"),"noHHsVillage"));
				
				criteria.setResultTransformer(Transformers.aliasToBean(RoScreenDto.class));
				roScreenDto = getHibernateTemplate().findByCriteria(criteria);
				
				System.out.println(roScreenDto.toString());

			} catch (DataAccessException e) {
				e.printStackTrace();
				log.error(e.getLocalizedMessage(), e);
				throw e;
			}
			return String.valueOf(roScreenDto.get(0).getNoHHsVillage());
		}
//---------------------------------------------------------------------------------------------------------------------------------------------
		
		@SuppressWarnings("unchecked")
		@Override
		public List<RoScreenBean> find(RoScreenBean roScreenBean,
				List<String> statusList) throws DataAccessException {
			List<RoScreenBean> roScreenBeans = new  ArrayList<RoScreenBean>();
			try {
				if(MisUtility.ifEmpty(roScreenBean)){
					
					DetachedCriteria criteria = DetachedCriteria.forClass(RoScreenBean.class);
						criteria.add(Restrictions.eq("activeFlag", Integer.parseInt(MISConstants.ONE)))
								.add(Restrictions.eq("division", roScreenBean.getDivision()))
								.add(Restrictions.eq("subDivision", roScreenBean.getSubDivision()))
								.add(Restrictions.eq("village", roScreenBean.getVillage()));
						
						roScreenBeans =  getHibernateTemplate().findByCriteria(criteria);
				}
			} catch (DataAccessException e) {
				e.printStackTrace();
				log.error(e.getLocalizedMessage(), e);
				throw e;
			}
			return roScreenBeans;
		}

}
