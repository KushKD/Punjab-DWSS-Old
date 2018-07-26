/**
 * 
 */
package com.prwss.mis.pmm.DSRBuilding.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author pjha
 *
 */
@Entity
@Table(name="pmm_dsr_works", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class DSRBuildingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9002248861477309243L;
	
	@Id	
	@Column(name="scheme_id", nullable=false)
	private String schemeId;
	
	@Column(name="village_id", nullable=false)
	private String villageId;
	
	@Column(name="dsr_date")
	private Date  dsrDate;
	
	@Column(name="location_id")
	private String  locationId;
	@Column(name="estate_pipeline_length")
	private long estatePipelineLength;
	@Column(name="estate_pipeline_cost")
	private double estatePipelineCost;
	@Column(name="headworks_pumpchamber_cost")
	private double headworksPumpchamberCost;
	@Column(name="headworks_head")
	private String headworksHead;
	@Column(name="headworks_discharge")
	private String headworksDischarge;
	@Column(name="headworks_cost")
	private double headworksCost;
	@Column(name="ohsr_capacity")
	private String ohsrCapacity;
	@Column(name="ohsr_cost")
	private double ohsrCost;
	@Column(name="internal_pipeline_length")
	private String  internalPipelineLength;
	@Column(name="internal_pipeline_cost")
	private double  internalPipelineCost;
	@Column(name="sanitary_owc_ewc_quantity")
	private long  sanitaryOwcEwcQuantity;
	@Column(name="sanitary_owc_ewc_cost")
	private double  sanitaryOwcEwcCost;
	@Column(name="sanitary_washbasin_quantity")
	private long  sanitaryWashbasinQuantity;
	@Column(name="sanitary_washbasin_cost")
	private double  sanitaryWashbasinCost;
	@Column(name="sanitary_urinal_quantity")
	private long  sanitaryUrinalQuantity;
	@Column(name="sanitary_urinal_cost")
	private double  sanitaryUrinalCost;
	@Column(name="sewerage_length")
	private String sewerageLength;
	@Column(name="sewerage_cost")
	private double sewerageCost;
	@Column(name="storm_sewerage_length")
	private String stormSewerageLength;
	@Column(name="storm_sewerage_cost")
	private double stormSewerageCost;
	@Column(name="rainwater_cost")
	private double rainwaterCost;
	@Column(name="firesystem_cost")
	private double firesystemCost;
	@Column(name="windowac_quantity")
	private long windowacQuantity;
	@Column(name="windowac_cost")
	private double windowacCost;
	@Column(name="splitac_quantity")
	private long splitacQuantity;
	@Column(name="splitac_cost")
	private double splitacCost;
	@Column(name="acplant_cost")
	private double acplantCost;
	@Column(name="departmental_charges")
	private double departmentalCharges;
	@Column(name="contigency_charges")
	private double contigencyCharges;
	@Column(name="total_cost")
	private BigDecimal totalCost;


	@Embedded
	private MISAuditBean misAuditBean;


	public String getSchemeId() {
		return schemeId;
	}


	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}


	public String getVillageId() {
		return villageId;
	}


	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}


	public Date getDsrDate() {
		return dsrDate;
	}


	public void setDsrDate(Date dsrDate) {
		this.dsrDate = dsrDate;
	}


	public String getLocationId() {
		return locationId;
	}


	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}


	public long getEstatePipelineLength() {
		return estatePipelineLength;
	}


	public void setEstatePipelineLength(long estatePipelineLength) {
		this.estatePipelineLength = estatePipelineLength;
	}


	public double getEstatePipelineCost() {
		return estatePipelineCost;
	}


	public void setEstatePipelineCost(double estatePipelineCost) {
		this.estatePipelineCost = estatePipelineCost;
	}


	public double getHeadworksPumpchamberCost() {
		return headworksPumpchamberCost;
	}


	public void setHeadworksPumpchamberCost(double headworksPumpchamberCost) {
		this.headworksPumpchamberCost = headworksPumpchamberCost;
	}


	public String getHeadworksHead() {
		return headworksHead;
	}


	public void setHeadworksHead(String headworksHead) {
		this.headworksHead = headworksHead;
	}


	public String getHeadworksDischarge() {
		return headworksDischarge;
	}


	public void setHeadworksDischarge(String headworksDischarge) {
		this.headworksDischarge = headworksDischarge;
	}


	public double getHeadworksCost() {
		return headworksCost;
	}


	public void setHeadworksCost(double headworksCost) {
		this.headworksCost = headworksCost;
	}


	public String getOhsrCapacity() {
		return ohsrCapacity;
	}


	public void setOhsrCapacity(String ohsrCapacity) {
		this.ohsrCapacity = ohsrCapacity;
	}


	public double getOhsrCost() {
		return ohsrCost;
	}


	public void setOhsrCost(double ohsrCost) {
		this.ohsrCost = ohsrCost;
	}


	public String getInternalPipelineLength() {
		return internalPipelineLength;
	}


	public void setInternalPipelineLength(String internalPipelineLength) {
		this.internalPipelineLength = internalPipelineLength;
	}


	public double getInternalPipelineCost() {
		return internalPipelineCost;
	}


	public void setInternalPipelineCost(double internalPipelineCost) {
		this.internalPipelineCost = internalPipelineCost;
	}


	public long getSanitaryOwcEwcQuantity() {
		return sanitaryOwcEwcQuantity;
	}


	public void setSanitaryOwcEwcQuantity(long sanitaryOwcEwcQuantity) {
		this.sanitaryOwcEwcQuantity = sanitaryOwcEwcQuantity;
	}


	public double getSanitaryOwcEwcCost() {
		return sanitaryOwcEwcCost;
	}


	public void setSanitaryOwcEwcCost(double sanitaryOwcEwcCost) {
		this.sanitaryOwcEwcCost = sanitaryOwcEwcCost;
	}


	public long getSanitaryWashbasinQuantity() {
		return sanitaryWashbasinQuantity;
	}


	public void setSanitaryWashbasinQuantity(long sanitaryWashbasinQuantity) {
		this.sanitaryWashbasinQuantity = sanitaryWashbasinQuantity;
	}


	public double getSanitaryWashbasinCost() {
		return sanitaryWashbasinCost;
	}


	public void setSanitaryWashbasinCost(double sanitaryWashbasinCost) {
		this.sanitaryWashbasinCost = sanitaryWashbasinCost;
	}


	public long getSanitaryUrinalQuantity() {
		return sanitaryUrinalQuantity;
	}


	public void setSanitaryUrinalQuantity(long sanitaryUrinalQuantity) {
		this.sanitaryUrinalQuantity = sanitaryUrinalQuantity;
	}


	public double getSanitaryUrinalCost() {
		return sanitaryUrinalCost;
	}


	public void setSanitaryUrinalCost(double sanitaryUrinalCost) {
		this.sanitaryUrinalCost = sanitaryUrinalCost;
	}


	public String getSewerageLength() {
		return sewerageLength;
	}


	public void setSewerageLength(String sewerageLength) {
		this.sewerageLength = sewerageLength;
	}


	public double getSewerageCost() {
		return sewerageCost;
	}


	public void setSewerageCost(double sewerageCost) {
		this.sewerageCost = sewerageCost;
	}


	public String getStormSewerageLength() {
		return stormSewerageLength;
	}


	public void setStormSewerageLength(String stormSewerageLength) {
		this.stormSewerageLength = stormSewerageLength;
	}


	public double getStormSewerageCost() {
		return stormSewerageCost;
	}


	public void setStormSewerageCost(double stormSewerageCost) {
		this.stormSewerageCost = stormSewerageCost;
	}


	public double getRainwaterCost() {
		return rainwaterCost;
	}


	public void setRainwaterCost(double rainwaterCost) {
		this.rainwaterCost = rainwaterCost;
	}


	public double getFiresystemCost() {
		return firesystemCost;
	}


	public void setFiresystemCost(double firesystemCost) {
		this.firesystemCost = firesystemCost;
	}


	public long getWindowacQuantity() {
		return windowacQuantity;
	}


	public void setWindowacQuantity(long windowacQuantity) {
		this.windowacQuantity = windowacQuantity;
	}


	public double getWindowacCost() {
		return windowacCost;
	}


	public void setWindowacCost(double windowacCost) {
		this.windowacCost = windowacCost;
	}


	public long getSplitacQuantity() {
		return splitacQuantity;
	}


	public void setSplitacQuantity(long splitacQuantity) {
		this.splitacQuantity = splitacQuantity;
	}


	public double getSplitacCost() {
		return splitacCost;
	}


	public void setSplitacCost(double splitacCost) {
		this.splitacCost = splitacCost;
	}


	public double getAcplantCost() {
		return acplantCost;
	}


	public void setAcplantCost(double acplantCost) {
		this.acplantCost = acplantCost;
	}


	public double getDepartmentalCharges() {
		return departmentalCharges;
	}


	public void setDepartmentalCharges(double departmentalCharges) {
		this.departmentalCharges = departmentalCharges;
	}


	public double getContigencyCharges() {
		return contigencyCharges;
	}


	public void setContigencyCharges(double contigencyCharges) {
		this.contigencyCharges = contigencyCharges;
	}


	public BigDecimal getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}


	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}


	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	
	

}
