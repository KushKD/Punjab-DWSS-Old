package com.prwss.mis.masters.block.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="mst_block", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class BlockBean implements Serializable, Comparable<BlockBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -1908973205817127070L;

	@Id
	@Column(name="block_id", nullable=false)
	private String blockId;
	
	@Column(name="block_name", nullable=false)
	private String blockName;
	
	@Column(name="district_id", nullable=false)
	private String locationId;
	
	@Column(name="constituency_id", nullable=false)
	private String constituencyId;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public int compareTo(BlockBean o) {
		return o.getBlockId().compareTo(this.getBlockId());
	}
	
	
	

}
