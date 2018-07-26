package com.prwss.mis.pmm.schemeupload.struts;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderBean;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderDao;
import com.prwss.mis.pmm.schemeupload.dao.SchemeUpdateDao;
import com.prwss.mis.pmm.schemeupload.dao.SchemeUploadBO;
import com.prwss.mis.pmm.schemeupload.dao.SchemeUploadBean;
import com.prwss.mis.pmm.schemeupload.dao.SchemeUploadDto;
import com.prwss.mis.tender.struts.TenderUploadForm;

public class SchemeUploadAction extends DispatchAction {
	private MISSessionBean misSessionBean;
	private Logger log = Logger.getLogger(SchemeUploadAction.class);

	private SchemeHeaderDao schemeHeaderDao;
	private SchemeUploadBO schemeUploadBO;
	private SchemeUpdateDao schemeUpdateDao;
   
	
	

	

	

	public SchemeUpdateDao getSchemeUpdateDao() {
		return schemeUpdateDao;
	}

	public void setSchemeUpdateDao(SchemeUpdateDao schemeUpdateDao) {
		this.schemeUpdateDao = schemeUpdateDao;
	}

	public MISSessionBean getMisSessionBean() {
		return misSessionBean;
	}

	public void setMisSessionBean(MISSessionBean misSessionBean) {
		this.misSessionBean = misSessionBean;
	}

	public SchemeHeaderDao getSchemeHeaderDao() {
		return schemeHeaderDao;
	}

	public void setSchemeHeaderDao(SchemeHeaderDao schemeHeaderDao) {
		this.schemeHeaderDao = schemeHeaderDao;
	}

	public SchemeUploadBO getSchemeUploadBO() {
		return schemeUploadBO;
	}

	public void setSchemeUploadBO(SchemeUploadBO schemeUploadBO) {
		this.schemeUploadBO = schemeUploadBO;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException,DataAccessException, FileNotFoundException, IOException

	{
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		System.out.println("In Scheme Upload Save");
		this.setAttrib(request);

		boolean status = false;
		List<SchemeUploadBean> schemeUploadBeanLength = null;
		SchemeUploadForm schemeuploadFom = (SchemeUploadForm) form;
		try {

			
           schemeUploadBeanLength = schemeUpdateDao.checkSchemeExistsOrNot(schemeuploadFom);
			
			if (schemeUploadBeanLength.size()>0) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("Scheme, location and SchemeType alredy exists.");
				throw new MISException(MISExceptionCodes.MIS601, buffer.toString());
			}
			
			status = schemeUploadBO.uploadScheme(schemeuploadFom,misSessionBean);

			if (status) {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("Scheme.upload.success.save");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshSchemeUploadForm(schemeuploadFom);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(), e);
			//e.printStackTrace();
			System.out.println(e.getCode());
			
			if (MISExceptionCodes.MIS601.equals(e.getCode())) {
				
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors error = new ActionErrors();
				ActionMessage messagee = new ActionMessage("MIS601.Dublicate.error",e.getMessage());
				error.add(ActionMessages.GLOBAL_MESSAGE, messagee);
				saveErrors(request, error);
			
		}
		} catch (DataAccessException ex){
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save", ex
					.getLocalizedMessage().toString());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}

