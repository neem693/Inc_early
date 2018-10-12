package com.anylogic.iot.api.util;


import java.util.Formatter;
import java.util.StringTokenizer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * <PRE>
 * ClassName : StringParsingUtil 
 * </PRE>
 * @brief 문자 Parsing 유틸
 * @version 1.0
 */ 
public class StringParsingUtil {
	
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
//	private static Gson gson = new Gson();
	
	//vo객체를 json객체로 변환
	public static String voToJsonStr(Object vo) {

		return gson.toJson(vo);

	}
	
	//json객체를 vo객체로 변환
	@SuppressWarnings("unchecked")
	public static Object jsonStrToVo(String jsonStr, Class vo) {
		
		return gson.fromJson(jsonStr, vo);
		
	}
	

    public static String null2blank(String prnStr)
    {
        if(prnStr==null)
            return "";
        else
            return prnStr;
    }

    public static String null2blank(String prnStr, String defaultValue)
    {
        if(prnStr==null)
            return defaultValue;
        else
            return prnStr;
    }


/*    public static String safeTrim(String str) {
	    if (str != null) {
	      String s = new String(str.trim());
	      return s;
	    } else
	      return (String) "";
    }*/

	/*public static String isSelected( String stValue, String stSelectValue ) {
		if( stSelectValue != null ) {
			if( stValue == null ) stValue = "";

			if( stSelectValue.equals(stValue) ) {
				return " selected";
			}else {
				return "";
			}
		}else {
			return "";
		}
	}*/
	

	public static String isChecked(String stVal1, String stVal2){
		String stCheck = "";
		if(stVal1 != null && stVal2 != null && stVal1.equals(stVal2)){
			stCheck = "checked";
		}
		return stCheck;
	}	
	

/*	public static String decodeX(String s)
    {
        try
        {
            return HextoUTF8(s);
        }
        catch(Exception exception)
        {
            return null;
        }
    }*/


/*	public static  String HextoUTF8(String s)
    {
        byte abyte0[] = new byte[s.length() / 2];
        byte abyte1[] = s.getBytes();

        for(int i = 0; i * 2 < s.length(); i++)
        {
            String s1 = new String(abyte1, i * 2, 2);
            abyte0[i] = Integer.valueOf(s1, 16).byteValue();
        }

        try
        {
            return new String(abyte0, "UTF8");
        }
        catch(Exception exception)
        {
            return null;
        }
    }*/
	
	
	

	/*public static String get(String sInputRetMsg, String psMsg)	{		
		if (sInputRetMsg != null)
			sInputRetMsg = replace(sInputRetMsg, "{0}", psMsg);
		
		return sInputRetMsg;
	}*/


	/*public static String get(String sInputRetMsg, String[] psMsgs)	{

		if (sInputRetMsg != null)
		{
			for (int i = 0; i < psMsgs.length; i++)
				sInputRetMsg = replace(sInputRetMsg, "{" + i + "}", psMsgs[i]);
		}
		
		return sInputRetMsg;
	}*/
	
 
    public static String replace(String source, String fromStr, String toStr) {
        if (source == null)
          return null;
        int start = 0;
        int end = 0;
        StringBuffer result = new StringBuffer();
        while ( (end = source.indexOf(fromStr, start)) >= 0) {
          result.append(source.substring(start, end));
          result.append(toStr);
          start = end + fromStr.length();
        }
        result.append(source.substring(start));
        return result.toString();
      }	


  	public static String[] split(String src, String delim) {
		StringTokenizer st = new StringTokenizer(src, delim);
		int i = 0;
		String arr[] = new String[st.countTokens()];
		while(st.hasMoreTokens()) {
			arr[i++] =st.nextToken();
		}
		return arr;
  	}    
  	

    public static boolean checkStrLen(String str, int len){
    	int strLen = 0;
    	if(str!=null) 
    		strLen = str.getBytes().length;
    	 
    	if(strLen > len) 
    		return false;
    	return true;
    }
    

 /*   public static String getString(String inputUnit) {
		if(inputUnit == null) inputUnit = "";
		return inputUnit;
	}*/
    
