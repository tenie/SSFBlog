package javamail;
public class Encrypt
 {
 
    public static final int pass1 = 10;
    public static final int pass2 = 1;

     public Encrypt(){
     }
 
	 /**
	 * @Title: DoEncrypt 
	 * @Description: 对密码进行加密的方法
	 * @param @param str    
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	  */
      public static String DoEncrypt(String str)
      {
         StringBuffer enStrBuff = new StringBuffer();
          for (int i = 0; i < str.length(); i++)
          {
              int tmpch = str.charAt(i);
              tmpch ^= 1;
              tmpch ^= 0xa;
              enStrBuff.append(Integer.toHexString(tmpch));
          }
  
          return enStrBuff.toString().toUpperCase();
      }
  
      /**
       * @Title: DoDecrypt 
      * @Description: 对密码进行解密的方法
       * @param @param str
       * @param @return    设定文件 
       * @return String    返回类型 
       * @throws
  */
      public static String DoDecrypt(String str)
      {
          String deStr = str.toLowerCase();
          StringBuffer deStrBuff = new StringBuffer();
          for (int i = 0; i < deStr.length(); i += 2)
         {
              String subStr = deStr.substring(i, i + 2);
              int tmpch = Integer.parseInt(subStr, 16);
              tmpch ^= 1;
              tmpch ^= 0xa;
              deStrBuff.append((char)tmpch);
          }
   
          return deStrBuff.toString();
      }
  
      public static void main(String args[])
     {
          String source = "mimatrj1314";
          String s = DoEncrypt(source);
          System.out.println("de=" + s);
          
//          source = DoDecrypt(s);
//          System.out.println("en=" + source);
  
      }
  }