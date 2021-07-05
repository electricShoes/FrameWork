package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.model.EzMenuMap;
import cspi.ezsmart.common.model.ITreeVo;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.dao.MenuDao;
import cspi.ezsmart.manage.service.MenuService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("menuService")
public class MenuServiceImpl implements MenuService
{
  @Autowired
  MenuDao menuDao;
  
  public List<ITreeVo> getUserMenuTree(Object param) throws Exception {
    EzMap itemMap = new EzMap();
    return getUserMenuTree(param, itemMap);
  }


  
  public List<ITreeVo> getTreeList(EzMap param) throws Exception {
    param.setInt("recordCountPerPage", Integer.valueOf(100000));
    return Utilities.makeHierarchy(getList(param));
  }





  
  public List<ITreeVo> getUserMenuTree(Object param, EzMap itemMap) throws Exception {
    List<ITreeVo> list = Utilities.makeHierarchy(getUserMenuList(param), itemMap);
    List<String> removeList = new ArrayList<>();
    for (String key : itemMap.keySet()) {
      EzMenuMap menu = (EzMenuMap)itemMap.get(key);
      if (!menu.hasLinkedMenu()) {
        removeList.add(menu.getId());
      }
    } 
    for (int i = 0; i < removeList.size(); i++) {
      EzMenuMap menu = (EzMenuMap)itemMap.remove(removeList.get(i));
      ITreeVo parant = menu.parent();
      if (parant != null) {
        parant.getNodes().remove(menu);
      }
    } 
    return list;
  }


  
  public List<EzMenuMap> getUserMenuList(Object param) throws Exception {
    return this.menuDao.selectUserMenuList(param);
  }



  
  public EzMenuMap getMenu(EzMap param) throws Exception {
    return this.menuDao.selectMenu(param);
  }

  
  public List<EzMenuMap> getList(Object param) throws Exception {
    return this.menuDao.selectList(param);
  }
  
  public int getListCount(Object param) throws Exception {
    return this.menuDao.selectListCount(param);
  }

  
  public EzMap updateMenu(EzMap param) throws Exception {
    return Utilities.getUpdateResult(this.menuDao.updateMenu(param));
  }

  
  public EzMap insertMenu(EzMap param) throws Exception {
    EzMap ret = Utilities.getInsertResult(this.menuDao.insertMenu(param));
    ret.put("result", this.menuDao.selectMenu(param));
    return ret;
  }

  
  public EzMap deleteMenu(EzMap param) throws Exception {
    return Utilities.getDeleteResult(this.menuDao.deleteMenu(param));
  }
  
  public EzMap deleteMenuList(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(deleteMenu(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }
  
  public EzMap saveMenu(EzMap param) throws Exception {
    if ("c".equals(param.getString("stat")))
      return insertMenu(param); 
    if ("u".equals(param.getString("stat")))
      return updateMenu(param); 
    if ("d".equals(param.getString("stat"))) {
      return deleteMenu(param);
    }
    return null;
  }

  
  public EzMap saveMenu(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(saveMenu(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }
}
