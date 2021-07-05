 package cspi.ezsmart.manage.controller.system;
 
 import cspi.ezsmart.common.util.Utilities;
 import cspi.ezsmart.manage.service.ExcelService;
 import java.util.Map;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.ModelMap;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
 import org.springframework.web.multipart.MultipartRequest;
 
 
 @Controller
 @RequestMapping({"excel", "{menuId}/excel"})
 public class ExcelController
 {
   @Resource(name = "excelService")
   ExcelService excelService;
   
   @RequestMapping({"", "index"})
   public String init(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage")) + "/excel/excel";
   }
   
   @RequestMapping(value = {"/excelUpload.do"}, method = {RequestMethod.POST})
   @ResponseBody
   public Object excelToList(HttpServletRequest request, @RequestParam Map<String, Object> param, MultipartRequest mrequest) throws Exception {
     return this.excelService.excelToList(mrequest);
   }
 }
