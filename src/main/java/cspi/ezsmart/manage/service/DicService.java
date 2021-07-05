package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import java.util.List;

public interface DicService {
  List<EzMap> getList(Object paramObject) throws Exception;
  
  int getListCount(Object paramObject) throws Exception;
  
  Object saveVariable(List<EzMap> paramList) throws Exception;
  
  Object deleteVariable(List<EzMap> paramList) throws Exception;
}

