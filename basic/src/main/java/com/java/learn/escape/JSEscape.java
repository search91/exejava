/**
 *
 */
package com.java.learn.escape;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.lang.StringEscapeUtils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author hzliuzhujie
 *
 */
public class JSEscape {
    public static String GetContent(String html) {
        //String html = "<ul><li>1.hehe</li><li>2.hi</li><li>3.hei</li></ul>";  
        String ss = ">[^<]+<";
        String temp = null;
        Pattern pa = Pattern.compile(ss);
        Matcher ma = null;
        ma = pa.matcher(html);
        String result = null;
        while (ma.find()) {
            temp = ma.group();
            if (temp != null) {
                if (temp.startsWith(">")) {
                    temp = temp.substring(1);
                }
                if (temp.endsWith("<")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                if (!temp.equalsIgnoreCase("")) {
                    if (result == null) {
                        result = temp;
                    } else {
                        result += "____" + temp;
                    }
                }
            }
        }
        return result;
    }

    public static String GetLabel(String html) {
        //String html = "<ul><li>1.hehe</li><li>2.hi</li><li>3.hei</li></ul>";
        String ss = "<[^>]+>";
        String temp = null;
        Pattern pa = Pattern.compile(ss);
        Matcher ma = null;
        ma = pa.matcher(html);
        String result = null;
        while (ma.find()) {
            temp = ma.group();
            if (temp != null) {
                if (temp.startsWith(">")) {
                    temp = temp.substring(1);
                }
                if (temp.endsWith("<")) {
                    temp = temp.substring(0, temp.length() - 1);
                }
                if (!temp.equalsIgnoreCase("")) {
                    if (result == null) {
                        result = temp;
                    } else {
                        result += "____" + temp;
                    }
                }
            }
        }
        return result;
    }


    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);