    /**
	 * 빈문자여부를 반환합니다.
	 * 
	 * @param str 입력문자열
	 * @return boolean null 또는 "" 이면 true
	 */
/*    public static boolean isEmpty(String str) {

        if (str != null) {
        	str = str.trim();
        }

        return (str == null || str.length() == 0);
    }*/
    
    /**
     * 입력문자열이 null 또는 빈문자열이 아니면 true를 반환합니다.
     * @param str 입력문자열
     * @return boolean 반환결과
     */
/*    public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
    */
    /**
     * 입력된 문자열이 숫자형이면 true를 반환합니다.
     * @param str 입력문자열
     * @return boolean 반환결과
     */
    public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		if (sz == 0)
			return false;
		for (int i = 0; i < sz; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
    
    /**
     * null인 문자열을 빈문자열로 반환합니다.
     * 
     * @param str 입력된 문자열
     * 
     * @return 반환된 문자열
     */
/*    public static String checkNull(String str){
    	return checkNull(str,"");
    }*/
    
    /**
     * 입력된 문자열이 null 인경 우 기본값을 반환합니다.
     * 
     * @param str 입력된 문자열
     * @param def 기본값
     * 
     * @return 반환된 문자열
     */
/*    public static String checkNull(String str, String def){
    	if (isEmpty(str)) {
        	str = def;
        }

        return str;
    }
    */
    

    /**
     * 문자열 오른쪽 공백문자를 제거합니다.
     * 
     * @param str 입력문자열
     * @return 반환문자열
     */
    /*public static String rtrim(String str) {
        int index = str.length();
        while (' ' == str.charAt(--index));
        if (index < str.length())
        	str = str.substring(0, index + 1);
        return str;
	}*/
    
    /**
     *  지정된 길이 len에서 문자 str로 채우고 남은 공간은 왼쪽부터 pad로 채워서 반환한다
     * @param str 문자열
     * @param len 문자열 길이
     * @param pad 채울문자열
     * @return 치환 문자열
     */
/*    public static String lPad(String str, int len, char pad) {
        return lPad(str, len, pad, false);
    }*/

    /**
     * 지정된 길이 len에서 문자 str로 채우고 남은 공간은 왼쪽부터 pad로 채워서 반환한다
     * @param str 문자열
     * @param len 문자열 길이
     * @param pad 채울문자열
     * @param isTrim 공백문자제거여부
     * @return 치환 문자열
     */
/*    public static String lPad(String inputStr, int len, char pad, boolean isTrim) {

        if (isEmpty(inputStr)) {
            return null;
        }

        if (isTrim) {
        	inputStr = inputStr.trim();
        }

        for (int i = inputStr.length(); i < len; i++) {
        	inputStr = pad + inputStr;
        }

        return inputStr;
    }*/
    
    /**
     *  지정된 길이 len에서 문자 str로 채우고 남은 공간은 오른쪽부터 pad로 채워서 반환한다
     * @param str 문자열
     * @param len 문자열 길이
     * @param pad 채울문자열
     * @return 치환 문자열
     */
 /*   public static String rPad(String str, int len, char pad) {
        return rPad(str, len, pad, false);
    }
    */
    /**
     * 지정된 길이 len에서 문자 str로 채우고 남은 공간은 오른쪽부터 pad로 채워서 반환한다
     * @param str 문자열
     * @param len 문자열 길이
     * @param pad 채울문자열
     * @param isTrim 공백문자제거여부
     * @return 치환 문자열
     */
/*    public static String rPad(String str, int len, char pad, boolean isTrim) {

        if (isEmpty(str)) {
            return null;
        }

        if (isTrim) {
        	str = str.trim();
        }

        for (int i = str.length(); i < len; i++) {
        	str = str + pad;
        }

        return str;
    }*/
  
	/**
	 * 문자열을 주어진 character 다음에 오는 글자를 대문자로 변환한다
	 * string_tool --> stringTool
	 * @param targetString 대상 문자열
	 * @param posChar 구분자
	 * @return
	 */
	public static String convertToUpperScore(String targetString, char posChar) {
		StringBuffer result = new StringBuffer();
		boolean nextUpper = false;
		String allLower = targetString.toLowerCase();
		
		for (int i = 0; i < allLower.length(); i++) {
			char currentChar = allLower.charAt(i);
			if (currentChar == posChar) {
				nextUpper = true;
			} else {
				if (nextUpper) {
					currentChar = Character.toUpperCase(currentChar);
					nextUpper = false;
				}
				result.append(currentChar);
			}
		}
		return result.toString();
	}
	
	/**
	 * 주어진 문자열중에서 '-' 다음에 오는 글자를 대문자로 변환한다.
	 * string_tool --> stringTool
	 * @param targetString 대상 문자열
	 * @param posChar 구분자
	 * @return
	 */
	public static String convertToCamelCase(String underScore) {
		return convertToUpperScore(underScore, '_');
	}
	
	/**
	 * 대문자를 구분자로 '_'가 들어있는 문자열로 변환한다
	 * stringTool --> string_tool
	 * @param camelCase
	 * @return
	 */
	public static String convertToUnderScore(String camelCase) {
		String result = "";
		for (int i = 0; i < camelCase.length(); i++) {
			char currentChar = camelCase.charAt(i);
			
			if (i > 0 && Character.isUpperCase(currentChar)) {
				result = result.concat("_");
			}
			result = result.concat(Character.toString(currentChar)
					.toLowerCase());
		}
		return result;
	}
	
	/**
	 * 문자열 배열을 입력된 문자를 구분자로 하나의 문자열로 변환합니다.
	 * 
	 * @param token 구분자
	 * @param strings 입력 문자열 배열
	 * 
	 * @return 합쳐진 문자열
	 */
	public static String last(String token, String[] strings) {
		StringBuffer sb = new StringBuffer();
		
		for(int n = 0; n < (strings.length - 1); n++) {
			sb.append(strings[n]);
			sb.append(token);
		}
		sb.append(strings[strings.length-1]);

		return (sb.toString());
	}	
	
	/**
	 * 입력된 문자열중에서 구분자로 나누어진 맨 마지막 문자열을 반환합니다.
	 * 
	 * @param str 입력문자열
	 * @param delim 구분자
	 * 
	 * @return 구분자로 나누어진 맨마지막 문자열(없다면 입력문자열 그대로)
	 */
    public String lastToken(String str, String delim){
        char[] temp = null;
        char[] type = null;
        String result = "";

        temp = str.toCharArray();
        type = new char[(temp.length-1)];
        for(int i=temp.length; i>0; i--){

            if(!(new String(temp, i-1, 1)).equals(delim)){
                type[i-2] = temp[i-1];

            }else{
                result = new String(type);
                break;
            }
        }
        return result.trim();
    }
    
	/**
	 * html  태그를 이스케이프한 문자열로 반환한다.
	 * 
	 * @param comment HTML 본문내용
	 * 
	 * @return 변환된 문자열
	 */
    public static String escapeHTML(String comment) {
        int length = comment.length();
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            String comp = comment.substring(i, i+1);
            if (" ".compareTo(comp) == 0) {
                comp = comment.substring(++i, i+1);
                buffer.append("&nbsp;");
            } else if ("\r".compareTo(comp) == 0) {
                comp = comment.substring(++i, i+1);
                if ("\n".compareTo(comp) == 0)
                    buffer.append("<BR>\r");
                else
                    buffer.append("\r");
            } else if("\n".compareTo(comp) == 0){
                buffer.append("<BR>\r");
            } else if("<".compareTo(comp) == 0){
                comp = comment.substring(++i, i+1);
                buffer.append("&lt;");
            } else if(">".compareTo(comp) == 0){
                comp = comment.substring(++i, i+1);
                buffer.append("&gt;");
            }

            buffer.append(comp);
        }
        return buffer.toString();
    }

