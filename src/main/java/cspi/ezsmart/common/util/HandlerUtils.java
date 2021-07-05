 package cspi.ezsmart.common.util;
 
 import org.springframework.web.method.HandlerMethod;
 
 public class HandlerUtils {
   public static boolean isInstance(Object handler, Class<?> clazz) {
     if (!(handler instanceof HandlerMethod)) {
       return false;
     }
     
     Object object = ((HandlerMethod)handler).getBean();
     if (clazz.isInstance(object)) {
       return true;
     }
     
     return false;
   }
 
 
   
   public static <T> T toHandlerClass(Object handler, Class<T> t) {
     if (handler instanceof HandlerMethod) {
       Object object = ((HandlerMethod)handler).getBean();
       
       if (t.isInstance(object)) {
         return (T)object;
       }
     } 
     
     return null;
   }
 }
