package com.prwss.mis.reports.performa.dao;

import java.util.List;

import com.prwss.mis.reports.performa.PerformaFiveYearPlanBean;

public class FiveYearPerformaMaster {

	List<VillagePerformaBean> performaBean;
	List<SchemeSourcePerformaBean> performaSrcBeans;
	List<SchemeBean1> schemeBean;
	List<PerformaFiveYearPlanBean> performaFiveYearPlanBeans;
	/**
	 * @return the performaBean
	 */
	public List<VillagePerformaBean> getPerformaBean() {
		return performaBean;
	}
	/**
	 * @param performaBean the performaBean to set
	 */
	public void setPerformaBean(List<VillagePerformaBean> performaBean) {
		this.performaBean = performaBean;
	}
	/**
	 * @return the performaSrcBeans
	 */
	public List<SchemeSourcePerformaBean> getPerformaSrcBeans() {
		return performaSrcBeans;
	}
	/**
	 * @param performaSrcBeans the performaSrcBeans to set
	 */
	public void setPerformaSrcBeans(List<SchemeSourcePerformaBean> performaSrcBeans) {
		this.performaSrcBeans = performaSrcBeans;
	}
	/**
	 * @return the schemeBean
	 */
	public List<SchemeBean1> getSchemeBean() {
		return schemeBean;
	}
	/**
	 * @param schemeBean the schemeBean to set
	 */
	public void setSchemeBean(List<SchemeBean1> schemeBean) {
		this.schemeBean = schemeBean;
	}
	/**
	 * @return the performaFiveYearPlanBeans
	 */
	public List<PerformaFiveYearPlanBean> getPerformaFiveYearPlanBeans() {
		return performaFiveYearPlanBeans;
	}
	/**
	 * @param performaFiveYearPlanBeans the performaFiveYearPlanBeans to set
	 */
	public void setPerformaFiveYearPlanBeans(List<PerformaFiveYearPlanBean> performaFiveYearPlanBeans) {
		this.performaFiveYearPlanBeans = performaFiveYearPlanBeans;
	}
	
	
	
}
