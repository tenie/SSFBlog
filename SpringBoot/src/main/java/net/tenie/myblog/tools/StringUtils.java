package net.tenie.myblog.tools;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
 
public class StringUtils {

    public final static String  EMPTY_STRING        = "";
    public final static String  BLANK_SPRING_STRING = " ";
    public static final char    CHAR_TILDE          = '~';
    // 定义日志接口
    private static final Logger logger              = LoggerFactory.getLogger(StringUtils.class);

    public static UUID getRandomUUID() {
        return UUID.randomUUID();
    }

    public static boolean isBlank(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isBlank(cs);
    }

    /**
     * check if null or empty string
     */
    public static boolean isNullOrEmpty(Object obj) {
        return (null == obj || EMPTY_STRING.equals(obj));
    }

    /**
     * 检查是否相等 
     */
    public static boolean isEquals(Object obj, String expectValue) {
        if (isNullOrEmpty(obj) || isNullOrEmpty(expectValue)) {
            return false;
        }
        return expectValue.equals(obj.toString());
    }

    /**
     * 检查是否相等（忽略大小写） 
     */
    public static boolean isEqualsNoCasetive(Object obj, String expectValue) {
        if (isNullOrEmpty(obj) || isNullOrEmpty(expectValue)) {
            return false;
        }
        return expectValue.toUpperCase().equals(obj.toString().toUpperCase());
    }

 
    /**
     * substring with utf-8 bytes length
     * 
     * @param utfStr the string with utf-8 encoding
     */
    public static String subStringWithBytesLength(String utfStr, int bytesLength) throws UtilException {
        if (null == utfStr) {
            throw new UtilException("input string cannot be null");
        }
        if (bytesLength <= 0) {
            throw new UtilException("bytesLength should >0");
        }
        byte[] bytes = null;
        try {
            bytes = utfStr.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UtilException("not support encoding", e);
        }
        if (bytes.length <= bytesLength) {
            return utfStr;
        }
        int index = bytesLength;
        String temp1 = new String(bytes, 0, index);
        String temp2 = new String(bytes, 0, index + 1);
        // when temp2 is end with a new char
        if (temp2.length() > temp1.length()) {
            return temp1;
        }
        // when temp2.length()=temp1.length()
        temp2 = new String(bytes, 0, index - 1);
        // when temp2 is end with a whole char
        if (temp2.length() < temp1.length()) {
            return temp2;
        }
        // when the new String(bytes, 0, index) is in the middle char
        return new String(bytes, 0, index - 2);
    }

    /**
     * split string(bytes) into list
     */
    public static List<String> subStringToListWithBytesLength(String utfStr, int bytesLength) throws UtilException {
        if (null == utfStr) {
            throw new UtilException("input string cannot be null");
        }
        if (bytesLength <= 0) {
            throw new UtilException("bytesLength should >0");
        }
        List<String> list = new ArrayList<>();
        String temp = utfStr;
        String data = EMPTY_STRING;
        while (temp.length() != data.length()) {
            temp = temp.substring(data.length());
            data = subStringWithBytesLength(temp, bytesLength);
            list.add(data);
        }
        return list;
    }

    /**
     * get last size string with maxsize,if string.length> maxsize , return string 'ABC' to '~BC'
     * 
     * @throws UtilException
     */
    public static String getLastStringWithTilde(String str, int maxSize) throws UtilException {
        if (maxSize <= 3) {
            throw new UtilException("max size should >3");
        }
        byte[] bytes = str.getBytes();
        if (bytes.length <= maxSize) {
            return str;
        }
        String temp1 = new String(bytes, bytes.length - maxSize + 1, maxSize - 1);
        logger.debug(temp1 + StringUtils.BLANK_SPRING_STRING + temp1.length() + StringUtils.BLANK_SPRING_STRING
                     + temp1.getBytes().length);

        // the first char is not messy code
        if (temp1.getBytes().length <= maxSize - 1) {
            return CHAR_TILDE + temp1;
        }
        String temp2 = new String(bytes, bytes.length - maxSize + 2, maxSize - 2);
        logger.debug(temp2 + StringUtils.BLANK_SPRING_STRING + temp2.length() + StringUtils.BLANK_SPRING_STRING
                     + temp2.getBytes().length);
        // the first char is not messy code
        if (temp2.getBytes().length <= maxSize - 2) {
            return CHAR_TILDE + temp2;
        }
        String temp3 = new String(bytes, bytes.length - maxSize + 3, maxSize - 3);
        logger.debug(temp3 + StringUtils.BLANK_SPRING_STRING + temp3.length() + StringUtils.BLANK_SPRING_STRING
                     + temp3.getBytes().length);
        // the first char is not messy code
        if (temp3.getBytes().length <= maxSize - 3) {
            return CHAR_TILDE + temp3;
        }
        throw new UtilException("this cannot be true in utf-8");
    }

    /**
     * 
    * 将集合转化为按指定分隔符分隔的字符串
    * @author zhangxc
    * @date 2016年7月19日
    * @param list
    * @param separator
    * @return
     */
    public static String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * get url response file name by useragent
     */
    public static String getEncodeFileName(String showName) {
        // try {
        return showName;
        // return URIEncodingUtils.fileNameEncoding(userAgent, showName);
        // } catch (UnsupportedEncodingException e) {
        // throw new UtilException(e);
        // }
    }

    
     /** 
    * 判断是否是符合的正则表达示
     
     */
    public static boolean isMatcherPatten(String pattenStr,String extensions){
        Pattern patten = Pattern.compile(pattenStr);
        Matcher matcher = patten.matcher(extensions);
        if(matcher.find()){
            return true;
        }else{
            return false;
        }
    }


    public static String getRandomString(int length) {
        String[] randStr = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                             "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                             "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1",
                             "2", "3", "4", "5", "6", "7", "8", "9" };
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomValue = (int) Math.round(Math.random() * (randStr.length - 1));
            sb.append(randStr[randomValue]);
        }
        return sb.toString();
    }
    
    /**
     * 对String 的正则表达示进行处理  */
    public static String getMatcherPatternStr(String sourceStr, String splitPattern, RegexReplaceCallBack callBack) {
        Pattern patten = Pattern.compile(splitPattern);
        Matcher matcher = patten.matcher(sourceStr);
        StringBuffer sb = new StringBuffer(); 
        while(matcher.find()){
            String groupStr = matcher.group(1);
            matcher.appendReplacement(sb, callBack.replace(groupStr)); 
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
