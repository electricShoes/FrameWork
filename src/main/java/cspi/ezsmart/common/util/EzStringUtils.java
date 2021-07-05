 package cspi.ezsmart.common.util;
 
 import java.util.Date;
 
 
 
 
 
 
 
 
 
 public class EzStringUtils
 {
   public static String replaceFirst(String source, String original, String replace) {
     return replace(source, original, replace, 1);
   }
 
 
 
 
 
 
 
 
   
   public static String replace(String source, char ch, String replace) {
     return replace(source, ch, replace, -1);
   }
 
 
 
 
 
 
 
 
 
   
   public static String replace(String source, char ch, String replace, int max) {
     return replace(source, (new StringBuilder(String.valueOf(ch))).toString(), replace, max);
   }
 
 
 
 
 
 
 
 
   
   public static String replace(String source, String original, String replace) {
     return replace(source, original, replace, -1);
   }
 
 
 
 
 
 
 
 
 
   
   public static String replace(String source, String original, String replace, int max) {
     if (source == null) return null; 
     int nextPos = 0;
     int currentPos = 0;
     int len = original.length();
     StringBuffer result = new StringBuffer(source.length());
     
     while ((nextPos = source.indexOf(original, currentPos)) != -1) {
       result.append(source.substring(currentPos, nextPos));
       result.append(replace);
       currentPos = nextPos + len;
       if (--max == 0) {
         break;
       }
     } 
     if (currentPos < source.length()) {
       result.append(source.substring(currentPos));
     }
     return result.toString();
   }
 
 
 
 
 
 
 
 
   
   public static String null2void(Object param) {
     String str = new String();
     
     if (param == null) {
       return "";
     }
     
     if (param instanceof String) {
       str = (String)param;
     } else if (param instanceof String[]) {
       str = ((String[])param)[0];
     } else if (param instanceof Date) {
       str = ((Date)param).toString();
     } else {
       str = String.valueOf(param);
     } 
     
     if (str.equals("")) {
       return "";
     }
     return str.trim();
   }
 }
