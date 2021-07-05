package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import java.util.List;

public interface CorpService {
  int getListCount(Object paramObject) throws Exception;
  
  List<EzMap> getList(Object paramObject) throws Exception;
  
  EzMap deleteCorp(List<EzMap> paramList) throws Exception;
  
  EzMap deleteCorp1(EzMap paramEzMap) throws Exception;
  
  EzMap saveCorp(EzMap paramEzMap) throws Exception;
}
