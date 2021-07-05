 package cspi.ezsmart.manage.controller.system;
 
 import cspi.ezsmart.common.util.Utilities;
 import cspi.ezsmart.manage.model.EzAjaxException;
 import cspi.ezsmart.manage.model.EzException;
 import java.sql.SQLIntegrityConstraintViolationException;
 import java.util.Enumeration;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.ModelMap;
 import org.springframework.web.bind.annotation.ControllerAdvice;
 import org.springframework.web.bind.annotation.ExceptionHandler;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 
 
 
 
 
 @Controller
 @ControllerAdvice
 @RequestMapping({"/error", "{menuId}/error"})
 public class ErrorController
 {
   private Logger logger = LoggerFactory.getLogger(cspi.ezsmart.manage.controller.system.ErrorController.class);
 
 
   
   @RequestMapping({"", "index"})
   public String init(HttpServletRequest request, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     this.logger.debug("[init] param :: {}", param);
     this.logger.debug("[init] model :: {}", model);
     
     for (Enumeration<String> enumeration = request.getAttributeNames(); enumeration.hasMoreElements(); ) {
       String attributeName = enumeration.nextElement();
       Object attribute = request.getAttribute(attributeName);
       
       this.logger.debug("[init] {} :: {}", attributeName, attribute);
     } 
 
 
 
     
     Exception ex = (Exception)request.getAttribute("javax.servlet.error.exception");
     if (ex != null)
     {
       if (ex instanceof org.apache.tiles.request.render.CannotRenderException) {
         return String.valueOf(Utilities.getProperty("tiles.manage.blank")) + "/error/error";
       }
     }
     return String.valueOf(Utilities.getProperty("tiles.manage")) + "/error/error";
   }
   
   @RequestMapping({"render"})
   public String render(HttpServletRequest request, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     return String.valueOf(Utilities.getProperty("tiles.manage.black")) + "/error/error";
   }
   @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
   @ResponseBody
   public void keyError(SQLIntegrityConstraintViolationException ex, HttpServletRequest request, HttpServletResponse response) throws Exception {
     if (ex.getErrorCode() == 1)
     { response.sendError(486, "중복된 값이 존재합니다."); }
     else { response.sendError(500); }
   
   }
   @ExceptionHandler({EzException.class})
   @ResponseBody
   public Object ezError(EzException ex, HttpServletRequest request, HttpServletResponse response) throws Exception {
     response.setStatus(487);
     return ex.toMap();
   }
   @ExceptionHandler({EzAjaxException.class})
   @ResponseBody
   public Object ezAjaxError(EzAjaxException ex, HttpServletRequest request, HttpServletResponse response) throws Exception {
     response.setStatus(487);
     return ex.toMap();
   }
 }
