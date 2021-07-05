 package cspi.ezsmart.common.util;
 
 import cspi.ezsmart.common.model.EzMap;
 import cspi.ezsmart.common.util.Utilities;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpSession;
 
 public class SessionUtil {
   public static final String HEADER_X_REQ_WITH = "x-requested-with";
   
   public static void logOut() {
     HttpSession session = Utilities.getSession();
     if (session == null)
       return; 
     session.removeAttribute("LOGIN_USER");
     session.removeAttribute("userId");
   }
   public static void setLoginUser(EzMap user) {
     HttpSession session = Utilities.getSession();
     if (session == null)
       return; 
     session.setAttribute("LOGIN_USER", user);
   }
   public static EzMap getLoginUser() {
     HttpSession session = Utilities.getSession();
     if (session == null)
       return null; 
     return (EzMap)session.getAttribute("LOGIN_USER");
   }
   public static String getLoginUserId() {
     HttpSession session = Utilities.getSession();
     if (session == null)
       return null; 
     EzMap user = (EzMap)session.getAttribute("LOGIN_USER");
     if (user == null)
       return null; 
     return (String)user.get("userId");
   }
   public static boolean isAjaxRequest() {
     return isAjaxRequest(Utilities.getRequest());
   }
   public static boolean isAjaxRequest(HttpServletRequest request) {
     if (request == null)
       return false; 
     return "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
   }
 }

