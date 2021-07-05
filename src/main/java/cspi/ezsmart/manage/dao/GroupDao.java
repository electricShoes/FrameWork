package cspi.ezsmart.manage.dao;

import cspi.ezsmart.common.dao.EzDao;
import cspi.ezsmart.common.model.EzMap;
import java.util.List;
import org.springframework.stereotype.Component;


@Component
public class GroupDao  extends EzDao
{
  public EzMap selectGroup(Object param) throws Exception {
    return (EzMap)getData("ezsmart.group.selectGroup", param);
  }
  
  public int selectListCount(Object param) throws Exception {
    return ((Integer)getData("ezsmart.group.selectListCount", param)).intValue();
  }

  
  public List<EzMap> selectList(Object param) throws Exception {
    return getList("ezsmart.group.selectList", param);
  }

  
  public int selectGroupUserCount(Object param) throws Exception {
    return ((Integer)getData("ezsmart.group.selectGroupUserCount", param)).intValue();
  }

  
  public List<EzMap> selectGroupUserList(Object param) throws Exception {
    return getList("ezsmart.group.selectGroupUserList", param);
  }
  
  public List<EzMap> selectGroupCheckList(Object param) throws Exception {
    return getList("ezsmart.group.selectGroupCheckList", param);
  }
  
  public int selectGroupMenuCount(Object param) throws Exception {
    return ((Integer)getData("ezsmart.group.selectGroupMenuCount", param)).intValue();
  }

  
  public List<EzMap> selectGroupMenuList(Object param) throws Exception {
    return getList("ezsmart.group.selectGroupMenuList", param);
  }

  
  public int insertGroup(Object param) throws Exception {
    return insert("ezsmart.zzGrpM.insertGroup", param);
  }

  
  public int insertGroupUser(Object param) throws Exception {
    return insert("ezsmart.zzGrpUserM.insertGroupUser", param);
  }

  
  public int insertGroupMenu(Object param) throws Exception {
    return insert("ezsmart.zzGrpMenuM.insertGroupMenu", param);
  }

  
  public int updateGroup(Object param) throws Exception {
    return update("ezsmart.zzGrpM.updateGroup", param);
  }
  
  public int updateGroupMenu(Object param) throws Exception {
    return update("ezsmart.zzGrpMenuM.updateGroupMenu", param);
  }
  
  public int deleteGroup(Object param) throws Exception {
    return delete("ezsmart.zzGrpM.deleteGroup", param);
  }

  
  public int deleteGroupUser(Object param) throws Exception {
    return delete("ezsmart.zzGrpUserM.deleteGroupUser", param);
  }

  
  public int deleteGroupMenu(Object param) throws Exception {
    return delete("ezsmart.group.deleteGroupMenu", param);
  }
}

