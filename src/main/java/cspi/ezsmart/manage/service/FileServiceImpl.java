package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.dao.FileDao;
import cspi.ezsmart.manage.service.FileService;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.http.HTTPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service("fileService")
public class FileServiceImpl
  implements FileService
{
  @Autowired
  FileDao fileDao;
  
  public EzMap getFileInfo(EzMap param) throws Exception {
    return this.fileDao.selectFileInfo(param);
  }

  
  public List<EzMap> getList(Object param) throws Exception {
    return this.fileDao.selectList(param);
  }

  
  public int getListCount(Object param) throws Exception {
    return this.fileDao.selectListCount(param);
  }

  
  public EzMap updateFileInfo(EzMap param) throws Exception {
    return Utilities.getUpdateResult(this.fileDao.updateFileInfo(param));
  }

  
  public EzMap insertFileInfo(EzMap param) throws Exception {
    String pwd = param.getString("pwd");
    param.put("pwd", Utilities.passwordEncode(pwd));
    EzMap ret = Utilities.getInsertResult(this.fileDao.insertFileInfo(param));
    ret.put("result", this.fileDao.selectFileInfo(param));
    return ret;
  }


  
  public EzMap deleteFileInfo(EzMap param) throws Exception {
    EzMap fileInfo = getFileInfo(param);
    String file = String.valueOf(Utilities.getProperty("storage.attachment")) + fileInfo.getString("saveName");
    Utilities.deleteFile(new File(file));
    return Utilities.getDeleteResult(this.fileDao.deleteFileInfo(fileInfo));
  }

  
  public EzMap deleteFileInfoList(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(deleteFileInfo(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }

  
  public EzMap saveFileInfo(EzMap param) throws Exception {
    if ("c".equals(param.getString("stat")))
      return insertFileInfo(param); 
    if ("u".equals(param.getString("stat")))
      return updateFileInfo(param); 
    if ("d".equals(param.getString("stat"))) {
      return deleteFileInfo(param);
    }
    return null;
  }

  
  public EzMap saveFileInfo(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(saveFileInfo(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }

  
  public EzMap uploadFile(MultipartFile uploadfile, EzMap fileInfo) throws Exception {
    if (uploadfile == null)
      throw new Exception(); 
    String date = Utilities.getDateString("");
    String location = Utilities.getProperty("storage.attachment");
    String subUrl = String.valueOf(date.substring(0, 6)) + "/" + fileInfo.getString("fileId") + "/";
    Utilities.createDirectory(String.valueOf(location) + subUrl);
    String fileName = Utilities.getUniqID(20);
    File dest = new File(String.valueOf(location) + subUrl + fileName);
    fileInfo.setString("saveName", String.valueOf(subUrl) + fileName);
    insertFileInfo(fileInfo);
    uploadfile.transferTo(dest);
    
    return fileInfo;
  }

  
  public EzMap uploadTempFile(MultipartFile uploadfile, EzMap fileInfo) throws Exception {
    String location = Utilities.getProperty("storage.temp");
    Utilities.createDirectory(location);
    String fileName = Utilities.getUniqID(20);
    File dest = new File(String.valueOf(location) + fileName);
    fileInfo.setString("saveName", fileName);
    uploadfile.transferTo(dest);
    return fileInfo;
  }


  
  public Object downloadFile(EzMap param) throws Exception {
    EzMap fileInfo = getFileInfo(param);
    if (fileInfo == null)
      throw new HTTPException(404); 
    String file = String.valueOf(Utilities.getProperty("storage.attachment")) + fileInfo.getString("saveName");

    
    return Boolean.valueOf(Utilities.DownloadFile(file, fileInfo.getString("fileName")));
  }
}
