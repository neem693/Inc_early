package com.anylogic.iot.base.excel.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anylogic.iot.Version;

/**
 * <PRE>
 *  ClassName : ExcelCTRL
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 6. 26. 오후 5:30:58
 * @author  : moo
 * @brief   :
 */

@RestController
@RequestMapping("/" + Version.V1+"/excel")
public class ExcelCTRL {

	/**
	 * <PRE>
	 *  MethodName : download
	 * </PRE>
	 * @author : moo
	 * @date   : 2015. 6. 26. 오후 5:32:29
	 * @param  :
	 * @return : void
	 * @brief  : 엑셀 샘플 다운로드
	 * @param filePath
	 * @param downFileName
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/fileDownload", method=RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public void download(String filePath, String downFileName, HttpServletRequest request, HttpServletResponse response) throws Exception{
        File fileToDownload = new File(request.getRealPath("/")+filePath+downFileName);
        InputStream inputStream = new FileInputStream(fileToDownload);
        String docName = URLEncoder.encode (downFileName,"UTF-8");
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment; filename="+docName);
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
        inputStream.close();
	}
}
