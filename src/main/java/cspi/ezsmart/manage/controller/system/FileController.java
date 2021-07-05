 package cspi.ezsmart.manage.controller.system;
 
 import cspi.ezsmart.common.model.EzMap;
 import cspi.ezsmart.common.util.Utilities;
 import cspi.ezsmart.manage.service.FileService;
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
 import org.springframework.web.multipart.MultipartFile;
 
 
 @Controller
 @RequestMapping({"0102020200", "{menuId}/file"})
 public class FileController
 {
   @Resource(name = "fileService")
   FileService fileService;
   
   @RequestMapping({"", "index"})
   public String init(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     return String.valueOf(Utilities.getProperty("tiles.manage")) + "/file/fileList";
   }
   @RequestMapping({"add", "mod", "fileInfo"})
   public String add(@RequestParam Map<String, Object> param, ModelMap model) throws Exception {
     model.addAllAttributes(param);
     String fileId = (String)param.get("fileId");
     EzMap ezMap = new EzMap();
     if (Utilities.isEmpty(fileId)) {
       ezMap.setString("fileId", Utilities.getFileId());
     } else {
       ezMap.setString("fileId", fileId);
     }  model.addAttribute("fileInfo", ezMap);
     return String.valueOf(Utilities.getProperty("tiles.manage.blank")) + "/file/filePopup";
   }
   @RequestMapping({"uploadFile"})
   @ResponseBody
   public Object uploadFile(@RequestParam("uploadFile") MultipartFile uploadfile, @RequestParam Map<String, Object> param) throws Exception {
     EzMap fileInfo = new EzMap();
     fileInfo.putAll(param);
     return this.fileService.uploadFile(uploadfile, fileInfo);
   } @RequestMapping({"downloadFile"})
   @ResponseBody
   public void downloadFile(@RequestBody EzMap fileInfo) throws Exception {
     this.fileService.downloadFile(fileInfo);
   }
   @RequestMapping({"uploadTempFile"})
   @ResponseBody
   public Object uploadTempFile(@RequestParam("uploadFile") MultipartFile uploadfile, @RequestParam Map<String, Object> param) throws Exception {
     EzMap fileInfo = new EzMap();
     fileInfo.putAll(param);
     return this.fileService.uploadTempFile(uploadfile, fileInfo);
   } @RequestMapping({"getList"})
   @ResponseBody
   public Object getList(@RequestBody EzMap param) throws Exception {
     PaginationInfo page = param.getPaginationInfo();
     List<EzMap> list = this.fileService.getList(param);
     page.setTotalRecordCount(this.fileService.getListCount(param));
     return Utilities.getGridData(list, page);
   } @RequestMapping({"addFileInfo"})
   @ResponseBody
   public Object addFileInfo(@RequestBody EzMap param) throws Exception {
     return this.fileService.insertFileInfo(param);
   } @RequestMapping({"editFileInfo"})
   @ResponseBody
   public Object editFileInfo(@RequestBody EzMap param) throws Exception {
     return this.fileService.updateFileInfo(param);
   } @RequestMapping({"removeFileList"})
   @ResponseBody
   public Object removeFileInfoList(@RequestBody List<EzMap> param) throws Exception {
     return this.fileService.deleteFileInfoList(param);
   } @RequestMapping({"removeFileInfo"})
   @ResponseBody
   public Object removeFileInfo(@RequestBody EzMap param) throws Exception {
     return this.fileService.deleteFileInfo(param);
   } @RequestMapping({"saveFileInfo"})
   @ResponseBody
   public Object saveFileInfo(@RequestBody EzMap param) throws Exception {
     return this.fileService.saveFileInfo(param);
   } @RequestMapping({"save"})
   @ResponseBody
   public Object save(@RequestBody List<EzMap> param) throws Exception {
     return this.fileService.saveFileInfo(param);
   }
 }
