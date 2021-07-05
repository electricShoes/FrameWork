 package cspi.ezsmart.manage.controller.system;
 
 import cspi.ezsmart.common.model.EzMap;
 import cspi.ezsmart.common.util.Utilities;
 import cspi.ezsmart.manage.service.GroupService;
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
 @RequestMapping({"0102010200", "{menuId}/group"})
 public class GroupController
 {
   @Resource(name = "groupService")
   GroupService groupService;
   
   @RequestMapping({"", "index"})
   public String init(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage")) + "/group/groupList";
   }
   
   @RequestMapping({"groupPopup"})
   public String groupPopup(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage.blank")) + "/group/groupPopup";
   }
   
   @RequestMapping({"add"})
   public String add(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage.blank")) + "/group/groupAdd";
   }
   
   @RequestMapping({"groupMenuPopup"})
   public String groupMenuPopup(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage.blank")) + "/group/groupMenuList";
   }
   @RequestMapping({"groupUserPopup"})
   public String groupUserPopup(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage.blank")) + "/group/groupUserList";
   }
   @RequestMapping({"getList"})
   @ResponseBody
   public Object getList(@RequestBody EzMap param) throws Exception {
     PaginationInfo page = param.getPaginationInfo();
     List<EzMap> list = this.groupService.getList(param);
     page.setTotalRecordCount(this.groupService.getListCount(param));
     return Utilities.getGridData(list, page);
   }
   @RequestMapping({"getGroupUserList"})
   @ResponseBody
   public Object getGroupUserList(@RequestBody EzMap param) throws Exception {
     PaginationInfo page = param.getPaginationInfo();
     List<EzMap> list = this.groupService.getGroupUserList(param);
     page.setTotalRecordCount(this.groupService.getGroupUserCount(param));
     return Utilities.getGridData(list, page);
   }
   @RequestMapping({"getGroupMenuList"})
   @ResponseBody
   public Object getGroupMenuList(@RequestBody EzMap param) throws Exception {
     PaginationInfo page = param.getPaginationInfo();
     List<EzMap> list = this.groupService.getGroupMenuList(param);
     page.setTotalRecordCount(this.groupService.getGroupMenuCount(param));
     return Utilities.getGridData(list, page);
   }
   @RequestMapping({"getGroup"})
   @ResponseBody
   public Object getGroup(@RequestBody EzMap param) throws Exception {
     EzMap group = this.groupService.getGroup(param);
     return group;
   } @RequestMapping({"getGroupCheckList"})
   @ResponseBody
   public Object getGroupCheckList(@RequestBody EzMap param) throws Exception {
     return this.groupService.getGroupCheckList(param);
   }
   
   @RequestMapping({"addGroup"})
   @ResponseBody
   public Object addGroup(@RequestBody EzMap param) throws Exception {
     return this.groupService.insertGroup(param);
   }
   @RequestMapping({"addGroupMenu"})
   @ResponseBody
   public Object addGroupMenu(@RequestBody EzMap param) throws Exception {
     return this.groupService.insertGroupMenu(param);
   }
   @RequestMapping({"addGroupUser"})
   @ResponseBody
   public Object addGroupUser(@RequestBody EzMap param) throws Exception {
     return this.groupService.insertGroupUser(param);
   }
   @RequestMapping({"addGroupMenuList"})
   @ResponseBody
   public Object addGroupMenuList(@RequestBody List<EzMap> list) throws Exception {
     return this.groupService.insertGroupMenuList(list);
   }
   @RequestMapping({"addGroupUserList"})
   @ResponseBody
   public Object addGroupUserList(@RequestBody List<EzMap> list) throws Exception {
     return this.groupService.insertGroupUserList(list);
   }
   @RequestMapping({"editGroup"})
   @ResponseBody
   public Object editGroup(@RequestBody EzMap param) throws Exception {
     return this.groupService.updateGroup(param);
   }
   @RequestMapping({"removeGroupList"})
   @ResponseBody
   public Object removeGroupList(@RequestBody List<EzMap> param) throws Exception {
     return this.groupService.deleteGroupList(param);
   }
   @RequestMapping({"removeGroup"})
   @ResponseBody
   public Object removeGroup(@RequestBody EzMap param) throws Exception {
     return this.groupService.deleteGroup(param);
   }
   @RequestMapping({"removeGroupMenu"})
   @ResponseBody
   public Object removeGroupMenu(@RequestBody EzMap param) throws Exception {
     return this.groupService.deleteGroupMenu(param);
   }
   @RequestMapping({"removeGroupUser"})
   @ResponseBody
   public Object removeGroupUser(@RequestBody EzMap param) throws Exception {
     return this.groupService.deleteGroupUser(param);
   }
   @RequestMapping({"removeGroupMenuList"})
   @ResponseBody
   public Object removeGroupMenuList(@RequestBody List<EzMap> list) throws Exception {
     return this.groupService.deleteGroupMenuList(list);
   }
   @RequestMapping({"removeGroupUserList"})
   @ResponseBody
   public Object removeGroupUserList(@RequestBody List<EzMap> list) throws Exception {
     return this.groupService.deleteGroupUserList(list);
   }
   @RequestMapping({"setUserGroup"})
   @ResponseBody
   public Object setUserGroup(@RequestBody List<EzMap> list, @RequestParam("userId") String userId) throws Exception {
     return this.groupService.updateUserGroup(userId, list);
   } @RequestMapping({"setMenuGroup"})
   @ResponseBody
   public Object setMenuGroup(@RequestBody List<EzMap> list, @RequestParam("menuId") String menuId) throws Exception {
     return this.groupService.updateMenuGroup(menuId, list);
   }
   @RequestMapping({"saveGroup"})
   @ResponseBody
   public Object saveGroup(@RequestBody EzMap param) throws Exception {
     return this.groupService.saveGroup(param);
   }
   @RequestMapping({"save"})
   @ResponseBody
   public Object save(@RequestBody List<EzMap> param) throws Exception {
     return this.groupService.saveGroup(param);
   } @RequestMapping({"saveGroupMenu"})
   @ResponseBody
   public Object saveGroupMenu(@RequestBody EzMap param) throws Exception {
     return this.groupService.saveGroupMenu(param);
   }
   @RequestMapping({"saveGroupMenuList"})
   @ResponseBody
   public Object saveGroupMenuList(@RequestBody List<EzMap> param) throws Exception {
     return this.groupService.saveGroupMenuList(param);
   }
 }
