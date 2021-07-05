package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  List<EzMap> getList(Object paramObject) throws Exception;
  
  int getListCount(Object paramObject) throws Exception;
  
  EzMap updateFileInfo(EzMap paramEzMap) throws Exception;
  
  EzMap insertFileInfo(EzMap paramEzMap) throws Exception;
  
  EzMap deleteFileInfoList(List<EzMap> paramList) throws Exception;
  
  EzMap deleteFileInfo(EzMap paramEzMap) throws Exception;
  
  EzMap saveFileInfo(EzMap paramEzMap) throws Exception;
  
  EzMap saveFileInfo(List<EzMap> paramList) throws Exception;
  
  EzMap getFileInfo(EzMap paramEzMap) throws Exception;
  
  EzMap uploadFile(MultipartFile paramMultipartFile, EzMap paramEzMap) throws Exception;
  
  EzMap uploadTempFile(MultipartFile paramMultipartFile, EzMap paramEzMap) throws Exception;
  
  Object downloadFile(EzMap paramEzMap) throws Exception;
}
