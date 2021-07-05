package cspi.ezsmart.manage.dao;

import cspi.ezsmart.common.dao.EzDao;
import cspi.ezsmart.common.model.EzMenuMap;
import java.util.List;
import org.springframework.stereotype.Component;



@Component
public class MenuDao
  extends EzDao
{
  public List<EzMenuMap> selectUserMenuList(Object param) {
    return getList("ezsmart.menu.selectUserMenuList", param);
  }

  
  public List<EzMenuMap> selectList(Object param) throws Exception {
    return getList("ezsmart.menu.selectList", param);
  }
  
  public EzMenuMap selectMenu(Object param) throws Exception {
    return (EzMenuMap)getData("ezsmart.menu.selectMenu", param);
  }
  
  public int selectListCount(Object param) throws Exception {
    return ((Integer)getData("ezsmart.menu.selectListCount", param)).intValue();
  }
  
  public int insertMenu(Object param) throws Exception {
    return insert("ezsmart.zzMenuM.insertMenu", param);
  }
  
  public int updateMenu(Object param) throws Exception {
    return update("ezsmart.zzMenuM.updateMenu", param);
  }
  
  public int deleteMenu(Object param) throws Exception {
    return delete("ezsmart.zzMenuM.deleteMenu", param);
  }
}
