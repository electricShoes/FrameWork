package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import java.util.List;

public interface SqlLogService {
  List<EzMap> getList(Object paramObject) throws Exception;
  
  int getListCount(Object paramObject) throws Exception;
  
  EzMap updateLog(EzMap paramEzMap) throws Exception;
  
  EzMap insertLog(EzMap paramEzMap) throws Exception;
  
  EzMap deleteLogList(List<EzMap> paramList) throws Exception;
  
  EzMap deleteLog(EzMap paramEzMap) throws Exception;
  
  EzMap saveLog(EzMap paramEzMap) throws Exception;
  
  EzMap saveLog(List<EzMap> paramList) throws Exception;
  
  EzMap getLog(EzMap paramEzMap) throws Exception;
}
