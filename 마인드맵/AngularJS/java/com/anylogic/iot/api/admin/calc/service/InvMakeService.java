
package com.anylogic.iot.api.admin.calc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.admin.calc.mapper.CalcMapper;

/**
 * ClassName : InvMakeService
 * 
 * @version : 1.0
 * @date    : 2017. 12. 28.
 * @author  : kpbaek 
 * @brief   :
 */

@Service
public class InvMakeService {

	@Autowired
	private CalcMapper calcMapper;
	
	public List<Object> showInfoTaxInvoice(Map<String, Object> parameter){
		return calcMapper.showInfoTaxInvoice(parameter);
	}

	 public List<Object> getCalculTenantList(Map<String, Object> parameter){
			return calcMapper.getCalculTenantList(parameter);
	 }
	 
	 public Map<?, ?> getCalculTenantOriginView(Map<String, Object> parameter){
			return calcMapper.getCalculTenantOriginView(parameter);
	 }

	 public String getInvoinceIdForOrigin(Map<String, Object> parameter){
			return calcMapper.getInvoinceIdForOrigin(parameter);
	 }
	  
	 public Map<?, ?> getCalculTenantView(Map<String, Object> parameter){
			return calcMapper.getCalculTenantView(parameter);
	 }
	 
	 public int invoiceWrite(Map<String, Object> parameter){
			return calcMapper.invoiceWrite(parameter);
	 }
	 
	 public int updateInvoiceVat(Map<String, Object> parameter){
			return calcMapper.updateInvoiceVat(parameter);
	 }
	 
	 public int updateInvoiceTotalAmount(Map<String, Object> parameter){
			return calcMapper.updateInvoiceTotalAmount(parameter);
	 }
	 
	 public Map<?, ?> getInvoiceSetVatDefault(Map<String, Object> parameter){
			return calcMapper.getInvoiceSetVatDefault(parameter);
	 }	 
	 
	 public int saveInvoiceSetVatDefault(Map<String, Object> parameter){
		 
		 if("I".equals(parameter.get("saveFlag"))) {
			 			 
			 return calcMapper.insertInvoicevattemplate(parameter);
		 } else {
			 return calcMapper.updateInvoicevattemplate(parameter);
		 }
	 }	
}
