package com.prwss.mis.tender.responsive;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.tender.dao.TenderHeaderBean;

@Entity
@Table(name="vw_responsive_tenders", schema=MISConstants.MIS_DB_SCHEMA_NAME)
@org.hibernate.annotations.Entity(dynamicUpdate=true, selectBeforeUpdate=true)
public class ResponsiveTenderBean implements Serializable, Comparable<ResponsiveTenderBean>  {



		private static final long serialVersionUID = 1L;


		@Id
		@Column(name="tender_id", nullable=false)
		private String tenderId;
		
	
		@Column(name="tender_type")
		private String tenderType;
		
		@Column(name="location_id")
		private String locationId;
			
		@Column(name="scheme_code")
		private String schemeCode;
				
		@Column(name="status")
		private String status;

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getSchemeCode() {
			return schemeCode;
		}

		public void setSchemeCode(String schemeCode) {
			this.schemeCode = schemeCode;
		}

		public String getTenderId() {
			return tenderId;
		}

		public void setTenderId(String tenderId) {
			this.tenderId = tenderId;
		}

		public String getTenderType() {
			return tenderType;
		}

		public void setTenderType(String tenderType) {
			this.tenderType = tenderType;
		}

		public String getLocationId() {
			return locationId;
		}

		public void setLocationId(String locationId) {
			this.locationId = locationId;
		}


		@Override
		public String toString() {
			return "ResponsiveTenderBean [tenderId=" + tenderId 
					+ ", tenderType=" + tenderType 
					+ ", locationId=" + locationId
					+ "]";
		}

		@Override
		public int compareTo(ResponsiveTenderBean o) {
			return this.tenderId.compareTo(o.getTenderId());
		}

		
		

	}