    /**
     * html 태그가 이스케이프한 문자열을 html문자열로 변환하여 반환한다.
     * 
     * @param comment 이스케이프한 문자열
     * 
     * @return 변환된 문자열
     */
 	public static String unescapeHTML(String comment) {
		int length = comment.length();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; ++i) {
		    String comp = comment.substring(i, i+1);
		    if (" ".compareTo(comp) == 0) {
			    comp = comment.substring(++i, i+1);
			    buffer.append("&nbsp");
			} else if ("\r".compareTo(comp) == 0) {
			    comp = comment.substring(++i, i+1);
			    if ("\n".compareTo(comp) == 0)
			        buffer.append("<BR>\r");
			    else
			        buffer.append("\r");
			} else if (",".compareTo(comp) == 0) {
			    comp = comment.substring(++i, i+1);
			    buffer.append(" ");
			} else if("\n".compareTo(comp) == 0){
			    buffer.append("<BR>\r");
	        }
	        buffer.append(comp);
	    }
	    return buffer.toString();
	}
 	
 	/**
	  * 특수문자를 자바스크립트 에서 인식할수 있는 문자로 바꾸어준다 JDK1.5 버전 적용
	  * 
	  * @param str the str
	  * 
	  * @return String 변환문자열
	  */
    public static String unescapeJS(String str){
    	/*
    	return str.replaceAll("\\\\","\\\\\\\\")
    				.replaceAll("'","\\\'")
    				.replaceAll("\"","\\\"")
    				.replaceAll("\r\n","\\\n")
    				.replaceAll("\n","\\\n");
    		1.4버전 이하인경우 위에 것을 사용
    	*/
    	return str.replace("\\","\\\\")
		.replaceAll("'","\\\'")
		.replaceAll("\"","\\\"")
		.replaceAll("\r\n","\\n")
		.replaceAll("\n","\\n");
	}
    

    /**
     * 1차 String배열을 JSON 배열형태로 반환합니다. ['aa','bb',..]
     * 
     * @param array the array
     * 
     * @return String
     */
