package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import java.util.List;

public interface GroupService {
  List<EzMap> getList(Object paramObject) throws Exception;
  
  int getListCount(Object paramObject) throws Exception;
  
  EzMap updateGroup(EzMap paramEzMap) throws Exception;
  
  EzMap insertGroup(EzMap paramEzMap) throws Exception;
  
  EzMap deleteGroupList(List<EzMap> paramList) throws Exception;
  
  EzMap deleteGroup(EzMap paramEzMap) throws Exception;
  
  EzMap saveGroup(EzMap paramEzMap) throws Exception;
  
  EzMap saveGroup(List<EzMap> paramList) throws Exception;
  
  EzMap getGroup(EzMap paramEzMap) throws Exception;
  
  EzMap deleteGroupMenuList(List<EzMap> paramList) throws Exception;
  
  EzMap deleteGroupUserList(List<EzMap> paramList) throws Exception;
  
  EzMap deleteGroupMenu(EzMap paramEzMap) throws Exception;
  
  EzMap deleteGroupUser(EzMap paramEzMap) throws Exception;
  
  int getGroupMenuCount(Object paramObject) throws Exception;
  
  int getGroupUserCount(Object paramObject) throws Exception;
  
  List<EzMap> getGroupMenuList(Object paramObject) throws Exception;
  
  List<EzMap> getGroupUserList(Object paramObject) throws Exception;
  
  List<EzMap> getGroupCheckList(Object paramObject) throws Exception;
  
  EzMap insertGroupUser(EzMap paramEzMap) throws Exception;
  
  EzMap insertGroupMenu(EzMap paramEzMap) throws Exception;
  
  EzMap insertGroupUserList(List<EzMap> paramList) throws Exception;
  
  EzMap insertGroupMenuList(List<EzMap> paramList) throws Exception;
  
  Object updateUserGroup(String paramString, List<EzMap> paramList) throws Exception;
  
  Object updateMenuGroup(String paramString, List<EzMap> paramList) throws Exception;
  
  EzMap saveGroupMenu(EzMap paramEzMap) throws Exception;
  
  EzMap saveGroupMenuList(List<EzMap> paramList) throws Exception;
}
