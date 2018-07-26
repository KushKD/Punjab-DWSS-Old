package com.prwss.mis.procurement.workspackage.struts;

import java.io.Serializable;

public class PackageWorksGridBean implements Serializable {
	private static final long serialVersionUID = -2434112393640662909L;
	private long id;
	private String componentName;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	
	
}