/*    public static String toJsonArray(String[] array){
    	String arr[] = array;
    	String statusJSON = "[";
    	for(int i=0 ;i<arr.length;i++){
    		arr[i] = unescapeJS(arr[i]);
    		statusJSON = statusJSON + "'"+arr[i]+"'";
    	}
    	statusJSON = statusJSON + "]";
    	return statusJSON.replaceAll("''","','");
    }*/
    
	    
    /**
     * 10진수 String값을 16진수로 변환하는 함수.
     * 
     * @param in (String, 10진수값)
     * 
     * @return (String, 16진수값)
     */
    public static String convertDecToHex(String in) {
    	if(in == null || in.equals("")) {
    		return in;
    	}else {
    		int dec = Integer.parseInt(in, 10);
    		
    		return String.valueOf(Integer.toHexString(dec));
    	}
    }
    
    /**
     * 16진수 String값을 10진수로 변환하는 함수.
     * 
     * @param in (String, 16진수값)
     * 
     * @return (String, 10진수값)
     */
    public static String convertHexToDec(String in) {
    	if(in == null) {
    		return null;
    	}else {
    		if (in.equals("**"))
    			return "*";
    		
    		int hex = Integer.parseInt(in, 16);
    		return String.valueOf(Integer.toString(hex));
    	}
    }
    
    /**
     * 10진수 IP를 16진수 IP로 변환하는 함수.
     * 
     * @param in (10진수 IP)
     * 
     * @return 16진수 IP
     */
   /* public static String convertDipToHip(String in) {
        if(in == null) {
    		return null;
    	}else if(in.indexOf(".") > -1) {
    		StringBuffer sb = new StringBuffer();
    		
    		String[] decIps = split(".", in);
    		String hex = null;
    		
    		for(int i=0 ; i<decIps.length ; i++) {
    			hex = convertDecToHex(decIps[i]);
    			if(hex.length() < 2)
    				hex = fill(2, hex);
    			
    			sb.append(hex);
    		}
    		
    		return sb.toString();
    	}else {
    		return "error";
    	}
    }*/
    
    /**
     * 주어진 문자열의 공백부부을 특정문자('0')로 채운다.
     * 
     * @param len the len
     * @param str the str
     * 
     * @return the string
     */
  /*  public static String fill(int len, String str) {
    	return fill(len, "0", str);
    }*/
    
    /**
     * 주어진 문자열의 공백부부을 특정문자로 채운다
     * 예)	원문자열		--> 1
     * 문자열 크기	--> 5
     * 채울문자		--> 0
     * 변환 		--> 00001.
     * 
     * @param len the len
     * @param token the token
     * @param str the str
     * 
     * @return the string
     */
