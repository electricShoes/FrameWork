 package cspi.ezsmart.manage.controller.system;
 
 import cspi.ezsmart.common.model.EzMap;
 import cspi.ezsmart.common.util.Utilities;
 import cspi.ezsmart.manage.service.CorpService;
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
 @RequestMapping({"corp", "{menuId}/corp"})
 public class CorpController
 {
   @Resource(name = "corpService")
   CorpService corpService;
   
   @RequestMapping({"", "index"})
   public String init(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage")) + "/corp/corp";
   } @RequestMapping({"getList"})
   @ResponseBody
   public Object getList(@RequestBody EzMap param) throws Exception {
     PaginationInfo page = param.getPaginationInfo();
     List<EzMap> list = this.corpService.getList(param);
     page.setTotalRecordCount(this.corpService.getListCount(param));
     return Utilities.getGridData(list, page);
   } @RequestMapping({"deleteCorp"})
   @ResponseBody
   public Object deleteCorp(@RequestBody List<EzMap> list) throws Exception {
     return this.corpService.deleteCorp(list);
   } @RequestMapping({"saveCorp"})
   @ResponseBody
   public Object saveCorp(@RequestBody EzMap param) throws Exception {
     return this.corpService.saveCorp(param);
   }
 }
