 package cspi.ezsmart.manage.controller.system;
 
 import cspi.ezsmart.common.model.EzMap;
 import cspi.ezsmart.common.model.EzMenuMap;
 import cspi.ezsmart.common.model.ITreeVo;
 import cspi.ezsmart.common.util.Utilities;
 import cspi.ezsmart.manage.service.MenuService;
 import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.ModelMap;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 
 
 
 
 @Controller
 @RequestMapping({"goMenuMng", "{menuId}/menu"})
 public class MenuController
 {
   private Logger logger = LoggerFactory.getLogger(cspi.ezsmart.manage.controller.system.MenuController.class);
   
   @Resource(name = "menuService")
   MenuService menuService;
 
   
   @RequestMapping({"", "index"})
   public String init(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     this.logger.debug("[init] param :: {}", param);
     this.logger.debug("[init] model :: {}", model);
     
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage")) + "/menu/menuList";
   }
   
   @RequestMapping({"add"})
   public String add(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     this.logger.debug("[add] param :: {}", param);
     this.logger.debug("[add] model :: {}", model);
     
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage.blank")) + "/menu/menuAdd";
   }
   
   @RequestMapping({"getList"})
   @ResponseBody
   public Object getList(@RequestBody EzMap param) throws Exception {
     this.logger.debug("[getList] param :: {}", param);
     
     PaginationInfo page = param.getPaginationInfo();
     List<EzMenuMap> list = this.menuService.getList(param);
     page.setTotalRecordCount(this.menuService.getListCount(param));
     return Utilities.getGridData(list, page);
   }
   
   @RequestMapping({"getMenuTree"})
   @ResponseBody
   public Object getMenuTree(@RequestBody EzMap param) throws Exception {
     this.logger.debug("[getMenuTree] param :: {}", param);
     
     List<ITreeVo> list = this.menuService.getTreeList(param);
     return list;
   }
   @RequestMapping({"addMenu"})
   @ResponseBody
   public Object addMenu(@RequestBody EzMap param) throws Exception {
     this.logger.debug("[addMenu] param :: {}", param);
     
     return this.menuService.insertMenu(param);
   }
   
   @RequestMapping({"editMenu"})
   @ResponseBody
   public Object editMenu(@RequestBody EzMap param) throws Exception {
     this.logger.debug("[editMenu] param :: {}", param);
     
     return this.menuService.updateMenu(param);
   }
   
   @RequestMapping({"removeMenuList"})
   @ResponseBody
   public Object removeMenuList(@RequestBody List<EzMap> param) throws Exception {
     this.logger.debug("[removeMenuList] param :: {}", param);
     
     return this.menuService.deleteMenuList(param);
   }
   
   @RequestMapping({"removeMenu"})
   @ResponseBody
   public Object removeMenu(@RequestBody EzMap param) throws Exception {
     this.logger.debug("[removeMenu] param :: {}", param);
     
     return this.menuService.deleteMenu(param);
   }
   
   @RequestMapping({"saveMenu"})
   @ResponseBody
   public Object saveMenu(@RequestBody EzMap param) throws Exception {
     this.logger.debug("[saveMenu] param :: {}", param);
     
     return this.menuService.saveMenu(param);
   }
   
   @RequestMapping({"save"})
   @ResponseBody
   public Object save(@RequestBody List<EzMap> param) throws Exception {
     this.logger.debug("[save] param :: {}", param);
     
     return this.menuService.saveMenu(param);
   }
 }