/*    public static String fill(int len, String token, String inputStr){
		if(token == null)
			token = "0";
		
		int differLen = len - inputStr.length();
		
		if(differLen <= 0) {
			return inputStr;
		}else {
			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i < differLen ; i++) {
				sb.append(token);
			}
			sb.append(inputStr);
			return sb.toString();
		}
	}*/
    
    /**
	 * 주어진 문자열에서 특정 문자열을 제외한 문자열을 반환합니다
	 * 
	 * <pre>
	 * StringTool.trim("pass*word", "*") = "password"
	 * </pre>
	 * 
	 * @param origString 원래문자열
	 * @param trimString 제외할 문자열
	 * @return 치완된 문자열
	 */
	public static String trim(String origString, String trimString) {
		int startPosit = origString.indexOf(trimString);
		if (startPosit != -1) {
			int endPosit = trimString.length() + startPosit;
			return origString.substring(0, startPosit)
					+ origString.substring(endPosit);
		}
		return origString;
	}
	
    /**
     * 문자열을 Byte 단위로 지정된 길이만큼 잘라내어 반환한다
     * DB length와 한글 데이타 길이의 시스템간 차이에 따라
     * 잘라낸 한글 문자열이 깨지는 현상 해결..
     * 만약 문자열의 바이트 길이가 정상 문자열 바이트 길이보다 같거나
     * 작은 경우는 원래 문자열을 리턴한다.
     * 
     * @param str 문자열
     * @param len 제한길이
     * 
     * @return the string
     */
    public static String truncate(String str, 
            					  int len) {
        if(str == null)
            return "";
        
        String crop = str;
        int slen = 0;
        int blen = 0;
        char c;
        
        try {
    		while (blen + 1 <= len) {
    			c = crop.charAt(slen);
    			blen++;
    			slen++;
    			if ( c > 127 ) blen++; //2-byte character..
    		}
    		crop = crop.substring(0, slen);
        }catch (Exception e) {
        	e.printStackTrace();
 //           logger.error("StringTool.cropByte() :::"+ e.toString());
        }
        
        return crop;
    }
    
    /**
     * 숫자형의 문자열을 데이타크기형으로 반환한다
     * 용량에 따라 단위 자동생성
     * K < M < G < T.
     * 
     * @param value the value
     * 
     * @return the string
     */
    public static String toDataLength(String value){
		long returnValue = Long.parseLong(value);
		String str = "";
		if(returnValue<1024){
			if(returnValue == 0){
				str = returnValue+"K";
			}else{
				str = "0."+returnValue*100/1024+"K";
			}
			if(str.equals("0.0K")){
				str = "0K";
			}
		}else{
			returnValue=returnValue/1024;
			if(returnValue<1024){
				str = returnValue+"K";
			}else{
				returnValue=returnValue/1024;
				if(returnValue<1024){
					str = returnValue+"M";
				}else{
					returnValue=returnValue/1024;
					if(returnValue<1024){
						str = returnValue+"G";
					}else{
						returnValue=returnValue/1024;
						str = returnValue+"T";
					}
				}
			}
		}
		return str;
    }
    
    
    
    
    /**
     * 문자열을 ISO8859_1 인코딩방식으로 변환하여 반환합니다.
     * 
     * @param english  문자열
     * 
     * @return 인자로 주어진 문자열을 ISO8859_1 인코딩방식으로 변환된 값
     */
	public static String toKr(String english) {
	    String korean = english;
		try {
		    if(english != null){
		      korean = new String(english.getBytes("8859_1"), "EUC-KR");
		    }
		} catch(Exception e) {
			e.printStackTrace();
		}
		return korean;
	}
	
	/**
	 * 문자열을 KSC5601 인코딩방식으로 변환하여 반환합니다.
	 * 
	 * @param korean 한글 문자열
	 * 
	 * @return 인자로 주어진 문자열을 KSC5601 인코딩방식으로 변환된 값
	 */
