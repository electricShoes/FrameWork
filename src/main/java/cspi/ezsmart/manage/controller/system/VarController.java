 package cspi.ezsmart.manage.controller.system;
 
 import cspi.ezsmart.common.model.EzMap;
 import cspi.ezsmart.common.util.Utilities;
 import cspi.ezsmart.manage.service.VarService;
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
 @RequestMapping({"varList", "{menuId}/var"})
 public class VarController
 {
   @Resource(name = "varService")
   VarService varService;
   
   @RequestMapping({"", "index"})
   public String init(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage")) + "/var/varList";
   }
   @RequestMapping({"getList"})
   @ResponseBody
   public Object getList(@RequestBody EzMap param) throws Exception {
     PaginationInfo page = param.getPaginationInfo();
     List<EzMap> list = this.varService.getList(param);
     page.setTotalRecordCount(this.varService.getListCount(param));
     return Utilities.getGridData(list, page);
   }
   @RequestMapping({"save"})
   @ResponseBody
   public Object save(@RequestBody List<EzMap> param) throws Exception {
     return this.varService.saveVariable(param);
   }
   @RequestMapping({"delete"})
   @ResponseBody
   public Object delete(@RequestBody List<EzMap> param) throws Exception {
     return this.varService.deleteVariable(param);
   }
 }

