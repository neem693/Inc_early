package com.anylogic.iot.api.admin.tenant.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.member.service.MemberService;
import com.anylogic.iot.api.admin.sms.controller.smsService;
import com.anylogic.iot.api.admin.tenant.service.TenantService;
import com.anylogic.iot.api.admin.tenant.vo.CompanyInfoVO;
import com.anylogic.iot.api.admin.tenant.vo.guestListVO;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.excel.service.ExcelSVC;
import com.anylogic.iot.base.mvc.message.Messages;
import com.anylogic.iot.base.util.Crypto;

import net.sf.jxls.exception.ParsePropertyException;
      
@RestController
@RequestMapping("/" + Version.V1+"/admin/tenant")
public class TenantController {  

	private static final Logger logger = LoggerFactory.getLogger(TenantController.class);

	@Autowired
	private TenantService tenantService;

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberService myService;

	@Autowired
	private ExcelSVC excelSVC;
	
	@Autowired
	private smsService smsservice;
	
	@RequestMapping(value = "/updateContractInfo", method = RequestMethod.POST)
	public int updateContractInfo(@RequestBody Map<String, Object> parameter, Messages messages) {
		//Map<String, Object> resultMap = new HashMap<>();
		int res;
		System.out.println("updateContractInfo : parameter" + parameter.toString());
		
		
		String contract_id = parameter.get("contract_id").toString();
		if(contract_id.equals("emptyValue")) {
			//res = tenantService.deleteContractInfo(parameter);
			res = tenantService.insertContractInfo(parameter);
		}else {
			res = tenantService.updateContractInfo(parameter);
		}
		
		
		//res = tenantService.deleteContractInfo(parameter);
		
		if (res == 1) {
			messages.addMessage("1", "OK");
		} else {
			messages.addMessage("0", "Not OK");
		}
		return res;
	}
	
	
	@RequestMapping(value = "/addTime", method = RequestMethod.POST)
	public void addTime(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		
		res = tenantService.addTime(parameter);
		if (res == 1) {
			messages.addMessage("OK", "");
		} else {
			messages.addMessage("Not OK", "");
		}

	}
	
