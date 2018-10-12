package com.anylogic.iot.api.util;

import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * @name FileWriter
 *
 * @ver 0.1
 *
 * @date 2015.03.18
 *
 * @author shin.
 */

public class FileWriter {
	private static final Logger logger = LoggerFactory.getLogger(FileWriter.class);

	private FileOutputStream fos;

	public String writeFile(MultipartFile file, String path, String fileName){
        logger.debug("=====================================================================");
        logger.debug("Write File Name: " + fileName);
        logger.debug("=====================================================================");

		try{
			byte fileData[] = file.getBytes();
			fos = new FileOutputStream(path + fileName);
			fos.write(fileData);
		}
		catch(Exception e){
			e.printStackTrace();
	    }
		finally {
	        if (fos != null) { try { fos.close(); } catch (Exception e2) {e2.printStackTrace();}}
	    }
		return fileName;
	}
}
