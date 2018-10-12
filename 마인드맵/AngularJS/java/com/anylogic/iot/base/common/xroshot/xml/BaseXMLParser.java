package com.anylogic.iot.base.common.xroshot.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.anylogic.iot.base.common.xroshot.common.ErrorCode;
import com.anylogic.iot.base.common.xroshot.common.LoggerMessage;
import com.anylogic.iot.base.common.xroshot.exception.BatchProcessingException;

public class BaseXMLParser {
	private static Logger logger = LoggerFactory.getLogger(BaseXMLParser.class);
	
//	private static Properties commonProperties;
//	
//	public static void setCommonProperties(Properties properties) {
//		commonProperties = properties;
//	}
	
	public static Document getXmlDocument(String requestXML) throws BatchProcessingException {		
		String errorMsgTitile = "Create XML Document";
		String errorMsg = "";
		int errorCode = ErrorCode.RC_OK;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document doc = null;
		ByteArrayInputStream byteArrayIs = null;
		
		try {
//			String charSet = commonProperties.getProperty("xroshot.encoding");
			String charSet = "UTF-8";
			builder = factory.newDocumentBuilder();
			byteArrayIs = new ByteArrayInputStream(requestXML.getBytes(charSet));
			InputSource is = new InputSource(byteArrayIs);
			doc = builder.parse(is);
		} 
		catch(ParserConfigurationException e) {
			errorCode = ErrorCode.XML_PARSING_ERROR;
			errorMsg = "XML 파싱 중 예외가 발생하였습니다.";
			
			logger.error(LoggerMessage.getErrorMessage(errorMsgTitile, errorCode, errorMsg, e.getMessage()));
			
			throw new BatchProcessingException(errorMsg, errorCode);
		}
		catch(SAXException e) {
			errorCode = ErrorCode.XML_PARSING_ERROR;
			errorMsg = "XML 파싱 중 예외가 발생하였습니다.";
			
			logger.error(LoggerMessage.getErrorMessage(errorMsgTitile, errorCode, errorMsg, e.getMessage()));
			
			throw new BatchProcessingException(errorMsg, errorCode);
		} 
		catch(IOException e) {
			errorCode = ErrorCode.IO_ERROR;
			errorMsg = "XML 파싱 중 I/O 예외가 발생하였습니다.";
			
			logger.error(LoggerMessage.getErrorMessage(errorMsgTitile, errorCode, errorMsg, e.getMessage()));
			
			throw new BatchProcessingException(errorMsg, errorCode);
		}
		catch(Exception e) {
			errorCode = ErrorCode.UNKNOWN_ERROR;
			errorMsg = "XML 파싱 중 알수 없는 예외가 발생하였습니다.";
			
			logger.error(LoggerMessage.getErrorMessage(errorMsgTitile, errorCode, errorMsg, e.getMessage()));
			
			throw new BatchProcessingException(errorMsg, errorCode);
		}
		finally {
			try {
				if(byteArrayIs != null) {
					byteArrayIs.close();
					byteArrayIs = null;
				}
			}
			catch (IOException e) {
				errorCode = ErrorCode.IO_ERROR;
				errorMsg = "스트림을 닫는 중 I/O 예외가 발생하였습니다.";
				
				logger.error(LoggerMessage.getErrorMessage(errorMsgTitile, errorCode, errorMsg, e.getMessage()));
			}
		}
		
		return doc;
	}
	
	public static String getNodeTextValue(Node node) throws BatchProcessingException {
		String errorMsgTitile = "XML Node ";
		String errorMsg = "";
		int errorCode = ErrorCode.RC_OK;
		
		String eValue = null;
		
		try {
			Element e = (Element)node;
			eValue = e.getTextContent();
		}
		catch(Exception e) {
			errorCode = ErrorCode.XML_PARSING_ERROR;
			errorMsg = "XML 노드 값을 가져오는 중 예외가 발생하였습니다.";
			
			logger.error(LoggerMessage.getErrorMessage(errorMsgTitile, errorCode, errorMsg, e.getMessage()));
			
			throw new BatchProcessingException(errorMsg, errorCode);
		}
		
		return eValue;
	}
}
