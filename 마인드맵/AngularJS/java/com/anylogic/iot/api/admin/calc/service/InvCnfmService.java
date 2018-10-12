
package com.anylogic.iot.api.admin.calc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.admin.calc.mapper.CalcMapper;
import com.anylogic.iot.base.excel.service.ExcelSVC;

/**
 * ClassName : InvCnfmService
 * 
 * @version : 1.0
 * @date    : 2017. 12. 28.
 * @author  : kpbaek 
 * @brief   :
 */

@Service
public class InvCnfmService {

	@Autowired
	private CalcMapper calcMapper;

	 
	 public List<Object> getInvoiceList(Map<String, Object> parameter){ 
		 
		 if("rent".equals(parameter.get("radio"))){
			 if("LAST".equals(parameter.get("check_last_invoice"))) {
				 return calcMapper.getInvoiceLastList(parameter);
			 } else {
				 return calcMapper.getInvoiceList(parameter);
			 }
		 }else{
			 if("LAST".equals(parameter.get("check_last_invoice"))) {
				 return calcMapper.getInvoiceLastListRental(parameter);
			 } else {
				 return calcMapper.getInvoiceListRental(parameter);
			 }
		 }
	 }

	 public Map<?, ?> getInvoiceView(Map<String, Object> parameter){
			return calcMapper.getInvoiceView(parameter);
	 }
	 
	 public Map<?, ?> getInvoiceIdSeq(Map<String, Object> parameter){
			return calcMapper.getInvoiceIdSeq(parameter);
	 }
	 
	 public int insertInvoiceIssuStatus(Map<String, Object> parameter){
			return calcMapper.insertInvoiceIssuStatus(parameter);
	 }
	 
	 public int insertInvoiceRelativeIssuStatus(Map<String, Object> parameter){
			return calcMapper.insertInvoiceRelativeIssuStatus(parameter);
	 }
	 
	 public int saveTaxInvoice(Map<String, Object> parameter){
			return calcMapper.saveTaxInvoice(parameter);
	 }	
	 
	 public int saveDepositInvoice(Map<String, Object> parameter){
			return calcMapper.saveDepositInvoice(parameter);
	 }	
	 
	 public  Map<?, ?> deleteInvoiceStatus(Map<String, Object> parameter){
		 return calcMapper.deleteInvoiceStatus(parameter);
	 }		 
}
