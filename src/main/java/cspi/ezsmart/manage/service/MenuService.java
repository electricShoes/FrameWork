package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.model.EzMenuMap;
import cspi.ezsmart.common.model.ITreeVo;
import java.util.List;

public interface MenuService {
  List<ITreeVo> getUserMenuTree(Object paramObject) throws Exception;
  
  List<ITreeVo> getUserMenuTree(Object paramObject, EzMap paramEzMap) throws Exception;
  
  List<EzMenuMap> getUserMenuList(Object paramObject) throws Exception;
  
  List<ITreeVo> getTreeList(EzMap paramEzMap) throws Exception;
  
  List<EzMenuMap> getList(Object paramObject) throws Exception;
  
  int getListCount(Object paramObject) throws Exception;
  
  EzMap updateMenu(EzMap paramEzMap) throws Exception;
  
  EzMap insertMenu(EzMap paramEzMap) throws Exception;
  
  EzMap deleteMenuList(List<EzMap> paramList) throws Exception;
  
  EzMap deleteMenu(EzMap paramEzMap) throws Exception;
  
  EzMap saveMenu(EzMap paramEzMap) throws Exception;
  
  EzMap saveMenu(List<EzMap> paramList) throws Exception;
  
  EzMenuMap getMenu(EzMap paramEzMap) throws Exception;
}
