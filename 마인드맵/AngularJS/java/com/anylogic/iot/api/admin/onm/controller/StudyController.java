package com.anylogic.iot.api.admin.onm.controller;

import java.util.List;
import java.util.Map;

import org.apache.avro.file.SyncableFileOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.onm.service.StudyService;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mvc.message.Messages;

@RestController
@RequestMapping("/" + Version.V1 + "/admin/onm")
public class StudyController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudyController.class);
	
	
	
	@Autowired
	private StudyService service;
	
	
	
	@RequestMapping(value = "/selectList_study" , method=  RequestMethod.GET)
	public ResultListVO selectList(@RequestParam Map<String,Object> param,Messages message) {
		System.out.println("이것은 리스트를 불러오는 과정입니다.");
		ResultListVO vo = new ResultListVO();
		vo.setRows(service.selectList(param));

		message.addMessage("OK", "");

		return vo;

	}
	
	
	@RequestMapping(value = "/search_study", method = RequestMethod.GET	)
	public ResultListVO searchList(@RequestParam Map<String,Object> param,Messages message) {
		System.out.println(param);
		ResultListVO vo = new ResultListVO();
		vo.setRows(service.search(param));
		
		message.addMessage("OK", "");
		
		return vo;
	}
	
	@RequestMapping(value = "/insert_study", method = RequestMethod.POST)
	public int insertOne(@RequestBody Map<String,Object> param, Messages messgae) {
		
		System.out.println(param);
		
		
		int res = service.insert(param);
		
		
		return res;
		
		
	}
	
	@RequestMapping(value = "/delete_study",method = RequestMethod.GET)
	public int deleteOne(@RequestParam Map<String,Object> param, Messages message) {
		System.out.println(param);
		
		
		int res = service.delete(param);
		
		return res;
		
		
		
	}
	
	
	@RequestMapping(value = "/update_study",method = RequestMethod.POST)
	public int updateOne(@RequestBody Map<String,Object> param, Messages message) {
		System.out.println(param);
		
		int res = service.update(param);
		
		return res;
		
	}
	
	
	   @RequestMapping(value = "/deleteList_study",method = RequestMethod.POST)
	    public int deleteList(@RequestBody  Map<String,Object> param, Messages messages) throws Exception {
	  //  System.out.println(param);
	    List<Map<String,Object>> target_list = (List<Map<String, Object>>) param.get("target_list");
		 
		//   System.out.println("삭제리스트 실행중입니다.");
		//  System.out.println(target_list.size());
		   if(target_list.size()==0)
			   return 0;
	    	
	    	int res =0;
	    	
	    	res = service.deleteList(target_list);
	    	
	    	if(res>0)
	    	messages.addMessage("OK", "" + res);
	    	else if(res==0)
	    		messages.addMessage("Not OK", "" + res);
	    	
	    	
	    	
	    	
	    	
	    	return res;
	    	
	    	
	    }
	   
	   

	

	
	
	
	
	
	
	
	
	

}
