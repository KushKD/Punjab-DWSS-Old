/**
 * 
 */
package com.prwss.mis.masters.subcomponent.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

/**
 * @author vgadiraju
 *
 */
public interface SubComponentDao {
	

	public  List<SubComponentBean> findSubComponent(SubComponentBean subComponentBean, List<String> statusList) throws DataAccessException;
	public  List<SubComponentBean> findSubComponent(List<String> subComponentIdList) throws DataAccessException;
	public boolean saveSubComponent(SubComponentBean subComponentBean) throws DataAccessException;
	public boolean updateSubComponent(SubComponentBean subComponentBean) throws DataAccessException;
	public boolean deleteSubComponent(SubComponentBean subComponentBean) throws DataAccessException;
	public Set<SubComponentBean> getDistinctSubComponentCodes(String componentId) throws DataAccessException;
	public boolean updateSubComponent(List<SubComponentBean> subComponentBeans) throws DataAccessException;

}
