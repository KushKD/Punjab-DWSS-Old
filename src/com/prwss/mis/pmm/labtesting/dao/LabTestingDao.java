/**
 * 
 */
package com.prwss.mis.pmm.labtesting.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.pmm.labtesting.LabTestingBean;

/**
 * @author pjha
 *
 */
public interface LabTestingDao {
	public  List<LabTestingBean> findLabTesting(LabTestingBean labTestingBean ,List<String> statusList) throws DataAccessException;

	public long saveLabTesting(LabTestingBean labTestingBean) throws DataAccessException;

	public boolean saveOrUpdateLabTesting(LabTestingBean labTestingBean) throws DataAccessException;
}
