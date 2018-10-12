package com.anylogic.iot.api.common.vo;

import java.io.Serializable;

import com.anylogic.iot.base.util.PropUtil;


/**
 * <PRE>
 *  ClassName : FilePathVO
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 6. 16. 오후 8:06:18
 * @author  : jun
 * @brief   :
 */

public class FilePathVO implements Serializable{

	private static final long serialVersionUID = 5437547473343711939L;

	private com.anylogic.iot.Const.FILE_TYPE_CD filePathInfo = null;

	private static String SYSTEM_ROOT_PATH;
	private static String SYSTEM_URL_PATH;

	static {
		try {
			SYSTEM_ROOT_PATH = PropUtil.getInstance().getPropValue(com.anylogic.iot.Const.DEFAULT_ATACH_FILE_PROPERTICE, com.anylogic.iot.Const.DEFAULT_ATACH_FILE_SYSTEM_ROOT);
			SYSTEM_URL_PATH = PropUtil.getInstance().getPropValue(com.anylogic.iot.Const.DEFAULT_ATACH_FILE_PROPERTICE, com.anylogic.iot.Const.DEFAULT_ATACH_FILE_URL_ROOT);
		} catch (Exception e) {
			SYSTEM_ROOT_PATH = "/";
			SYSTEM_URL_PATH = "/";
		}
	}

	private FilePathVO(){
		super();
	}

	public FilePathVO(String fileTypeCd){
		super();

		if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_COM.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_COM;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_THUMB.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_THUMB;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_BRC.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_BRC;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_CRS.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_CRS;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_LOGO.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_LOGO;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_CPA.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_CPA;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_RP.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_RP;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_AVI.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_AVI;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_ETC.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_ETC;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_MODEL.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_MODEL;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_MODEM.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_MODEM;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_WARES.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_WARES;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_NET.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_NET;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_KC.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_KC;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_BIZR.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_BIZR;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_DCLA.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_DCLA;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_WAIVER.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_WAIVER;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_ETCTXT.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_ETCTXT;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_TFA.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_TFA;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_MFA.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_MFA;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_VERS.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_VERS;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_VERF.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_VERF;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_VERFA.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_VERFA;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_VERSUL.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_VERSUL;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_VERETC.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_VERETC;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_DIST.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_DIST;
		}else if(com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_MODEMFW.getTypeCd().equals(fileTypeCd)){
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_MODEMFW;
		}else {
			filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_DEF;
		}
	}

	/**
	 * <PRE>
	 *  MethodName : getSystemRootPath
	 *               시스템 경로 획득
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 22. 오전 11:03:28
	 * @param  :
	 * @return : String
	 * @brief  :
	 * @return
	 */
	public String getSystemRootPath(){
		if(this.filePathInfo == null) this.filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_DEF;

		String rtnStr = "";

		try {
			rtnStr =  SYSTEM_ROOT_PATH + PropUtil.getInstance().getPropValue(com.anylogic.iot.Const.DEFAULT_ATACH_FILE_PROPERTICE, filePathInfo.getSystemPath());
		} catch (Exception e) {
			throw new com.anylogic.iot.base.exception.KTBCCException("Attach file path not define");
		}
		return rtnStr;
	}

	/**
	 * <PRE>
	 *  MethodName : getUrlPath
	 *               URL 경로 획득
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 22. 오전 11:03:38
	 * @param  :
	 * @return : String
	 * @brief  :
	 * @return
	 */
	public String getUrlPath(){
		if(filePathInfo == null) filePathInfo = com.anylogic.iot.Const.FILE_TYPE_CD.ATTACH_DEF;
		String rtnStr = "";
		try {
			rtnStr = SYSTEM_URL_PATH + PropUtil.getInstance().getPropValue(com.anylogic.iot.Const.DEFAULT_ATACH_FILE_PROPERTICE, filePathInfo.getSystemPath());
		} catch (Exception e) {
			throw new com.anylogic.iot.base.exception.KTBCCException("Attach file path not define");
		}

		return rtnStr;
	}


}
