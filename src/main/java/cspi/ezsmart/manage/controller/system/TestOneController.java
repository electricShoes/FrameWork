 package cspi.ezsmart.manage.controller.system;
 
 import cspi.ezsmart.common.util.Utilities;
 import java.util.Map;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.ModelMap;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 
 
 
 @Controller
 @RequestMapping({"test2", "{menuId}/user"})
 public class TestOneController
 {
   @RequestMapping({"", "index"})
   public String init(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage")) + "/test/testTwo";
   }
   @RequestMapping({"testPop"})
   public String userPopup(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage.blank")) + "/test/testPop";
   }
 }

