 package cspi.ezsmart.manage.controller.system;
 
 import cspi.ezsmart.common.model.EzMap;
 import cspi.ezsmart.common.util.Utilities;
 import cspi.ezsmart.manage.service.UserService;
 import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.ModelMap;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 
 
 @Controller
 @RequestMapping({"0102010300", "{menuId}/user"})
 public class UserController
 {
   @Resource(name = "userService")
   UserService userService;
   
   @RequestMapping({"", "index"})
   public String init(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage")) + "/user/userList";
   }
   @RequestMapping({"userPopup"})
   public String userPopup(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage.blank")) + "/user/userPop";
   }
 
   
   @RequestMapping({"add"})
   public String add(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage.blank")) + "/user/userAdd";
   } @RequestMapping({"getList"})
   @ResponseBody
   public Object getList(@RequestBody EzMap param) throws Exception {
     PaginationInfo page = param.getPaginationInfo();
     List<EzMap> list = this.userService.getList(param);
     page.setTotalRecordCount(this.userService.getListCount(param));
     return Utilities.getGridData(list, page);
   } @RequestMapping({"getUser"})
   @ResponseBody
   public Object getUser(@RequestBody EzMap param) throws Exception {
     EzMap user = this.userService.getUser(param);
     return user;
   } @RequestMapping({"addUser"})
   @ResponseBody
   public Object addUser(@RequestBody EzMap param) throws Exception {
     return this.userService.insertUser(param);
   } @RequestMapping({"editUser"})
   @ResponseBody
   public Object editUser(@RequestBody EzMap param) throws Exception {
     return this.userService.updateUser(param);
   } @RequestMapping({"removeUserList"})
   @ResponseBody
   public Object removeUserList(@RequestBody List<EzMap> param) throws Exception {
     return this.userService.deleteUserList(param);
   } @RequestMapping({"removeUser"})
   @ResponseBody
   public Object removeUser(@RequestBody EzMap param) throws Exception {
     return this.userService.deleteUser(param);
   } @RequestMapping({"saveUser"})
   @ResponseBody
   public Object saveUser(@RequestBody EzMap param) throws Exception {
     return this.userService.saveUser(param);
   } @RequestMapping({"save"})
   @ResponseBody
   public Object save(@RequestBody List<EzMap> param) throws Exception {
     return this.userService.saveUser(param);
   } @RequestMapping({"resetPassword"})
   @ResponseBody
   public Object resetPassword(@RequestBody EzMap param) throws Exception {
     return this.userService.updateResetPassword(param);
   }
 }
