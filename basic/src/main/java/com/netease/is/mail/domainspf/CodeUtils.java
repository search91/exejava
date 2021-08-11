package com.netease.is.mail.domainspf;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;


public class CodeUtils {
    /**
     * 以特定编码方案编码字符串，返回编码得到的字节流
     * 
     * @param str
     * @param codeMethod
     * @return
     * @throws UnsupportedEncodingException
     */
    public static byte[] encode(String str, CodeMethod codeMethod) throws UnsupportedEncodingException {
        if (str == null || str.equals(""))
            return null;

        return str.getBytes(codeMethod.getCharset());
    }

    /**
     * 以特定编码方案解码字节流，返回解码得到的字符串
     * 
     * @param bytes
     * @param codeMethod
     * @return
     */
    public static String decode(byte[] bytes, CodeMethod codeMethod) {
        if (bytes == null || bytes.length == 0)
            return null;

        return new String(bytes, Charset.forName(codeMethod.getCharset()));
    }

    /**
     * 常见编码方案枚举
     */
    public enum CodeMethod {
        UTF8("UTF-8"), GBK("GBK"), ISO("ISO"), ASCII("ASCII");

        private String charset;

        CodeMethod(String charset) {
            this.charset = charset;
        }

        public String getCharset() {
            return charset;
        }
    }
    
    static boolean isGBK(String a) {
        String b;
		try {
			b = CodeUtils.decode(CodeUtils.encode(a, CodeMethod.GBK), CodeMethod.GBK);
			 return a.equals(b);
		} catch (UnsupportedEncodingException e) {
			return false;
		}
       
    }

    public static void main(String[] args) {
    	isGBK("�");
    }
}