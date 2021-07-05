 package cspi.ezsmart.manage.controller.main;
 
 import cspi.ezsmart.common.model.EzMap;
 import cspi.ezsmart.common.util.Utilities;
 import cspi.ezsmart.manage.service.LoginService;
 import egovframework.rte.fdl.property.EgovPropertyService;
 import java.util.Map;
 import javax.annotation.Resource;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import org.apache.ibatis.executor.ExecutorException;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.ModelMap;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 
 
 @Controller
 @RequestMapping({"login", "logout"})
 public class LoginController
 {
   private Logger logger = LoggerFactory.getLogger(getClass());
 
   
   @Resource(name = "loginService")
   LoginService loginService;
   
   @Resource(name = "propertiesService")
   EgovPropertyService propertiesService;
 
   
   @RequestMapping({"index.do"})
   public String loginForm(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     this.logger.debug("[loginForm] param :: {}", param);
     this.logger.debug("[loginForm] model :: {}", model);
     this.logger.debug("[loginForm] propertyService.urlSuffix :: {}", this.propertiesService.getString("urlSuffix"));
     
     this.loginService.updatelogout();
     String saveId = Utilities.getCookieValue("saveId");
     
     if (Utilities.isNotEmpty(saveId))
     {
       model.addAttribute("userId", Utilities.decrypt(saveId)); }
     
//     model.addAttribute("userId", "TestId");
//     model.addAttribute("urlSuffix", ".do");
     
     return String.valueOf(Utilities.getProperty("tiles.manage.blankBody")) + "/login/login";
   }
 
 
   
   @RequestMapping({"/login.do"})
   @ResponseBody
   public Object login(@RequestBody EzMap param, HttpServletRequest request, HttpServletResponse response) throws Exception {
     this.logger.debug("[login] param :: {}", param);
     Object result = null;
     
     try {
       result = this.loginService.updatelogin(param);
    	
     } catch (ExecutorException e) {
       this.logger.debug("[login] error.message :: {}", e.getMessage());
       
       HttpSession session = Utilities.getSession();
       if (session == null) {
         String url = "/";
         request.getRequestDispatcher(url).forward((ServletRequest)request, (ServletResponse)response);
       } 
     } catch (Exception e) {
       this.logger.debug("[login] error.message :: {}", e.getMessage());
     } 
     
     return result;
   }
   
   @RequestMapping({"/logout.do"})
   @ResponseBody
   public Object logout() throws Exception {
     this.logger.debug("[logout]");
     
     return this.loginService.updatelogout();
   }
 }