        for (i = 0; i < src.length(); i++) {

            j = src.charAt(i);

            if (Character.isDigit(j) || Character.isLowerCase(j)
                    || Character.isUpperCase(j)) {
                tmp.append(j);
            } else if (j < 256) {
                tmp.append("%");
                if (j < 16) {
                    tmp.append("0");
                }
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }


    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    /**
     * 将Base64位编码的图片进行解码，并保存到指定目录 
     *
     * @param base64
     *            base64编码的图片信息 
     * @return
     */
    public static void decodeBase64ToImage(String base64, String path,
                                           String imgName) {
        try {
            FileOutputStream write = new FileOutputStream(new File(path
                    + imgName));
            byte[] decoderBytes = Base64.getDecoder().decode(base64);
            System.out.println("fffff");
            System.out.println(new String(decoderBytes));
            write.write(decoderBytes);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String content = null;
        try {
            FileInputStream in = new FileInputStream(new File("C:/Users/hzliuzhujie/Desktop/index.html"));
            byte[] filecontent = new byte[50000];
            in.read(filecontent);
            content = new String(filecontent, "ISO-8859-1");

            int startIdx = 0, endIndx = 0, times = 0;
            String afterContent = content;
            while (content.contains("unescape") && times < 10) {
                times++;//dead loop
                Matcher matcher = Pattern.compile("unescape\\(['\"](.*?)['\"]\\)").matcher(content);
                if (matcher.find()) {
                    startIdx = matcher.start();
                    endIndx = matcher.end();
                    String encodeString = matcher.group(1);
                    String tmp = matcher.group();
                    tmp = unescape(encodeString);
                    String decodeString = "\"" + tmp + "\"";
                    afterContent = afterContent.replace(matcher.group(), decodeString);
                    content = content.substring(endIndx);
                    startIdx = 0;
                }

            }
            System.out.println(afterContent);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        try {
            // HTML Entities https://www.zhihu.com/question/21390312
            // http://ourcodeworld.com/articles/read/188/encode-and-decode-html-entities-using-pure-javascript
            System.out.println(StringEscapeUtils.unescapeHtml("&#38651;&#23376;&#37109;&#20214;&#35373;&#23450;"));

            System.out.println(URLDecoder.decode("%3Ctr%3E%3Ctd%20", "UTF-8"));

            System.out.println(new String(Base64.getEncoder().encode("http://www.letuknowit.com/images/wg.png".getBytes())));
            System.out.println(Base64.getEncoder().encodeToString("http://www.letuknowit.com/images/wg.png".getBytes()));


            System.out.println(new String(Base64.getDecoder().decode("iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAMAAABEpIrGAAAAYFBMVEX///8fckSxzr6VvKZmn38nd0rU5NvY5t4qeU38/fwbb0BZlnTu9PHr8u4+hV6GspmcwKxSkm/J3dKsyrmhxLFLjWiMtp/C2Mx1p4tFimO608Xk7ug4glns8u9vpIb2+fdmMRAaAAABBElEQVQ4ja2T63KEIAxGkxXFcPGCuLutVN//LRu0W9wLdtrpmeHzR44DmAjwzyzOXylTmy6FaJB5FkieWh3wxqPg+hLvqBONZOF0X0ZVJXTxQkBrjAFeZgHxUlBKIXGoNiOsB1UcT4LaHh1DMdxOKIYSZzOsghBiJg4x7AQPM0oK+S0CVRq6rdgwFKPYn8FDtYT0HSDGtBcCQHF4CwHk1JEg7QB62+LEQAy5E67QvkG1vd0zFMMnoZyWGi981dwWdT8ijt15LWqGYvhMLzrvPfDy7tfNuvXsp3Zbaz+Aw2YGRlXym3Xk3PgwtGUiyK+xH1pdpwPmfpz3swhHwopxXfbX+yufIGsPR2RuRtEAAAAASUVORK5CYII=".getBytes())));
            System.out.println(new String(Base64.getDecoder().decode("iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAMAAABEpIrGAAAAYFBMVEX///8fckSxzr6VvKZmn38nd0rU5NvY5t4qeU38/fwbb0BZlnTu9PHr8u4+hV6GspmcwKxSkm/J3dKsyrmhxLFLjWiMtp/C2Mx1p4tFimO608Xk7ug4glns8u9vpIb2+fdmMRAaAAABBElEQVQ4ja2T63KEIAxGkxXFcPGCuLutVN//LRu0W9wLdtrpmeHzR44DmAjwzyzOXylTmy6FaJB5FkieWh3wxqPg+hLvqBONZOF0X0ZVJXTxQkBrjAFeZgHxUlBKIXGoNiOsB1UcT4LaHh1DMdxOKIYSZzOsghBiJg4x7AQPM0oK+S0CVRq6rdgwFKPYn8FDtYT0HSDGtBcCQHF4CwHk1JEg7QB62+LEQAy5E67QvkG1vd0zFMMnoZyWGi981dwWdT8ijt15LWqGYvhMLzrvPfDy7tfNuvXsp3Zbaz+Aw2YGRlXym3Xk3PgwtGUiyK+xH1pdpwPmfpz3swhHwopxXfbX+yufIGsPR2RuRtEAAAAASUVORK5CYII=")));
            decodeBase64ToImage("iVBORw0KGgoAAAANSUhEUgAAADEAAAApCAIAAAATe1a9AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAANGSURBVFhHzZgvaOtAHMcnKyNrJyMnaysrC0+sMFHGRCkTY0yMMjOmHo+JMZ4Y4YlCnhhkopCKQmoGqSh0opCJQSYmTlScmIh4ou97Td52/V3+bU33+uUj2tzvLp/dLtdet+ablw12Cv7M2z1fO3e3Tpxq1794CLIZ87BvShw2p73iqP3y9BO7c+dBA4mcILR16kS07J0br/MQZHL9kKZlPdP6WKo/vfKBtX1kg4ueh46Rk5ihN6dTp9S09KtJZ0z7q1xPg3AEEmNKK2MYB5XLibZnbh8KIaCfOugbOclCISWUfnePR8HxOAPjkWr9GHNSE8Mo2Dl3tV2z/E8oBN0TnbCwSuhw5rTvs29gPL1rtYeMtKpgTEyJ9s0QQstghGQnAC10O7L3h7w9CtIJtRo9Rq6rYDTMhxi5RYWAkFkoJTiBRWetZdUHbH/E02kM6BWVRp9hRS+ELEySSg4nEGo1zVqP7d/zVaj3WLlpCqGDeCGQzwkcLrR2zeqt3xjyz1G79TFCuhDI7YTHsCUmHKuy0vXrQ/5Rql0PfcP5JhKEXE7uM90Yrx+D2oDnh+xh7hPH6k4CBdlOYQEJnrJqn+dB3b0Q4iGD1k86IfjoqPZZOuZzjBBCPGTQ+nknxH4JKn1W6cWD1qhOidgIEkDrSk6IwwLcfmcZXMH1qCIuxEMGras6IZPZQusuAq/dWZoQIraDBNBagBPicfFP1BdCeB1dTQ7xkEFrMU6I/zpvDJn/mi2EEA8ZfK0rzOlDIR4y/DXYOCfG/5dT00qiSCd/FnhZj9tb8O0gicKc7CdeOhPF9mP2YQarmHjIFOOEGXo7YmjnDt5GDQnhPCAeMmy2shP+aP1q6cyjX7rpO5T3womHTAFO9d8eqQe17iRqjos7ZeU9MwlM80pOF0PppLpMZ+BHRcvBvBp9j3jI5HJSv9OFwbomlQRryqJSKVhMxzcu8ZDxZzzbaRXEElHumo7P1uyEEyK5ZSbrd8Lh4sgu7xn5+QonUDq0yrtGTr7ICWj4fFVuHwt2ry9yAuJkpxiovM8T+f1pLYj1Tg1UxASFTku/060Nsd4VCRlsXe9O2F6j3zOVgYolab3rTbPTdaGBRE4blc1zms//AhEqoZP10er/AAAAAElFTkSuQmCC"
                    , "C:/Users/hzliuzhujie/Desktop/"
                    , "hhhh.png");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("#####");

        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByExtension("js");
        try {
            Object res = engine.eval("");
            System.out.println(res);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("#####");
    }
}
