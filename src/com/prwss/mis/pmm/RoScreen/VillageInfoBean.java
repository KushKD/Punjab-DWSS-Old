package com.prwss.mis.pmm.RoScreen;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
//@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="vw_mst_village_info", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class VillageInfoBean implements Serializable{
	
	private static final long serialVersionUID = 276257926153797041L;
	
	@Id
	@Column(name="village_id")
	private String villageId;
	
	@Column(name="households")
	private Long totalHouseholds;

	
	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public Long getTotalHouseholds() {
		return totalHouseholds;
	}

	public void setTotalHouseholds(Long totalHouseholds) {
		this.totalHouseholds = totalHouseholds;
	}

	@Override
	public String toString() {
		return "VillageInfoBean [villageId=" + villageId + ", totalHouseholds=" + totalHouseholds + "]";
	}
	
}