/*	public static String toEn(String korean) {
		String english = korean;
		try {
			if(korean != null)
				english = new String(korean.getBytes("EUC-KR"),"8859_1");

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return english;
	}*/
	
	/**
	 * 문자열을 UTF-8 인코딩방식으로 변환하여 반환합니다.
	 * 
	 * @param in the in
	 * 
	 * @return the string
	 */
	public static String toUTF8e(String in) {
		
		String utf = in;
		try {
			
			if(in != null)
				utf = new String(in.getBytes("8859_1"), "UTF-8");

		}catch(Exception e) {
			e.printStackTrace();
		}
		return utf;
		
	}
	
	/**
	 * 문자열을 UTF-8 인코딩방식으로 변환하여 반환합니다.
	 * 
	 * @param in the in
	 * 
	 * @return the string
	 */
	public static String toUTF8k(String in) {
		
		String utf = in;
		
		try {
			
			if(in != null)
				utf = new String(in.getBytes("EUC-KR"), "UTF-8");

		} catch(Exception e) {
			e.printStackTrace();
		}
		return utf;
	}
	
	

	/*
	 * 000000000~255255255의 RGB 값을 #000000~#ffffff로 변환 합니다.
	 */
	/**
	 * Convert rgb dec to hex.
	 * 
	 * @param decimal the decimal
	 * 
	 * @return the string
	 */
	public static String convertRGBDecToHex(String decimal) {
		String returnValue = null;
		Formatter formatter = null;
		
		formatter = new Formatter();
		returnValue = "#"+ 
					formatter.format("%02x%02x%02x",
						Integer.parseInt(decimal.substring(0, 3)),
						Integer.parseInt(decimal.substring(3, 6)),
						Integer.parseInt(decimal.substring(6, 9))
					).toString();
		return returnValue;
	}
	/*
	 * 카드 이름 으로 부터 카드 위치 부분을 파싱 합니다. 형식 : 'C'로 시작 하는 숫자 형식이고 이후에 '/'가 있을 수 있음.
	 * 예)C1/0, 'C'와 '/'이후를 삭제 하고 '1' 만 추출.
	 */
	/**
	 * Parses the slot position.
	 * 
	 * @param cardName the card name
	 * 
	 * @return the string
	 */
	public static String parseSlotPosition(String cardName) {
		String returnValue = null; // return value;
		String regexp = "\\d+"; // 숫자형식 인지 확인하기 위한 정규식 입니다.
		returnValue = cardName.replaceFirst("C", "").replaceFirst("/.*", ""); // 첫번째 문자'C'를 없애고, '/'가 있다면 '/'와 그 이후 문자열을 삭제합니다.
		if (!returnValue.matches(regexp)) { // 숫자가 아니라면 null을 리턴 합니다.
			returnValue = null;
		}
		return returnValue;
	}
	
	/**
	 * Str format.
	 * 
	 * @param fmt the fmt
	 * @param str the str
	 * 
	 * @return the string
	 */
	/*public String strFormat(String fmt, String str) {
		int iFmt = 0;
		String rtnStr = "";

		if (fmt != null && !fmt.equals("")) {
			iFmt = Integer.parseInt(fmt);
		} else {
			rtnStr = "";
			return rtnStr;
		}

		if (iFmt < 0) {
			iFmt = iFmt * -1;

			rtnStr += str;

			for (int i = 0; i < iFmt - str.length(); i++) {
				rtnStr += " ";
			}
		} else {
			if (!fmt.substring(0, 1).equals("0")) {
				rtnStr = str;
			} else {
				for (int i = 0; i < iFmt - str.length(); i++) {
					rtnStr = rtnStr+"0";
				}

				rtnStr = rtnStr + str;
			}
		}

		return rtnStr;

	}*/


	/**
	 * Split2.
	 * 
	 * @param s0 the s0
	 * @param c0 the c0
	 * 
	 * @return the string[]
	 */
	public static String[] split2(String s0, char c0) {
		char[] ch = s0.toCharArray();
		StringBuffer sb = new StringBuffer();
		try {
			for (int pos = 0; pos < ch.length; pos++) {
				if ( ch[pos] == c0 ){
					sb.append("@");
				} else {
					sb.append(ch[pos]);
				}
			}

			return sb.toString().split("@");
		}finally{
			ch = null;
			sb = null;
		}
	}
	
	/**
	 * 입력된 문자열을 Reverse합니다.
	 * abc --> cba
	 * @param str 입력문자열
	 * @return 치환 문자열
	 */
	public static String reverse(String str) {
		if (str == null) {
			return null;
		}
		return new StringBuffer(str).reverse().toString();
	}

	/**
	 * 두 문자열이 동일문자열인지를 비교합니다.
	 * 비교대상 문자열이 null 과 "" 인경우에도 동일 문자열로 처리됩니다.
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean equals(String source, String target) {

        return checkNull(source).equals(checkNull(target));

    }
	
	/**
	 * 지정된 위치의 문자열을 추출합니다
     * @param str 대상문자열
     * @param beginIndex 시작위치
     * @param endIndex 종료위치
     * @return 추출된 문자열
     */
    public static String toSubString(String str, int beginIndex, int endIndex) {

        if (equals(str, "")) {
            return str;
        } else if (str.length() < beginIndex) {
            return "";
        } else if (str.length() < endIndex) {
            return str.substring(beginIndex);
        } else {
            return str.substring(beginIndex, endIndex);
        }

    }
	
    /**
     * 문자열이 특정 정규표현식에 부합하는지를 검사한다.
     * @param pattern 정규표현식
     * @param str 대상문자열
     * @return 검사결과
     * @throws Exception
     */
  /*  public static boolean isPatternMatch(String pattern, String str) throws Exception {
    	return Pattern.matches(pattern,str);
	}*/
    
    /**
     * 모든 문자열에 특정 정규표현식에 부합하는지를 검사한다.
     * 
     * @param pattern 정규표현식
     * @param str 대상문자열
     * @return 검사결과
     * @throws Exception
     */