	@RequestMapping(value = "/getContract", method = RequestMethod.GET)
	public ResultListVO getContract(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(tenantService.getContract(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	
	@RequestMapping(value="/getGuestListExcel", method=RequestMethod.POST)
	@ResponseBody
	public void getGuestListExcel(HttpServletRequest request, HttpServletResponse response, 
			   @RequestParam Map<String, Object> parameter, Messages messages) throws ParsePropertyException, InvalidFormatException, IOException {
		String fileName = "출입등록목록";

		//total_count + 1 - x.RNUM  Integer.parseInt(from);
		List<guestListVO> list = tenantService.getGuestListExcel(parameter);
		int lng = list.size();
		for(int i=0;i<lng;i++){
		int rNum = Integer.parseInt(list.get(i).getRNUM());
		rNum = lng +1 - rNum;
		list.get(i).setRNUM(Integer.toString(rNum));
		}
		excelSVC.getExcelDownLoad("출입등록목록", "xlsx", "list", list, response);
	}
	
	
	@RequestMapping(value = "/getGuestList", method = RequestMethod.GET)
	public ResultListVO getGuestList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(tenantService.getGuestList(parameter)); 
		return resultListVO;
	}
	
	
	@RequestMapping(value = "/getOfficeRentUseInfo", method = RequestMethod.GET)
	public ResultListVO getOfficeRentUseInfo(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(tenantService.getOfficeRentUseInfo(parameter)); 
		return resultListVO;
	}
	
	@RequestMapping(value="/sendSMS", method=RequestMethod.POST)
	@ResponseBody
	public void sendSMS(@RequestBody Map<String, Object> parameter, Messages messages) {
		
		List<Map<String, Object>> sendList = (List<Map<String, Object>>) parameter.get("sendList");
		List<String> supplierNames1 = new ArrayList<String>();
		
		for(int i=0;i<sendList.size();i++){
			String setData = sendList.get(i).get("hpNo").toString();
			setData  = setData.replace("-", "");
			supplierNames1.add(setData);
		}
		String messageStr = (String) parameter.get("content");
		//boolean resSMS = sendSmsMessage(messageStr, supplierNames1);
		smsservice.sendSmsMessage(messageStr, supplierNames1);
		try {
//			if (resSMS && ("A".equals((String)parameter.get("type")) || "I".equals((String)parameter.get("type")) )) {
			if (("A".equals((String)parameter.get("type")) || "I".equals((String)parameter.get("type")) )) {
				memberService.insertSendingHistorySMS(parameter);
			}
		} catch (Exception e) {
			return;
		}
	}
	
	
	private boolean sendSmsMessage(String message, List<String> receiverNumbers) {
		boolean res = false;
		try {
			// Xroshot 설정 정보
			String spid = com.anylogic.iot.base.util.PropUtil.getInstance().getPropValue(com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.DEFAULT_PROP_FILE, com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.XROSUOT_SPID);
			String sppwd = com.anylogic.iot.base.util.PropUtil.getInstance().getPropValue(com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.DEFAULT_PROP_FILE, com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.XROSUOT_SPPWD);
			String certfile_path = com.anylogic.iot.base.util.PropUtil.getInstance().getPropValue(com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.DEFAULT_PROP_FILE, com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.XROSUOT_CERTFILE_PATH);

			// Sender 정보
			String senderNumber = com.anylogic.iot.base.util.PropUtil.getInstance().getPropValue(com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.DEFAULT_PROP_FILE, com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.XROSUOT_SENDER);

			com.anylogic.iot.base.common.xroshot.job.XroshotSms smsSend = new com.anylogic.iot.base.common.xroshot.job.XroshotSms(spid, sppwd, certfile_path);

			// SMS 메세지 전송
			res = smsSend.sendSms(message, senderNumber, receiverNumbers);

		} catch (Exception e) {
			logger.info("Send SMS MESSAGE ERROR :" + e.getMessage());
		}

		return res;
	}
	
	@RequestMapping(value="/deleteCompanyList", method=RequestMethod.POST)
	@ResponseBody
	public void deleteCompanyList(@RequestBody Map<String, Object> parameter, Messages messages) {
		List<Map<String, Object>> delList = new ArrayList<Map<String, Object>>();
		delList =    (List<Map<String, Object>>) parameter.get("delList");
		for(int i=0;i<delList.size();i++){
			tenantService.deleteMember(delList.get(i));
			
			tenantService.deleteCompany(delList.get(i));
			
			tenantService.resetOffice(delList.get(i));
			
			tenantService.resetOfficeRentUse(delList.get(i));
		}
	}
	
	@RequestMapping(value = "/fileDownload")
	public void download(String uploadPath,  String orgFilename, String mimeTypeParam, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException {
		if(logger.isDebugEnabled()) {
			logger.debug("download...");
			logger.debug("파일 정보 : {} - {} - {}", uploadPath,  orgFilename);
		}
		
		OutputStream outputStream = response.getOutputStream();
		if(StringUtils.isEmpty(uploadPath) || StringUtils.isEmpty(orgFilename)  || StringUtils.isEmpty(mimeTypeParam)) {
			String errorMessage = "다운로드 파일 정보가 존재하지 않습니다.";
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            if(logger.isDebugEnabled()) logger.debug(errorMessage);
            
            return;
		}
		
		if(logger.isDebugEnabled()) logger.debug(uploadPath);
		File file = new File(uploadPath);
		
		if(!file.exists()) {
			String errorMessage = "다운로드 파일이 존재하지 않습니다.";
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			
			if(logger.isDebugEnabled()) logger.debug(errorMessage);
			return;
		}
		
        String mimeType = mimeTypeParam;
		if(logger.isDebugEnabled()) logger.debug(mimeType);
		
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", "inline; filename=\"" + URLEncoder.encode(orgFilename, "UTF-8") + "\"");
		response.setContentLength((int) file.length());
		
		//String temp = ;
		int comma = orgFilename.indexOf(".");
		String fileExt = orgFilename.substring(comma, orgFilename.length());
		//if(fileExt.equals(".txt")){
		//	String sendData = new String(readFile2(uploadPath),"euc-kr");
		//	response.getOutputStream().write(sendData.getBytes());
		//}else{
			response.getOutputStream().write(readFile2(uploadPath));
		//}
		
	}
	
	 public static byte[] readFile2(String fileName){
	        FileInputStream fis=null;
	        byte[] data = null;
	        try {
	            fis = new FileInputStream(fileName);
	            data = new byte[fis.available()];
	            fis.read(data);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally{
	            try{
	                if(fis!=null) fis.close();
	            }catch(IOException e){ ; }
	        }
	        return data;
	    }
	
	@RequestMapping(value = "/getCompanyFile", method = RequestMethod.GET)
	public ResultListVO getCompanyFile(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(tenantService.getCompanyFile(parameter)); 
		return resultListVO;
	}
	
	
	@RequestMapping(value = "/insertCompanyInfo", method = RequestMethod.POST)
	public String insertCompanyInfo(HttpServletRequest request, HttpServletResponse response, Messages messages){

		String atcFileId = "";
		String json = request.getParameter("data").toString();

		List<Map<String, Object>> atcFileSeqList = new ArrayList<Map<String, Object>>();
		Map<String,Object> parameter = null;

		try {
			parameter = new ObjectMapper().readValue(json, HashMap.class);
		} catch (Exception e) {
			throw new com.anylogic.iot.base.exception.KTBCCException();
		}

		if(parameter.get("atcFileId") != null){
			atcFileId = parameter.get("atcFileId").toString();
		}

		int insertKey ;
		Map<String, Object> companyInfo = new HashMap<String, Object>(); 
		if(parameter.get("companyInfo") != null){
			companyInfo =   (Map<String, Object>) parameter.get("companyInfo");
			
			//회사 등록(office_rent)
			tenantService.insertCompanyInfo(companyInfo);
			
			//office use 등록
			List<Map<String, Object>> officeList = new ArrayList<Map<String, Object>>();
			
			officeList = (List<Map<String, Object>>) companyInfo.get("selectedOfficeList");
			for(int i=0;i<officeList.size();i++){
				officeList.get(i).put("office_rent_id", companyInfo.get("insertKey"));
				tenantService.insertOfficeRentUse(officeList.get(i));
				tenantService.updateOfficeStatus(officeList.get(i));
			}
			if(companyInfo.containsKey("member_id") == false){
				
			}else{
				//담당자 등록
				tenantService.insertMngInfo(companyInfo);
				
				//회사 담당자 key update
				tenantService.updateMngInfo(companyInfo);
				
				
				Map<String, Object> mngInfo = new HashMap<String, Object>(); 
				mngInfo.put("permit_yn", "Y");
				mngInfo.put("member_id", companyInfo.get("insertMemberKey"));
				mngInfo.put("office_rent_id", companyInfo.get("insertKey"));
				mngInfo.put("id", companyInfo.get("member_id"));
				
				
				String qr = mngInfo.get("id").toString() + "|" + mngInfo.get("member_id").toString() + "|" + mngInfo.get("office_rent_id").toString();
				String cryption_qr = Crypto.AseedEnc(qr);
				String decript = Crypto.AseedDec(cryption_qr); 

				mngInfo.put("qr_code", cryption_qr);
				myService.updateMemberPermit(mngInfo);
			}
			
		}

		
		tenantService.insertFileData(companyInfo, atcFileId, request, response);
		messages.addMessage("OK", "");

		return atcFileId;
	}
	
	
	@RequestMapping(value = "/dupCheckId", method = RequestMethod.GET)
	public ResultListVO dupCheckId(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(tenantService.dupCheckId(parameter)); 
		return resultListVO;
	}
	
	
	@RequestMapping(value = "/updateCompanyInfo", method = RequestMethod.POST)
	public String updateCompanyInfo(HttpServletRequest request, HttpServletResponse response, Messages messages){

		String atcFileId = "";
		//String memId = "";
		String json = request.getParameter("data").toString();

		List<Map<String, Object>> atcFileSeqList = new ArrayList<Map<String, Object>>();
		Map<String,Object> parameter = null;

		try {
			parameter = new ObjectMapper().readValue(json, HashMap.class);
		} catch (Exception e) {
			throw new com.anylogic.iot.base.exception.KTBCCException();
		}

		if(parameter.get("atcFileId") != null){
			atcFileId = parameter.get("atcFileId").toString();
		}

		int insertKey ;
		Map<String, Object> companyInfo = new HashMap<String, Object>(); 
		if(parameter.get("companyInfo") != null){
			companyInfo =   (Map<String, Object>) parameter.get("companyInfo");
			
			//tenantService.updateCompanyInfo(companyInfo);
			
			List<Map<String, Object>> recvFileData = new ArrayList<Map<String, Object>>();
			List<Object> curFileData = new ArrayList<Object>();
			recvFileData = (List<Map<String, Object>>) companyInfo.get("recvFileList");
			companyInfo.put("id", companyInfo.get("office_rent_id"));
			companyInfo.put("insertKey", companyInfo.get("office_rent_id"));
			curFileData = tenantService.getCompanyFile(companyInfo);
			
			tenantService.resetCompantFile(companyInfo);
			
			List<Map<String, Object>> officeList = new ArrayList<Map<String, Object>>();
			officeList = (List<Map<String, Object>>) companyInfo.get("selectedOfficeList");
			
			
			
			if(officeList != null){
				tenantService.resetOfficeStatus(companyInfo);
				
				tenantService.resetOfficeRentUse(companyInfo);
				for(int i=0;i<officeList.size();i++){
					officeList.get(i).put("office_rent_id", companyInfo.get("office_rent_id"));
					tenantService.insertOfficeRentUse(officeList.get(i));
					tenantService.updateOfficeStatus(officeList.get(i));
				}
			}
			
			tenantService.resetMngInfo(companyInfo);
			
			tenantService.updateSetMngInfo(companyInfo);
			
			Map<String, Object> mngInfo = new HashMap<String, Object>(); 
			mngInfo.put("permit_yn", "Y");
			mngInfo.put("member_id", companyInfo.get("member_id"));
			mngInfo.put("office_rent_id", companyInfo.get("office_rent_id"));
			mngInfo.put("id", companyInfo.get("member_id"));
			
			if(mngInfo.containsValue("id") && mngInfo.containsValue("member_id")) {
				String qr = mngInfo.get("id").toString() + "|" + mngInfo.get("member_id").toString() + "|" + mngInfo.get("office_rent_id").toString();
				String cryption_qr = Crypto.AseedEnc(qr);
				String decript = Crypto.AseedDec(cryption_qr); 

				mngInfo.put("qr_code", cryption_qr);
				myService.updateMemberPermit(mngInfo);
				
			}
		
			for(int i=0;i<recvFileData.size();i++){
				Map<String, Object> temp = recvFileData.get(i);
				try{
					
					//String confirmStr = (String)temp.get("attach_file_id");
					if(temp.containsKey("attach_file_id")){
						recvFileData.get(i).put("id", companyInfo.get("office_rent_id"));
						tenantService.reinsertCompanyFile(recvFileData.get(i));
					}
				}catch (Exception e) {
					messages.addMessage("not OK", "");

					return atcFileId;
				}
				
			}
			tenantService.updateCompanyInfo(companyInfo);
		}

		
		tenantService.insertFileData(companyInfo, atcFileId, request, response);
		messages.addMessage("OK", "");

		return atcFileId;
	}
	
	
	
	
	
	@RequestMapping(value="/getCompanyListExcel", method=RequestMethod.POST)
	@ResponseBody
	public void getCompanyListExcel(HttpServletRequest request, HttpServletResponse response, 
			   @RequestParam Map<String, Object> parameter, Messages messages) throws ParsePropertyException, InvalidFormatException, IOException {
		String fileName = "입주사목록";

		//total_count + 1 - x.RNUM  Integer.parseInt(from);
		List<CompanyInfoVO> list = tenantService.getCompanyListExcel(parameter);
		int lng = list.size();
		for(int i=0;i<lng;i++){
		int rNum = Integer.parseInt(list.get(i).getRNUM());
		rNum = lng +1 - rNum;
		list.get(i).setRNUM(Integer.toString(rNum));
		}
		excelSVC.getExcelDownLoad("입주사목록", "xlsx", "list", list, response);
	}
	
	
	@RequestMapping(value = "/getCompanyList", method = RequestMethod.GET)
	public ResultListVO getCompanyList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(tenantService.getCompanyList(parameter)); 
		return resultListVO;
	}
	
	@RequestMapping(value = "/getCompanyMember", method = RequestMethod.GET)
	public ResultListVO getCompanyMember(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(tenantService.getCompanyMember(parameter)); 
		return resultListVO;
	}
	
	@RequestMapping(value = "/getOfficeInfo", method = RequestMethod.GET)
	public ResultListVO getOfficeInfo(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(tenantService.getOfficeInfo(parameter)); 
		return resultListVO;
	}
	
}





