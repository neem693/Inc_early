
package com.anylogic.iot.api.admin.systemConfig.controller;

import java.util.ArrayList;
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
import com.anylogic.iot.api.admin.systemConfig.service.SystemConfigService;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mvc.message.Messages;


      
@RestController
@RequestMapping("/" + Version.V1+"/admin/systemConfig")
public class SystemConfigController {

	private static final Logger logger = LoggerFactory.getLogger(SystemConfigController.class);

	@Autowired
	private SystemConfigService systemConfigService;

	
	
	@RequestMapping(value = "/getDefaultList", method = RequestMethod.GET)
	public ResultListVO getDefaultList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(systemConfigService.getDefaultList(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
	public void deleteAccount(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.deleteAccount(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	@RequestMapping(value = "/deleteEquipment", method = RequestMethod.POST)
	public void deleteEquipment(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.deleteEquipment(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	@RequestMapping(value = "/deleteFacility", method = RequestMethod.POST)
	public void deleteFacility(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.deleteFacility(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	@RequestMapping(value = "/deleteGoods", method = RequestMethod.POST)
	public void deleteGoods(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.deleteGoods(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	@RequestMapping(value = "/deleteMeetingRoom", method = RequestMethod.POST)
	public void deleteMeetingRoom(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.deleteMeetingRoom(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	@RequestMapping(value = "/deleteOffice", method = RequestMethod.POST)
	public void deleteOffice(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.deleteOffice(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	

	
	
	
	@RequestMapping(value = "/insertAccount", method = RequestMethod.POST)
	public void insertAccount(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.insertAccount(sendParam.get(i));
			systemConfigService.insertDevice(sendParam.get(i));
			
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	@RequestMapping(value = "/insertEquipment", method = RequestMethod.POST)
	public void insertEquipment(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.insertEquipment(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	@RequestMapping(value = "/insertFacility", method = RequestMethod.POST)
	public void insertFacility(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.insertFacility(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	@RequestMapping(value = "/insertGoods", method = RequestMethod.POST)
	public void insertGoods(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.insertGoods(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	@RequestMapping(value = "/insertMeetingRoom", method = RequestMethod.POST)
	public void insertMeetingRoom(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			/*res = systemConfigService.insertMeetingRoom(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}*/
			try {
				res = systemConfigService.insertMeetingRoom(sendParam.get(i));
			} catch (Exception e) {
				String msg = e.getMessage();
			
				
				messages.addMessage("Not OK", "");
			}
			
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				
			}
		}
	}
	@RequestMapping(value = "/insertOffice", method = RequestMethod.POST)
	public void insertOffice(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			try {
				res = systemConfigService.insertOffice(sendParam.get(i));
			} catch (Exception e) {
				String msg = e.getMessage();
			
				
				messages.addMessage("Not OK", "");
			}
			
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				
			}
		}
	}
	
	

	
	
	
	
	@RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
	public void updateAccount(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.updateAccount(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	@RequestMapping(value = "/updateEquipment", method = RequestMethod.POST)
	public void updateEquipment(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.updateEquipment(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	@RequestMapping(value = "/updateFacility", method = RequestMethod.POST)
	public void updateFacility(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.updateFacility(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	@RequestMapping(value = "/updateGoods", method = RequestMethod.POST)
	public void updateGoods(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.updateGoods(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	@RequestMapping(value = "/updateMeetingRoom", method = RequestMethod.POST)
	public void updateMeetingRoom(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.updateMeetingRoom(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	@RequestMapping(value = "/updateOffice", method = RequestMethod.POST)
	public void updateOffice(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.updateOffice(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	
	

	
	
	@RequestMapping(value = "/updateTimeFacility", method = RequestMethod.POST)
	public void updateTimeFacility(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.insertUpdateOpTime(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
			res = systemConfigService.updateTimeFacility(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	@RequestMapping(value = "/updateTimeMeetingRoom", method = RequestMethod.POST)
	public void updateTimeMeetingRoom(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (int i = 0; i < sendParam.size(); i++) {
			res = systemConfigService.insertUpdateOpTime(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
			res = systemConfigService.updateTimeMeetingRoom(sendParam.get(i));
			if (res == 1) {
				messages.addMessage("OK", "");
			} else {
				messages.addMessage("Not OK", "");
			}
		}
	}
	
	
	
	
	@RequestMapping(value = "/idCheck", method = RequestMethod.GET)
	public ResultListVO idCheck(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(systemConfigService.idCheck(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/getBranchList", method = RequestMethod.GET)
	public ResultListVO getBranchList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(systemConfigService.getBranchList(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/getOpTimeList", method = RequestMethod.GET)
	public ResultListVO getOpTimeList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(systemConfigService.getOpTimeList(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	
	@RequestMapping(value = "/getAccountList", method = RequestMethod.GET)
	public ResultListVO getAccountList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(systemConfigService.getAccountList(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	@RequestMapping(value = "/getEquipmentList", method = RequestMethod.GET)
	public ResultListVO getEquipmentList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(systemConfigService.getEquipmentList(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	@RequestMapping(value = "/getFacilityList", method = RequestMethod.GET)
	public ResultListVO getFacilityList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(systemConfigService.getFacilityList(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	@RequestMapping(value = "/getGoodsList", method = RequestMethod.GET)
	public ResultListVO getGoodsList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(systemConfigService.getGoodsList(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	@RequestMapping(value = "/getMeetingRoomList", method = RequestMethod.GET)
	public ResultListVO getMeetingRoomList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(systemConfigService.getMeetingRoomList(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	@RequestMapping(value = "/getOfficeList", method = RequestMethod.GET)
	public ResultListVO getOfficeList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(systemConfigService.getOfficeList(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}	
	

	
//	@RequestMapping(value = "/insertMeetingRoom", method = RequestMethod.POST)
//	public int insertMeetingRoom(@RequestBody Map<String, Object> parameter, Messages messages) {
//		
//		int res = systemConfigService.insertMeetingRoom( parameter );
//
//    	return res;
//	}
//	
//	@RequestMapping(value = "/insertAccount", method = RequestMethod.POST)
//	public int insertAccount(@RequestBody Map<String, Object> parameter, Messages messages) {
//		
//		int res = systemConfigService.insertAccount( parameter );
//
//    	return res;
//	}
}