/*    public static boolean isPatternMatching(String inputPattern, String str) throws Exception {
		if (inputPattern.indexOf('*') >= 0) {
			inputPattern = inputPattern.replaceAll("\\*", ".*");
		}
		
		inputPattern = "^" + inputPattern + "$";

		return isPatternMatch(inputPattern, str);
	}*/
    
    /**
     * 주어진 객체가 NULL 여부를 확인한다. Null인 경우 true를 반환한다.
     * @param object
     * @return
     */
    public static boolean isNull(Object object){

    	boolean result = true;
    	if (object != null) {
            result = false;
        }
    	return result;
    }
    
    /**
     * 문자형 객체를 전달 받아 String으로 반환한다. 단 NULL인 경우 ""를 반환하고 
     * @param object
     * @return
     */
    public static String checkNull(Object object){
    	String string ="";
        if (object != null) {
            string = object.toString().trim();
        }
        return string;
    }

	/**
	 * LPAD, RPAD 처리
	 * 
	 * @param str
	 *            : 입력문자열
	 * @param padLen
	 *            : 총자리수
	 * @param padChar
	 *            : 채워야할 문자
	 * @param padFlag
	 *            : 'L' LPAD, 'R' RPAD 구분
	 * @return String
	 */
	/*public static String setPad(String inputStr, int padLen, String padChar, String padFlag) {
		if (inputStr == null) {
			inputStr = "";
		}
		
		if(inputStr.length() > padLen)
			return inputStr.substring(0, padLen);
		
		// LPAD
		if (padFlag.equals("L")) {
			while (inputStr.length() < padLen)
				inputStr = padChar + inputStr;
		// RPAD
		} else if (padFlag.equals("R")) {
			while (inputStr.length() < padLen)
				inputStr = inputStr + padChar;
		}

		return inputStr;
	}*/
	

}
