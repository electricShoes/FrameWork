 package cspi.ezsmart.manage.controller.system;
 
 import cspi.ezsmart.common.model.EzMap;
 import cspi.ezsmart.common.util.Utilities;
 import cspi.ezsmart.manage.service.SqlLogService;
 import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.ModelMap;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 
 
 
 @Controller
 @RequestMapping({"/0102020300", "{menuId}/sqlLog"})
 public class SqlLogController
 {
   @Resource(name = "sqlLogService")
   SqlLogService sqlLogService;
   
   @RequestMapping({"", "index"})
   public String init(HttpServletRequest request, @RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     return String.valueOf(Utilities.getProperty("tiles.manage")) + "/system/logList";
   }
   @RequestMapping({"add"})
   public String add(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage.blank")) + "/log/logAdd";
   } @RequestMapping({"getList"})
   @ResponseBody
   public Object getList(@RequestBody EzMap param) throws Exception {
     PaginationInfo page = param.getPaginationInfo();
     List<EzMap> list = this.sqlLogService.getList(param);
     page.setTotalRecordCount(this.sqlLogService.getListCount(param));
     return Utilities.getGridData(list, page);
   } @RequestMapping({"getLog"})
   @ResponseBody
   public Object getLog(@RequestBody EzMap param) throws Exception {
     EzMap log = this.sqlLogService.getLog(param);
     return log;
   } @RequestMapping({"addLog"})
   @ResponseBody
   public Object addLog(@RequestBody EzMap param) throws Exception {
     return this.sqlLogService.insertLog(param);
   } @RequestMapping({"editLog"})
   @ResponseBody
   public Object editLog(@RequestBody EzMap param) throws Exception {
     return this.sqlLogService.updateLog(param);
   } @RequestMapping({"removeLogList"})
   @ResponseBody
   public Object removeLogList(@RequestBody List<EzMap> param) throws Exception {
     return this.sqlLogService.deleteLogList(param);
   } @RequestMapping({"removeLog"})
   @ResponseBody
   public Object removeLog(@RequestBody EzMap param) throws Exception {
     return this.sqlLogService.deleteLog(param);
   } @RequestMapping({"saveLog"})
   @ResponseBody
   public Object saveLog(@RequestBody EzMap param) throws Exception {
     return this.sqlLogService.saveLog(param);
   } @RequestMapping({"save"})
   @ResponseBody
   public Object save(@RequestBody List<EzMap> param) throws Exception {
     return this.sqlLogService.saveLog(param);
   }
 }
