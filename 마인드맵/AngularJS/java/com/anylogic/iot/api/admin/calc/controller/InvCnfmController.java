
package com.anylogic.iot.api.admin.calc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.calc.service.InvCnfmService;
import com.anylogic.iot.api.admin.calc.service.InvMakeService;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mvc.message.Messages;


      
@RestController
@RequestMapping("/" + Version.V1+"/admin/calc")
public class InvCnfmController {

	private static final Logger logger = LoggerFactory.getLogger(InvCnfmController.class);

	@Autowired
	private InvCnfmService invCnfmService;
	
	@Autowired
	private InvMakeService invMakeService;

	@RequestMapping(value = "/getInvoiceList", method = RequestMethod.GET)
	public ResultListVO getInvoiceList(@RequestParam Map<String, Object> parameter, Messages messages) {
	
		logger.debug("parameter==" + parameter);

		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(invCnfmService.getInvoiceList(parameter));
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	
	
	@RequestMapping(value = "/getInvoiceView", method = RequestMethod.GET)
	public Map<?, ?> getInvoiceView(@RequestParam Map<String, Object> parameter, Messages messages) {
		
    	Map<?, ?> resultMap = invCnfmService.getInvoiceView(parameter); 
    	 
    	messages.addMessage("OK", "");
		return resultMap;
	}
	
	
	@RequestMapping(value = "/saveInvoiceIssuStatus", method = RequestMethod.POST)
	public int saveInvoiceIssuStatus(@RequestBody Map<String, Object> parameter, Messages messages) {
		 
		Map<?, ?> resultMap = invCnfmService.getInvoiceIdSeq(parameter);
		parameter.put("invoice_id", resultMap.get("invoice_id_seq")) ;
		
		int res = invCnfmService.insertInvoiceIssuStatus(parameter);
		logger.debug("[by kgh] invoiceWrite res==" + res);

		res = invMakeService.updateInvoiceVat(parameter);
		logger.debug("[by kgh] updateInvoiceVat res==" + res);

		res = invMakeService.updateInvoiceTotalAmount(parameter);
		logger.debug("[by kgh] updateInvoiceTotalAmount res==" + res);
	    	 
		if (res == 1) {
			messages.addMessage("OK", "");
		} else {
			messages.addMessage("NotOK", "Invoice 발송  저장 실패 했습니다.");
		}
		return res;
	 }	
	
	
	
	
	@RequestMapping(value = "/saveInvoiceRelativeIssuStatus", method = RequestMethod.POST)
	public int saveInvoiceRelativeIssuStatus(@RequestBody Map<String, Object> parameter, Messages messages) {
		 
		Map<?, ?> resultMap = invCnfmService.getInvoiceIdSeq(parameter);
		parameter.put("invoice_id", resultMap.get("invoice_id_seq")) ;
		
		int res = invCnfmService.insertInvoiceRelativeIssuStatus(parameter);
		logger.debug("[by kgh] invoiceRelativeWrite res==" + res);

		res = invMakeService.updateInvoiceVat(parameter);
		logger.debug("[by kgh] updateInvoiceVat res==" + res);

		res = invMakeService.updateInvoiceTotalAmount(parameter);
		logger.debug("[by kgh] updateInvoiceTotalAmount res==" + res);
	    	 
		if (res == 1) {
			messages.addMessage("OK", "");
		} else {
			messages.addMessage("NotOK", "Invoice Relative 발송  저장 실패 했습니다.");
		}
		return res;
	 }	
	
	
	@RequestMapping(value = "/saveTaxInvoice", method = RequestMethod.POST)
	public int saveTaxInvoice(@RequestBody Map<String, Object> parameter, Messages messages) {
		
		int res = 0;
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> taxBillCheckedDataList =  (List<Map<String, Object>>) parameter.get("taxBillCheckedDataList");
		
		for(int i=0;i<taxBillCheckedDataList.size();i++){
			
			Map<String, Object> paramApi = new HashMap<>();
			paramApi = taxBillCheckedDataList.get(i);

			logger.debug("[by kgh] saveTaxInvoice >> paramApi==" + paramApi.toString());
			
			res += invCnfmService.saveTaxInvoice(paramApi);
		}
		
		logger.debug("[by kgh] saveTaxInvoice res==" + res);
    	 
		if (res > 0) {
			messages.addMessage("OK", "");
		} else {
			messages.addMessage("NotOK", "세금계산서 발행  실패 했습니다.");
		}
		return res;	
	}

	
	@RequestMapping(value = "/saveDepositInvoice", method = RequestMethod.POST)
	public int saveDepositInvoice(@RequestBody Map<String, Object> parameter, Messages messages) {
		
		int res = 0;
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> depositCheckedDataList =  (List<Map<String, Object>>) parameter.get("depositCheckedDataList");
		
		for(int i=0;i<depositCheckedDataList.size();i++){
			
			Map<String, Object> paramApi = new HashMap<>();
			paramApi = depositCheckedDataList.get(i);

			logger.debug("[by kgh] saveDepositInvoice >> paramApi==" + paramApi.toString());
			
			res += invCnfmService.saveDepositInvoice(paramApi);
		}
		
		logger.debug("[by kgh] saveDepositInvoice res==" + res);
    	 
		if (res > 0) {
			messages.addMessage("OK", "");
		} else {
			messages.addMessage("NotOK", "입금여부 저장에  실패 했습니다.");
		}
		return res;	
	}

	
	@RequestMapping(value = "/deleteInvoiceStatus", method = RequestMethod.POST)
	public boolean deleteInvoiceStatus(@RequestBody Map<String, Object> parameter, Messages messages) {

		try {
			invCnfmService.deleteInvoiceStatus(parameter);
			messages.addMessage("OK", "Sucess!!");
			return true;
			
		} catch(Exception e) {
			messages.addMessage("NotOk", "It's failed fror delete Invoice Status..[" + e.getMessage() + "]");
			return false;	
		}
	}	
}