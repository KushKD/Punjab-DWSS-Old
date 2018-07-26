package com.prwss.mis.masters.scheme;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.prwss.mis.masters.scheme.dao.SchemeHeaderBean;
import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;

public class SchemeMasterBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SchemeHeaderBean> schemeHeaderBeans = null;

	public List<SchemeHeaderBean> getSchemeHeaderBeans() {
		return schemeHeaderBeans;
	}

	public void setSchemeHeaderBeans(List<SchemeHeaderBean> schemeHeaderBeans) {
		this.schemeHeaderBeans = schemeHeaderBeans;
	}

	public List<SchemeVillageBean> getSchemeVillageBeans() {
		return schemeVillageBeans;
	}

	public void setSchemeVillageBeans(List<SchemeVillageBean> schemeVillageBeans) {
		this.schemeVillageBeans = schemeVillageBeans;
	}

	private List<SchemeVillageBean> schemeVillageBeans = null;

}
