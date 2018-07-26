/**
 * 
 */
package com.prwss.mis.reports.performa.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.reports.performa.PerformaFiveYearPlanBean;
import com.prwss.mis.reports.performa.PerformaMasterAction;
import com.prwss.mis.reports.performa.PerformaMasterForm;

/**
 * @author bhsingh
 *
 */ 
public class PerformaMasterBOImpl implements PerformaMasterBo{
	
	Logger log = Logger.getLogger(PerformaMasterAction.class);

	private PerformaMasterDao performaMasterDao;
	
	/**
	 * @param performaMasterDao the performaMasterDao to set
	 */
	public void setPerformaMasterDao(PerformaMasterDao performaMasterDao) {
		this.performaMasterDao = performaMasterDao;
	}

	@Override
	public List<PerformaMasterBean> findPerformaMaster(PerformaMasterForm performaMasterForm,List<String> statusList) throws MISException {

		List<PerformaMasterBean> performaMasterBean=null;
		try{
			performaMasterBean=performaMasterDao.findPerformaMaster(performaMasterForm, statusList);
			
			System.out.println("--------after findPerformaMaster in PerformaMasterBOImpl------");
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e.getMostSpecificCause());
		}
		return performaMasterBean;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updatePerformaMaster(PerformaMasterForm performaMasterForm, MISSessionBean misSessionBean)
			throws MISException {
		
		boolean status=false;
		List<VillagePerformaBean> performaBean=null;
		List<SchemeSourcePerformaBean> performaSrcBeans=null;
		List<SchemeBean1> schemeBean=null;
		List<PerformaFiveYearPlanBean> performaFiveYearPlanBeans=null;
		FiveYearPerformaMaster fiveYearPerformaMaster=new FiveYearPerformaMaster();
		try{
					performaBean=populateHeaderBean(performaMasterForm,misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
					if(!MisUtility.ifEmpty(performaBean)){
						fiveYearPerformaMaster.setPerformaBean(performaBean);
					}
					
					performaSrcBeans=populatePerformaSourceBean(performaMasterForm,misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
					if(!MisUtility.ifEmpty(performaSrcBeans)){
						fiveYearPerformaMaster.setPerformaSrcBeans(performaSrcBeans);
					}
					schemeBean=populatePerformaSchemeBean(performaMasterForm,misSessionBean, MISConstants.MASTER_STATUS_VERIFIED); 
					if(!MisUtility.ifEmpty(schemeBean)){
						fiveYearPerformaMaster.setSchemeBean(schemeBean);
					}
					
					performaFiveYearPlanBeans=populateFiveYearPlanBean(performaMasterForm,misSessionBean, MISConstants.MASTER_STATUS_VERIFIED); 
					if(!MisUtility.ifEmpty(performaFiveYearPlanBeans)){
						fiveYearPerformaMaster.setPerformaFiveYearPlanBeans(performaFiveYearPlanBeans);
					}
					//savePerformaMaster
					status=performaMasterDao.savePerformaMaster(fiveYearPerformaMaster);
					
		}catch(Exception e){
			log.debug(e);
			throw new MISException(e);
		}
		return status;
	}
	
	private List<SchemeBean1> populatePerformaSchemeBean(PerformaMasterForm performaMasterForm, MISSessionBean misSessionBean,String status){
		
		List<SchemeBean1> lstschemeBean=new ArrayList<SchemeBean1>();
		
		MISAuditBean misAuditBean = new MISAuditBean();
	
			//if(MISConstants.MASTER_STATUS_VERIFIED.equalsIgnoreCase(status)){
			/*	misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());*/
			//} 
			
			
			
			
			@SuppressWarnings("unchecked")
			Collection<VillageSourceGridBean> villagePerformaGrid=performaMasterForm.getVillageSourceGrid().getAddedData();
			System.out.println("size--->"+villagePerformaGrid.size());
			SchemeSourcePerformaBean schemeSourcePerformaBean=null;
			if(villagePerformaGrid.size()>0){
				for(VillageSourceGridBean gridBean:villagePerformaGrid){
					SchemeBean1 schemeBean=new SchemeBean1();
					schemeBean.setSchemeId(performaMasterForm.getSchemeId());
					schemeBean.setVillageId(gridBean.getVilIds());
					if(MisUtility.ifEmptyField(gridBean.getGround_water_potablestatus())){
						schemeBean.setGround_water_potable_status(Integer.parseInt(gridBean.getGround_water_potablestatus()));
					}
					
					
					//schemeBean.setPreventive_measures_adopted(gridBean.getPreventive_measuresadopted());
					
					/**
					 * XKUSH
					 */
					if(MisUtility.ifEmptyField(gridBean.getAny_other_desc())){
						schemeBean.setAny_other_desc(gridBean.getAny_other_desc());
					}
					
					
					if(MisUtility.ifEmptyField(gridBean.getCapacity_ofplant())){
						schemeBean.setCapacity_of_plant(Double.parseDouble(gridBean.getCapacity_ofplant()));
					}
					schemeBean.setDate_of_installation(MisUtility.convertStringSqlDate(gridBean.getDateInstallation()));
					
					schemeBean.setBeing_operated_by(gridBean.getBeing_operatedby());
					if(MisUtility.ifEmptyField(gridBean.getVolume_of_Water_Dailybasis())){
						schemeBean.setVolume_of_water_daily_basis(Double.parseDouble(gridBean.getVolume_of_Water_Dailybasis()));
					}
					schemeBean.setDisposal_of_reject_water(gridBean.getDisposal_of_rejectwater());
					
					if(MisUtility.ifEmptyField(gridBean.getPenetration_inpercentage())){
						schemeBean.setPenetration_in_percentage(Double.parseDouble(gridBean.getPenetration_inpercentage()));
					}
					if(MisUtility.ifEmptyField(gridBean.getPresent_rate_of_usercharges())){
						schemeBean.setPresent_rate_of_user_charges(Double.parseDouble(gridBean.getPresent_rate_of_usercharges()));
					}
					if(MisUtility.ifEmptyField(gridBean.getSeperate_SanctionedLoad())){
						schemeBean.setSeperate_sanctioned_load(Double.parseDouble(gridBean.getSeperate_SanctionedLoad()));
					}
					if(MisUtility.ifEmptyField(gridBean.getSeperate_Pending_eletricbill31032017())){
						schemeBean.setSeperate_pending_eletric_bill31032017(Double.parseDouble(gridBean.getSeperate_Pending_eletricbill31032017()));
					}
					if(MisUtility.ifEmptyField(gridBean.getAverage_monthly_bill_of_Treatmentplant())){
						schemeBean.setAverage_monthly_bill_of_treatment_plant(Double.parseDouble(gridBean.getAverage_monthly_bill_of_Treatmentplant()));
					}
				
			
			if(MisUtility.ifEmptyField(performaMasterForm.getPipelineAC())){
				schemeBean.setPipelineAc(Double.parseDouble(performaMasterForm.getPipelineAC()));
			}
			schemeBean.setPercentage_functional_pipeline(performaMasterForm.getFunctionalDistributionPercentage());
			//schemeBean.setMisAuditBean(misAuditBean);
			if(MisUtility.ifEmptyField(performaMasterForm.getPipelineMSDICi())){
				schemeBean.setPipelineMsDi(Double.parseDouble(performaMasterForm.getPipelineMSDICi()));
			}
			
			if(MisUtility.ifEmptyField(performaMasterForm.getPipelinePVC())){
				schemeBean.setPipelinePvc(Double.parseDouble(performaMasterForm.getPipelinePVC()));
			}
			if(MisUtility.ifEmptyField(performaMasterForm.getPipelineGI())){
				schemeBean.setPipelineGi(Double.parseDouble(performaMasterForm.getPipelineGI()));
			}
			
			if(MisUtility.ifEmptyField(performaMasterForm.getHouseholdWaterConnection())){
				schemeBean.setHouseholdWaterConnection(Double.parseDouble((performaMasterForm.getHouseholdWaterConnection())));
			}
			
			if(MisUtility.ifEmptyField(performaMasterForm.getWatered_connection())){
			schemeBean.setWateredConnection(Double.parseDouble(performaMasterForm.getWatered_connection()));
			}
			
			if(MisUtility.ifEmptyField(performaMasterForm.getMetered_connection())){
				schemeBean.setMeteredConnection(Double.parseDouble(performaMasterForm.getMetered_connection()));
			}
			
				schemeBean.setMeteredRupplyRecovery(performaMasterForm.getMetered_supply_recovery());
			
			if(MisUtility.ifEmptyField(performaMasterForm.getFlat_rate_charges_per_month())){
				schemeBean.setFlat_rate_charges_per_month(Double.parseDouble(performaMasterForm.getFlat_rate_charges_per_month()));
			}
			
			if(MisUtility.ifEmptyField(performaMasterForm.getArrear_of_recovery_with_consumers_as01042017())){
				schemeBean.setArrear_of_recovery_with_consumers_as01042017(Double.parseDouble(performaMasterForm.getArrear_of_recovery_with_consumers_as01042017()));
			}
			
			if(MisUtility.ifEmptyField(performaMasterForm.getScheme_Functional())){
				schemeBean.setScheme_functional(Double.parseDouble(performaMasterForm.getScheme_Functional()));
			}
			schemeBean.setScheme_nonfunctional(performaMasterForm.getScheme_NonFunctional());
			
			schemeBean.setScheme_nonfunctional_date(MisUtility.convertStringSqlDate(performaMasterForm.getScheme_NonFunctional_date()));
			schemeBean.setGround_water_potable_no(performaMasterForm.getGround_water_potable_No());
			
			if(MisUtility.ifEmptyField(performaMasterForm.getGround_water_potable_status())){
				schemeBean.setGround_water_potable_status(Integer.parseInt(performaMasterForm.getGround_water_potable_status()));
			}
			
			schemeBean.setPreventive_measures_adopted(performaMasterForm.getPreventive_measures_adopted());
			
			if(MisUtility.ifEmptyField(performaMasterForm.getCapacity_of_plant())){
				schemeBean.setCapacity_of_plant(Double.parseDouble(performaMasterForm.getCapacity_of_plant()));
			}
			schemeBean.setDate_of_installation(MisUtility.convertStringSqlDate(performaMasterForm.getDate_of_installation()));
			
			schemeBean.setBeing_operated_by(performaMasterForm.getBeing_operated_by());
			
			if(MisUtility.ifEmptyField(performaMasterForm.getVolume_of_Water_Daily_basis())){
				schemeBean.setVolume_of_water_daily_basis(Double.parseDouble(performaMasterForm.getVolume_of_Water_Daily_basis()));
			}
			
			
			schemeBean.setDisposal_of_reject_water(performaMasterForm.getDisposal_of_reject_water());
			
			if(MisUtility.ifEmptyField(performaMasterForm.getPenetration_in_percentage())){
				schemeBean.setPenetration_in_percentage(Double.parseDouble(performaMasterForm.getPenetration_in_percentage()));
			}
			
			if(MisUtility.ifEmptyField(performaMasterForm.getPresent_rate_of_user_charges())){
				schemeBean.setPresent_rate_of_user_charges(Double.parseDouble(performaMasterForm.getPresent_rate_of_user_charges()));
			}
			
			if(MisUtility.ifEmptyField(performaMasterForm.getSeperate_Sanctioned_Load())){
				schemeBean.setSeperate_sanctioned_load(Double.parseDouble(performaMasterForm.getSeperate_Sanctioned_Load()));
			}
			
			if(MisUtility.ifEmptyField(performaMasterForm.getSeperate_Pending_eletric_bill31032017())){
				schemeBean.setSeperate_pending_eletric_bill31032017(Double.parseDouble(performaMasterForm.getSeperate_Pending_eletric_bill31032017()));
			}
			
			if(MisUtility.ifEmptyField(performaMasterForm.getAverage_monthly_bill_of_Treatment_plant())){
				schemeBean.setAverage_monthly_bill_of_treatment_plant(Double.parseDouble(performaMasterForm.getAverage_monthly_bill_of_Treatment_plant()));
			}
			
			lstschemeBean.add(schemeBean);
			}
			}
			else{
				SchemeBean1 schemeBean=new SchemeBean1();
				
				//latest
				schemeBean.setSchemeId(performaMasterForm.getSchemeId());
				//schemeBean.setVillageId(performaMasterForm.getVillageId());
				if(MisUtility.ifEmptyField(performaMasterForm.getGround_water_potable_status())){
					schemeBean.setGround_water_potable_status(Integer.parseInt(performaMasterForm.getGround_water_potable_status()));
				}
				
				
				//schemeBean.setPreventive_measures_adopted(performaMasterForm.getPreventive_measures_adopted());
				
				/**
				 * XKUSH
				 */
				if(MisUtility.ifEmptyField(performaMasterForm.getAny_other_desc())){
					schemeBean.setAny_other_desc(performaMasterForm.getAny_other_desc());
				}
				
				if(MisUtility.ifEmptyField(performaMasterForm.getCapacity_of_plant())){
					schemeBean.setCapacity_of_plant(Double.parseDouble(performaMasterForm.getCapacity_of_plant()));
				}
				schemeBean.setDate_of_installation(MisUtility.convertStringSqlDate(performaMasterForm.getDate_of_installation()));
				
				schemeBean.setBeing_operated_by(performaMasterForm.getBeing_operated_by());
				if(MisUtility.ifEmptyField(performaMasterForm.getVolume_of_Water_Daily_basis())){
					schemeBean.setVolume_of_water_daily_basis(Double.parseDouble(performaMasterForm.getVolume_of_Water_Daily_basis()));
				}
				schemeBean.setDisposal_of_reject_water(performaMasterForm.getDisposal_of_reject_water());
				
				if(MisUtility.ifEmptyField(performaMasterForm.getPenetration_in_percentage())){
					schemeBean.setPenetration_in_percentage(Double.parseDouble(performaMasterForm.getPenetration_in_percentage()));
				}
				if(MisUtility.ifEmptyField(performaMasterForm.getPresent_rate_of_user_charges())){
					schemeBean.setPresent_rate_of_user_charges(Double.parseDouble(performaMasterForm.getPresent_rate_of_user_charges()));
				}
				if(MisUtility.ifEmptyField(performaMasterForm.getSeperate_Sanctioned_Load())){
					schemeBean.setSeperate_sanctioned_load(Double.parseDouble(performaMasterForm.getSeperate_Sanctioned_Load()));
				}
				if(MisUtility.ifEmptyField(performaMasterForm.getSeperate_Pending_eletric_bill31032017())){
					schemeBean.setSeperate_pending_eletric_bill31032017(Double.parseDouble(performaMasterForm.getSeperate_Pending_eletric_bill31032017()));
				}
				if(MisUtility.ifEmptyField(performaMasterForm.getAverage_monthly_bill_of_Treatment_plant())){
					schemeBean.setAverage_monthly_bill_of_treatment_plant(Double.parseDouble(performaMasterForm.getAverage_monthly_bill_of_Treatment_plant()));
				}
				
				///
				
			
				
				if(MisUtility.ifEmptyField(performaMasterForm.getPipelineAC())){
					schemeBean.setPipelineAc(Double.parseDouble(performaMasterForm.getPipelineAC()));
				}
				schemeBean.setPercentage_functional_pipeline(performaMasterForm.getFunctionalDistributionPercentage());
				//schemeBean.setMisAuditBean(misAuditBean);
				if(MisUtility.ifEmptyField(performaMasterForm.getPipelineMSDICi())){
					schemeBean.setPipelineMsDi(Double.parseDouble(performaMasterForm.getPipelineMSDICi()));
				}
				
				if(MisUtility.ifEmptyField(performaMasterForm.getPipelinePVC())){
					schemeBean.setPipelinePvc(Double.parseDouble(performaMasterForm.getPipelinePVC()));
				}
				if(MisUtility.ifEmptyField(performaMasterForm.getPipelineGI())){
					schemeBean.setPipelineGi(Double.parseDouble(performaMasterForm.getPipelineGI()));
				}
				
				if(MisUtility.ifEmptyField(performaMasterForm.getHouseholdWaterConnection())){
					schemeBean.setHouseholdWaterConnection(Double.parseDouble((performaMasterForm.getHouseholdWaterConnection())));
				}
				
				if(MisUtility.ifEmptyField(performaMasterForm.getWatered_connection())){
				schemeBean.setWateredConnection(Double.parseDouble(performaMasterForm.getWatered_connection()));
				}
				
				if(MisUtility.ifEmptyField(performaMasterForm.getMetered_connection())){
					schemeBean.setMeteredConnection(Double.parseDouble(performaMasterForm.getMetered_connection()));
				}
				
					schemeBean.setMeteredRupplyRecovery(performaMasterForm.getMetered_supply_recovery());
				
				if(MisUtility.ifEmptyField(performaMasterForm.getFlat_rate_charges_per_month())){
					schemeBean.setFlat_rate_charges_per_month(Double.parseDouble(performaMasterForm.getFlat_rate_charges_per_month()));
				}
				
				if(MisUtility.ifEmptyField(performaMasterForm.getArrear_of_recovery_with_consumers_as01042017())){
					schemeBean.setArrear_of_recovery_with_consumers_as01042017(Double.parseDouble(performaMasterForm.getArrear_of_recovery_with_consumers_as01042017()));
				}
				
				if(MisUtility.ifEmptyField(performaMasterForm.getScheme_Functional())){
					schemeBean.setScheme_functional(Double.parseDouble(performaMasterForm.getScheme_Functional()));
				}
				schemeBean.setScheme_nonfunctional(performaMasterForm.getScheme_NonFunctional());
				
				schemeBean.setScheme_nonfunctional_date(MisUtility.convertStringSqlDate(performaMasterForm.getScheme_NonFunctional_date()));
				schemeBean.setGround_water_potable_no(performaMasterForm.getGround_water_potable_No());
				
				if(MisUtility.ifEmptyField(performaMasterForm.getGround_water_potable_status())){
					schemeBean.setGround_water_potable_status(Integer.parseInt(performaMasterForm.getGround_water_potable_status()));
				}
				
				schemeBean.setPreventive_measures_adopted(performaMasterForm.getPreventive_measures_adopted());
				
				if(MisUtility.ifEmptyField(performaMasterForm.getCapacity_of_plant())){
					schemeBean.setCapacity_of_plant(Double.parseDouble(performaMasterForm.getCapacity_of_plant()));
				}
				schemeBean.setDate_of_installation(MisUtility.convertStringSqlDate(performaMasterForm.getDate_of_installation()));
				
				schemeBean.setBeing_operated_by(performaMasterForm.getBeing_operated_by());
				
				if(MisUtility.ifEmptyField(performaMasterForm.getVolume_of_Water_Daily_basis())){
					schemeBean.setVolume_of_water_daily_basis(Double.parseDouble(performaMasterForm.getVolume_of_Water_Daily_basis()));
				}
				
				
				schemeBean.setDisposal_of_reject_water(performaMasterForm.getDisposal_of_reject_water());
				
				if(MisUtility.ifEmptyField(performaMasterForm.getPenetration_in_percentage())){
					schemeBean.setPenetration_in_percentage(Double.parseDouble(performaMasterForm.getPenetration_in_percentage()));
				}
				
				if(MisUtility.ifEmptyField(performaMasterForm.getPresent_rate_of_user_charges())){
					schemeBean.setPresent_rate_of_user_charges(Double.parseDouble(performaMasterForm.getPresent_rate_of_user_charges()));
				}
				
				if(MisUtility.ifEmptyField(performaMasterForm.getSeperate_Sanctioned_Load())){
					schemeBean.setSeperate_sanctioned_load(Double.parseDouble(performaMasterForm.getSeperate_Sanctioned_Load()));
				}
				
				if(MisUtility.ifEmptyField(performaMasterForm.getSeperate_Pending_eletric_bill31032017())){
					schemeBean.setSeperate_pending_eletric_bill31032017(Double.parseDouble(performaMasterForm.getSeperate_Pending_eletric_bill31032017()));
				}
				
				if(MisUtility.ifEmptyField(performaMasterForm.getAverage_monthly_bill_of_Treatment_plant())){
					schemeBean.setAverage_monthly_bill_of_treatment_plant(Double.parseDouble(performaMasterForm.getAverage_monthly_bill_of_Treatment_plant()));
				}
				
				lstschemeBean.add(schemeBean);
			}
		
		return lstschemeBean;
	}
	private List<SchemeSourcePerformaBean> populatePerformaSourceBean(PerformaMasterForm performaMasterForm, MISSessionBean misSessionBean, String status){
		
		List<SchemeSourcePerformaBean> performaBeans = new ArrayList<SchemeSourcePerformaBean>();
		MISAuditBean misAuditBean = new MISAuditBean();
		System.out.println("");
	
			if(MISConstants.MASTER_STATUS_VERIFIED.equalsIgnoreCase(status)){
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			} 
			VillagePerformaBean performaBean=null;
			
			System.out.println("performaMasterForm--->"+performaMasterForm.toString());
			@SuppressWarnings("unchecked")
			Collection<VillageSourceGridBean> villagePerformaGrid=performaMasterForm.getVillageSourceGrid().getAddedData();
			System.out.println("size--->"+villagePerformaGrid.size());
			System.out.println("villagePerformaGrid -->"+villagePerformaGrid.toString());
			SchemeSourcePerformaBean schemeSourcePerformaBean=null;
			if(villagePerformaGrid.size()>0){
				for(VillageSourceGridBean gridBean:villagePerformaGrid){
					System.out.println("hosr--->"+gridBean.getNoOhsrOhsr());
					schemeSourcePerformaBean=new SchemeSourcePerformaBean();
					schemeSourcePerformaBean.setSchemeId(performaMasterForm.getSchemeId());
					schemeSourcePerformaBean.setVillageId(gridBean.getVilIds());
					schemeSourcePerformaBean.setSchemeSource(gridBean.getSchmSource());
					schemeSourcePerformaBean.setSchemeType(gridBean.getSchmType());
					schemeSourcePerformaBean.setDismantling_received(gridBean.getoHSRDismantling1());
					schemeSourcePerformaBean.setOhsr_full_supply_level(gridBean.getoHSRFullSuplyLvl1());
					schemeSourcePerformaBean.setCommissioningDate(	MisUtility.convertStringSqlDate(gridBean.getDateComm()));
					schemeSourcePerformaBean.setProgId(gridBean.getProgramId());
					
					schemeSourcePerformaBean.setServiceLevel(gridBean.getSrvcLevel());
					schemeSourcePerformaBean.setSchemeUpgraded(MisUtility.convertStringSqlDate(gridBean.getSchemeUpgrd()));
					
						if(MisUtility.ifEmptyField(gridBean.getSchemeExpen())){
						schemeSourcePerformaBean.setSchemeExpenditure(Double.parseDouble(gridBean.getSchemeExpen()));
						}
						
						if(MisUtility.ifEmptyField(gridBean.getDepthTubewell())){
							schemeSourcePerformaBean.setTubewellDepth(Double.parseDouble(gridBean.getDepthTubewell()));
						}
						
						if(MisUtility.ifEmptyField(gridBean.getSizeTubewell())){
							schemeSourcePerformaBean.setTubewellSize(gridBean.getSizeTubewell());
						}
						if(MisUtility.ifEmptyField(gridBean.getDischargeCommun())){
							schemeSourcePerformaBean.setDischargeComm(Double.parseDouble(gridBean.getDischargeCommun()));
						}
						
						
						schemeSourcePerformaBean.setDrillingYear(MisUtility.convertStringSqlDate(gridBean.getYearDrilling()));
						if(MisUtility.ifEmptyField(gridBean.getPresentDischrg())){
							schemeSourcePerformaBean.setPresentDischarge(Double.parseDouble(gridBean.getPresentDischrg()));
						}
						if(MisUtility.ifEmptyField(gridBean.getPresentSpringLvl())){
							schemeSourcePerformaBean.setPresentSpringLevel(Double.parseDouble(gridBean.getPresentSpringLvl()));
						}
						
						schemeSourcePerformaBean.setMachineryInstallation(MisUtility.convertStringSqlDate(gridBean.getInstallationNewMachinery()));
						if(MisUtility.ifEmptyField(gridBean.getCapacityMachinery())){
							schemeSourcePerformaBean.setMachineryCapacity(Double.parseDouble(gridBean.getCapacityMachinery()));
						}
						schemeSourcePerformaBean.setInletChannelType(gridBean.getInltType());
						
						if(MisUtility.ifEmptyField(gridBean.getInletLnght())){
							schemeSourcePerformaBean.setInletChannelLength(Double.parseDouble(gridBean.getInletLnght()));
						}
						schemeSourcePerformaBean.setPipeType(gridBean.getPipType());
						
						if(MisUtility.ifEmptyField(gridBean.getCapacitySTank())){
							schemeSourcePerformaBean.setSsTankCapacity(Double.parseDouble(gridBean.getCapacitySTank()));
						}
						
						if(MisUtility.ifEmptyField(gridBean.getCapacityHTank())){
							schemeSourcePerformaBean.setHlTankCapacity(Double.parseDouble(gridBean.getCapacityHTank()));
						}
						
						if(MisUtility.ifEmptyField(gridBean.getCapacityCTank())){
							schemeSourcePerformaBean.setCwTankCapacity(Double.parseDouble(gridBean.getCapacityCTank()));
						}
						
						schemeSourcePerformaBean.setFilterationType(gridBean.getFilterType());
						
						if(MisUtility.ifEmptyField(gridBean.getFilterCapacity())){
							schemeSourcePerformaBean.setFiltertionCapacity(Double.parseDouble(gridBean.getFilterCapacity()));
						}
						
						if(MisUtility.ifEmptyField(gridBean.getFilterNo())){
							schemeSourcePerformaBean.setFiltertionNo(Double.parseDouble(gridBean.getFilterNo()));
						}
						
						if(MisUtility.ifEmptyField(gridBean.getCapacityRawWatr())){
							schemeSourcePerformaBean.setRawWaterCaps(Double.parseDouble(gridBean.getCapacityRawWatr()));
						}
						
						if(MisUtility.ifEmptyField(gridBean.getCapacityClrWater())){
							schemeSourcePerformaBean.setClearWaterCaps(Double.parseDouble(gridBean.getCapacityClrWater()));
						}
						
						//ohsr
						
						if(MisUtility.ifEmptyField(gridBean.getNoOhsrOhsr())){
							schemeSourcePerformaBean.setOhsrNo(Double.parseDouble(gridBean.getNoOhsrOhsr()));
						}
						schemeSourcePerformaBean.setOhsrConsDate(MisUtility.convertStringSqlDate(gridBean.getoHSRConstructionDate()));
						schemeSourcePerformaBean.setOhsr_full_supply_level(gridBean.getoHSRFullSuplyLvl1());
						
						if(MisUtility.ifEmptyField(gridBean.getoHSRCap())){
							schemeSourcePerformaBean.setOhsrCaps(Double.parseDouble(gridBean.getoHSRCap()));
						}
						if(MisUtility.ifEmptyField(gridBean.getoHSRCtDia1())){
							schemeSourcePerformaBean.setOhsr_ct(Double.parseDouble(gridBean.getoHSRCtDia1()));
						}
						if(MisUtility.ifEmptyField(gridBean.getoHSRCtDepth1())){
							schemeSourcePerformaBean.setOhsr_ct_depth(Double.parseDouble(gridBean.getoHSRCtDepth1()));
						}
						
						if(MisUtility.ifEmptyField(gridBean.getoHSRUgsrDia1())){
							schemeSourcePerformaBean.setOhsr_ugsr(Double.parseDouble(gridBean.getoHSRUgsrDia1()));
						}
						
						if(MisUtility.ifEmptyField(gridBean.getoHSRUgsrDepth1())){
							schemeSourcePerformaBean.setOhsr_ugsr_depth(Double.parseDouble(gridBean.getoHSRUgsrDepth1()));
						}
						if(MisUtility.ifEmptyField(gridBean.getoHSRWorkingCond())){
							schemeSourcePerformaBean.setOhsrWorkingCondition(Integer.parseInt(gridBean.getoHSRWorkingCond()));
						}
						schemeSourcePerformaBean.setOhsrConditionIfNo(gridBean.getoHSRCond());
						
						schemeSourcePerformaBean.setDisinfectionType(gridBean.getDisinfType());
						schemeSourcePerformaBean.setDisinfectionInstalTime(MisUtility.convertStringSqlDate(gridBean.getDisInstallationTime()));
						schemeSourcePerformaBean.setDisnPresentStatus(gridBean.getDisinPrStatus());
						schemeSourcePerformaBean.setSchemeOpertedBy(gridBean.getSchemeOperatBy());
						schemeSourcePerformaBean.setDwss_operated_arrangement(gridBean.getStafScheme());
						if(MisUtility.ifEmptyField(gridBean.getSanctionLoad())){
							schemeSourcePerformaBean.setSanctionedLoad(Double.parseDouble(gridBean.getSanctionLoad()));
						}
						
						if(MisUtility.ifEmptyField(gridBean.getPendingBill3103())){
							schemeSourcePerformaBean.setPendingBill3103(Double.parseDouble(gridBean.getPendingBill3103()));
						}
						
						if(MisUtility.ifEmptyField(gridBean.getPendingBill3006())){
							schemeSourcePerformaBean.setPendingBill3006(Double.parseDouble(gridBean.getPendingBill3006()));
						}
						if(MisUtility.ifEmptyField(gridBean.getAvgMonthBillWSS())){
							schemeSourcePerformaBean.setAvgMonthBill(Double.parseDouble(gridBean.getAvgMonthBillWSS()));
						}
						
						performaBeans.add(schemeSourcePerformaBean);
					}
				}else{
					System.out.println("outside grid-------->"+performaMasterForm.toString());
					schemeSourcePerformaBean=new SchemeSourcePerformaBean();
					schemeSourcePerformaBean.setSchemeId(performaMasterForm.getSchemeId());
					//schemeSourcePerformaBean.setVillageId(performaMasterForm.getVillageId());
					schemeSourcePerformaBean.setSchemeSource(performaMasterForm.getSchemeSource());
					schemeSourcePerformaBean.setSchemeType(performaMasterForm.getSchemeType());
					schemeSourcePerformaBean.setCommissioningDate(MisUtility.convertStringSqlDate(performaMasterForm.getDateOfComm()));
					schemeSourcePerformaBean.setProgId(performaMasterForm.getProgId());
					schemeSourcePerformaBean.setServiceLevel(performaMasterForm.getServiceLevel());
					if(MisUtility.ifEmptyField(performaMasterForm.getSchemeUpgraded())){
						schemeSourcePerformaBean.setSchemeUpgraded(MisUtility.convertStringSqlDate(performaMasterForm.getSchemeUpgraded()));
					}
					if(MisUtility.ifEmptyField(performaMasterForm.getSchemeExpenditure())){
						schemeSourcePerformaBean.setSchemeExpenditure(Double.parseDouble(performaMasterForm.getSchemeExpenditure()));
					}
					
					if(MisUtility.ifEmptyField(performaMasterForm.getDepthOfTubewell())){
						schemeSourcePerformaBean.setTubewellDepth(Double.parseDouble(performaMasterForm.getDepthOfTubewell()));
					}
					
					if(MisUtility.ifEmptyField(performaMasterForm.getSizeOfTubewell())){
						schemeSourcePerformaBean.setTubewellSize(performaMasterForm.getSizeOfTubewell());
					}
					if(MisUtility.ifEmptyField(performaMasterForm.getYearOfDrilling())){
				
						schemeSourcePerformaBean.setDrillingYear(MisUtility.convertStringSqlDate(performaMasterForm.getYearOfDrilling()));
					}
					if(MisUtility.ifEmptyField(performaMasterForm.getPresentDis())){
						schemeSourcePerformaBean.setPresentDischarge(Double.parseDouble(performaMasterForm.getPresentDis()));
					}
					if(MisUtility.ifEmptyField(performaMasterForm.getDischargeComm())){
						schemeSourcePerformaBean.setDischargeComm(Double.parseDouble(performaMasterForm.getDischargeComm()));
					}
					
					if(MisUtility.ifEmptyField(performaMasterForm.getPresentSpringLevel())){
						schemeSourcePerformaBean.setPresentSpringLevel(Double.parseDouble(performaMasterForm.getPresentSpringLevel()));
					}
					
					schemeSourcePerformaBean.setMachineryInstallation(MisUtility.convertStringSqlDate(performaMasterForm.getInstallation_of_new_machinery()));
					if(MisUtility.ifEmptyField(performaMasterForm.getCapMachinery())){
						schemeSourcePerformaBean.setMachineryCapacity(Double.parseDouble(performaMasterForm.getCapMachinery()));
					}
					schemeSourcePerformaBean.setInletChannelType(performaMasterForm.getInletType());
					
					if(MisUtility.ifEmptyField(performaMasterForm.getInletLenght())){
						schemeSourcePerformaBean.setInletChannelLength(Double.parseDouble(performaMasterForm.getInletLenght()));
					}
					schemeSourcePerformaBean.setPipeType(performaMasterForm.getPipeType());
					
					if(MisUtility.ifEmptyField(performaMasterForm.getCapacitySSTank())){
						schemeSourcePerformaBean.setSsTankCapacity(Double.parseDouble(performaMasterForm.getCapacitySSTank()));
					}
					
					if(MisUtility.ifEmptyField(performaMasterForm.getCapacityHLTank())){
						schemeSourcePerformaBean.setHlTankCapacity(Double.parseDouble(performaMasterForm.getCapacityHLTank()));
					}
					
					if(MisUtility.ifEmptyField(performaMasterForm.getCapacityCWTank())){
						schemeSourcePerformaBean.setCwTankCapacity(Double.parseDouble(performaMasterForm.getCapacityCWTank()));
					}
					
					schemeSourcePerformaBean.setFilterationType(performaMasterForm.getFiltertionType());
					
					if(MisUtility.ifEmptyField(performaMasterForm.getFiltertionCapacity())){
						schemeSourcePerformaBean.setFiltertionCapacity(Double.parseDouble(performaMasterForm.getFiltertionCapacity()));
					}
					
					if(MisUtility.ifEmptyField(performaMasterForm.getFiltertionNo())){
						schemeSourcePerformaBean.setFiltertionNo(Double.parseDouble(performaMasterForm.getFiltertionNo()));
					}
					
					if(MisUtility.ifEmptyField(performaMasterForm.getCapacityRawWater())){
						schemeSourcePerformaBean.setRawWaterCaps(Double.parseDouble(performaMasterForm.getCapacityRawWater()));
					}
					
					if(MisUtility.ifEmptyField(performaMasterForm.getCapacityClearWater())){
						schemeSourcePerformaBean.setClearWaterCaps(Double.parseDouble(performaMasterForm.getCapacityClearWater()));
					}
					
					//ohsr
					
					if(MisUtility.ifEmptyField(performaMasterForm.getNoOfOhsr())){
						schemeSourcePerformaBean.setOhsrNo(Double.parseDouble(performaMasterForm.getNoOfOhsr()));
					}
					schemeSourcePerformaBean.setOhsrConsDate(MisUtility.convertStringSqlDate(performaMasterForm.getoHSR_construction_date()));
					
					if(MisUtility.ifEmptyField(performaMasterForm.getoHSRCapcity())){
						schemeSourcePerformaBean.setOhsrCaps(Double.parseDouble(performaMasterForm.getoHSRCapcity()));
					}
					if(MisUtility.ifEmptyField(performaMasterForm.getoHSRCTDia())){
						schemeSourcePerformaBean.setOhsr_ct(Double.parseDouble(performaMasterForm.getoHSRCTDia()));
					}
					if(MisUtility.ifEmptyField(performaMasterForm.getoHSRCTDepth())){
						schemeSourcePerformaBean.setOhsr_ct_depth(Double.parseDouble(performaMasterForm.getoHSRCTDepth()));
					}
					if(MisUtility.ifEmptyField(performaMasterForm.getoHSRUGSRDia())){
						schemeSourcePerformaBean.setOhsr_ugsr(Double.parseDouble(performaMasterForm.getoHSRUGSRDia()));
					}
					if(MisUtility.ifEmptyField(performaMasterForm.getoHSRUGSRDepth())){
						schemeSourcePerformaBean.setOhsr_ugsr_depth(Double.parseDouble(performaMasterForm.getoHSRUGSRDepth()));
					}
					
					if(MisUtility.ifEmptyField(performaMasterForm.getoHSRWorkingCondition())){
						schemeSourcePerformaBean.setOhsrWorkingCondition(Integer.parseInt(performaMasterForm.getoHSRWorkingCondition()));
					}
					schemeSourcePerformaBean.setOhsrConditionIfNo(performaMasterForm.getoHSRCondition());
					schemeSourcePerformaBean.setDismantling_received(performaMasterForm.getoHSRDismantling());
					schemeSourcePerformaBean.setOhsr_full_supply_level(performaMasterForm.getoHSRFullSuplyLvl());
					schemeSourcePerformaBean.setDisinfectionType(performaMasterForm.getDisinfectionType());
					schemeSourcePerformaBean.setDisinfectionInstalTime(MisUtility.convertStringSqlDate(performaMasterForm.getDisinfection_Instalation_time()));
					schemeSourcePerformaBean.setDisnPresentStatus(performaMasterForm.getDisinfectionPrStatus());
					schemeSourcePerformaBean.setSchemeOpertedBy(performaMasterForm.getSchemeOperatedBy());
					schemeSourcePerformaBean.setDwss_operated_arrangement(performaMasterForm.getStaffScheme());
					if(MisUtility.ifEmptyField(performaMasterForm.getSanctionedLoad())){
						schemeSourcePerformaBean.setSanctionedLoad(Double.parseDouble(performaMasterForm.getSanctionedLoad()));
					}
					
					if(MisUtility.ifEmptyField(performaMasterForm.getPendingBill3103())){
						schemeSourcePerformaBean.setPendingBill3103(Double.parseDouble(performaMasterForm.getPendingBill3103()));
					}
					
					if(MisUtility.ifEmptyField(performaMasterForm.getPendingBill3006())){
						schemeSourcePerformaBean.setPendingBill3006(Double.parseDouble(performaMasterForm.getPendingBill3006()));
					}
					if(MisUtility.ifEmptyField(performaMasterForm.getAvgMonthBillofWSS())){
						schemeSourcePerformaBean.setAvgMonthBill(Double.parseDouble(performaMasterForm.getAvgMonthBillofWSS()));
					}
				
				performaBeans.add(schemeSourcePerformaBean);
			}
		
		return performaBeans;
	}
	
	
	private List<PerformaFiveYearPlanBean> populateFiveYearPlanBean(PerformaMasterForm performaMasterForm, MISSessionBean misSessionBean, String status){
		List<PerformaFiveYearPlanBean> performaBeans = new ArrayList<PerformaFiveYearPlanBean>();
		MISAuditBean misAuditBean = new MISAuditBean();

	
			if(MISConstants.MASTER_STATUS_VERIFIED.equalsIgnoreCase(status)){
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			} 
			PerformaFiveYearPlanBean performaBean=null;
			if(performaMasterForm.getUnderProcessSelection().equalsIgnoreCase("No")){
			@SuppressWarnings("unchecked")
			
			Collection<FiveYearPlanGridBean> fiveyearplan=performaMasterForm.getFiveYearPlanGridBean().getDeletedData();
		
			Collection<FiveYearPlanGridBean> fiverYearPlanGrid=performaMasterForm.getFiveYearPlanGridBean().getAddedData();
			System.out.println("size after addition--->"+fiverYearPlanGrid.size());
			System.out.println("data after addition -----in grid---->"+fiverYearPlanGrid.toString());

			if(fiverYearPlanGrid!=null && fiverYearPlanGrid.size()>0){
				
				for(FiveYearPlanGridBean gridBean:fiverYearPlanGrid){
					
					performaBean=new PerformaFiveYearPlanBean();
					performaBean.setSchemeId(performaMasterForm.getSchemeId());
					performaBean.setVillageId(gridBean.getVilllgId());
					performaBean.setClear_water_tank(gridBean.getClear_water_tank1());
					
					if(MisUtility.ifEmptyField(gridBean.getIndependentNewWSS())){
						performaBean.setIndependent_new_wss(Double.parseDouble(gridBean.getIndependentNewWSS()));
					}
				
					if(MisUtility.ifEmptyField(gridBean.getUpgradationexistingWSS())){
						performaBean.setUpgradation_of_existing_wss(Double.parseDouble(gridBean.getUpgradationexistingWSS()));
					}
					
					if(MisUtility.ifEmptyField(gridBean.getInstltion_Wtr_Treatment_plant1())){
						performaBean.setInstltion_wtr_treatment_plant(gridBean.getInstltion_Wtr_Treatment_plant1());
					}
					performaBean.setSource_of_wss(gridBean.getSource_of_WSS1());
					performaBean.setShifted_to_canal_from_other(gridBean.getShifted_to_canal_from_Other1());
					
					if(MisUtility.ifEmptyField(gridBean.getDriling_of_new_tubewell_machinery_capacity1())){
						performaBean.setDriling_of_new_tubewell_machinery_capacity(Double.parseDouble(gridBean.getDriling_of_new_tubewell_machinery_capacity1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getDriling_of_new_tubewell_machinery_size1())){
						performaBean.setDriling_of_new_tubewell_machinery_size(Double.parseDouble(gridBean.getDriling_of_new_tubewell_machinery_size1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getDriling_of_new_tubewell_machinery_cost1())){
						performaBean.setDriling_of_new_tubewell_machinery_cost(Double.parseDouble(gridBean.getDriling_of_new_tubewell_machinery_cost1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getDriling_of_new_tubewell_machinery_depth1())){
						performaBean.setDriling_of_new_tubewell_machinery_depth(Double.parseDouble(gridBean.getDriling_of_new_tubewell_machinery_depth1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getCanal_based_Inlet_channel_length1())){
						performaBean.setCanal_based_inlet_channel_length(Double.parseDouble(gridBean.getCanal_based_Inlet_channel_length1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getCanal_based_Inlet_channel_Size_of_pipe1())){
						performaBean.setCanal_based_inlet_channel_size_of_pipe(Double.parseDouble(gridBean.getCanal_based_Inlet_channel_Size_of_pipe1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getCanal_based_s_and_s_Capacity1())){
						performaBean.setCanal_based_s_and_s_capacity(Double.parseDouble(gridBean.getCanal_based_s_and_s_Capacity1()));
					}
					performaBean.setCanal_based_filteration_plan_type(gridBean.getCanal_based_Filteration_Plan_type1());
					
					if(MisUtility.ifEmptyField(gridBean.getCanal_based_s_and_s_Capacity1())){
						performaBean.setCanal_based_filteration_plan_capacity(Double.parseDouble(gridBean.getCanal_based_Filteration_Plan_capacity1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getCanal_based_Cost1())){
						performaBean.setCanal_based_cost(Double.parseDouble(gridBean.getCanal_based_Cost1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getClear_water_tank1())){
						performaBean.setClear_water_tank(gridBean.getClear_water_tank1());
					}
					if(MisUtility.ifEmptyField(gridBean.getoHSR_Capacity1())){
						performaBean.setOhsr_capacity(Double.parseDouble(gridBean.getoHSR_Capacity1()));
					}
					
					//
					if(MisUtility.ifEmptyField(gridBean.getoHSR_CT_Depth1())){
						performaBean.setOhsr_ct_dep(Double.parseDouble(gridBean.getoHSR_CT_Depth1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getoHSRCTDiameter1())){
						performaBean.setOhsr_ct_dia(Double.parseDouble(gridBean.getoHSRCTDiameter1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getoHSR_UGS_RDia1())){
						performaBean.setOhsr_ugsr_dia(Double.parseDouble(gridBean.getoHSR_UGS_RDia1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getoHSR_UGSR_Depth1())){
						performaBean.setOhsr_ugsr_dep(Double.parseDouble(gridBean.getoHSR_UGSR_Depth1()));
					}
					//
					
					if(MisUtility.ifEmptyField(gridBean.getoHSR_Full_Supply_Level1())){
						performaBean.setOhsr_full_supply_level(Double.parseDouble(gridBean.getoHSR_Full_Supply_Level1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getOHSR_Cost())){
						performaBean.setOhsr_cost(Double.parseDouble(gridBean.getOHSR_Cost()));
					}
					performaBean.setOther_structures_at_waterworks(gridBean.getOther_structures_at_waterworks1());
					
					if(MisUtility.ifEmptyField(gridBean.getOther_structures_at_waterworks_Cost1())){
						performaBean.setOther_structures_at_waterworks_cost(Double.parseDouble(gridBean.getOther_structures_at_waterworks_Cost1()));
					}
					performaBean.setDistribution_wss_to_village_type(gridBean.getDistribution_WSS_to_village_type1());
					
					if(MisUtility.ifEmptyField(gridBean.getDistribution_WSS_to_village_length1())){
						performaBean.setDistribution_wss_to_village_length(Double.parseDouble(gridBean.getDistribution_WSS_to_village_length1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getDistribution_WSS_within_village_type_pvc1())){

						performaBean.setPvc(Double.parseDouble(gridBean.getDistribution_WSS_within_village_type_pvc1()));
					}
					
					if(MisUtility.ifEmptyField(gridBean.getDistribution_WSS_within_village_type_gi1())){
						performaBean.setNetwork_gi(Double.parseDouble(gridBean.getDistribution_WSS_within_village_type_gi1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getDistribution_WSS_within_village_type_di_ms1())){
						performaBean.setMs_di_ci(Double.parseDouble(gridBean.getDistribution_WSS_within_village_type_di_ms1()));
					}
					
					if(MisUtility.ifEmptyField(gridBean.getDistribution_Cost1())){
						performaBean.setDistribution_cost(Double.parseDouble(gridBean.getDistribution_Cost1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getNo_of_connections_100_mtr1())){
						performaBean.setNo_of_connections_100_mtr(Double.parseDouble(gridBean.getNo_of_connections_100_mtr1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getNo_of_mtrs_100_mtr1())){
						performaBean.setNo_of_mtrs_100_mtr(Double.parseDouble(gridBean.getNo_of_mtrs_100_mtr1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getCost_100_mtr1())){
						performaBean.setCost_100_mtr(Double.parseDouble(gridBean.getCost_100_mtr1()));
					}
					performaBean.setDisinfection_unit_type(gridBean.getDisinfection_Unit_Type1());
					
					if(MisUtility.ifEmptyField(gridBean.getDisinfection_Unit_Cost1())){
						performaBean.setDisinfection_unit_cost(Double.parseDouble(gridBean.getDisinfection_Unit_Cost1()));
					}
					performaBean.setWater_treatment_plant_in_case_of_quality_village_type(gridBean.getWater_Treatment_plant_in_case_of_quality_village_type1());
					
					if(MisUtility.ifEmptyField(gridBean.getWater_Treatment_plant_in_case_of_quality_village_capacity1())){
						performaBean.setWater_treatment_plant_in_case_of_quality_village_capacity(Double.parseDouble(gridBean.getWater_Treatment_plant_in_case_of_quality_village_capacity1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getWater_Treatment_plant_in_case_of_quality_village_Cost1())){
						performaBean.setWater_treatment_plant_in_case_of_quality_village_cost(Double.parseDouble(gridBean.getWater_Treatment_plant_in_case_of_quality_village_Cost1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getBulk_Water_meter_cost1())){
						performaBean.setBulk_water_meter_cost(Double.parseDouble(gridBean.getBulk_Water_meter_cost1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getBulk_Water_meter_no1())){
						performaBean.setBulk_water_meter_no(Double.parseDouble(gridBean.getBulk_Water_meter_no1()));
					}
					if(MisUtility.ifEmptyField(gridBean.getExtension_Sanction_of_new_electric_connection_cost1())){
						performaBean.setExtension_sanction_of_new_electric_connection_cost(Double.parseDouble(gridBean.getExtension_Sanction_of_new_electric_connection_cost1()));
					}
					performaBeans.add(performaBean);
				}
			}
			
			}else{
				//TODO
				//Set the Four Values
				performaBean=new PerformaFiveYearPlanBean();
				performaBean.setSchemeId(performaMasterForm.getSchemeId());
				performaBean.setVillageId(performaMasterForm.getVillageId());
				if(MisUtility.ifEmptyField(performaMasterForm.getDateOfComplition())){
					performaBean.setDateOfComplition(MisUtility.convertStringSqlDate(performaMasterForm.getDateOfComplition()));
				}
				if(MisUtility.ifEmptyField(performaMasterForm.getRemarksdateOfComplition())){
					performaBean.setRemarksdateOfComplition(performaMasterForm.getRemarksdateOfComplition());
				}
				performaBeans.add(performaBean);
				//Set Values to Bean
				//Set the Bean to DAO
			}
		return performaBeans;
	}
	private List<VillagePerformaBean> populateHeaderBean(PerformaMasterForm performaMasterForm, MISSessionBean misSessionBean, String status){
		List<VillagePerformaBean> performaBeans = new ArrayList<VillagePerformaBean>();

		
			VillagePerformaBean performaBean=null;
			@SuppressWarnings("unchecked")
			Collection<VillagePerformaGridBean> villagePerformaGrid=performaMasterForm.getVillagePerformaGrid().getModifiedData();
			
			for(VillagePerformaGridBean performaGrid:villagePerformaGrid){
				performaBean=new VillagePerformaBean();
				performaBean.setVillageId(performaGrid.getVillageId());
				performaBean.setVillageName(performaGrid.getVillageName());
				performaBean.setSchemeId(performaMasterForm.getSchemeId());
				System.out.println("Value of Hadbast No is:" + performaGrid.getHadBastNo());
				if(MisUtility.ifEmptyField(performaGrid.getHadBastNo())){
					//remove digits after decimal .
					//Remove Decimal from string
					//Parse String to Int 
					//performaBean.setHadBastNo(Integer.parseInt(performaGrid.getHadBastNo().trim()));
					//removeDecimalAndDigits(performaGrid.getHadBastNo().trim() );
					performaBean.setHadBastNo(Integer.parseInt(removeDecimalAndDigits(performaGrid.getHadBastNo().trim())));
					
					//str = str.replaceAll("\\..*", "");
				}
				if(MisUtility.ifEmptyField(performaGrid.getNoOfHabitation())){
				performaBean.setHabitationNo(Integer.parseInt(performaGrid.getNoOfHabitation().trim()));
				}
				performaBean.setHabitationType(performaGrid.getTypeOfHabitation());
				performaBean.setHabitationName(performaGrid.getNameOfHabitation());
				performaBean.setAssemblyCons(performaGrid.getAssemblyConstituency());
				
				if(MisUtility.ifEmptyField(performaGrid.getPoputlationTotal())){
					performaBean.setTotalPopulation(Integer.parseInt(performaGrid.getPoputlationTotal().trim()));
				}
				if(MisUtility.ifEmptyField(performaGrid.getPopulationSc())){
					performaBean.setScPopulation(Integer.parseInt(performaGrid.getPopulationSc().trim()));
				}
				if(MisUtility.ifEmptyField(performaGrid.getNoOfHouseholdsTotal())){
					performaBean.setTotalHouseholds(Integer.parseInt(performaGrid.getNoOfHouseholdsTotal().trim()));
				}
				
				if(MisUtility.ifEmptyField(performaGrid.getNoOfHouseholdsSc())){
					performaBean.setScHouseholds(Integer.parseInt(performaGrid.getNoOfHouseholdsSc().trim()));
				}
				
				
					if(MisUtility.ifEmptyField(performaGrid.getPanchayatGhr())){
						performaBean.setPanchayatGhr(Integer.parseInt(performaGrid.getPanchayatGhr().trim()));
					}
				
				if(MisUtility.ifEmptyField(performaGrid.getCommCenterDhar())){
					performaBean.setComCenter(Integer.parseInt(performaGrid.getCommCenterDhar().trim()));
				}
				if(performaGrid.getIsAngarwaries().equalsIgnoreCase("Yes")){
					if(MisUtility.ifEmptyField(performaGrid.getAngarwaries())){
						performaBean.setAngarwaries(Integer.parseInt(performaGrid.getAngarwaries().trim()));
					}
					if(MisUtility.ifEmptyField(performaGrid.getGovtBuildings())){
						performaBean.setGovtBuilding(Integer.parseInt(performaGrid.getGovtBuildings().trim()));
				  }
					if(MisUtility.ifEmptyField(performaGrid.getPrivateBuildings())){
						performaBean.setPrivateBuilding(Integer.parseInt(performaGrid.getPrivateBuildings()));
				  }
				}
				
				if(performaGrid.getIsGovtSchools().equalsIgnoreCase("Yes")){
					if(MisUtility.ifEmptyField(performaGrid.getGovtSchools())){
						performaBean.setGovtSchool(Integer.parseInt(performaGrid.getGovtSchools()));
					}
				}
				if(performaGrid.getIsHealthCntr().equalsIgnoreCase("Yes")){

					if(MisUtility.ifEmptyField(performaGrid.getHealthCntr())){
						performaBean.setHealthCenter(Integer.parseInt(performaGrid.getHealthCntr()));
					}
				}
				if(performaGrid.getIsVillagePonds().equalsIgnoreCase("Yes")){
					if(MisUtility.ifEmptyField(performaGrid.getVillagePonds())){
						performaBean.setVillagePonds(Integer.parseInt(performaGrid.getVillagePonds()));
					}
				
					if(MisUtility.ifEmptyField(performaGrid.getAreaSqrMtr())){
						performaBean.setAreaSquareMtr(Double.parseDouble(performaGrid.getAreaSqrMtr()));
					}
				}
				if(performaGrid.getSewerageScheme().equalsIgnoreCase("Yes")){
					performaBean.setSew_connectio_sc_households(Integer.parseInt(performaGrid.getSewerageConSc()));
				}
				if(MisUtility.ifEmptyField(performaGrid.getFemaleGnPop())){
					performaBean.setFemale_gen_population(Integer.parseInt(performaGrid.getFemaleGnPop()));
				}
				if(MisUtility.ifEmptyField(performaGrid.getFemaleScPop())){
					performaBean.setFemale_sc_population(Integer.parseInt(performaGrid.getFemaleScPop()));
				}
				if(MisUtility.ifEmptyField(performaGrid.getHabitationWaterSupply())){
					performaBean.setSewrahabitation_distance_from_water_supply_schge(Double.parseDouble(performaGrid.getHabitationWaterSupply()));
				}
				if(MisUtility.ifEmptyField(performaGrid.getWaterConSc())){
					performaBean.setWater_connectio_sc_households(Integer.parseInt(performaGrid.getWaterConSc()));
				}
				performaBeans.add(performaBean);
			}
		
		
		return performaBeans;
	}

	private String removeDecimalAndDigits(String hadBastNo) {
		// TODO Auto-generated method stub
		System.out.println("##=========Returning From Function Remove Digits and Decimal"+hadBastNo.replaceAll("\\..*", ""));
		return hadBastNo.replaceAll("\\..*", "");
	}

}
