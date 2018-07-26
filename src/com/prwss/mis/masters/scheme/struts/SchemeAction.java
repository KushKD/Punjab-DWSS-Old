package com.prwss.mis.masters.scheme.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.scheme.SchemeBO;
import com.prwss.mis.masters.scheme.dao.SchemeBean;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderBean;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderDao;
import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;
import com.prwss.mis.masters.scheme.dao.SchemeVillageDao;
import com.sun.mail.imap.protocol.Status;

public class SchemeAction extends DispatchAction {

	Logger log = Logger.getLogger(SchemeAction.class);

	private MISSessionBean misSessionBean;
	private SchemeBO schemeBO;
	private SchemeHeaderDao schemeHeaderDao;
	private LocationDao locationDao;
	private MISJdcDaoImpl misJdcDaoImpl;
	private SchemeVillageDao schemeVillageDao;

	public void setSchemeVillageDao(SchemeVillageDao schemeVillageDao) {
		this.schemeVillageDao = schemeVillageDao;
	}

	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public void setSchemeHeaderDao(SchemeHeaderDao schemeHeaderDao) {
		this.schemeHeaderDao = schemeHeaderDao;
	}

	public void setSchemeBO(SchemeBO schemeBO) {
		this.schemeBO = schemeBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("In Find");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		String mode = (String) request.getParameter("d__mode");
		List<String> statusList = new ArrayList<String>();
		if (mode != null
				&& MISConstants.D_MODE_ENQUIRE.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null
				&& MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null
				&& MISConstants.D_MODE_DELETE.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null
				&& MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		this.setAttrib(request);
		try {

			SchemeForm schemeForm = (SchemeForm) form;

			if (!MisUtility.ifEmpty(schemeForm.getSchemeStatus())) {

				throw new MISException(MISExceptionCodes.MIS012,
						"Please select Scheme status");
			}

			List<SchemeBean> schemeBeans = null;
			schemeBeans = schemeBO.findScheme(schemeForm, misSessionBean,
					statusList);
			if (!MisUtility.ifEmpty(schemeBeans)) {
				request.setAttribute("level2", "true");
				for (SchemeBean schemeBean : schemeBeans) {
					schemeForm.setAdminAppGoPAmount(schemeBean
							.getAdminAppGoPAmount());
					schemeForm.setAdminAppGoPLetterDate(MisUtility
							.convertDateToString(schemeBean
									.getAdminAppGoPLetterDate()));
					schemeForm.setAdminAppGoPLetterNo(schemeBean
							.getAdminAppGoPLetterNo());
					schemeForm.setAdminAppLyingWithEeAmount(schemeBean
							.getAdminAppLyingWithEeAmount());
					schemeForm.setAdminAppSentCeVideSeAmount(schemeBean
							.getAdminAppSentCeVideSeAmount());
					schemeForm.setAdminAppSentCeVideSeDate(MisUtility
							.convertDateToString(schemeBean
									.getAdminAppSentCeVideSeDate()));
					schemeForm.setAdminAppSentCeVideSeNo(schemeBean
							.getAdminAppSentCeVideSeNo());
					schemeForm.setAdminAppSentGovtVideCeAmount(schemeBean
							.getAdminAppSentGovtVideCeAmount());
					schemeForm.setAdminAppSentGovtVideCeDate(MisUtility
							.convertDateToString(schemeBean
									.getAdminAppSentGovtVideCeDate()));
					schemeForm.setAdminAppSentGovtVideCeNo(schemeBean
							.getAdminAppSentGovtVideCeNo());
					schemeForm.setAdminAppSentSeVideEeAmount(schemeBean
							.getAdminAppSentSeVideEeAmount());
					schemeForm.setTechAppCeAmount(schemeBean
							.getTechAppCeAmount());
					schemeForm.setAdminAppSentSeVideEeDate(MisUtility
							.convertDateToString(schemeBean
									.getAdminAppSentSeVideEeDate()));
					schemeForm.setAdminAppSentSeVideEeNo(schemeBean
							.getAdminAppSentSeVideEeNo());
					schemeForm.setConstitutionDateSLC(MisUtility
							.convertDateToString(schemeBean
									.getConstitutionDateSLC()));
					schemeForm.setMouSigned(schemeBean.getMouSigned());
					schemeForm
							.setResolutionDate(MisUtility
									.convertDateToString(schemeBean
											.getResolutionDate()));
					schemeForm.setSchemeCommisionedDate(MisUtility
							.convertDateToString(schemeBean
									.getSchemeCommisionedDate()));
					schemeForm.setSchemeCompletedDate(MisUtility
							.convertDateToString(schemeBean
									.getSchemeCompletedDate()));
					schemeForm.setSchemeCompleted(schemeBean
							.isSchemeCompleted());
					schemeForm.setSchemeId(schemeBean.getSchemeId());
					request.setAttribute("schemeId", schemeBean.getSchemeId());
					System.out.println(schemeBean.getSchemeId());
					schemeForm.setSchemeSource(schemeBean.getSchemeSource());
					schemeForm.setTechAppCeLetterDate(MisUtility
							.convertDateToString(schemeBean
									.getTechAppCeLetterDate()));
					schemeForm.setTechAppCeLetterNo(schemeBean
							.getTechAppCeLetterNo());
					schemeForm.setTechAppEeAmount(schemeBean
							.getTechAppEeAmount());
					schemeForm
							.setTechAppEeDate(MisUtility
									.convertDateToString(schemeBean
											.getTechAppEeDate()));
					schemeForm.setTechAppEeLetterNo(schemeBean
							.getTechAppEeLetterNo());
					schemeForm.setTechAppLyingWithEeAmount(schemeBean
							.getTechAppLyingWithEeAmount());
					schemeForm.setTechAppSeAmount(schemeBean
							.getTechAppSeAmount());
					schemeForm.setTechAppSeLetterDate(MisUtility
							.convertDateToString(schemeBean
									.getTechAppSeLetterDate()));
					schemeForm.setTechAppSeLetterNo(schemeBean
							.getTechAppSeLetterNo());
					schemeForm.setTechAppSentToCeVideSeAmount(schemeBean
							.getTechAppSentToCeVideSeAmount());
					schemeForm.setTechAppSentToCeVideSeDate(MisUtility
							.convertDateToString(schemeBean
									.getTechAppSentToCeVideSeDate()));
					schemeForm.setTechAppSentToCeVideSeNo(schemeBean
							.getTechAppSentToCeVideSeNo());
					schemeForm.setTechAppSentToSeVideEeAmount(schemeBean
							.getTechAppSentToSeVideEeAmount());
					schemeForm.setTechAppSentToSeVideEeDate(MisUtility
							.convertDateToString(schemeBean
									.getTechAppSentToSeVideEeDate()));
					schemeForm.setTechAppSentToSeVideEeNo(schemeBean
							.getTechAppSentToSeVideEeNo());
					schemeForm.setTechAppSentToSeVideEeNo(schemeBean
							.getTechAppSentToSeVideEeNo());
					schemeForm.setBeneficiaryByCommunity(schemeBean
							.getBeneficiaryByCommunity());
					schemeForm.setBeneficiaryShareDue(schemeBean
							.getBeneficiaryShareDue());
					schemeForm.setBeneficiaryShareNonBudgGp(schemeBean
							.getBeneficiaryShareNonBudgGp());
					schemeForm.setBeneficiaryShareStateGovtGrant(schemeBean
							.getBeneficiaryShareStateGovtGrant());
					schemeForm.setBeneficiaryShareUntiedDistrict(schemeBean
							.getBeneficiaryShareUntiedDistrict());
					schemeForm.setBeneficiaryShareVoluntarily(schemeBean
							.getBeneficiaryShareVoluntarily());
					System.out.println(schemeBean.isShareLessThanUpperLimit());
					schemeForm.setShareLessThanUpperLimit(schemeBean
							.isShareLessThanUpperLimit());
					schemeForm.setDesignInvestigationDate(MisUtility
							.convertDateToString(schemeBean
									.getDesignInvestigationDate()));
					schemeForm.setDigitalSurveyDate(MisUtility
							.convertDateToString(schemeBean
									.getDigitalSurveyDate()));
					schemeForm.setDigitalSurveyCompleted(schemeBean
							.isDigitalSurveyCompleted());
					schemeForm.setSchemeStatus(schemeBean.getSchemeUpgraded());
				}
			} else {
				refreshSchemeForm(schemeForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record",
						"found or available for this mode for ",
						"the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS012.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(), e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields",
						e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {
				log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record",
						"found or available for this mode for ",
						"the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}
		return mapping.findForward("display");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("Delete ... IEC Progress");
		this.setAttrib(request);
		SchemeForm schemeForm = (SchemeForm) form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession()
							.getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = schemeBO.deleteScheme(schemeForm, misSessionBean);
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.delete",
						"Scheme Information", "Scheme code -->"
								+ schemeForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete",
						"Scheme Information", "Scheme code -->"
								+ schemeForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save",
					"Scheme Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save",
					"Scheme Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshSchemeForm(schemeForm);
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		SchemeForm schemeForm = (SchemeForm) form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession()
							.getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}

			status = schemeBO.saveScheme(schemeForm, misSessionBean);
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.save",
						"Scheme Information", "Scheme code -->"
								+ schemeForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save",
						"Scheme Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage(
						"error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save",
					"Scheme Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);

		}
		refreshSchemeForm(schemeForm);
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		SchemeForm schemeForm = (SchemeForm) form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession()
							.getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			adminAppDateCheck(schemeForm);
			if (!schemeForm.getSchemeCompletedDate().equals("")) {
				boolean flag1 = false;
				boolean flag2 = false;
				List<SchemeVillageBean> schemeVillageBeans = checkCommissionedDate(schemeForm);
				for (SchemeVillageBean scVillageBean : schemeVillageBeans) {
					// SimpleDateFormat sdf = new
					// SimpleDateFormat("yyyy-MM-dd");
					Date date1 = scVillageBean.getSchemeCommissionedDate();
					System.out.println("scheme id=="
							+ scVillageBean.getSchemeId());
					System.out.println("villageId==="
							+ scVillageBean.getVillageId());
					System.out.println("villageName==="
							+ scVillageBean.getVillageName());
					System.out.println("comm date==="
							+ scVillageBean.getSchemeCommissionedDate());

					Date date2 = MisUtility.convertStringToDate(schemeForm
							.getSchemeCompletedDate());

					// Date date2 =
					// sdf.parse(schemeForm.getSchemeCompletedDate());

					if (date1 == null) {
						flag1 = true;
					} else if (date2.before(date1) || date2.equals(date1)) {
						flag2 = true;
					}
				}
				if (flag1) {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage(
							"error.dateNotPresent", "Scheme Information");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				} else if (flag2) {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.greater",
							"");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				} else {
					status = schemeBO.updateScheme(schemeForm, misSessionBean);
					if (status) {
						ActionMessages messages = new ActionMessages();
						ActionMessage message = new ActionMessage(
								"success.update", "Scheme Information",
								"Scheme code -->" + schemeForm.getSchemeId());
						messages.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveMessages(request, messages);

					} else {
						ActionErrors errors = new ActionErrors();
						ActionMessage message = new ActionMessage(
								"error.update", "Scheme Information",
								"Scheme code -->" + schemeForm.getSchemeId());
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveErrors(request, errors);
					}
				}

			} else {
				System.out.println("Date is null");
				status = schemeBO.updateScheme(schemeForm, misSessionBean);
				if (status) {
					ActionMessages messages = new ActionMessages();
					ActionMessage message = new ActionMessage("success.update",
							"Scheme Information", "Scheme code -->"
									+ schemeForm.getSchemeId());
					messages.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, messages);

				} else {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.update",
							"Scheme Information", "Scheme code -->"
									+ schemeForm.getSchemeId());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS013.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(), e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("MST024.PRO.error",
						e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save",
						"Scheme Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save",
					"Scheme Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshSchemeForm(schemeForm);
		return mapping.findForward("display");
	}

	private List<SchemeVillageBean> checkCommissionedDate(ActionForm form) {
		SchemeForm schemeForm = (SchemeForm) form;
		String scheme = schemeForm.getSchemeId();
		SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
		schemeVillageBean.setSchemeId(scheme);
		List<String> statusList = new ArrayList<String>();
		statusList.add("A");

		return schemeVillageDao
				.findSchemevillage(schemeVillageBean, statusList);
	}

	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("save:" + request.getParameter("d__mode"));
		this.setAttrib(request);
		SchemeForm schemeForm = (SchemeForm) form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession()
							.getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = schemeBO.postScheme(schemeForm, misSessionBean);
			if (status) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.post",
						"Scheme Information", "Scheme code -->"
								+ schemeForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post",
						"Scheme Information", "Scheme code -->"
								+ schemeForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save",
					"Scheme Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);

		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save",
					"Scheme Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}

		refreshSchemeForm(schemeForm);

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky",
				"schemeId@locationId@schemeStatus@schemeType");   //@subdivisionId

	}

	private void refreshSchemeForm(SchemeForm schemeForm) {

		schemeForm.setDesignInvestigationDate(null);

		schemeForm.setAdminAppGoPAmount(new BigDecimal(0.00));

		schemeForm.setAdminAppGoPLetterDate(null);

		schemeForm.setAdminAppGoPLetterNo(null);

		schemeForm.setAdminAppLyingWithEeAmount(new BigDecimal(0.00));

		schemeForm.setAdminAppSentCeVideSeAmount(new BigDecimal(0.00));

		schemeForm.setAdminAppSentCeVideSeDate(null);
		schemeForm.setAdminAppSentCeVideSeNo(null);

		schemeForm.setAdminAppSentGovtVideCeAmount(new BigDecimal(0.00));

		schemeForm.setAdminAppSentGovtVideCeDate(null);

		schemeForm.setAdminAppSentGovtVideCeNo(null);

		schemeForm.setAdminAppSentSeVideEeAmount(new BigDecimal(0.00));
		// schemeForm.setAdminAppSentCeVideSeAmount(new BigDecimal(0.00));

		// new
		schemeForm.setTechAppSentToSeVideEeAmount(new BigDecimal(0.00));

		schemeForm.setAdminAppSentSeVideEeDate(null);

		schemeForm.setAdminAppSentSeVideEeNo(null);

		schemeForm.setConstitutionDateSLC(null);

		schemeForm.setMouSigned(null);

		schemeForm.setResolutionDate(null);

		schemeForm.setSchemeCommisionedDate(null);
		schemeForm.setSchemeCompletedDate(null);

		schemeForm.setSchemeId(null);

		schemeForm.setSchemeSource(null);

		schemeForm.setTechAppCeAmount(new BigDecimal(0.00));

		schemeForm.setTechAppCeLetterDate(null);

		schemeForm.setTechAppCeLetterNo(null);

		schemeForm.setTechAppEeAmount(new BigDecimal(0.00));

		schemeForm.setTechAppEeDate(null);

		schemeForm.setTechAppEeLetterNo(null);

		schemeForm.setTechAppLyingWithEeAmount(new BigDecimal(0.00));

		schemeForm.setTechAppSeAmount(new BigDecimal(0.00));

		schemeForm.setTechAppSeLetterDate(null);

		schemeForm.setTechAppSeLetterNo(null);

		schemeForm.setTechAppSentToCeVideSeAmount(new BigDecimal(0.00));

		schemeForm.setTechAppSentToCeVideSeDate(null);
		schemeForm.setTechAppSentToCeVideSeNo(null);

		schemeForm.setTechAppSentToSeVideEeAmount(new BigDecimal(0.00));

		schemeForm.setTechAppSentToSeVideEeDate(null);

		schemeForm.setTechAppSentToSeVideEeNo(null);
		schemeForm.setMouSigned("NO");
		schemeForm.setBeneficiaryByCommunity(new BigDecimal(0.00));
		schemeForm.setBeneficiaryShareDue(new BigDecimal(0.00));
		schemeForm.setBeneficiaryShareNonBudgGp(new BigDecimal(0.00));
		schemeForm.setBeneficiaryShareStateGovtGrant(new BigDecimal(0.00));
		schemeForm.setBeneficiaryShareUntiedDistrict(new BigDecimal(0.00));
		schemeForm.setBeneficiaryShareVoluntarily(new BigDecimal(0.00));
		schemeForm.setShareLessThanUpperLimit(false);
		schemeForm.setSchemeCompleted(false);

		schemeForm.setDigitalSurveyDate(null);
		schemeForm.setDigitalSurveyCompleted(false);
	}

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		System.out.println("in SCEHEME");
		try {
			SchemeForm schemeForm = (SchemeForm) form;
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession()
							.getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			refreshSchemeForm(schemeForm);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}

	public ActionForward fetchScheme(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		List<SchemeHeaderBean> schemeHeaderBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		SchemeHeaderBean schemeHeaderBean = new SchemeHeaderBean();
		try {
			if (MisUtility.ifEmpty(request.getParameter("locationId"))) {
				schemeHeaderBean.setLocationId(request
						.getParameter("locationId"));
				if (request.getParameter("schemeType").equals("imp")) {
					schemeHeaderBean.setSchemeSource("IMPROVEMENT");
				} else if (request.getParameter("schemeType").equals("sw")) {
					schemeHeaderBean.setSchemeSource("SEWERAGE");
				} else {
					schemeHeaderBean.setSchemeSource(request
							.getParameter("schemeType"));
				}

				schemeHeaderBean.setSchemeUpgraded(request
						.getParameter("schemeStatus"));
				schemeHeaderBeans = schemeHeaderDao.findSchemeHeader(
						schemeHeaderBean, statusList);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (SchemeHeaderBean schemeHeaderBean2 : schemeHeaderBeans) {
					buffer.append("<option value=\"")
							.append(schemeHeaderBean2.getSchemeId())
							.append("\">");
					buffer.append(schemeHeaderBean2.getSchemeName() + " -("
							+ schemeHeaderBean2.getSchemeId() + ")-"
							+ schemeHeaderBean2.getProgId());
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}

		return null;
	}

	private void adminAppDateCheck(ActionForm form) throws MISException {
		String message = null;
		SchemeForm schemeForm = (SchemeForm) form;
		String argument1 = "scheme_id~admin_app_gop_letter_date~admin_app_gop_letter_no~admin_app_gop_amount";
		String argument2 = schemeForm.getSchemeId() + "~"
				+ schemeForm.getAdminAppGoPLetterDate() + "~"
				+ schemeForm.getAdminAppGoPLetterNo() + "~"
				+ schemeForm.getAdminAppGoPAmount();
		System.out.println("arg2" + argument2);

		try {
			DataSource db = misJdcDaoImpl.getDataSource();
			Connection con = db.getConnection();
			CallableStatement cs = con
					.prepareCall("{ call prwss_main.bl_MST015(?,?) }");
			System.out.println("argument1:" + argument1);
			System.out.println("argument2:" + argument2);
			cs.setString(1, argument1);
			cs.setString(2, argument2);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.execute();
			message = cs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (MisUtility.ifEmpty(message)) {
			throw new MISException(MISExceptionCodes.MIS013, message);
		}

	}

}