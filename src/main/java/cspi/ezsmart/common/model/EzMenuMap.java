package cspi.ezsmart.common.model;

import cspi.ezsmart.common.model.EzTreeMap;
import cspi.ezsmart.common.model.ITreeVo;
import cspi.ezsmart.common.util.Utilities;
import java.util.List;


public class EzMenuMap extends EzTreeMap
{
  private static final long serialVersionUID = 4895767940898982895L;
  
  public Object put(String key, Object value) {
    Object ret = super.put(key, value);
    key = Utilities.convert2CamelCase(key);
    if ("menuId".equals(key))
      setString("id", value); 
    if ("menuNm".equals(key))
      setString("text", value); 
    if ("upMenuId".equals(key))
      setString("parentId", value); 
    if ("menuLevel".equals(key))
      setInt("lvl", value); 
    if ("menuIcon".equals(key))
      setString("icon", value); 
    if ("menuUrl".equals(key))
      setString("href", value); 
    return ret;
  }


  
  public String getIcon() {
    if (!Utilities.isEmpty(getString("menuIcon")))
      return getString("menuIcon"); 
    switch (getLevel()) {
      case 1:
      case 2:
      case 3:
        return "far fa-folder";
      case 4:
        return "far fa-circle";
      case 5:
        return "far fa-dot-circle";
    } 
    return "fas fa-book";
  }

  
  public boolean hasLinkedMenu() {
    if (Utilities.isNotEmpty(getString("menuUrl")))
      return true; 
    List<ITreeVo> list = getNodes();
    for (int i = 0; list != null && i < list.size(); i++) {
      cspi.ezsmart.common.model.EzMenuMap ch = (cspi.ezsmart.common.model.EzMenuMap)list.get(i);
      if (ch.hasLinkedMenu())
        return true; 
    } 
    return false;
  }
}
