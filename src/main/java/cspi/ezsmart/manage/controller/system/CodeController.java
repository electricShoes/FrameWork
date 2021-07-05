 package cspi.ezsmart.manage.controller.system;
 
 import cspi.ezsmart.common.model.EzMap;
 import cspi.ezsmart.common.util.Utilities;
 import cspi.ezsmart.manage.service.CodeService;
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
 @RequestMapping({"codeList", "{menuId}/code"})
 public class CodeController
 {
   @Resource(name = "codeService")
   CodeService codeService;
   
   @RequestMapping({"", "index"})
   public String init(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     System.out.println("URL :: " + Utilities.getProperty("tiles.manage"));
     return String.valueOf(Utilities.getProperty("tiles.manage")) + "/code/codeList";
   }
   @RequestMapping({"getList"})
   @ResponseBody
   public Object getList(@RequestBody EzMap param) throws Exception {
     PaginationInfo page = param.getPaginationInfo();
     List<EzMap> list = this.codeService.getList(param);
     
     return Utilities.getGridData(list, page);
   }
   @RequestMapping({"getListDetail"})
   @ResponseBody
   public Object getListDetail(@RequestBody EzMap param) throws Exception {
     PaginationInfo page = param.getPaginationInfo();
     List<EzMap> list = this.codeService.getListDetail(param);
     
     return Utilities.getGridData(list, page);
   }
   @RequestMapping({"removeCodeList"})
   @ResponseBody
   public Object removeCodeList(@RequestBody List<EzMap> param) throws Exception {
     return this.codeService.deleteCodeList(param);
   }
   @RequestMapping({"removeCodeDetailList"})
   @ResponseBody
   public Object removeCodeDetailList(@RequestBody List<EzMap> param) throws Exception {
     return this.codeService.deleteCodeDetailList(param);
   }
   @RequestMapping({"save"})
   @ResponseBody
   public Object save(@RequestBody List<EzMap> param) throws Exception {
     return this.codeService.saveCode(param);
   }
   @RequestMapping({"saveDetail"})
   @ResponseBody
   public Object saveDetail(@RequestBody List<EzMap> param) throws Exception {
     return this.codeService.saveDetail(param);
   }
   
   @RequestMapping({"langDown"})
   public void langDown(@RequestBody List<EzMap> param) throws Exception {
     this.codeService.langDown(param);
   }
 }
