package com.prwss.mis.tender.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.scheme.dao.SchemeDao;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderBean;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderDao;
import com.prwss.mis.masters.vendor.dao.BlackListVendorBean;
import com.prwss.mis.masters.vendor.dao.BlackListVendorDao;
import com.prwss.mis.masters.vendor.dao.VendorBean;
import com.prwss.mis.masters.vendor.dao.VendorDao;
import com.prwss.mis.tender.BidderBO;
import com.prwss.mis.tender.biddersdetail.dao.BidderDetailBean;
import com.prwss.mis.tender.biddersdetail.dao.BidderHeaderBean;
import com.prwss.mis.tender.biddersdetail.dao.BidderHeaderDao;
import com.prwss.mis.tender.dao.TenderDao;
import com.prwss.mis.tender.dao.TenderHeaderBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class BiddersDetailAction extends DispatchAction {
	
	private BidderBO bidderBO;
	private BidderHeaderDao bidderHeaderDao;
	private MISSessionBean misSessionBean = null;
	private TenderDao tenderDao;
	private VendorDao vendorDao;
	private SchemeHeaderDao schemeHeaderDao;
	private BlackListVendorDao blackListVendorDao;
	
	
	public void setBlackListVendorDao(BlackListVendorDao blackListVendorDao) {
		this.blackListVendorDao = blackListVendorDao;
	}
	public void setSchemeHeaderDao(SchemeHeaderDao schemeHeaderDao) {
		this.schemeHeaderDao = schemeHeaderDao;
	} 
	public void setBidderHeaderDao(BidderHeaderDao bidderHeaderDao) {
		this.bidderHeaderDao = bidderHeaderDao;
	}
	public void setTenderDao(TenderDao tenderDao) {
		this.tenderDao = tenderDao;
	}
	public void setBidderBO(BidderBO bidderBO) {
		this.bidderBO = bidderBO;
	}
	public void setVendorDao(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}
	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws MISException {
		this.setAttrib(request);
		try {
			List<String> statusList = new ArrayList<String>();
			String mode = (String) request.getParameter("d__mode");
			if (request.getSession().getAttribute("misSessionBean") != null) {
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				log.debug("Bidder Find : Your session timed out");
				return mapping.findForward("login");
			}
			if (mode != null && MISConstants.D_MODE_ENQUIRE.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null && MISConstants.D_MODE_DELETE.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			String tenderId = null;
			String locationId = null;
			long bidInfoId = 0;
			BiddersDetailForm biddersDetailForm = (BiddersDetailForm)form;
			tenderId = biddersDetailForm.getTenderId();
			locationId = biddersDetailForm.getLocationId();
			bidInfoId = biddersDetailForm.getBidInfoId();
			refreshBidderForm(biddersDetailForm);
			biddersDetailForm.setBidInfoId(bidInfoId);
			biddersDetailForm.setLocationId(locationId);
			biddersDetailForm.setTenderId(tenderId);
			List<BidderHeaderBean> bidderHeaderBeans = null;
			System.out.println("1 Action In Find");
			bidderHeaderBeans = bidderBO.findBidder(biddersDetailForm, statusList);
			
			if(MisUtility.ifEmpty(bidderHeaderBeans)){
				System.out.println("2 Action In Find");
				ActionErrors messages= new ActionErrors();
				ActionMessage message = new ActionMessage("No.record"," for Tender Number ",biddersDetailForm.getTenderId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, messages);				
			} else {
				System.out.println("3 Action In Find");
				request.setAttribute("level2", "true");
				Datagrid bidderDetail = null;
				for (BidderHeaderBean bidderHeaderBean : bidderHeaderBeans) {
					biddersDetailForm.setBiddingPhase(bidderHeaderBean.getBiddingPhase());
					biddersDetailForm.setBidInfoId(bidderHeaderBean.getBidInfoId());
					biddersDetailForm.setBidOpeningDate(MisUtility.convertDateToString(bidderHeaderBean.getBidOpeningDate()));
					biddersDetailForm.setLocationId(bidderHeaderBean.getLocationId());
					biddersDetailForm.setReferenceTenderId(bidderHeaderBean.getReferenceTenderId());
					biddersDetailForm.setTenderId(bidderHeaderBean.getTenderId());
					request.setAttribute("tenderNo",bidderHeaderBean.getTenderId());/*This is set as to preserve the tender number on UI after response is send back */
					bidderDetail = getBidderDetailDatagrid(bidderHeaderBean.getBidderDetailBeans());
					biddersDetailForm.setReferenceTenderId(bidderHeaderBean.getReferenceTenderId());
					request.setAttribute("referenceTender",bidderHeaderBean.getReferenceTenderId() );/*This is set as to preserve the refrence tender number on UI after response is send back */
					biddersDetailForm.setBidderDetailDatagrid(bidderDetail);
				}
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.find");
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			StackTraceElement l = new Exception().getStackTrace()[0]; 
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.find",e.toString()+ " at line number "+l.getClassName()+"/"+l.getMethodName()+":"+l.getLineNumber());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}		
		return mapping.findForward("display");
	}

	
	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BiddersDetailForm biddersDetailForm = (BiddersDetailForm)form;
		boolean status = false;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			log.debug("Bidder post : Your session timed out");
			return mapping.findForward("login");
		}
		try {
			status = bidderBO.postBidder(biddersDetailForm, misSessionBean);
			if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.post", "Bid for Tender No. "+biddersDetailForm.getTenderId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);				
			} else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Tender No. "+  biddersDetailForm.getTenderId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			StackTraceElement l = new Exception().getStackTrace()[0]; 
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.post",e.toString()+ " at line number "+l.getClassName()+"/"+l.getMethodName()+":"+l.getLineNumber());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		
		refreshBidderForm(biddersDetailForm);
		return mapping.findForward("display");
	}
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		long id = 0;
		BiddersDetailForm biddersDetailForm = (BiddersDetailForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			
		} else {
			log.debug("Bidder save : Your session timed out");
			return mapping.findForward("login");
		}
		try {
			StringBuffer stringMessage = new StringBuffer();
			 
			if(!MisUtility.ifEmpty(biddersDetailForm.getLocationId()) || !MisUtility.ifEmpty(biddersDetailForm.getTenderId())||!MisUtility.ifEmpty(biddersDetailForm.getBiddingPhase())||!MisUtility.ifEmpty(biddersDetailForm.getBidderNameId())){
				if(!MisUtility.ifEmpty(biddersDetailForm.getLocationId())){
					stringMessage.append("Please select Location");
				}
				if(!MisUtility.ifEmpty(biddersDetailForm.getTenderId())){
					stringMessage.append("<br>Please select Tender Number");
				}
				if(!MisUtility.ifEmpty(biddersDetailForm.getBiddingPhase())){
					stringMessage.append("<br>Please select Bidding Phase ");
				}
				if(!MisUtility.ifEmpty(biddersDetailForm.getBidderNameId())){
					stringMessage.append("<br>Please select Bidder Name ");
				}
				 
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
			if(MisUtility.ifEmpty(biddersDetailForm.getBidderDetailDatagrid().getAddedData())){
				stringMessage.append("Please attach Bidder Detail Grid.");
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
		 
			id = bidderBO.saveBidder(biddersDetailForm, misSessionBean);
			System.out.println(id);
			if (id != 0){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Bid for Tender No. "+biddersDetailForm.getTenderId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
				refreshBidderForm(biddersDetailForm);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save","Tender No. "+ biddersDetailForm.getTenderId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}

		} catch (MISException e) {
			if(MISExceptionCodes.MIS001.equals(e.getCode())){
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry","Bid for Tender No. "+biddersDetailForm.getTenderId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				log.debug(e);
				saveErrors(request, errors);
				refreshBidderForm(biddersDetailForm);

			}else if(MISExceptionCodes.MIS012.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save","Tender No. "+ biddersDetailForm.getTenderId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.debug("Error in Posting tender \t" + e);
			}
		
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			StackTraceElement l = new Exception().getStackTrace()[0]; 
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save",e.toString()+ " at line number "+l.getClassName()+"/"+l.getMethodName()+":"+l.getLineNumber());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		
		
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		this.setAttrib(request);
		BiddersDetailForm biddersDetailForm = (BiddersDetailForm)form;
		boolean status = false;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			log.debug("Bidder save : Your session timed out");
			return mapping.findForward("login");
		}
		try {
			StringBuffer stringMessage = new StringBuffer();
			 
			if(!MisUtility.ifEmpty(biddersDetailForm.getLocationId()) || !MisUtility.ifEmpty(biddersDetailForm.getTenderId())||!MisUtility.ifEmpty(biddersDetailForm.getBiddingPhase())){
				if(!MisUtility.ifEmpty(biddersDetailForm.getLocationId())){
					stringMessage.append("Please select Location");
				}
				if(!MisUtility.ifEmpty(biddersDetailForm.getTenderId())){
					stringMessage.append("<br>Please select Tender Number");
				}
				if(!MisUtility.ifEmpty(biddersDetailForm.getBiddingPhase())){
					stringMessage.append("<br>Please select Bidding Phase ");
				}
				 
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
			status = bidderBO.updateBidder(biddersDetailForm, misSessionBean);
			if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Bid for Tender No. "+biddersDetailForm.getTenderId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			} else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Tender No. "+  biddersDetailForm.getTenderId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}catch (MISException e) {
			  if(MISExceptionCodes.MIS012.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			  else if(MISExceptionCodes.MIS003.equals(e.getCode())){
					log.error(e.getLocalizedMessage(),e);
					e.printStackTrace();
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("required.fields",e.getMessage());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				
			  }
		}catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			StackTraceElement l = new Exception().getStackTrace()[0]; 
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.update",e.toString()+ " at line number "+l.getClassName()+"/"+l.getMethodName()+":"+l.getLineNumber());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		
		refreshBidderForm(biddersDetailForm);
		return mapping.findForward("display");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		this.setAttrib(request);
		BiddersDetailForm biddersDetailForm = (BiddersDetailForm)form;
		boolean status = false;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			log.debug("Bidder save : Your session timed out");
			return mapping.findForward("login");
		}
		try {
			status = bidderBO.deleteBidder(biddersDetailForm, misSessionBean);
			if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete", "Bid for Tender No. "+biddersDetailForm.getTenderId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			} else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Tender No. "+  biddersDetailForm.getTenderId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			StackTraceElement l = new Exception().getStackTrace()[0]; 
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.delete",e.toString()+ " at line number "+l.getClassName()+"/"+l.getMethodName()+":"+l.getLineNumber());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		
		refreshBidderForm(biddersDetailForm);
		return mapping.findForward("display");
	}

	private Datagrid getBidderDetailDatagrid(Set<BidderDetailBean> bidderDetailBeans)throws MISException {
		List<BidderDetailGridBean> bidderDetailGridBeans = new ArrayList<BidderDetailGridBean>();
		Datagrid bidderDetailDatagrid = Datagrid.getInstance();
		bidderDetailDatagrid.setDataClass(BidderDetailGridBean.class);
		try {
			if (!MisUtility.ifEmpty(bidderDetailBeans)) {
				BidderDetailGridBean bidderDetailGridBean = null;
			for (BidderDetailBean bidderDetailBean : bidderDetailBeans) {
				if(!bidderDetailBean.getMisAuditBean().getStatus().equals(MISConstants.MASTER_STATUS_DELETED)){
				bidderDetailGridBean = new BidderDetailGridBean();
				bidderDetailGridBean.setSeqBidId(bidderDetailBean.getSeqBidId());
				bidderDetailGridBean.setBankName(bidderDetailBean.getBankName());
				bidderDetailGridBean.setBidderName(bidderDetailBean.getBidderName());
				bidderDetailGridBean.setBidInfoId(bidderDetailBean.getBidInfoId());
				bidderDetailGridBean.setBidSaleDate(MisUtility.convertDateToString(bidderDetailBean.getBidSaleDate()));
				bidderDetailGridBean.setBidSubmitted(bidderDetailBean.getBidSubmitted());
				bidderDetailGridBean.setBidAmount(bidderDetailBean.getBidAmount());
				bidderDetailGridBean.setContactNumber(bidderDetailBean.getContactNumber());
				bidderDetailGridBean.setEmdAmount(bidderDetailBean.getEmdAmount());
				bidderDetailGridBean.setEmdInstrumentType(bidderDetailBean.getEmdInstrumentType());
				bidderDetailGridBean.setEmdValidUpto(MisUtility.convertDateToString(bidderDetailBean.getEmdValidUpto()));
				bidderDetailGridBean.setNotResponsive(bidderDetailBean.getNotResponsive());
				bidderDetailGridBean.setRemarks(bidderDetailBean.getRemarks());
				bidderDetailGridBeans.add(bidderDetailGridBean);
			}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		bidderDetailDatagrid.setData(bidderDetailGridBeans);
		return bidderDetailDatagrid;

	}
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "tenderNo@locationId");
	}


	private void refreshBidderForm(BiddersDetailForm biddersDetailForm)throws MISException{
		biddersDetailForm.setBiddingPhase(0);
		biddersDetailForm.setBidOpeningDate(null);
		biddersDetailForm.setLocationId(null);
		biddersDetailForm.setTenderId(null);
		biddersDetailForm.setBidderDetailDatagrid(getBidderDetailDatagrid(null));
		biddersDetailForm.setBidInfoId(0);
		biddersDetailForm.setReferenceTenderId(null);
		biddersDetailForm.setReferenceTenderId(null);
		 
	}	
	
	public ActionForward fetchBidSaleDateLogic(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
	throws MISException {
		Set<TenderHeaderBean> tenders = null;
		List<String> statusList = new ArrayList<String>();
		try {
			StringBuffer buffer = new StringBuffer();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			PrintWriter out = res.getWriter();
			if(MisUtility.ifEmpty(req.getParameter("tenderId")))
			{	
				System.out.println(req.getParameter("bidderNameId"));
				if(!req.getParameter("bidderNameId").equals("NOBIDDER")){
					System.out.println("Inside  ");
				TenderHeaderBean tenderBean = new TenderHeaderBean(); 
				tenderBean.setTenderId(req.getParameter("tenderId"));
				if(MisUtility.ifEmpty(req.getParameter("bidSaleDate"))){
				log.debug("Location"+req.getParameter("locationId"));
//				System.out.println("LocationID"+req.getParameter("locationId"));
				List<TenderHeaderBean> tenderHeaderBeans = tenderDao.findTender(tenderBean, statusList);
//				System.out.println("Tenders"+tenders);
				if(!MisUtility.ifEmpty(tenderHeaderBeans)){

					TenderHeaderBean tenderBean1 = tenderHeaderBeans.get(0);

					if(MisUtility.ifEmpty(tenderBean1.getCloseDate()) && MisUtility.ifEmpty(tenderBean1.getPublishDate())){

						if(checkDate(tenderBean1.getPublishDate(), tenderBean1.getCloseDate(), MisUtility.convertStringToDate(req.getParameter("bidSaleDate")))){
							out.print("OK");
						}else{
							out.print("Error: Bid sale date, should be in between tender invitation date and tender closing date ");
						}

					}else{
						out.print("Warning: Tender invitation date or closing date missing for this tender. Please verify.");
					}

				}else{
					out.print("Warning: No record found for this Tender number");
				}
				}else{
					out.print("Please Enter Bid Sale Date");
				}
			}else{
				out.print("OK");
			}
		}else{
			out.print("Please Enter Tender Number");
		}
			

			
//			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				
//			}


		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}
	
	public ActionForward fetchTender(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
	throws MISException {
		
		Set<TenderHeaderBean> tenders = null;
		List<SchemeHeaderBean> schemeHeaderBeans = null;
		List<String> statusList = new ArrayList<String>();
		 
		try {
			StringBuffer buffer = new StringBuffer();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		/*	statusList.add(MISConstants.MASTER_STATUS_VERIFIED);*/
			String procurmentType = null;
			 
			if(MisUtility.ifEmpty(req.getParameter("locationId")))
			{
				 
				tenders = tenderDao.getDistinctTenderCodes(req.getParameter("locationId"),statusList);
				System.out.println(tenders);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (TenderHeaderBean tenderHeaderBean : tenders) {
					String schemeName=null;
					SchemeHeaderBean schemeHeaderBean = new SchemeHeaderBean();
					schemeHeaderBean.setLocationId(req.getParameter("locationId"));
					schemeHeaderBean.setSchemeId(tenderHeaderBean.getSchemeCode());
					log.debug("Location"+req.getParameter("locationId"));
					schemeHeaderBeans=schemeHeaderDao.findSchemeHeader(schemeHeaderBean, statusList);
					if(!MisUtility.ifEmpty(schemeHeaderBeans)){
						schemeName=schemeHeaderBeans.get(0).getSchemeName();
					}
				
					procurmentType = tenderHeaderBean.getProcurementType();
					if(MisUtility.ifEmpty(procurmentType)){
					if(!procurmentType.equalsIgnoreCase("DGS&D") && !procurmentType.equalsIgnoreCase("DIRECT CONTRACTING") ){
						buffer.append("<option value=\"").append(tenderHeaderBean.getTenderId()).append("\">");
						buffer.append(schemeName+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+tenderHeaderBean.getSchemeCode()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+tenderHeaderBean.getTenderId());
					//	buffer.append(tenderHeaderBean.getTenderId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+tenderHeaderBean.getSchemeCode());
						buffer.append("</option>");
						//System.out.println(buffer);
					}
					}
				}
			}
			PrintWriter out = res.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}


		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}
		return null;
	}
	private Set<LabelValueBean> fetchBiddersNames(){
		Set<LabelValueBean> bidderNames = null;
		Set<VendorBean> vendors = null;
		List<BlackListVendorBean> listVendorBeans = null;
		BlackListVendorBean blackListVendorBean = new BlackListVendorBean();
		blackListVendorBean.setVendorId("");
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try{
			vendors = vendorDao.getDistinctVendorCodes();
			listVendorBeans = blackListVendorDao.findBlackListVendor(blackListVendorBean, statusList);
			bidderNames = new TreeSet<LabelValueBean>();
			for(VendorBean vendor : vendors){
				bidderNames.add(new LabelValueBean(vendor.getVendorName()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+vendor.getVendorId(),
						vendor.getVendorId()));
				for(BlackListVendorBean bean:listVendorBeans){						
					if(vendor.getVendorId().equals(bean.getVendorId())){
						bidderNames.remove(new LabelValueBean(vendor.getVendorName()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+vendor.getVendorId(),
								vendor.getVendorId()));
					}
						
				}
			}			
		}catch(DataAccessException e){
			log.error(e);
			e.printStackTrace();
		}
		catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}		
		return bidderNames;	
	}
/*	public ActionForward fetchBiddersNames(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
	throws MISException {
		List<VendorBean> biddernames = null;
		List<String> statusList = new ArrayList<String>();
		try {
			StringBuffer buffer = new StringBuffer();
			System.out.println("bidder method in side");
			//if(MisUtility.ifEmpty(req.getParameter("locationId")))
		//	{
				//log.debug("Location"+req.getParameter("locationId"));
//				System.out.println("LocationID"+req.getParameter("locationId"));
			//	tenders = tenderDao.getDistinctTenderCodes(req.getParameter("locationId"),statusList);
				biddernames = vendorDao.getVendersNames();
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
//				System.out.println("Tenders"+tenders);
				for (VendorBean vendorBeans: biddernames) {
					buffer.append("<option value=\"").append(vendorBeans.getVendorId()).append("\">");
					buffer.append(vendorBeans.getVendorName()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+vendorBeans.getVendorId());
					buffer.append("</option>");
			//	}
			}

			PrintWriter out = res.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}


		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}*/
	
	
	public ActionForward fetchPhaseTender(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
	throws MISException {
		List<String> statusList = null;
		List<BidderHeaderBean>bidderHeaderBeans = new ArrayList<BidderHeaderBean>();
		StringBuffer buffer = new StringBuffer();
		System.out.println(req.getParameter("locationId")+"\t"+ req.getParameter("biddingPhase"));
		try {
			
			if(MisUtility.ifEmpty(req.getParameter("locationId")) && MisUtility.ifEmpty(req.getParameter("biddingPhase"))){
				BidderHeaderBean bidderHeaderBean = new BidderHeaderBean();
				bidderHeaderBean.setLocationId(req.getParameter("locationId"));
				int biddingPhase = Integer.parseInt(req.getParameter("biddingPhase"));
				if(biddingPhase>1){
					bidderHeaderBean.setBiddingPhase(biddingPhase-1);
					bidderHeaderBeans = bidderHeaderDao.findBidderHeader(bidderHeaderBean, statusList);
				}
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (BidderHeaderBean bidderHeaderBean2 : bidderHeaderBeans) {
					buffer.append("<option value=\"").append(bidderHeaderBean2.getTenderId()).append("\">");
					buffer.append(bidderHeaderBean2.getTenderId());
					buffer.append("</option>");
				}
			}
			PrintWriter out = res.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error(e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		
		
		
		return null;
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			BiddersDetailForm biddersDetailForm = (BiddersDetailForm)form;
			refreshBidderForm(biddersDetailForm);
			Set<LabelValueBean> bidderNames = fetchBiddersNames();
			request.getSession().setAttribute("bidderNames", bidderNames);
		} catch (MISException e) {
			e.printStackTrace();
			log.error(e);
		}
		this.setAttrib(request);
		return mapping.findForward("display");
	}
	
	public boolean checkDate(Date invitationDate,Date closingDate, Date bidSaleDate){
		
		if(invitationDate.compareTo(bidSaleDate)<=0 && closingDate.compareTo(bidSaleDate)>=0){
//			System.out.println("Closing Date---------------"+closingDate);
//			System.out.println("Bid Sale Date---------------"+bidSaleDate);
//			System.out.println("Compare---cal2.compareTo(cal3)------------"+closingDate.compareTo(bidSaleDate));
			return true;
		}else{
//			System.out.println("Closing Date---------------"+closingDate);
//			System.out.println("Bid Sale Date---------------"+bidSaleDate);
//			System.out.println("Compare---cal2.compareTo(cal3)------------"+closingDate.compareTo(bidSaleDate));
			
			return false;
		}
		
	}
}
