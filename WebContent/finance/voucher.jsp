<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/onlynumber.js"></script>
<script type="text/javascript">
		function resetForm(){
			
		   var form = document.getElementById("voucherForm").elements;

		   for(i=0;i<=form.length;i++)
		   {
			   if (form[i].getAttribute('type') == 'submit' || form[i].getAttribute('type') == 'button') {
				   continue;
			   }
			   else{
	form[i].value = "";
				   }
	}
						
			   }
		    

		function de_find(){		
			document.voucherForm.action="voucherAction.do?method=Find&d__mode="+d__mode;
			document.voucherForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.voucherForm.action="voucherAction.do?method=Update&d__mode="+d__mode;
				document.voucherForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.voucherForm.action="voucherAction.do?method=Delete&d__mode="+d__mode;
				document.voucherForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.voucherForm.action="voucherAction.do?method=Post&d__mode="+d__mode;
				document.voucherForm.submit();
			}
		}
		function de_add(){
			if(d__mode=='ent_add') {
				document.voucherForm.action="voucherAction.do?method=Save&d__mode="+d__mode;
				document.voucherForm.submit();
			}

			}
	</script>
<title>Vouchers</title>

</head>
<html:html>

<body bgcolor="#6699FF">
<center>
<h1>Voucher/LOC</h1>
</center>
<html:form action="voucherAction" styleId="voucherForm">
	
<logic:messagesPresent >
					<div id="errorDiv" class="error displaynone" style="width: 470px%; margin-bottom: 7px; height: 13px;">
 						<html:errors bundle="finance"/>   
					</div>
				</logic:messagesPresent >
				<logic:messagesPresent message="true">
					<div id="successDiv" class="success diplaynone" style="width: 470px;">
						<html:messages id="message" bundle="finance" message="true">
       						<li><bean:write name="message" /></li>
   						</html:messages>
					</div>
				</logic:messagesPresent >
	<fieldset><legend>Header Information</legend>
	<center>
	<table>


		<tr>
			<td nowrap><label>Voucher Number</label></td>
			<td><html:text property="voucherNumber" disabled="true"/></td>

			<td><label>Voucher Type</label></td>
			<td><html:select property="voucherType">
				<html:option value="V">Payment Voucher</html:option>
				<html:option value="L">LOC</html:option>
			</html:select></td>
		</tr>

		<tr>
			<td><label>Transaction Date</label></td>
			<td><html:text property="transactionDate" readonly="true"></html:text></td>

			<td><label>Party</label></td>
			<td><html:text property="party"></html:text></td>
		</tr>

		<tr>

			<td><label>Instrument Type</label></td>
			<td><html:select property="instrumentType">
				<html:option value="Cash">Cash</html:option>
				<html:option value="Cheque">Cheque</html:option>
				<html:option value="Cheque">Bank Gaurantee</html:option>
				<html:option value="Cheque">Fixed Deposit</html:option>
			</html:select></td>

			
			<td><label>Amount</label></td>
			<td><html:text property="amount" /></td>
			
			
		</tr>
		
		<tr>
		
			<td><label>Instrument Number</label></td>
			<td><html:text property="instrumentNumber" /></td>
			
			
			<td nowrap><label>Instrument's Banker</label></td>
			<td><html:text property="instrumentBank" /></td>
		
		
		
		
		</tr>
		
		<tr>
		
			<td><label>Instrument Dated</label></td>
			<td><html:text property="instrumentDated" /></td>
			
			
			<td><label>Invoice Number</label></td>
			<td><html:text property="invoiceNumber" /></td>
		
				
		</tr>
		
		<tr>
		
			<td><label>Notes</label></td>
			<td><html:textarea property="notes" ></html:textarea></td>
			
			
			<td><label>Invoice Date</label></td>
			<td><html:text property="invoiceDate" readonly="true"/></td>
		
				
		</tr>


	</table>
	</center>
	</fieldset>

	<fieldset><legend>Payement Details Data Grid</legend>
	<center>
	<table>
		<tr>
			<td><label>Component</label></td>
			<td><label>Sub-Component</label></td>
			<td><label>Activity</label></td>
			<td><label>Item</label></td>
			<td><label>Quantity</label></td>
			<td><label>Debit/Credit</label></td>
			<td><label>Amount</label></td>
		</tr>



	</table>

	</center>
	</fieldset>
</html:form>
</body>
</html:html>