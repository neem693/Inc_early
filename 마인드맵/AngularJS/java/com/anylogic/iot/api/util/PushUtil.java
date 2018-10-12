package com.anylogic.iot.api.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * @name PushUtil
 */

public class PushUtil {
	private static final Logger logger = LoggerFactory.getLogger(PushUtil.class);

	private FileOutputStream fos;

	public boolean sendPush(String url, String memberId, String title, String message) {
        try {
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
 
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
 
            conn.setRequestMethod("GET");
 
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
 
            String inputLine;
            StringBuffer response = new StringBuffer();
 
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
 
            System.out.println(response.toString()); //결과, json결과를 parser하여 처리
 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
	}

}
