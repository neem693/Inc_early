package com.anylogic.iot.api.common.vo;

import java.util.ArrayList;
import java.util.List;

public class DstrContextVO {
	
	String dstrCd, dstrNm;
	List<ThemeContextVO> svcThemeList;
	
	public void makeContext(){
		if(svcThemeList == null) svcThemeList = new ArrayList<ThemeContextVO>();
	}

	public String getDstrCd() {
		return dstrCd;
	}

	public void setDstrCd(String dstrCd) {
		this.dstrCd = dstrCd;
	}

	public String getDstrNm() {
		return dstrNm;
	}

	public void setDstrNm(String dstrNm) {
		this.dstrNm = dstrNm;
	}

	public List<ThemeContextVO> getSvcThemeList() {
		return svcThemeList;
	}

	public void addItem(ResultSvcListVO svc) {
		
		ThemeContextVO dv = null;
		for(ThemeContextVO theme : svcThemeList) if(theme.getSvcThemeCd().equals(svc.svcThemeCd)){
			dv = theme;
			break;
		}
		if(dv == null){
			dv = new ThemeContextVO();
			dv.makeContext();
			dv.setSvcThemeCd(svc.svcThemeCd);
			dv.setSvcThemeNm(svc.svcThemeNm);
			
			svcThemeList.add(dv);
		}
		
		dv.addItem(svc);
	}

}
