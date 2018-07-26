/**
 * 
 */
package com.prwss.mis.pmm.labtesting;

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

/**
 * @author pjha
 *
 */
@Entity
@Table(name="pmm_lab_testing", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class LabTestingBean implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1505286745067903359L;
	
	
	@Id
	@GeneratedValue(generator = "seq_pmm_labtesting", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_pmm_labtesting", sequenceName = "prwss_main.seq_pmm_labtesting")
	@Column(name="lab_testid", nullable=false)
	private  long labTestid;
	
	@Column(name="village_id", nullable=false)
	private String villageId;
	
	@Column(name="location_id", nullable=false)
	private String locationId;
	
	@Column(name="block_id")
	private String blockId;
	
	@Column(name="habitation")
	private String habitation;
	
	@Column(name="overall_sample_result")
	private String overallSampleResult;
	
	@Column(name="type_of_water_source")
	private String typeOfWaterSource;
	@Column(name="lab_name")
	private String labName;
	@Column(name="test_date")
	private Date testDate;
	@Column(name="type_of_parameter")
	private String typeOfParameter;
	@Column(name="turbidity_result")
	private String turbidityResult;
	@Column(name="colour_result")
	private String colourResult;
	@Column(name="taste_odour_result")
	private String tasteOdourResult;
	@Column(name="ph_result")
	private String phResult;
	@Column(name="dissolved_result")
	private String dissolvedResult;
	@Column(name="alkalinity_result")
	private String alkalinityResult;
	@Column(name="hardness_result")
	private String hardnessResult; 
	@Column(name="residual_result")
	private String residualResult; 
	@Column(name="fluorides_result")
	private String fluoridesResult; 
	@Column(name="nitrate_result")
	private String nitrateResult;
	@Column(name="sulphate_result")
	private String sulphateResult; 
	@Column(name="iron_result")
	private String ironResult;  
	@Column(name="chloride_result")
	private String chlorideResult;  
	@Column(name="calcium_result")
	private String calciumResult;  
	@Column(name="uranium_result")
	private String uraniumResult;  
	@Column(name="selenium_result")
	private String seleniumResult;   
	@Column(name="cadmium_result")
	private String cadmiumResult;  
	@Column(name="arsenic_result")
	private String arsenicResult;  
	@Column(name="turbidity_actual")
	private String turbidityActual;
	@Column(name="colour_actual")
	private String colourActual;
	@Column(name="taste_odour_actual")
	private String tasteOdourActual;
	@Column(name="ph_actual")
	private String phActual;
	@Column(name="dissolved_actual")
	private String dissolvedActual;
	@Column(name="alkalinity_actual")
	private String alkalinityActual;
	@Column(name="hardness_actual")
	private String hardnessActual; 
	@Column(name="residual_actual")
	private String residualActual; 
	@Column(name="fluorides_actual")
	private String fluoridesActual; 
	@Column(name="nitrate_actual")
	private String nitrateActual;
	@Column(name="sulphate_actual")
	private String sulphateActual; 
	@Column(name="iron_actual")
	private String ironActual;  
	@Column(name="chloride_actual")
	private String chlorideActual;  
	@Column(name="calcium_actual")
	private String calciumActual;  
	@Column(name="uranium_actual")
	private String uraniumActual;  
	@Column(name="selenium_actual")
	private String seleniumActual;   
	@Column(name="cadmium_actual")
	private String cadmiumActual;  
	@Column(name="arsenic_actual")
	private String arsenicActual;  
	@Column(name="other1_name")
	private String other1Name; 
	@Column(name="other1_desirable")
	private String other1Desirable;  
	@Column(name="other1_permissible")
	private String other1Permissible;  
	@Column(name="other1_result")
	private String other1Result;  
	@Column(name="other1_actual")
	private String other1Actual;  
	@Column(name="other2_name")
	private String other2Name; 
	@Column(name="other2_desirable")
	private String other2Desirable;  
	@Column(name="other2_permissible")
	private String other2Permissible;  
	@Column(name="other2_result")
	private String other2Result;  
	@Column(name="other2_actual")
	private String other2Actual;  
	@Column(name="other3_name")
	private String other3Name; 
	@Column(name="other3_desirable")
	private String other3Desirable;  
	@Column(name="other3_permissible")
	private String other3Permissible;  
	@Column(name="other3_result")
	private String other3Result;  
	@Column(name="other3_actual")
	private String other3Actual;  
	@Column(name="other4_name")
	private String other4Name; 
	@Column(name="other4_desirable")
	private String other4Desirable;  
	@Column(name="other4_permissible")
	private String other4Permissible;  
	@Column(name="other4_result")
	private String other4Result;  
	@Column(name="other4_actual")
	private String other4Actual;  
	@Column(name="otherbact1_name")
	private String otherbact1Name; 
	@Column(name="otherbact1_desirable")
	private String otherbact1Desirable;  
	@Column(name="otherbact1_permissible")
	private String otherbact1Permissible;  
	@Column(name="otherbact1_result")
	private String otherbact1Result;  
	@Column(name="otherbact1_actual")
	private String otherbact1Actual;  
	@Column(name="otherbact2_name")
	private String otherbact2Name; 
	@Column(name="otherbact2_desirable")
	private String otherbact2Desirable;  
	@Column(name="otherbact2_permissible")
	private String otherbact2Permissible;  
	@Column(name="otherbact2_result")
	private String otherbact2Result;  
	@Column(name="otherbact2_actual")
	private String otherbact2Actual;  
	
	@Embedded
	private MISAuditBean misAuditBean;

	
	public String getOverallSampleResult() {
		return overallSampleResult;
	}

	public void setOverallSampleResult(String overallSampleResult) {
		this.overallSampleResult = overallSampleResult;
	}

	public long getLabTestid() {
		return labTestid;
	}

	public void setLabTestid(long labTestid) {
		this.labTestid = labTestid;
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

	public String getHabitation() {
		return habitation;
	}

	public void setHabitation(String habitation) {
		this.habitation = habitation;
	}

	public String getTypeOfWaterSource() {
		return typeOfWaterSource;
	}

	public void setTypeOfWaterSource(String typeOfWaterSource) {
		this.typeOfWaterSource = typeOfWaterSource;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public String getTypeOfParameter() {
		return typeOfParameter;
	}

	public void setTypeOfParameter(String typeOfParameter) {
		this.typeOfParameter = typeOfParameter;
	}

	public String getTurbidityResult() {
		return turbidityResult;
	}

	public void setTurbidityResult(String turbidityResult) {
		this.turbidityResult = turbidityResult;
	}

	public String getColourResult() {
		return colourResult;
	}

	public void setColourResult(String colourResult) {
		this.colourResult = colourResult;
	}

	public String getTasteOdourResult() {
		return tasteOdourResult;
	}

	public void setTasteOdourResult(String tasteOdourResult) {
		this.tasteOdourResult = tasteOdourResult;
	}

	public String getPhResult() {
		return phResult;
	}

	public void setPhResult(String phResult) {
		this.phResult = phResult;
	}

	public String getDissolvedResult() {
		return dissolvedResult;
	}

	public void setDissolvedResult(String dissolvedResult) {
		this.dissolvedResult = dissolvedResult;
	}

	public String getAlkalinityResult() {
		return alkalinityResult;
	}

	public void setAlkalinityResult(String alkalinityResult) {
		this.alkalinityResult = alkalinityResult;
	}

	public String getHardnessResult() {
		return hardnessResult;
	}

	public void setHardnessResult(String hardnessResult) {
		this.hardnessResult = hardnessResult;
	}

	public String getResidualResult() {
		return residualResult;
	}

	public void setResidualResult(String residualResult) {
		this.residualResult = residualResult;
	}

	public String getFluoridesResult() {
		return fluoridesResult;
	}

	public void setFluoridesResult(String fluoridesResult) {
		this.fluoridesResult = fluoridesResult;
	}

	public String getNitrateResult() {
		return nitrateResult;
	}

	public void setNitrateResult(String nitrateResult) {
		this.nitrateResult = nitrateResult;
	}

	public String getSulphateResult() {
		return sulphateResult;
	}

	public void setSulphateResult(String sulphateResult) {
		this.sulphateResult = sulphateResult;
	}

	public String getIronResult() {
		return ironResult;
	}

	public void setIronResult(String ironResult) {
		this.ironResult = ironResult;
	}

	public String getChlorideResult() {
		return chlorideResult;
	}

	public void setChlorideResult(String chlorideResult) {
		this.chlorideResult = chlorideResult;
	}

	public String getCalciumResult() {
		return calciumResult;
	}

	public void setCalciumResult(String calciumResult) {
		this.calciumResult = calciumResult;
	}

	public String getUraniumResult() {
		return uraniumResult;
	}

	public void setUraniumResult(String uraniumResult) {
		this.uraniumResult = uraniumResult;
	}

	public String getSeleniumResult() {
		return seleniumResult;
	}

	public void setSeleniumResult(String seleniumResult) {
		this.seleniumResult = seleniumResult;
	}

	public String getCadmiumResult() {
		return cadmiumResult;
	}

	public void setCadmiumResult(String cadmiumResult) {
		this.cadmiumResult = cadmiumResult;
	}

	public String getArsenicResult() {
		return arsenicResult;
	}

	public void setArsenicResult(String arsenicResult) {
		this.arsenicResult = arsenicResult;
	}

	public String getTurbidityActual() {
		return turbidityActual;
	}

	public void setTurbidityActual(String turbidityActual) {
		this.turbidityActual = turbidityActual;
	}

	public String getColourActual() {
		return colourActual;
	}

	public void setColourActual(String colourActual) {
		this.colourActual = colourActual;
	}

	public String getTasteOdourActual() {
		return tasteOdourActual;
	}

	public void setTasteOdourActual(String tasteOdourActual) {
		this.tasteOdourActual = tasteOdourActual;
	}

	public String getPhActual() {
		return phActual;
	}

	public void setPhActual(String phActual) {
		this.phActual = phActual;
	}

	public String getDissolvedActual() {
		return dissolvedActual;
	}

	public void setDissolvedActual(String dissolvedActual) {
		this.dissolvedActual = dissolvedActual;
	}

	public String getAlkalinityActual() {
		return alkalinityActual;
	}

	public void setAlkalinityActual(String alkalinityActual) {
		this.alkalinityActual = alkalinityActual;
	}

	public String getHardnessActual() {
		return hardnessActual;
	}

	public void setHardnessActual(String hardnessActual) {
		this.hardnessActual = hardnessActual;
	}

	public String getResidualActual() {
		return residualActual;
	}

	public void setResidualActual(String residualActual) {
		this.residualActual = residualActual;
	}

	public String getFluoridesActual() {
		return fluoridesActual;
	}

	public void setFluoridesActual(String fluoridesActual) {
		this.fluoridesActual = fluoridesActual;
	}

	public String getNitrateActual() {
		return nitrateActual;
	}

	public void setNitrateActual(String nitrateActual) {
		this.nitrateActual = nitrateActual;
	}

	public String getSulphateActual() {
		return sulphateActual;
	}

	public void setSulphateActual(String sulphateActual) {
		this.sulphateActual = sulphateActual;
	}

	public String getIronActual() {
		return ironActual;
	}

	public void setIronActual(String ironActual) {
		this.ironActual = ironActual;
	}

	public String getChlorideActual() {
		return chlorideActual;
	}

	public void setChlorideActual(String chlorideActual) {
		this.chlorideActual = chlorideActual;
	}

	public String getCalciumActual() {
		return calciumActual;
	}

	public void setCalciumActual(String calciumActual) {
		this.calciumActual = calciumActual;
	}

	public String getUraniumActual() {
		return uraniumActual;
	}

	public void setUraniumActual(String uraniumActual) {
		this.uraniumActual = uraniumActual;
	}

	public String getSeleniumActual() {
		return seleniumActual;
	}

	public void setSeleniumActual(String seleniumActual) {
		this.seleniumActual = seleniumActual;
	}

	public String getCadmiumActual() {
		return cadmiumActual;
	}

	public void setCadmiumActual(String cadmiumActual) {
		this.cadmiumActual = cadmiumActual;
	}

	public String getArsenicActual() {
		return arsenicActual;
	}

	public void setArsenicActual(String arsenicActual) {
		this.arsenicActual = arsenicActual;
	}

	public String getOther1Name() {
		return other1Name;
	}

	public void setOther1Name(String other1Name) {
		this.other1Name = other1Name;
	}

	public String getOther1Desirable() {
		return other1Desirable;
	}

	public void setOther1Desirable(String other1Desirable) {
		this.other1Desirable = other1Desirable;
	}

	public String getOther1Permissible() {
		return other1Permissible;
	}

	public void setOther1Permissible(String other1Permissible) {
		this.other1Permissible = other1Permissible;
	}

	public String getOther1Result() {
		return other1Result;
	}

	public void setOther1Result(String other1Result) {
		this.other1Result = other1Result;
	}

	public String getOther1Actual() {
		return other1Actual;
	}

	public void setOther1Actual(String other1Actual) {
		this.other1Actual = other1Actual;
	}

	public String getOther2Name() {
		return other2Name;
	}

	public void setOther2Name(String other2Name) {
		this.other2Name = other2Name;
	}

	public String getOther2Desirable() {
		return other2Desirable;
	}

	public void setOther2Desirable(String other2Desirable) {
		this.other2Desirable = other2Desirable;
	}

	public String getOther2Permissible() {
		return other2Permissible;
	}

	public void setOther2Permissible(String other2Permissible) {
		this.other2Permissible = other2Permissible;
	}

	public String getOther2Result() {
		return other2Result;
	}

	public void setOther2Result(String other2Result) {
		this.other2Result = other2Result;
	}

	public String getOther2Actual() {
		return other2Actual;
	}

	public void setOther2Actual(String other2Actual) {
		this.other2Actual = other2Actual;
	}

	public String getOther3Name() {
		return other3Name;
	}

	public void setOther3Name(String other3Name) {
		this.other3Name = other3Name;
	}

	public String getOther3Desirable() {
		return other3Desirable;
	}

	public void setOther3Desirable(String other3Desirable) {
		this.other3Desirable = other3Desirable;
	}

	public String getOther3Permissible() {
		return other3Permissible;
	}

	public void setOther3Permissible(String other3Permissible) {
		this.other3Permissible = other3Permissible;
	}

	public String getOther3Result() {
		return other3Result;
	}

	public void setOther3Result(String other3Result) {
		this.other3Result = other3Result;
	}

	public String getOther3Actual() {
		return other3Actual;
	}

	public void setOther3Actual(String other3Actual) {
		this.other3Actual = other3Actual;
	}

	public String getOther4Name() {
		return other4Name;
	}

	public void setOther4Name(String other4Name) {
		this.other4Name = other4Name;
	}

	public String getOther4Desirable() {
		return other4Desirable;
	}

	public void setOther4Desirable(String other4Desirable) {
		this.other4Desirable = other4Desirable;
	}

	public String getOther4Permissible() {
		return other4Permissible;
	}

	public void setOther4Permissible(String other4Permissible) {
		this.other4Permissible = other4Permissible;
	}

	public String getOther4Result() {
		return other4Result;
	}

	public void setOther4Result(String other4Result) {
		this.other4Result = other4Result;
	}

	public String getOther4Actual() {
		return other4Actual;
	}

	public void setOther4Actual(String other4Actual) {
		this.other4Actual = other4Actual;
	}

	public String getOtherbact1Name() {
		return otherbact1Name;
	}

	public void setOtherbact1Name(String otherbact1Name) {
		this.otherbact1Name = otherbact1Name;
	}

	public String getOtherbact1Desirable() {
		return otherbact1Desirable;
	}

	public void setOtherbact1Desirable(String otherbact1Desirable) {
		this.otherbact1Desirable = otherbact1Desirable;
	}

	public String getOtherbact1Permissible() {
		return otherbact1Permissible;
	}

	public void setOtherbact1Permissible(String otherbact1Permissible) {
		this.otherbact1Permissible = otherbact1Permissible;
	}

	public String getOtherbact1Result() {
		return otherbact1Result;
	}

	public void setOtherbact1Result(String otherbact1Result) {
		this.otherbact1Result = otherbact1Result;
	}

	public String getOtherbact1Actual() {
		return otherbact1Actual;
	}

	public void setOtherbact1Actual(String otherbact1Actual) {
		this.otherbact1Actual = otherbact1Actual;
	}

	public String getOtherbact2Name() {
		return otherbact2Name;
	}

	public void setOtherbact2Name(String otherbact2Name) {
		this.otherbact2Name = otherbact2Name;
	}

	public String getOtherbact2Desirable() {
		return otherbact2Desirable;
	}

	public void setOtherbact2Desirable(String otherbact2Desirable) {
		this.otherbact2Desirable = otherbact2Desirable;
	}

	public String getOtherbact2Permissible() {
		return otherbact2Permissible;
	}

	public void setOtherbact2Permissible(String otherbact2Permissible) {
		this.otherbact2Permissible = otherbact2Permissible;
	}

	public String getOtherbact2Result() {
		return otherbact2Result;
	}

	public void setOtherbact2Result(String otherbact2Result) {
		this.otherbact2Result = otherbact2Result;
	}

	public String getOtherbact2Actual() {
		return otherbact2Actual;
	}

	public void setOtherbact2Actual(String otherbact2Actual) {
		this.otherbact2Actual = otherbact2Actual;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "LabTestingBean [labTestid=" + labTestid + ", villageId="
				+ villageId + ", locationId=" + locationId + ", habitation="
				+ habitation + ", typeOfWaterSource=" + typeOfWaterSource
				+ ", labName=" + labName + ", testDate=" + testDate
				+ ", typeOfParameter=" + typeOfParameter + ", turbidityResult="
				+ turbidityResult + ", colourResult=" + colourResult
				+ ", tasteOdourResult=" + tasteOdourResult + ", phResult="
				+ phResult + ", dissolvedResult=" + dissolvedResult
				+ ", alkalinityResult=" + alkalinityResult
				+ ", hardnessResult=" + hardnessResult + ", residualResult="
				+ residualResult + ", fluoridesResult=" + fluoridesResult
				+ ", nitrateResult=" + nitrateResult + ", sulphateResult="
				+ sulphateResult + ", ironResult=" + ironResult
				+ ", chlorideResult=" + chlorideResult + ", calciumResult="
				+ calciumResult + ", uraniumResult=" + uraniumResult
				+ ", seleniumResult=" + seleniumResult + ", cadmiumResult="
				+ cadmiumResult + ", arsenicResult=" + arsenicResult
				+ ", turbidityActual=" + turbidityActual + ", colourActual="
				+ colourActual + ", tasteOdourActual=" + tasteOdourActual
				+ ", phActual=" + phActual + ", dissolvedActual="
				+ dissolvedActual + ", alkalinityActual=" + alkalinityActual
				+ ", hardnessActual=" + hardnessActual + ", residualActual="
				+ residualActual + ", fluoridesActual=" + fluoridesActual
				+ ", nitrateActual=" + nitrateActual + ", sulphateActual="
				+ sulphateActual + ", ironActual=" + ironActual
				+ ", chlorideActual=" + chlorideActual + ", calciumActual="
				+ calciumActual + ", uraniumActual=" + uraniumActual
				+ ", seleniumActual=" + seleniumActual + ", cadmiumActual="
				+ cadmiumActual + ", arsenicActual=" + arsenicActual
				+ ", other1Name=" + other1Name + ", other1Desirable="
				+ other1Desirable + ", other1Permissible=" + other1Permissible
				+ ", other1Result=" + other1Result + ", other1Actual="
				+ other1Actual + ", other2Name=" + other2Name
				+ ", other2Desirable=" + other2Desirable
				+ ", other2Permissible=" + other2Permissible
				+ ", other2Result=" + other2Result + ", other2Actual="
				+ other2Actual + ", other3Name=" + other3Name
				+ ", other3Desirable=" + other3Desirable
				+ ", other3Permissible=" + other3Permissible
				+ ", other3Result=" + other3Result + ", other3Actual="
				+ other3Actual + ", other4Name=" + other4Name
				+ ", other4Desirable=" + other4Desirable
				+ ", other4Permissible=" + other4Permissible
				+ ", other4Result=" + other4Result + ", other4Actual="
				+ other4Actual + ", otherbact1Name=" + otherbact1Name
				+ ", otherbact1Desirable=" + otherbact1Desirable
				+ ", otherbact1Permissible=" + otherbact1Permissible
				+ ", otherbact1Result=" + otherbact1Result
				+ ", otherbact1Actual=" + otherbact1Actual
				+ ", otherbact2Name=" + otherbact2Name
				+ ", otherbact2Desirable=" + otherbact2Desirable
				+ ", otherbact2Permissible=" + otherbact2Permissible
				+ ", otherbact2Result=" + otherbact2Result
				+ ", otherbact2Actual=" + otherbact2Actual + ", misAuditBean="
				+ misAuditBean + "]";
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	
	


}
