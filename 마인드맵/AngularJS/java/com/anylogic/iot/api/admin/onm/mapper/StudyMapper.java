package com.anylogic.iot.api.admin.onm.mapper;

import java.util.List;
import java.util.Map;

public interface StudyMapper {
	
	
	public List<Object> selectList(Map<String, Object> param);

    public int insertTest2(Map<String, Object> param);
    
    public List<Object> searchTest(Map<String, Object> param);
    
    public int deleteOne(Map<String, Object> param);

	public int updateOne(Map<String, Object> param);

}
