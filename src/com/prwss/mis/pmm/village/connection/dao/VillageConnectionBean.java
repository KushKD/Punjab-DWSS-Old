package com.prwss.mis.pmm.village.connection.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="mst_village_connection", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class VillageConnectionBean implements Serializable {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 1259413958643800388L;

	@Id
	@SequenceGenerator(name="seq_village_connection_id", sequenceName="prwss_main.seq_village_connection_id")
	@GeneratedValue(generator="seq_village_connection_id", strategy=GenerationType.AUTO)
	@Column(name="connection_id", nullable=false)
	private long connectionId;
	
	@Column(name="village_id", nullable=false)
	private String villageId;
	
	@Column(name="no_of_water_connection")
	private long noOfWaterConnection;
	
	@Column(name="no_of_standpost")
	private long noOfStandpost;
	
	@Column(name="as_on_date")
	private Date asOnDate;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(long connectionId) {
		this.connectionId = connectionId;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public long getNoOfWaterConnection() {
		return noOfWaterConnection;
	}

	public void setNoOfWaterConnection(long noOfWaterConnection) {
		this.noOfWaterConnection = noOfWaterConnection;
	}

	public long getNoOfStandpost() {
		return noOfStandpost;
	}

	public void setNoOfStandpost(long noOfStandpost) {
		this.noOfStandpost = noOfStandpost;
	}

	public Date getAsOnDate() {
		return asOnDate;
	}

	public void setAsOnDate(Date asOnDate) {
		this.asOnDate = asOnDate;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "\nVillageConnectionBean [connectionId=" + connectionId + ", villageId=" + villageId
				+ ", noOfWaterConnection=" + noOfWaterConnection + ", noOfStandpost=" + noOfStandpost + ", asOnDate="
				+ asOnDate + ", misAuditBean=" + misAuditBean + "]\n";
	}

}
