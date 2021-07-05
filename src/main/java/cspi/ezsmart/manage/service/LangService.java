package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import java.util.List;

public interface LangService {
  List<EzMap> getList(Object paramObject) throws Exception;
  
  int getListCount(Object paramObject) throws Exception;
  
  EzMap updateLang(EzMap paramEzMap) throws Exception;
  
  EzMap insertLang(EzMap paramEzMap) throws Exception;
  
  EzMap deleteLangList(List<EzMap> paramList) throws Exception;
  
  EzMap deleteLang(EzMap paramEzMap) throws Exception;
  
  EzMap saveLang(EzMap paramEzMap) throws Exception;
  
  EzMap saveLang(List<EzMap> paramList) throws Exception;
  
  List<EzMap> getLang(EzMap paramEzMap) throws Exception;
}
