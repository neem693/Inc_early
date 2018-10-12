
package com.anylogic.iot.api.mobile.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.mobile.service.MobileService;
import com.anylogic.iot.base.common.InterfacJson;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.excel.service.ExcelSVC;
import com.anylogic.iot.base.mvc.message.Messages;

@RestController
@RequestMapping("/" + Version.V1 + "/mobile")
public class MobileController {

	private static final Logger logger = LoggerFactory.getLogger(MobileController.class);

	@Autowired
	private MobileService mobileService;

	@Autowired
	private ExcelSVC excelSVC;

	public String temporaryPassword(int size) {

		StringBuffer buffer = new StringBuffer();

		Random random = new Random();

		String chars[] = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9"
				.split(",");

		for (int i = 0; i < size; i++) {

			buffer.append(chars[random.nextInt(chars.length)]);

		}

		return buffer.toString();

	}

	
	@RequestMapping(value = "/getLogin", method = RequestMethod.GET)
	public Map<String, Object> getLogin(@RequestParam Map<String, Object> parameter, Messages messages) {
		Map<String, Object> resultMap = new HashMap<>();

		resultMap = mobileService.getLogin(parameter);
		if (resultMap.size() > 0) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return resultMap;
	}

	@RequestMapping(value = "/getPassword", method = RequestMethod.GET)
	public Map<String, Object> getPassword(@RequestParam Map<String, Object> parameter, Messages messages) {
		Map<String, Object> resultMap = new HashMap<>();

		resultMap = mobileService.getPassword(parameter);
		if (resultMap.size() > 0) {
			messages.addMessage("0", "OK");
			
			String tempPw = temporaryPassword(8);
			resultMap.put("tempPw", tempPw);
			mobileService.updatePw(resultMap);
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return resultMap;
	}

	@RequestMapping(value = "/getNoticeList", method = RequestMethod.GET)
	public ResultListVO getNoticeList(@RequestParam Map<String, Object> parameter, Messages messages) {

		ResultListVO resultListVO = new ResultListVO();
		resultListVO.setRows(mobileService.getNoticeList(parameter));
		if (resultListVO.getRowNum() > 0) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "No Data");
		}

