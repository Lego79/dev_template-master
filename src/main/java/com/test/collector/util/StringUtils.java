package com.test.collector.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.sql.Clob;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


public class StringUtils {

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static String printStackTraceToHtml(Throwable e) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(e.toString());
		buffer.append("<br/>");
		StackTraceElement element[] = e.getStackTrace();
		for (StackTraceElement stack : element) {
			buffer.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("at").append("&nbsp;").append(stack.toString()).append("<br/>");
		}
		return buffer.toString();
	}


	public static String printStacTraceToString(Throwable e) {
		String buffer = "";
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(out);
		e.printStackTrace(printStream);
		buffer = out.toString();
		return buffer;
	}

	public static Object getInputData(String str) {
		if (str.trim().length() == 0) {
			return null;
		} else {
			return str;
		}
	}

	public static String getRandomStr(int length) {
		StringBuffer randStr = new StringBuffer();
		Random random = new Random();
		for (int i = 1; i < length + 1; i++) {
			randStr.append(random.nextInt(10));
		}
		return randStr.toString().substring(0, length);
	}


	public final static String EMPTY_STR = "";

	public enum Patterns {
		CEL("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", "01"), TEL("^\\d{2,3}-\\d{3,4}-\\d{4}$", "06"), RRN("\\d{6}\\-[1-4]\\d{6}", "02"), EMAIL(
				"^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$", "03"), IP("([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})", "04"), CARD(
				"([0-9]{4})\\-([0-9]{4})\\-([0-9]{4})\\-([0-9]{4})", "05"), NUMBER("^[0-9]*", "06");

		private String value;
		private String type;

		private Patterns(String value, String type) {
			this.value = value;
			this.type = type;
		}

		public String getValue() {
			return this.value;
		}

		public String getType() {
			return this.type;
		}

		public boolean isMatch(String compare) {
			return Pattern.compile(this.value).matcher(compare).matches();
		}

		public String replace(String replace) {
			if (this.isMatch(replace)) {
				switch (this.type) {
				case "01":
				case "06":
					return StringUtils.replace(replace, '*', replace.indexOf("-"), replace.lastIndexOf("-"));
				case "02":
					return StringUtils.replace(replace, '*', replace.indexOf("-") + 1, replace.length());
				case "03":
					int idx = 2;
					replace = StringUtils.replace(replace, '*', idx, replace.indexOf("@"));
					return StringUtils.replace(replace, '*', replace.indexOf("@") + idx, replace.lastIndexOf("."));
				case "04":
					return StringUtils.replace(replace, '*', replace.indexOf("."), replace.lastIndexOf("."), ".");
				case "05":
					return StringUtils.replace(replace, '*', replace.indexOf("-"), replace.lastIndexOf("-"), "-");
				}

			}

			return replace;
		}
	}

	public static String replace(String oldStr, char newChar, int start, int end) {
		return replace(oldStr, newChar, start, end, EMPTY_STR);
	}

	public static String replace(String oldStr, char newChar, int start, int end, String exc) {
		StringBuilder sb = new StringBuilder(oldStr);

		for (int i = start + 1; i < end; i++) {
			if (exc.equals(sb.substring(i, i + 1)))
				continue;

			sb.setCharAt(i, newChar);
		}

		return sb.toString();
	}

	public static String clob2String(Clob clob) {
		try {
			StringBuffer sb = new StringBuffer();
			Reader reader = clob.getCharacterStream();

			char[] buff = new char[1024];
			int nchars = 0;

			while ((nchars = reader.read(buff)) > 0) {
				sb.append(buff, 0, nchars);
			}

			return sb.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public static void setByteValue(byte[] byteValue, String value) {
		if (value == null) {
			value = "";
		}
		byte[] bytes = getBytesUtf8(value);
		int endIdx = 0;

		if (byteValue.length >= bytes.length) {
			endIdx = bytes.length;
		} else {
			endIdx = byteValue.length;
		}

		for (int i = 0; i < endIdx; i++) {
			byteValue[i] = bytes[i];
		}

		for (int j = endIdx; j < byteValue.length; j++) {
			byteValue[j] = ' ';
		}
	}


	public static byte[] getBytesUtf8(String value) {
		return org.apache.commons.codec.binary.StringUtils.getBytesUtf8(value);
	}


	 public static String pad(String src, String pad, int totLen, int mode){
		  String paddedString = "";

		  if(src == null) return "";
		  int srcLen = src.length();

		  if((totLen<1)||(srcLen>=totLen)) return src;

		  for(int i=0; i< (totLen-srcLen); i++){
		   paddedString += pad;
		  }

		  if(mode == -1)
		   paddedString += src; // front padding
		  else
		       paddedString = src + paddedString; //back padding

		  return paddedString;
		 }


	    public static String numberGen(int len, int dupCd ) {
	        
	        Random rand = new Random();
	        String numStr = ""; //난수가 저장될 변수
	        
	        for(int i=0;i<len;i++) {
	            
	            //0~9 까지 난수 생성
	            String ran = Integer.toString(rand.nextInt(10));
	            
	            if(dupCd==1) {
	                //중복 허용시 numStr에 append
	                numStr += ran;
	            }else if(dupCd==2) {
	                //중복을 허용하지 않을시 중복된 값이 있는지 검사한다
	                if(!numStr.contains(ran)) {
	                    //중복된 값이 없으면 numStr에 append
	                    numStr += ran;
	                }else {
	                    //생성된 난수가 중복되면 루틴을 다시 실행한다
	                    i-=1;
	                }
	            }
	        }
	        return numStr;
	    }
	
	public static String getBaseUrl(HttpServletRequest request){
		String scheme = request.getScheme() + "://";
		String serverName = request.getServerName();
		String serverPort = (request.getServerPort() == 80) ? "" : ":" +request.getServerPort();
		String contextPath = request.getContextPath();
		return scheme+serverName+serverPort+contextPath;
	}
	
	public static boolean urlCheck(String url){
		Pattern urlPattern = Pattern.compile("^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$");
		Matcher mc = urlPattern.matcher(url);
		return mc.matches();
	}

	public static String removeSpecialChar(String param)	{
		String match = "[\\{\\}\\[\\]\\/?.,;:|*~`!^\\-_+<>@\\#$%&\\\\\\=\\'\\\"]";
		return param.replaceAll(match, "");
	}
}
