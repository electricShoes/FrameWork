 package cspi.ezsmart.manage.controller.system;
 
 import cspi.ezsmart.common.model.EzMap;
 import cspi.ezsmart.common.util.Utilities;
 import cspi.ezsmart.manage.service.LangService;
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
 @RequestMapping({"0102020999", "{menuId}/lang"})
 public class LangController
 {
   protected Logger logger = LoggerFactory.getLogger(getClass());
   
   @Resource(name = "langService")
   LangService langService;
   
   @RequestMapping({"", "index"})
   public String init(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage")) + "/lang/lang";
   }
   
   @RequestMapping({"langPopup"})
   public String langPopup(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage.blank")) + "/lang/langPop";
   }
   
   @RequestMapping({"add"})
   public String add(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage.blank")) + "/lang/langAdd";
   }
   @RequestMapping({"getList"})
   @ResponseBody
   public Object getList(@RequestBody EzMap param) throws Exception {
     PaginationInfo page = param.getPaginationInfo();
     this.logger.debug("1111111111111111111=============");
     
     List<EzMap> list = this.langService.getList(param);
     page.setTotalRecordCount(this.langService.getListCount(param));
     
     return Utilities.getGridData(list, page);
   }
   @RequestMapping({"getLang"})
   @ResponseBody
   public Object getLang(@RequestBody EzMap param) throws Exception {
     List<EzMap> lang = this.langService.getLang(param);
     return lang;
   }
   @RequestMapping({"addLang"})
   @ResponseBody
   public Object addLang(@RequestBody EzMap param) throws Exception {
     return this.langService.insertLang(param);
   }
   @RequestMapping({"editLang"})
   @ResponseBody
   public Object editLang(@RequestBody EzMap param) throws Exception {
     return this.langService.updateLang(param);
   }
   @RequestMapping({"removeLangList"})
   @ResponseBody
   public Object removeLangList(@RequestBody List<EzMap> param) throws Exception {
     return this.langService.deleteLangList(param);
   }
   @RequestMapping({"removeLang"})
   @ResponseBody
   public Object removeLang(@RequestBody EzMap param) throws Exception {
     return this.langService.deleteLang(param);
   }
   @RequestMapping({"saveLang"})
   @ResponseBody
   public Object saveLang(@RequestBody EzMap param) throws Exception {
     return this.langService.saveLang(param);
   }
   @RequestMapping({"save"})
   @ResponseBody
   public Object save(@RequestBody List<EzMap> param) throws Exception {
     return this.langService.saveLang(param);
   }
 }
