 package cspi.ezsmart.manage.controller.main;
 
 import cspi.ezsmart.common.service.IBaseService;
 import cspi.ezsmart.common.util.Utilities;
 import java.io.IOException;
 import java.util.Map;
 import javax.annotation.Resource;
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.ModelMap;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 
 
 
 
 @Controller
 @RequestMapping({""})
 public class MainController
 {
   private Logger logger = LoggerFactory.getLogger(getClass());
 
   
   @Resource(name = "baseService")
   private IBaseService baseService;
 
   
   @RequestMapping({"", "index.do"})
   public String init(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param, ModelMap map) throws ServletException, IOException {
     if (this.logger.isDebugEnabled()) {
       this.logger.debug("[init] param :: {}", param);
       this.logger.debug("[init] map :: {}", map);
     } 
     
     return String.valueOf(Utilities.getProperty("tiles.manage")) + "/main/index";
   }
 }