		return resultListVO;
	}

	@RequestMapping(value = "/getNotice", method = RequestMethod.GET)
	public Map<String, Object> getNotice(@RequestParam Map<String, Object> parameter, Messages messages) {
		Map<String, Object> resultMap = new HashMap<>();

		resultMap = mobileService.getNotice(parameter);
		if (resultMap.size() > 0) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/setRegUser", method = RequestMethod.POST)
	public int setRegUser(@RequestBody Map<String, Object> parameter, Messages messages) {
		//Map<String, Object> resultMap = new HashMap<>();
		int res;
		res = mobileService.setRegUser(parameter);
		if (res == 1) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return res;
	}
	
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	public Map<String, Object> getUserInfo(@RequestParam Map<String, Object> parameter, Messages messages) {
		Map<String, Object> resultMap = new HashMap<>();

		resultMap = mobileService.getUserInfo(parameter);
		if (resultMap.size() > 0) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/setUserInfo", method = RequestMethod.PUT)
	public int setUserInfo(@RequestBody Map<String, Object> parameter, Messages messages) {
		//Map<String, Object> resultMap = new HashMap<>();
		int res;
		res = mobileService.setUserInfo(parameter);
		if (res == 1) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return res;
	}
	
	@RequestMapping(value = "/getReservationList", method = RequestMethod.GET)
	public ResultListVO getReservationList(@RequestParam Map<String, Object> parameter, Messages messages) {

		ResultListVO resultListVO = new ResultListVO();
		resultListVO.setRows(mobileService.getReservationList(parameter));
		if (resultListVO.getRowNum() > 0) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "No Data");
		}

		return resultListVO;
	}
	
	@RequestMapping(value = "/getReservation", method = RequestMethod.GET)
	public Map<String, Object> getReservation(@RequestParam Map<String, Object> parameter, Messages messages) {
		Map<String, Object> resultMap = new HashMap<>();

		resultMap = mobileService.getReservation(parameter);
		if (resultMap != null && resultMap.size() > 0) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/setReservationCancel", method = RequestMethod.DELETE)
	public int setReservationCancel(@RequestParam Map<String, Object> parameter, Messages messages) {
		//Map<String, Object> resultMap = new HashMap<>();
		int res, resmember;
		resmember = mobileService.setReservationMemberCancel(parameter);
		res = mobileService.setReservationCancel(parameter);
		if (res == 1) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		/*if(resmember == 1){
			res = mobileService.setReservationCancel(parameter);
			if (res == 1) {
				messages.addMessage("0", "OK");
			} else {
				messages.addMessage("1", "Not OK");
			}
		}else{
			res = 0;
			messages.addMessage("1", "Not OK");
		}*/
		
		return res;
	}
	
	@RequestMapping(value = "/getMemberList", method = RequestMethod.GET)
	public ResultListVO getMemberList(@RequestParam Map<String, Object> parameter, Messages messages) {

		ResultListVO resultListVO = new ResultListVO();
		resultListVO.setRows(mobileService.getMemberList(parameter));
		if (resultListVO.getRowNum() > 0) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "No Data");
		}

		return resultListVO;
	}
	
	@RequestMapping(value = "/setReservationService", method = RequestMethod.POST)
	public int setReservationService(@RequestBody Map<String, Object> parameter, Messages messages) {
		//Map<String, Object> resultMap = new HashMap<>();
		int res;
		res = mobileService.setReservationService(parameter);
		if (res == 1) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return res;
	}
	
	@RequestMapping(value = "/getQuestionList", method = RequestMethod.GET)
	public ResultListVO getQuestionList(@RequestParam Map<String, Object> parameter, Messages messages) {

		ResultListVO resultListVO = new ResultListVO();
		resultListVO.setRows(mobileService.getQuestionList(parameter));
		if (resultListVO.getRowNum() > 0) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "No Data");
		}

		return resultListVO;
	}
	
	@RequestMapping(value = "/getQuestion", method = RequestMethod.GET)
	public Map<String, Object> getQuestion(@RequestParam Map<String, Object> parameter, Messages messages) {
		Map<String, Object> resultMap = new HashMap<>();

		resultMap = mobileService.getQuestion(parameter);
		if (resultMap.size() > 0) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/setQuestionDelete", method = RequestMethod.DELETE)
	public int setQuestionDelete(@RequestBody Map<String, Object> parameter, Messages messages) {
		//Map<String, Object> resultMap = new HashMap<>();
		int res;
		res = mobileService.setQuestionReplyDelete(parameter);
		
		//setQuestionDelete
		if (res == 1) {
			res = mobileService.setQuestionReplyDelete(parameter);
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return res;
	}
	
	@RequestMapping(value = "/setQuestionRegistration", method = RequestMethod.POST)
	public int setQuestionRegistration(@RequestBody Map<String, Object> parameter, Messages messages) {
		//Map<String, Object> resultMap = new HashMap<>();
		int res;
		res = mobileService.setQuestionRegistration(parameter);
		
		//setQuestionDelete
		if (res == 1) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return res;
	}
	
	@RequestMapping(value = "/getProgramList", method = RequestMethod.GET)
	public ResultListVO getProgramList(@RequestParam Map<String, Object> parameter, Messages messages) {

		ResultListVO resultListVO = new ResultListVO();
		resultListVO.setRows(mobileService.getProgramList(parameter));
		if (resultListVO.getRowNum() > 0) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "No Data");
		}

		return resultListVO;
	}
	
	@RequestMapping(value = "/getProgram", method = RequestMethod.GET)
	public Map<String, Object> getProgram(@RequestParam Map<String, Object> parameter, Messages messages) {
		Map<String, Object> resultMap = new HashMap<>();

		resultMap = mobileService.getProgram(parameter);
		if (resultMap.size() > 0) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/setProgramRegistration", method = RequestMethod.POST)
	public int setProgramRegistration(@RequestBody Map<String, Object> parameter, Messages messages) {
		//Map<String, Object> resultMap = new HashMap<>();
		int res;
		res = mobileService.setProgramRegistration(parameter);
		
		//setQuestionDelete
		if (res == 1) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return res;
	}
	
	
	@RequestMapping(value = "/getRoomList", method = RequestMethod.GET)
	public InterfacJson getRoomList(@RequestParam Map<String, Object> parameter, Messages messages) {

		ResultListVO resultListVO = new ResultListVO();
		List<Map<String, Object>> seqList = mobileService.getRoomList(parameter);
		
		for(int i=0;i<seqList.size();i++){
			Map<String, Object> paramApi = new HashMap<>();
			List<Map<String, Object>> resDate = new  ArrayList<Map<String, Object>>();
			paramApi.put("findSeq", seqList.get(i).get("CONFERENCE_ID"));
			paramApi.put("resDate", parameter.get("reservationDt"));
			resDate = mobileService.getResDate(paramApi);
			seqList.get(i).put("reservationFinishTime", resDate);
		}
		InterfacJson resCls = new InterfacJson();
		resCls.setResult(seqList.toString());
		//resultListVO.setRows(seqList);
		/*resultListVO.setRows();
		if (resultListVO.getRowNum() > 0) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "No Data");
		}
*/
		return resCls;
	}

	
	@RequestMapping(value = "/setReservationRoom", method = RequestMethod.POST)
	public int setReservationRoom(@RequestBody Map<String, Object> parameter, Messages messages) {
		//Map<String, Object> resultMap = new HashMap<>();
		int res = 0;
		int resKey;
		resKey = mobileService.setReservationRoom(parameter);
		
		List<Map<String, Object>> memberList =  (List<Map<String, Object>>) parameter.get("memberList");
		for(int i=0;i<memberList.size();i++){
			Map<String, Object> paramApi = new HashMap<>();
			paramApi.put("memberId", memberList.get(i).get("memberId"));
			paramApi.put("participantName", memberList.get(i).get("participantName"));
			paramApi.put("companyName", memberList.get(i).get("companyName"));
			paramApi.put("phoneNumber", memberList.get(i).get("phoneNumber"));
			paramApi.put("insertKey", parameter.get("insertKey"));
			res = mobileService.setReservationRoomMember(paramApi);
		}
		
		if (res == 1) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return res;
	}

}
