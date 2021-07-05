package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import java.util.List;

public interface CodeService {
  List<EzMap> getComboCode(Object paramObject) throws Exception;
  
  List<EzMap> getList(Object paramObject) throws Exception;
  
  List<EzMap> getListDetail(Object paramObject) throws Exception;
  
  int getListCount(Object paramObject) throws Exception;
  
  EzMap updateCode(EzMap paramEzMap) throws Exception;
  
  EzMap updateCodeDetail(EzMap paramEzMap) throws Exception;
  
  EzMap insertCode(EzMap paramEzMap) throws Exception;
  
  EzMap insertCodeDetail(EzMap paramEzMap) throws Exception;
  
  EzMap deleteCodeList(List<EzMap> paramList) throws Exception;
  
  EzMap deleteCode(EzMap paramEzMap) throws Exception;
  
  EzMap deleteCodeDetail(EzMap paramEzMap) throws Exception;
  
  EzMap saveCode(EzMap paramEzMap) throws Exception;
  
  EzMap saveCode(List<EzMap> paramList) throws Exception;
  
  EzMap saveDetail(List<EzMap> paramList) throws Exception;
  
  EzMap getCode(EzMap paramEzMap) throws Exception;
  
  EzMap saveCodeDetail(EzMap paramEzMap) throws Exception;
  
  EzMap deleteCodeDetailList(List<EzMap> paramList) throws Exception;
  
  void langDown(List<EzMap> paramList) throws Exception;
}
