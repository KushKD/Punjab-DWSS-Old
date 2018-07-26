package com.prwss.mis.admin.struts;

import org.apache.struts.validator.ValidatorForm;

public class ConstituencyForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;
		private String constituencyId;
		private String constituencyName;
		private String districtId;
		private String[] constituencyIds;
		
		public String[] getConstituencyIds() {
			return constituencyIds;
		}
		public void setConstituencyIds(String[] constituencyIds) {
			this.constituencyIds = constituencyIds;
		}
		public String getConstituencyId() {
			return constituencyId;
		}
		public void setConstituencyId(String constituencyId) {
			this.constituencyId = constituencyId;
		}
		public String getConstituencyName() {
			return constituencyName;
		}
		public void setConstituencyName(String constituencyName) {
			this.constituencyName = constituencyName;
		}
		public String getDistrictId() {
			return districtId;
		}
		public void setDistrictId(String districtId) {
			this.districtId = districtId;
		}
	
}