		return mapping.findForward("display");
	}
	
	
	
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException

	{
		
		System.out.println("In Scheme Upload Save");
	
		
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		

		boolean status = false;

		SchemeUploadForm schemeuploadFom = (SchemeUploadForm) form;
		List<SchemeUploadBean> schemeUploadBeans = null;
		
		
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		try {
					System.out.println(schemeuploadFom.toString());
					schemeUploadBeans = schemeUploadBO.getUploadedSchemes(schemeuploadFom,misSessionBean);

			if (schemeUploadBeans.size()>0) {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("Scheme.upload.success.find");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				request.setAttribute("ListSchemeUpload", schemeUploadBeans);
				request.setAttribute("schemeId", schemeuploadFom.getScheme_id());
				//refreshSchemeUploadForm(schemeuploadFom);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.find");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save", e
					.getLocalizedMessage().toString());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}

		return mapping.findForward("display");
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");
			}
		} else {
			// System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		setAttrib(request);
		System.out.println("In Scheme Upload");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "location_id@scheme_type@scheme_id");
		System.out.println("In Scheme Upload");
		System.out.println(request.getParameter("menuId"));
		System.out.println(request.getParameter("d__mode"));
		//request.setAttribute("d__auto", "locRequestNo");

	}

	public ActionForward fetchScheme(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {

		System.out.println("We are in Fetch Scheme");
		List<SchemeHeaderBean> schemeHeaderBeans = null;
		List<SchemeHeaderBean> schemeHeaderBeans2 = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		SchemeHeaderBean schemeHeaderBean = new SchemeHeaderBean();
		try {
			if (MisUtility.ifEmpty(request.getParameter("locationId"))) {
				schemeHeaderBean.setLocationId(request
						.getParameter("locationId"));

				System.out.println("Location  Id Is:"
						+ request.getParameter("locationId"));

				System.out.println("Scheme Type"
						+ request.getParameter("schemeType"));

				schemeHeaderBean.setLocationId(request
						.getParameter("locationId"));

				// schemeHeaderBean.setBlockId(request.getParameter("blockId"));

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

				System.out.println("Length is:-" + schemeHeaderBeans.size());

				for (SchemeHeaderBean schemeHeaderBean2 : schemeHeaderBeans) {

					buffer.append("<option value=\"")
							.append(schemeHeaderBean2.getSchemeId())
							.append("\">");

					buffer.append(schemeHeaderBean2.getSchemeName() + " -("
							+ schemeHeaderBean2.getSchemeId() + ")-"
							+ schemeHeaderBean2.getProgId());
					buffer.append("</option>");
				}
				System.out.println("Length is:-" + schemeHeaderBeans.size());

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
			System.out.println(e.getLocalizedMessage().toString().trim());
		}

		return null;
	}

	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);

		// SchemeUploadForm schemeUploadForm = (SchemeUploadForm) form;

		return mapping.findForward("display");
	}

	private void refreshSchemeUploadForm(SchemeUploadForm schemeUploadForm) {
		
		schemeUploadForm.setScheme_id(null);
		schemeUploadForm.setLocation_id(null);
		schemeUploadForm.setScheme_type(null);
		
		schemeUploadForm.setDigitalSurvey_name_cdr_File(null);
		schemeUploadForm.setDigitalSurvey_name_pdf_File(null);
		schemeUploadForm.setAdminAprovalFile(null);
		schemeUploadForm.setSchemeEstimateFile(null);
		schemeUploadForm.setStrataChartFile(null);
	}

	private String fileNameAppenderToPDF(String fileName, String toAppend) {

		if (!fileName.isEmpty() && !toAppend.isEmpty()) {
			String file[] = fileName.split(".pdf");
			String changedFileName = file[0];
			changedFileName = changedFileName + "-" + toAppend + ".pdf";
			System.out.println("Changed File Name   " + changedFileName);
			return changedFileName;
		}
		return null;

	}
	
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		
		try {
			System.out.println("name------------------" + request.getParameter("name"));
			System.out.println("Id------------------" + request.getParameter("id"));
			System.out.println("Column Name------------------" + request.getParameter("columnName"));
			
			String name= request.getParameter("name");
			String id = request.getParameter("id");
			String ColumnName =  request.getParameter("columnName"); 
			List<SchemeUploadDto> schemeUploadDto = null;
			
			if (MisUtility.ifEmpty(id) && MisUtility.ifEmpty(name) && MisUtility.ifEmpty(ColumnName)) {
				schemeUploadDto = schemeUpdateDao.getAttachmentData(name,id,ColumnName);
				if (!MisUtility.ifEmpty(schemeUploadDto)) {

					for (SchemeUploadDto dto : schemeUploadDto) {
						byte[] attachment = dto.getAttachmentFile();
						response.reset();
						response.setContentType("application/pdf");
						response.setHeader("Content-Disposition", "attachment; filename=" + dto.getAttachmentName());
						response.setHeader("Pragma", "public");
						response.setHeader("Cache-Control", "no-store");
						response.addHeader("Cache-Control", "max-age=0");
						response.setContentLength(attachment.length);

						InputStream in = new ByteArrayInputStream(attachment);
						ServletOutputStream out = response.getOutputStream();
						byte[] outputByte = new byte[attachment.length];
						while (in.read(outputByte, 0, attachment.length) != -1) {
							out.write(outputByte, 0, attachment.length);
						}
						
						in.close();
						out.flush();
						out.close();

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
 		return null;
	}
	
	//Update
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException,DataAccessException, FileNotFoundException, IOException

	{
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		System.out.println("In Scheme Upload Save");
		this.setAttrib(request);

		boolean status = false;
		
		SchemeUploadForm schemeuploadFom = (SchemeUploadForm) form;
		try {
            SchemeUploadBean bean = null;
			System.out.println(schemeuploadFom.toString()); 
			
			
			
          
		
			status = schemeUploadBO.uploadSchemeUpdate(schemeuploadFom,misSessionBean);

			if (status) {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("Scheme.upload.success.save");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshSchemeUploadForm(schemeuploadFom);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(), e);
			//e.printStackTrace();
			System.out.println(e.getCode());
			
			if (MISExceptionCodes.MIS601.equals(e.getCode())) {
				
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors error = new ActionErrors();
				ActionMessage messagee = new ActionMessage("MIS601.Dublicate.error",e.getMessage());
				error.add(ActionMessages.GLOBAL_MESSAGE, messagee);
				saveErrors(request, error);
			
		}
		} catch (DataAccessException ex){
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save", ex
					.getLocalizedMessage().toString());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}

		return mapping.findForward("display");
	}

	
	

}
