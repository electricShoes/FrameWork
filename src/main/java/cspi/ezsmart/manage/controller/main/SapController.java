 package cspi.ezsmart.manage.controller.main;
 
 import cspi.ezsmart.common.util.Utilities;
 import java.util.Map;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.ModelMap;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 
 
 
 @Controller
 @RequestMapping({"sap", "{menuId}/sap"})
 public class SapController
 {
   @RequestMapping({"", "index"})
   public String init(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage")) + "/sap/sap";
   }
 }
