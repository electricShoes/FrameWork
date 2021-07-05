package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.dao.GroupDao;
import cspi.ezsmart.manage.service.GroupService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("groupService")
public class GroupServiceImpl
  implements GroupService
{
  @Autowired
  GroupDao groupDao;
  
  public EzMap getGroup(EzMap param) throws Exception {
    return this.groupDao.selectGroup(param);
  }

  
  public List<EzMap> getList(Object param) throws Exception {
    return this.groupDao.selectList(param);
  }
  
  public List<EzMap> getGroupUserList(Object param) throws Exception {
    return this.groupDao.selectGroupUserList(param);
  }
  
  public List<EzMap> getGroupMenuList(Object param) throws Exception {
    return this.groupDao.selectGroupMenuList(param);
  }
  
  public List<EzMap> getGroupCheckList(Object param) throws Exception {
    return this.groupDao.selectGroupCheckList(param);
  }
  
  public int getListCount(Object param) throws Exception {
    return this.groupDao.selectListCount(param);
  }
  
  public int getGroupUserCount(Object param) throws Exception {
    return this.groupDao.selectGroupUserCount(param);
  }
  
  public int getGroupMenuCount(Object param) throws Exception {
    return this.groupDao.selectGroupMenuCount(param);
  }


  
  public EzMap updateGroup(EzMap param) throws Exception {
    return Utilities.getUpdateResult(this.groupDao.updateGroup(param));
  }

  
  public EzMap insertGroup(EzMap param) throws Exception {
    EzMap ret = Utilities.getInsertResult(this.groupDao.insertGroup(param));
    ret.put("result", this.groupDao.selectGroup(param));
    return ret;
  }
  
  public EzMap insertGroupUser(EzMap param) throws Exception {
    EzMap ret = Utilities.getInsertResult(this.groupDao.insertGroupUser(param));
    ret.put("result", param);
    return ret;
  }
  
  public EzMap insertGroupMenu(EzMap param) throws Exception {
    EzMap ret = Utilities.getInsertResult(this.groupDao.insertGroupMenu(param));
    ret.put("result", param);
    return ret;
  }
  
  public EzMap insertGroupUserList(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(insertGroupUser(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }

  
  public EzMap insertGroupMenuList(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(insertGroupMenu(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }
  
  public EzMap saveGroupMenuList(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(saveGroupMenu(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }
  
  public EzMap saveGroupMenu(EzMap param) throws Exception {
    return Utilities.getUpdateResult(this.groupDao.updateGroupMenu(param));
  }
  
  public EzMap deleteGroup(EzMap param) throws Exception {
    this.groupDao.deleteGroupUser(param);
    this.groupDao.deleteGroupMenu(param);
    return Utilities.getDeleteResult(this.groupDao.deleteGroup(param));
  }
  
  public EzMap deleteGroupUser(EzMap param) throws Exception {
    return Utilities.getDeleteResult(this.groupDao.deleteGroupUser(param));
  }
  
  public EzMap deleteGroupMenu(EzMap param) throws Exception {
    return Utilities.getDeleteResult(this.groupDao.deleteGroupMenu(param));
  }
  
  public EzMap deleteGroupList(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(deleteGroup(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }
  
  public EzMap deleteGroupUserList(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(deleteGroupUser(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }
  
  public EzMap deleteGroupMenuList(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(deleteGroupMenu(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }
  
  public EzMap saveGroup(EzMap param) throws Exception {
    if ("c".equals(param.getString("stat")))
      return insertGroup(param); 
    if ("u".equals(param.getString("stat")))
      return updateGroup(param); 
    if ("d".equals(param.getString("stat"))) {
      return deleteGroup(param);
    }
    return null;
  }

  
  public EzMap saveGroup(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(saveGroup(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }

  
  public Object updateUserGroup(String userId, List<EzMap> list) throws Exception {
    EzMap param = new EzMap();
    param.setString("userId", userId);
    this.groupDao.deleteGroupUser(param);
    return insertGroupUserList(list);
  }
  
  public Object updateMenuGroup(String menuId, List<EzMap> list) throws Exception {
    EzMap param = new EzMap();
    param.setString("menuId", menuId);
    this.groupDao.deleteGroupMenu(param);
    return insertGroupMenuList(list);
  }
}
