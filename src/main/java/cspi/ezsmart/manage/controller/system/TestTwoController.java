 package cspi.ezsmart.manage.controller.system;
 
 import cspi.ezsmart.common.util.Utilities;
 import java.util.Map;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.ModelMap;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 
 
 
 @Controller
 @RequestMapping({"test1", "{menuId}/user"})
 public class TestTwoController
 {
   @RequestMapping({"", "index"})
   public String init(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage")) + "/test/testOne";
   }
   @RequestMapping({"userPopup"})
   public String userPopup(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage.blank")) + "/user/userPop";
   }
 }
