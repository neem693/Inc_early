package com.anylogic.iot.api.admin.onm.service;

import java.util.List;
import java.util.Map;
import java.lang.Exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anylogic.iot.api.admin.onm.mapper.StudyMapper;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;

@Service
@Transactional
public class StudyService {
	
	
	@Autowired
	StudyMapper study;
	
	public List selectList(Map<String,Object> param) {
		return study.selectList(param);
	}
	
	public int delete(Map<String,Object> param) {
		return study.deleteOne(param);
	}
	
	public List search(Map<String,Object> param) {
		return study.searchTest(param);
	}
	
	
	public int insert(Map<String,Object> param) {
		return study.insertTest2(param);
		
	}
	
	public int update(Map<String,Object> param) {
		return study.updateOne(param);
	}

	public int deleteList(List<Map<String, Object>> delete_list) throws java.lang.Exception {
		// TODO Auto-generated method stub
		
		int res;
		int total_res;
		res = total_res =0;
		for(int i =0; i<delete_list.size();i++) {
			res  = study.deleteOne(delete_list.get(i));
			if(res!=0)
				total_res ++;
			else 
				throw new Exception("삭제리스트 오류 발생");
			
			res =0;
			
		}
		
		return total_res;
	}
	
	
	

}
