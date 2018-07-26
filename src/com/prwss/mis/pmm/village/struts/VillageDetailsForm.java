package com.prwss.mis.pmm.village.struts;

import org.apache.struts.validator.ValidatorActionForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author 
 *
 */ 
public class VillageDetailsForm extends ValidatorActionForm {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 6103941486368726944L;
	private String blockId;
	private String villageId;
	private String locationId;
	private Datagrid waterConnectionGrid;
	private Datagrid tariffRateGrid;
	private Datagrid houseHoldGrid;
	private Datagrid populationGrid;
	private Datagrid sewerageGrid;
	private Datagrid ncPcStatusDatagrid;
	private String noOfWaterConnectionId;
	private String noOfStandpostId;
	private String asOnDateId;
	
	
	public String getAsOnDateId() {
		return asOnDateId;
	}
	public void setAsOnDateId(String asOnDateId) {
		this.asOnDateId = asOnDateId;
	}
	public String getNoOfStandpostId() {
		return noOfStandpostId;
	}
	public void setNoOfStandpostId(String noOfStandpostId) {
		this.noOfStandpostId = noOfStandpostId;
	}
	public String getNoOfWaterConnectionId() {
		return noOfWaterConnectionId;
	}
	public void setNoOfWaterConnectionId(String noOfWaterConnectionId) {
		this.noOfWaterConnectionId = noOfWaterConnectionId;
	}
	public Datagrid getNcPcStatusDatagrid() {
		return ncPcStatusDatagrid;
	}
	public void setNcPcStatusDatagrid(Datagrid ncPcStatusDatagrid) {
		this.ncPcStatusDatagrid = ncPcStatusDatagrid;
	}
	public Datagrid getSewerageGrid() {
		return sewerageGrid;
	}
	public void setSewerageGrid(Datagrid sewerageGrid) {
		this.sewerageGrid = sewerageGrid;
	}
	public Datagrid getPopulationGrid() {
		return populationGrid;
	}
	public void setPopulationGrid(Datagrid populationGrid) {
		this.populationGrid = populationGrid;
	}
	public Datagrid getHouseHoldGrid() {
		return houseHoldGrid;
	}
	public void setHouseHoldGrid(Datagrid houseHoldGrid) {
		this.houseHoldGrid = houseHoldGrid;
	}
	public Datagrid getTariffRateGrid() {
		return tariffRateGrid;
	}
	public void setTariffRateGrid(Datagrid tariffRateGrid) {
		this.tariffRateGrid = tariffRateGrid;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public void setWaterConnectionGrid(Datagrid waterConnectionGrid) {
		this.waterConnectionGrid = waterConnectionGrid;
	}
	public Datagrid getWaterConnectionGrid() {
		return waterConnectionGrid;
	}
	@Override
	public String toString() {
		return "VillageDetailsForm [blockId=" + blockId + ", villageId="
				+ villageId + ", locationId=" + locationId
				+ ", waterConnectionGrid=" + waterConnectionGrid
				+ ", tariffRateGrid=" + tariffRateGrid + ", houseHoldGrid="
				+ houseHoldGrid + ", populationGrid=" + populationGrid + "]";
	}
	 

}
