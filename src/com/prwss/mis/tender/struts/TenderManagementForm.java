package com.prwss.mis.tender.struts;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class TenderManagementForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8733748085127975226L;
	private String tenderNo;
	private String villageId;
	private boolean submitForm;
	private String blockId;
	private String userLocation;
	private String tenderNotificationDate;
	private String openingDate;
	private String closingDate;
	private String typeOfTender;
	private String eTenderRefNo;
	private String postPrior;
	private BigDecimal estimatedTenderAmount;
	private String modeOfProcurement;
	private String planId;
	private long procurementId;
	private String schemeCode;
	private String packageId;
	private String packageDescription;
	private String w1EstimateDateofCompletion;
	private String w1ActualDateofCompletion;
	private String w1StatusOfActivity;
	private String w2EstimateDateofCompletion;
	private String w2ActualDateofCompletion;
	private String w2StatusOfActivity;
	private String w3EstimateDateofCompletion;
	private String w3ActualDateofCompletion;
	private String w3StatusOfActivity;
	private String w4EstimateDateofCompletion;
	private String w4ActualDateofCompletion;
	private String w4StatusOfActivity;
	private String w5EstimateDateofCompletion;
	private String w5ActualDateofCompletion;
	private String w5StatusOfActivity;
	private String w6EstimateDateofCompletion;
	private String w6ActualDateofCompletion;
	private String w6StatusOfActivity;
	private String w7EstimateDateofCompletion;
	private String w7ActualDateofCompletion;
	private String w7StatusOfActivity;
	
	
	private String c1EstimateDateofCompletion;
	private String c1ActualDateofCompletion;
	private String c1StatusOfActivity;
	private String c2EstimateDateofCompletion;
	private String c2ActualDateofCompletion;
	private String c2StatusOfActivity;
	private String c3EstimateDateofCompletion;
	private String c3ActualDateofCompletion;
	private String c3StatusOfActivity;
	private String c4EstimateDateofCompletion;
	private String c4ActualDateofCompletion;
	private String c4StatusOfActivity;
	private String c5EstimateDateofCompletion;
	private String c5ActualDateofCompletion;
	private String c5StatusOfActivity;
	private String c6EstimateDateofCompletion;
	private String c6ActualDateofCompletion;
	private String c6StatusOfActivity;
	private String c7EstimateDateofCompletion;
	private String c7ActualDateofCompletion;
	private String c7StatusOfActivity;
	private String c8EstimateDateofCompletion;
	private String c8ActualDateofCompletion;
	private String c8StatusOfActivity;
	private String c9EstimateDateofCompletion;
	private String c9ActualDateofCompletion;
	private String c9StatusOfActivity;
	private String c10EstimateDateofCompletion;
	private String c10ActualDateofCompletion;
	private String c10StatusOfActivity;
	private String c11EstimateDateofCompletion;
	private String c11ActualDateofCompletion;
	private String c11StatusOfActivity;
	private String c12EstimateDateofCompletion;
	private String c12ActualDateofCompletion;
	private String c12StatusOfActivity;
	private String c13EstimateDateofCompletion;
	private String c13ActualDateofCompletion;
	private String c13StatusOfActivity;
	
	
	private String g1EstimateDateofCompletion;
	private String g1ActualDateofCompletion;
	private String g1StatusOfActivity;
	private String g2EstimateDateofCompletion;
	private String g2ActualDateofCompletion;
	private String g2StatusOfActivity;
	private String g3EstimateDateofCompletion;
	private String g3ActualDateofCompletion;
	private String g3StatusOfActivity;
	private String g4EstimateDateofCompletion;
	private String g4ActualDateofCompletion;
	private String g4StatusOfActivity;
	
	
	private String s1EstimateDateofCompletion;
	private String s1ActualDateofCompletion;
	private String s1StatusOfActivity;
	private String s2EstimateDateofCompletion;
	private String s2ActualDateofCompletion;
	private String s2StatusOfActivity;
	private String s3EstimateDateofCompletion;
	private String s3ActualDateofCompletion;
	private String s3StatusOfActivity;
	private String s4EstimateDateofCompletion;
	private String s4ActualDateofCompletion;
	private String s4StatusOfActivity;
	private String s5EstimateDateofCompletion;
	private String s5ActualDateofCompletion;
	private String s5StatusOfActivity;
	private String s6EstimateDateofCompletion;
	private String s6ActualDateofCompletion;
	private String s6StatusOfActivity;
	private String s7EstimateDateofCompletion;
	private String s7ActualDateofCompletion;
	private String s7StatusOfActivity;
	private String locationId;
	
	private String dateAdvertisingShortlisting;
	
	private String rfpDraftToBankDate;
	
	private String bankNocForTorDate;
	
	private String bankNocForShortlistDate;
	
	private String bankNocForRfpDate;
	
	private String rfpIssuedDate;
	
	private String torShortlistFinalizedDate;
	
	private String bankNocTechnicalDate;
	
	private String bankNocCombinedDate;
	
	private String evaluationFinalTechnicalDate;
	
	private String evaluationFinalCombinedDate;
	
	private String nocBankDraftDate;
	
	private String evaluationFinalDraftDate;
	
	private String bankNocFinalContractDate;
	
	private String evaluationFinalContractDate;
	
	private String proposalReciptDate;
	private String serviceCompletionDate;
	
	private String bidSanctionDate;
	private String bidSanctionNumber;
	
	private String rebidApprovalDate;
	private String rebidApprovalNumber;
	
	private String proposalReciptDateTechnical;
	private String proposalReciptDateFinancial;
	private String projectCode;
	
	 
	
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getPostPrior() {
		return postPrior;
	}
	public void setPostPrior(String postPrior) {
		this.postPrior = postPrior;
	}
	public String getBidSanctionDate() {
		return bidSanctionDate;
	}
	public void setBidSanctionDate(String bidSanctionDate) {
		this.bidSanctionDate = bidSanctionDate;
	}
	public String getBidSanctionNumber() {
		return bidSanctionNumber;
	}
	public void setBidSanctionNumber(String bidSanctionNumber) {
		this.bidSanctionNumber = bidSanctionNumber;
	}
	public String getTenderNo() {
		return tenderNo;
	}
	public void setTenderNo(String tenderNo) {
		this.tenderNo = tenderNo;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public boolean isSubmitForm() {
		return submitForm;
	}
	public void setSubmitForm(boolean submitForm) {
		this.submitForm = submitForm;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getTenderNotificationDate() {
		return tenderNotificationDate;
	}
	public void setTenderNotificationDate(String tenderNotificationDate) {
		this.tenderNotificationDate = tenderNotificationDate;
	}
	public String getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}
	public String getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}
	public String getTypeOfTender() {
		return typeOfTender;
	}
	public void setTypeOfTender(String typeOfTender) {
		this.typeOfTender = typeOfTender;
	}
	public String geteTenderRefNo() {
		return eTenderRefNo;
	}
	public void seteTenderRefNo(String eTenderRefNo) {
		this.eTenderRefNo = eTenderRefNo;
	}
	public String getModeOfProcurement() {
		return modeOfProcurement;
	}
	public void setModeOfProcurement(String modeOfProcurement) {
		this.modeOfProcurement = modeOfProcurement;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public long getProcurementId() {
		return procurementId;
	}
	public void setProcurementId(long procurementId) {
		this.procurementId = procurementId;
	}
	public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getW1EstimateDateofCompletion() {
		return w1EstimateDateofCompletion;
	}
	public void setW1EstimateDateofCompletion(String w1EstimateDateofCompletion) {
		this.w1EstimateDateofCompletion = w1EstimateDateofCompletion;
	}
	public String getW1ActualDateofCompletion() {
		return w1ActualDateofCompletion;
	}
	public void setW1ActualDateofCompletion(String w1ActualDateofCompletion) {
		this.w1ActualDateofCompletion = w1ActualDateofCompletion;
	}
	public String getW1StatusOfActivity() {
		return w1StatusOfActivity;
	}
	public void setW1StatusOfActivity(String w1StatusOfActivity) {
		this.w1StatusOfActivity = w1StatusOfActivity;
	}
	public String getW2EstimateDateofCompletion() {
		return w2EstimateDateofCompletion;
	}
	public void setW2EstimateDateofCompletion(String w2EstimateDateofCompletion) {
		this.w2EstimateDateofCompletion = w2EstimateDateofCompletion;
	}
	public String getW2ActualDateofCompletion() {
		return w2ActualDateofCompletion;
	}
	public void setW2ActualDateofCompletion(String w2ActualDateofCompletion) {
		this.w2ActualDateofCompletion = w2ActualDateofCompletion;
	}
	public String getW2StatusOfActivity() {
		return w2StatusOfActivity;
	}
	public void setW2StatusOfActivity(String w2StatusOfActivity) {
		this.w2StatusOfActivity = w2StatusOfActivity;
	}
	public String getW3EstimateDateofCompletion() {
		return w3EstimateDateofCompletion;
	}
	public void setW3EstimateDateofCompletion(String w3EstimateDateofCompletion) {
		this.w3EstimateDateofCompletion = w3EstimateDateofCompletion;
	}
	public String getW3ActualDateofCompletion() {
		return w3ActualDateofCompletion;
	}
	public void setW3ActualDateofCompletion(String w3ActualDateofCompletion) {
		this.w3ActualDateofCompletion = w3ActualDateofCompletion;
	}
	public String getW3StatusOfActivity() {
		return w3StatusOfActivity;
	}
	public void setW3StatusOfActivity(String w3StatusOfActivity) {
		this.w3StatusOfActivity = w3StatusOfActivity;
	}
	public String getW4EstimateDateofCompletion() {
		return w4EstimateDateofCompletion;
	}
	public void setW4EstimateDateofCompletion(String w4EstimateDateofCompletion) {
		this.w4EstimateDateofCompletion = w4EstimateDateofCompletion;
	}
	public String getW4ActualDateofCompletion() {
		return w4ActualDateofCompletion;
	}
	public void setW4ActualDateofCompletion(String w4ActualDateofCompletion) {
		this.w4ActualDateofCompletion = w4ActualDateofCompletion;
	}
	public String getW4StatusOfActivity() {
		return w4StatusOfActivity;
	}
	public void setW4StatusOfActivity(String w4StatusOfActivity) {
		this.w4StatusOfActivity = w4StatusOfActivity;
	}
	public String getW5EstimateDateofCompletion() {
		return w5EstimateDateofCompletion;
	}
	public void setW5EstimateDateofCompletion(String w5EstimateDateofCompletion) {
		this.w5EstimateDateofCompletion = w5EstimateDateofCompletion;
	}
	public String getW5ActualDateofCompletion() {
		return w5ActualDateofCompletion;
	}
	public void setW5ActualDateofCompletion(String w5ActualDateofCompletion) {
		this.w5ActualDateofCompletion = w5ActualDateofCompletion;
	}
	public String getW5StatusOfActivity() {
		return w5StatusOfActivity;
	}
	public void setW5StatusOfActivity(String w5StatusOfActivity) {
		this.w5StatusOfActivity = w5StatusOfActivity;
	}
	public String getW6EstimateDateofCompletion() {
		return w6EstimateDateofCompletion;
	}
	public void setW6EstimateDateofCompletion(String w6EstimateDateofCompletion) {
		this.w6EstimateDateofCompletion = w6EstimateDateofCompletion;
	}
	public String getW6ActualDateofCompletion() {
		return w6ActualDateofCompletion;
	}
	public void setW6ActualDateofCompletion(String w6ActualDateofCompletion) {
		this.w6ActualDateofCompletion = w6ActualDateofCompletion;
	}
	public String getW6StatusOfActivity() {
		return w6StatusOfActivity;
	}
	public void setW6StatusOfActivity(String w6StatusOfActivity) {
		this.w6StatusOfActivity = w6StatusOfActivity;
	}
	public String getW7EstimateDateofCompletion() {
		return w7EstimateDateofCompletion;
	}
	public void setW7EstimateDateofCompletion(String w7EstimateDateofCompletion) {
		this.w7EstimateDateofCompletion = w7EstimateDateofCompletion;
	}
	public String getW7ActualDateofCompletion() {
		return w7ActualDateofCompletion;
	}
	public void setW7ActualDateofCompletion(String w7ActualDateofCompletion) {
		this.w7ActualDateofCompletion = w7ActualDateofCompletion;
	}
	public String getW7StatusOfActivity() {
		return w7StatusOfActivity;
	}
	public void setW7StatusOfActivity(String w7StatusOfActivity) {
		this.w7StatusOfActivity = w7StatusOfActivity;
	}
	public String getC1EstimateDateofCompletion() {
		return c1EstimateDateofCompletion;
	}
	public void setC1EstimateDateofCompletion(String c1EstimateDateofCompletion) {
		this.c1EstimateDateofCompletion = c1EstimateDateofCompletion;
	}
	public String getC1ActualDateofCompletion() {
		return c1ActualDateofCompletion;
	}
	public void setC1ActualDateofCompletion(String c1ActualDateofCompletion) {
		this.c1ActualDateofCompletion = c1ActualDateofCompletion;
	}
	public String getC1StatusOfActivity() {
		return c1StatusOfActivity;
	}
	public void setC1StatusOfActivity(String c1StatusOfActivity) {
		this.c1StatusOfActivity = c1StatusOfActivity;
	}
	public String getC2EstimateDateofCompletion() {
		return c2EstimateDateofCompletion;
	}
	public void setC2EstimateDateofCompletion(String c2EstimateDateofCompletion) {
		this.c2EstimateDateofCompletion = c2EstimateDateofCompletion;
	}
	public String getC2ActualDateofCompletion() {
		return c2ActualDateofCompletion;
	}
	public void setC2ActualDateofCompletion(String c2ActualDateofCompletion) {
		this.c2ActualDateofCompletion = c2ActualDateofCompletion;
	}
	public String getC2StatusOfActivity() {
		return c2StatusOfActivity;
	}
	public void setC2StatusOfActivity(String c2StatusOfActivity) {
		this.c2StatusOfActivity = c2StatusOfActivity;
	}
	public String getC3EstimateDateofCompletion() {
		return c3EstimateDateofCompletion;
	}
	public void setC3EstimateDateofCompletion(String c3EstimateDateofCompletion) {
		this.c3EstimateDateofCompletion = c3EstimateDateofCompletion;
	}
	public String getC3ActualDateofCompletion() {
		return c3ActualDateofCompletion;
	}
	public void setC3ActualDateofCompletion(String c3ActualDateofCompletion) {
		this.c3ActualDateofCompletion = c3ActualDateofCompletion;
	}
	public String getC3StatusOfActivity() {
		return c3StatusOfActivity;
	}
	public void setC3StatusOfActivity(String c3StatusOfActivity) {
		this.c3StatusOfActivity = c3StatusOfActivity;
	}
	public String getC4EstimateDateofCompletion() {
		return c4EstimateDateofCompletion;
	}
	public void setC4EstimateDateofCompletion(String c4EstimateDateofCompletion) {
		this.c4EstimateDateofCompletion = c4EstimateDateofCompletion;
	}
	public String getC4ActualDateofCompletion() {
		return c4ActualDateofCompletion;
	}
	public void setC4ActualDateofCompletion(String c4ActualDateofCompletion) {
		this.c4ActualDateofCompletion = c4ActualDateofCompletion;
	}
	public String getC4StatusOfActivity() {
		return c4StatusOfActivity;
	}
	public void setC4StatusOfActivity(String c4StatusOfActivity) {
		this.c4StatusOfActivity = c4StatusOfActivity;
	}
	public String getC5EstimateDateofCompletion() {
		return c5EstimateDateofCompletion;
	}
	public void setC5EstimateDateofCompletion(String c5EstimateDateofCompletion) {
		this.c5EstimateDateofCompletion = c5EstimateDateofCompletion;
	}
	public String getC5ActualDateofCompletion() {
		return c5ActualDateofCompletion;
	}
	public void setC5ActualDateofCompletion(String c5ActualDateofCompletion) {
		this.c5ActualDateofCompletion = c5ActualDateofCompletion;
	}
	public String getC5StatusOfActivity() {
		return c5StatusOfActivity;
	}
	public void setC5StatusOfActivity(String c5StatusOfActivity) {
		this.c5StatusOfActivity = c5StatusOfActivity;
	}
	public String getC6EstimateDateofCompletion() {
		return c6EstimateDateofCompletion;
	}
	public void setC6EstimateDateofCompletion(String c6EstimateDateofCompletion) {
		this.c6EstimateDateofCompletion = c6EstimateDateofCompletion;
	}
	public String getC6ActualDateofCompletion() {
		return c6ActualDateofCompletion;
	}
	public void setC6ActualDateofCompletion(String c6ActualDateofCompletion) {
		this.c6ActualDateofCompletion = c6ActualDateofCompletion;
	}
	public String getC6StatusOfActivity() {
		return c6StatusOfActivity;
	}
	public void setC6StatusOfActivity(String c6StatusOfActivity) {
		this.c6StatusOfActivity = c6StatusOfActivity;
	}
	public String getC7EstimateDateofCompletion() {
		return c7EstimateDateofCompletion;
	}
	public void setC7EstimateDateofCompletion(String c7EstimateDateofCompletion) {
		this.c7EstimateDateofCompletion = c7EstimateDateofCompletion;
	}
	public String getC7ActualDateofCompletion() {
		return c7ActualDateofCompletion;
	}
	public void setC7ActualDateofCompletion(String c7ActualDateofCompletion) {
		this.c7ActualDateofCompletion = c7ActualDateofCompletion;
	}
	public String getDateAdvertisingShortlisting() {
		return dateAdvertisingShortlisting;
	}
	public void setDateAdvertisingShortlisting(String dateAdvertisingShortlisting) {
		this.dateAdvertisingShortlisting = dateAdvertisingShortlisting;
	}
	public String getRfpDraftToBankDate() {
		return rfpDraftToBankDate;
	}
	public void setRfpDraftToBankDate(String rfpDraftToBankDate) {
		this.rfpDraftToBankDate = rfpDraftToBankDate;
	}
	public String getBankNocForTorDate() {
		return bankNocForTorDate;
	}
	public void setBankNocForTorDate(String bankNocForTorDate) {
		this.bankNocForTorDate = bankNocForTorDate;
	}
	public String getBankNocForShortlistDate() {
		return bankNocForShortlistDate;
	}
	public void setBankNocForShortlistDate(String bankNocForShortlistDate) {
		this.bankNocForShortlistDate = bankNocForShortlistDate;
	}
	public String getBankNocForRfpDate() {
		return bankNocForRfpDate;
	}
	public void setBankNocForRfpDate(String bankNocForRfpDate) {
		this.bankNocForRfpDate = bankNocForRfpDate;
	}
	public String getRfpIssuedDate() {
		return rfpIssuedDate;
	}
	public void setRfpIssuedDate(String rfpIssuedDate) {
		this.rfpIssuedDate = rfpIssuedDate;
	}
	public String getTorShortlistFinalizedDate() {
		return torShortlistFinalizedDate;
	}
	public void setTorShortlistFinalizedDate(String torShortlistFinalizedDate) {
		this.torShortlistFinalizedDate = torShortlistFinalizedDate;
	}
	public String getBankNocTechnicalDate() {
		return bankNocTechnicalDate;
	}
	public void setBankNocTechnicalDate(String bankNocTechnicalDate) {
		this.bankNocTechnicalDate = bankNocTechnicalDate;
	}
	public String getBankNocCombinedDate() {
		return bankNocCombinedDate;
	}
	public void setBankNocCombinedDate(String bankNocCombinedDate) {
		this.bankNocCombinedDate = bankNocCombinedDate;
	}
	public String getEvaluationFinalTechnicalDate() {
		return evaluationFinalTechnicalDate;
	}
	public void setEvaluationFinalTechnicalDate(String evaluationFinalTechnicalDate) {
		this.evaluationFinalTechnicalDate = evaluationFinalTechnicalDate;
	}
	public String getEvaluationFinalCombinedDate() {
		return evaluationFinalCombinedDate;
	}
	public void setEvaluationFinalCombinedDate(String evaluationFinalCombinedDate) {
		this.evaluationFinalCombinedDate = evaluationFinalCombinedDate;
	}
	public String getNocBankDraftDate() {
		return nocBankDraftDate;
	}
	public void setNocBankDraftDate(String nocBankDraftDate) {
		this.nocBankDraftDate = nocBankDraftDate;
	}
	public String getEvaluationFinalDraftDate() {
		return evaluationFinalDraftDate;
	}
	public void setEvaluationFinalDraftDate(String evaluationFinalDraftDate) {
		this.evaluationFinalDraftDate = evaluationFinalDraftDate;
	}
	public String getBankNocFinalContractDate() {
		return bankNocFinalContractDate;
	}
	public void setBankNocFinalContractDate(String bankNocFinalContractDate) {
		this.bankNocFinalContractDate = bankNocFinalContractDate;
	}
	public String getEvaluationFinalContractDate() {
		return evaluationFinalContractDate;
	}
	public void setEvaluationFinalContractDate(String evaluationFinalContractDate) {
		this.evaluationFinalContractDate = evaluationFinalContractDate;
	}
	public String getProposalReciptDate() {
		return proposalReciptDate;
	}
	public void setProposalReciptDate(String proposalReciptDate) {
		this.proposalReciptDate = proposalReciptDate;
	}
	public String getServiceCompletionDate() {
		return serviceCompletionDate;
	}
	public void setServiceCompletionDate(String serviceCompletionDate) {
		this.serviceCompletionDate = serviceCompletionDate;
	}
	public String getC7StatusOfActivity() {
		return c7StatusOfActivity;
	}
	public void setC7StatusOfActivity(String c7StatusOfActivity) {
		this.c7StatusOfActivity = c7StatusOfActivity;
	}
	public String getC8EstimateDateofCompletion() {
		return c8EstimateDateofCompletion;
	}
	public void setC8EstimateDateofCompletion(String c8EstimateDateofCompletion) {
		this.c8EstimateDateofCompletion = c8EstimateDateofCompletion;
	}
	public String getC8ActualDateofCompletion() {
		return c8ActualDateofCompletion;
	}
	public void setC8ActualDateofCompletion(String c8ActualDateofCompletion) {
		this.c8ActualDateofCompletion = c8ActualDateofCompletion;
	}
	public String getC8StatusOfActivity() {
		return c8StatusOfActivity;
	}
	public void setC8StatusOfActivity(String c8StatusOfActivity) {
		this.c8StatusOfActivity = c8StatusOfActivity;
	}
	public String getC9EstimateDateofCompletion() {
		return c9EstimateDateofCompletion;
	}
	public void setC9EstimateDateofCompletion(String c9EstimateDateofCompletion) {
		this.c9EstimateDateofCompletion = c9EstimateDateofCompletion;
	}
	public String getC9ActualDateofCompletion() {
		return c9ActualDateofCompletion;
	}
	public void setC9ActualDateofCompletion(String c9ActualDateofCompletion) {
		this.c9ActualDateofCompletion = c9ActualDateofCompletion;
	}
	public String getC9StatusOfActivity() {
		return c9StatusOfActivity;
	}
	public void setC9StatusOfActivity(String c9StatusOfActivity) {
		this.c9StatusOfActivity = c9StatusOfActivity;
	}
	public String getC10EstimateDateofCompletion() {
		return c10EstimateDateofCompletion;
	}
	public void setC10EstimateDateofCompletion(String c10EstimateDateofCompletion) {
		this.c10EstimateDateofCompletion = c10EstimateDateofCompletion;
	}
	public String getC10ActualDateofCompletion() {
		return c10ActualDateofCompletion;
	}
	public void setC10ActualDateofCompletion(String c10ActualDateofCompletion) {
		this.c10ActualDateofCompletion = c10ActualDateofCompletion;
	}
	public String getC10StatusOfActivity() {
		return c10StatusOfActivity;
	}
	public void setC10StatusOfActivity(String c10StatusOfActivity) {
		this.c10StatusOfActivity = c10StatusOfActivity;
	}
	public String getC11EstimateDateofCompletion() {
		return c11EstimateDateofCompletion;
	}
	public void setC11EstimateDateofCompletion(String c11EstimateDateofCompletion) {
		this.c11EstimateDateofCompletion = c11EstimateDateofCompletion;
	}
	public String getC11ActualDateofCompletion() {
		return c11ActualDateofCompletion;
	}
	public void setC11ActualDateofCompletion(String c11ActualDateofCompletion) {
		this.c11ActualDateofCompletion = c11ActualDateofCompletion;
	}
	public String getC11StatusOfActivity() {
		return c11StatusOfActivity;
	}
	public void setC11StatusOfActivity(String c11StatusOfActivity) {
		this.c11StatusOfActivity = c11StatusOfActivity;
	}
	public String getC12EstimateDateofCompletion() {
		return c12EstimateDateofCompletion;
	}
	public void setC12EstimateDateofCompletion(String c12EstimateDateofCompletion) {
		this.c12EstimateDateofCompletion = c12EstimateDateofCompletion;
	}
	public String getC12ActualDateofCompletion() {
		return c12ActualDateofCompletion;
	}
	public void setC12ActualDateofCompletion(String c12ActualDateofCompletion) {
		this.c12ActualDateofCompletion = c12ActualDateofCompletion;
	}
	public String getC12StatusOfActivity() {
		return c12StatusOfActivity;
	}
	public void setC12StatusOfActivity(String c12StatusOfActivity) {
		this.c12StatusOfActivity = c12StatusOfActivity;
	}
	public String getC13EstimateDateofCompletion() {
		return c13EstimateDateofCompletion;
	}
	public void setC13EstimateDateofCompletion(String c13EstimateDateofCompletion) {
		this.c13EstimateDateofCompletion = c13EstimateDateofCompletion;
	}
	public String getC13ActualDateofCompletion() {
		return c13ActualDateofCompletion;
	}
	public void setC13ActualDateofCompletion(String c13ActualDateofCompletion) {
		this.c13ActualDateofCompletion = c13ActualDateofCompletion;
	}
	public String getC13StatusOfActivity() {
		return c13StatusOfActivity;
	}
	public void setC13StatusOfActivity(String c13StatusOfActivity) {
		this.c13StatusOfActivity = c13StatusOfActivity;
	}
	public String getG1EstimateDateofCompletion() {
		return g1EstimateDateofCompletion;
	}
	public void setG1EstimateDateofCompletion(String g1EstimateDateofCompletion) {
		this.g1EstimateDateofCompletion = g1EstimateDateofCompletion;
	}
	public String getG1ActualDateofCompletion() {
		return g1ActualDateofCompletion;
	}
	public void setG1ActualDateofCompletion(String g1ActualDateofCompletion) {
		this.g1ActualDateofCompletion = g1ActualDateofCompletion;
	}
	public String getG1StatusOfActivity() {
		return g1StatusOfActivity;
	}
	public void setG1StatusOfActivity(String g1StatusOfActivity) {
		this.g1StatusOfActivity = g1StatusOfActivity;
	}
	public String getG2EstimateDateofCompletion() {
		return g2EstimateDateofCompletion;
	}
	public void setG2EstimateDateofCompletion(String g2EstimateDateofCompletion) {
		this.g2EstimateDateofCompletion = g2EstimateDateofCompletion;
	}
	public String getG2ActualDateofCompletion() {
		return g2ActualDateofCompletion;
	}
	public void setG2ActualDateofCompletion(String g2ActualDateofCompletion) {
		this.g2ActualDateofCompletion = g2ActualDateofCompletion;
	}
	public String getG2StatusOfActivity() {
		return g2StatusOfActivity;
	}
	public void setG2StatusOfActivity(String g2StatusOfActivity) {
		this.g2StatusOfActivity = g2StatusOfActivity;
	}
	public String getG3EstimateDateofCompletion() {
		return g3EstimateDateofCompletion;
	}
	public void setG3EstimateDateofCompletion(String g3EstimateDateofCompletion) {
		this.g3EstimateDateofCompletion = g3EstimateDateofCompletion;
	}
	public String getG3ActualDateofCompletion() {
		return g3ActualDateofCompletion;
	}
	public void setG3ActualDateofCompletion(String g3ActualDateofCompletion) {
		this.g3ActualDateofCompletion = g3ActualDateofCompletion;
	}
	public String getG3StatusOfActivity() {
		return g3StatusOfActivity;
	}
	public void setG3StatusOfActivity(String g3StatusOfActivity) {
		this.g3StatusOfActivity = g3StatusOfActivity;
	}
	public String getG4EstimateDateofCompletion() {
		return g4EstimateDateofCompletion;
	}
	public void setG4EstimateDateofCompletion(String g4EstimateDateofCompletion) {
		this.g4EstimateDateofCompletion = g4EstimateDateofCompletion;
	}
	public String getG4ActualDateofCompletion() {
		return g4ActualDateofCompletion;
	}
	public void setG4ActualDateofCompletion(String g4ActualDateofCompletion) {
		this.g4ActualDateofCompletion = g4ActualDateofCompletion;
	}
	public String getG4StatusOfActivity() {
		return g4StatusOfActivity;
	}
	public void setG4StatusOfActivity(String g4StatusOfActivity) {
		this.g4StatusOfActivity = g4StatusOfActivity;
	}
	public String getS1EstimateDateofCompletion() {
		return s1EstimateDateofCompletion;
	}
	public void setS1EstimateDateofCompletion(String s1EstimateDateofCompletion) {
		this.s1EstimateDateofCompletion = s1EstimateDateofCompletion;
	}
	public String getS1ActualDateofCompletion() {
		return s1ActualDateofCompletion;
	}
	public void setS1ActualDateofCompletion(String s1ActualDateofCompletion) {
		this.s1ActualDateofCompletion = s1ActualDateofCompletion;
	}
	public String getS1StatusOfActivity() {
		return s1StatusOfActivity;
	}
	public void setS1StatusOfActivity(String s1StatusOfActivity) {
		this.s1StatusOfActivity = s1StatusOfActivity;
	}
	public String getS2EstimateDateofCompletion() {
		return s2EstimateDateofCompletion;
	}
	public void setS2EstimateDateofCompletion(String s2EstimateDateofCompletion) {
		this.s2EstimateDateofCompletion = s2EstimateDateofCompletion;
	}
	public String getS2ActualDateofCompletion() {
		return s2ActualDateofCompletion;
	}
	public void setS2ActualDateofCompletion(String s2ActualDateofCompletion) {
		this.s2ActualDateofCompletion = s2ActualDateofCompletion;
	}
	public String getS2StatusOfActivity() {
		return s2StatusOfActivity;
	}
	public void setS2StatusOfActivity(String s2StatusOfActivity) {
		this.s2StatusOfActivity = s2StatusOfActivity;
	}
	public String getS3EstimateDateofCompletion() {
		return s3EstimateDateofCompletion;
	}
	public void setS3EstimateDateofCompletion(String s3EstimateDateofCompletion) {
		this.s3EstimateDateofCompletion = s3EstimateDateofCompletion;
	}
	public String getS3ActualDateofCompletion() {
		return s3ActualDateofCompletion;
	}
	public void setS3ActualDateofCompletion(String s3ActualDateofCompletion) {
		this.s3ActualDateofCompletion = s3ActualDateofCompletion;
	}
	public String getS3StatusOfActivity() {
		return s3StatusOfActivity;
	}
	public void setS3StatusOfActivity(String s3StatusOfActivity) {
		this.s3StatusOfActivity = s3StatusOfActivity;
	}
	public String getS4EstimateDateofCompletion() {
		return s4EstimateDateofCompletion;
	}
	public void setS4EstimateDateofCompletion(String s4EstimateDateofCompletion) {
		this.s4EstimateDateofCompletion = s4EstimateDateofCompletion;
	}
	public String getS4ActualDateofCompletion() {
		return s4ActualDateofCompletion;
	}
	public void setS4ActualDateofCompletion(String s4ActualDateofCompletion) {
		this.s4ActualDateofCompletion = s4ActualDateofCompletion;
	}
	public String getS4StatusOfActivity() {
		return s4StatusOfActivity;
	}
	public void setS4StatusOfActivity(String s4StatusOfActivity) {
		this.s4StatusOfActivity = s4StatusOfActivity;
	}
	public String getS5EstimateDateofCompletion() {
		return s5EstimateDateofCompletion;
	}
	public void setS5EstimateDateofCompletion(String s5EstimateDateofCompletion) {
		this.s5EstimateDateofCompletion = s5EstimateDateofCompletion;
	}
	public String getS5ActualDateofCompletion() {
		return s5ActualDateofCompletion;
	}
	public void setS5ActualDateofCompletion(String s5ActualDateofCompletion) {
		this.s5ActualDateofCompletion = s5ActualDateofCompletion;
	}
	public String getS5StatusOfActivity() {
		return s5StatusOfActivity;
	}
	public void setS5StatusOfActivity(String s5StatusOfActivity) {
		this.s5StatusOfActivity = s5StatusOfActivity;
	}
	public String getS6EstimateDateofCompletion() {
		return s6EstimateDateofCompletion;
	}
	public void setS6EstimateDateofCompletion(String s6EstimateDateofCompletion) {
		this.s6EstimateDateofCompletion = s6EstimateDateofCompletion;
	}
	public String getS6ActualDateofCompletion() {
		return s6ActualDateofCompletion;
	}
	public void setS6ActualDateofCompletion(String s6ActualDateofCompletion) {
		this.s6ActualDateofCompletion = s6ActualDateofCompletion;
	}
	public String getS6StatusOfActivity() {
		return s6StatusOfActivity;
	}
	public void setS6StatusOfActivity(String s6StatusOfActivity) {
		this.s6StatusOfActivity = s6StatusOfActivity;
	}
	public String getS7EstimateDateofCompletion() {
		return s7EstimateDateofCompletion;
	}
	public void setS7EstimateDateofCompletion(String s7EstimateDateofCompletion) {
		this.s7EstimateDateofCompletion = s7EstimateDateofCompletion;
	}
	public String getS7ActualDateofCompletion() {
		return s7ActualDateofCompletion;
	}
	public void setS7ActualDateofCompletion(String s7ActualDateofCompletion) {
		this.s7ActualDateofCompletion = s7ActualDateofCompletion;
	}
	public String getS7StatusOfActivity() {
		return s7StatusOfActivity;
	}
	public void setS7StatusOfActivity(String s7StatusOfActivity) {
		this.s7StatusOfActivity = s7StatusOfActivity;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getUserLocation() {
		return userLocation;
	}
	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}
	public BigDecimal getEstimatedTenderAmount() {
		return estimatedTenderAmount;
	}
	public void setEstimatedTenderAmount(BigDecimal estimatedTenderAmount) {
		this.estimatedTenderAmount = estimatedTenderAmount;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "tenderNo@locationId@planId@subPlanId@packageId@schemeCodeId");
		request.setAttribute("d__auto", "tenderNo");
		
		
		if(this.submitForm)
		{
		ActionErrors errors = super.validate(mapping, request);
//		System.out.println("**********************************************************************IN VALIDATION");
//		this.setSubmitForm(false);
		//System.out.println("**********************************************************************IN VALIDATION ----"+errors.toString());
		return errors;
		}
		else
		{
			//System.out.println("**********************************************************************NOT IN VALIDATION");
		return null;
		}

	}
	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}
	public String getPackageDescription() {
		return packageDescription;
	}
	public void setProposalReciptDateTechnical(
			String proposalReciptDateTechnical) {
		this.proposalReciptDateTechnical = proposalReciptDateTechnical;
	}
	public String getProposalReciptDateTechnical() {
		return proposalReciptDateTechnical;
	}
	public void setProposalReciptDateFinancial(
			String proposalReciptDateFinancial) {
		this.proposalReciptDateFinancial = proposalReciptDateFinancial;
	}
	public String getProposalReciptDateFinancial() {
		return proposalReciptDateFinancial;
	}

	public String getRebidApprovalDate() {
		return rebidApprovalDate;
	}
	public void setRebidApprovalDate(String rebidApprovalDate) {
		this.rebidApprovalDate = rebidApprovalDate;
	}
	public String getRebidApprovalNumber() {
		return rebidApprovalNumber;
	}
	public void setRebidApprovalNumber(String rebidApprovalNumber) {
		this.rebidApprovalNumber = rebidApprovalNumber;
	}
	@Override
	public String toString(){
		return "TenderManagementForm[bankNocForTorDate="+bankNocForTorDate+",Bank NOC Date For Shortlisting="+dateAdvertisingShortlisting;
	}
}