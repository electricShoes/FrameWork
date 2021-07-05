package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import java.util.List;

public interface UserService {
  List<EzMap> getList(Object paramObject) throws Exception;
  
  int getListCount(Object paramObject) throws Exception;
  
  EzMap updateUser(EzMap paramEzMap) throws Exception;
  
  EzMap insertUser(EzMap paramEzMap) throws Exception;
  
  EzMap deleteUserList(List<EzMap> paramList) throws Exception;
  
  EzMap deleteUser(EzMap paramEzMap) throws Exception;
  
  EzMap saveUser(EzMap paramEzMap) throws Exception;
  
  EzMap saveUser(List<EzMap> paramList) throws Exception;
  
  EzMap getUser(EzMap paramEzMap) throws Exception;
  
  EzMap updateResetPassword(EzMap paramEzMap) throws Exception;
}