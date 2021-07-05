package cspi.ezsmart.common.util.mybatis;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.lang.StringUtils;



public class iBatisUtils
{
  public static boolean isEmpty(Object o) {
    if (o == null) return true;
    
    if (o instanceof String)
      return ((String)o).equals(""); 
    if (o instanceof Collection)
      return ((Collection)o).isEmpty(); 
    if (o.getClass().isArray())
      return (Array.getLength(o) == 0); 
    if (o instanceof Map) {
      return ((Map)o).isEmpty();
    }
    return (o == null);
  }







  
  public static boolean isNotEmpty(Object o) {
    return !isEmpty(o);
  }









  
  public static boolean isExists(Object _parameter, String name) {
    if (_parameter == null) {
      return false;
    }
    
    if (_parameter instanceof Map) {
      if (((Map)_parameter).containsKey(name)) {
        return true;
      }
      return false;
    } 





    
    return true;
  }









  
  public static boolean isNotExists(Object _parameter, String name) {
    return !isExists(_parameter, name);
  }
  
  public static boolean isNotBlank(Object o) {
    return !isBlank(o);
  }
  
  public static boolean isNumber(Object o) {
    if (o == null)
      return false; 
    if (o instanceof Number) {
      return true;
    }
    if (o instanceof String) {
      String str = (String)o;
      if (str.length() == 0)
        return false; 
      if (str.trim().length() == 0)
        return false; 
      return StringUtils.isNumeric(str);
    } 
    return false;
  }
  
  public static boolean isBlank(Object o) {
    if (o == null)
      return true; 
    if (o instanceof String) {
      String str = (String)o;
      return isBlank(str);
    } 
    return false;
  }
  
  public static boolean isBlank(String str) {
    if (str == null || str.length() == 0) {
      return true;
    }
    
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isWhitespace(str.charAt(i))) {
        return false;
      }
    } 
    return true;
  }
}
