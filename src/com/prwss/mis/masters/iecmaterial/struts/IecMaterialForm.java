package com.prwss.mis.masters.iecmaterial.struts;

import org.apache.struts.validator.ValidatorForm;

public class IecMaterialForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5265859682899807751L;
	private String iecMaterialCode;
	private String iecMaterialName;
	private String iecMaterialDescription;
	
	public String getIecMaterialCode() {
		return iecMaterialCode;
	}
	public void setIecMaterialCode(String iecMaterialCode) {
		this.iecMaterialCode = iecMaterialCode;
	}
	public String getIecMaterialName() {
		return iecMaterialName;
	}
	public void setIecMaterialName(String iecMaterialName) {
		this.iecMaterialName = iecMaterialName;
	}
	public String getIecMaterialDescription() {
		return iecMaterialDescription;
	}
	public void setIecMaterialDescription(String iecMaterialDescription) {
		this.iecMaterialDescription = iecMaterialDescription;
	}
	
}
