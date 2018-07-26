/**
 * 
 */
package com.prwss.mis.reports.performa.dao;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.reports.performa.PerformaMasterForm;

/**
 * @author bhsingh
 *
 */
public interface PerformaMasterBo {

	public List<PerformaMasterBean> findPerformaMaster(PerformaMasterForm performaMasterForm,List<String> statusList) throws MISException;

	public boolean updatePerformaMaster(PerformaMasterForm performaMasterForm, MISSessionBean misSessionBean) throws MISException;


}
