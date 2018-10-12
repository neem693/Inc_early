package com.anylogic.iot.api.common.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThemeContextVO {

	String svcThemeCd, svcThemeNm;
	List<Map<String,String>> unitSvcList;
	
	public void makeContext(){
		if(unitSvcList == null) unitSvcList = new ArrayList<Map<String,String>>();
	}

	public String getSvcThemeCd() {
		return svcThemeCd;
	}

	public void setSvcThemeCd(String svcThemeCd) {
		this.svcThemeCd = svcThemeCd;
	}

	public String getSvcThemeNm() {
		return svcThemeNm;
	}

	public void setSvcThemeNm(String svcThemeNm) {
		this.svcThemeNm = svcThemeNm;
	}

	public List<Map<String, String>> getUnitSvcList() {
		return unitSvcList;
	}

	public void addItem(ResultSvcListVO svc) {
		
		for(Map<String,String> map : unitSvcList) if(map.get("unitSvcCd").equals(svc.unitSvcCd)) return;

		Map<String,String> map = new HashMap<String,String>();
		map.put("unitSvcCd", svc.unitSvcCd);
		map.put("unitSvcNm", svc.unitSvcNm);
		unitSvcList.add(map);
	}

}
