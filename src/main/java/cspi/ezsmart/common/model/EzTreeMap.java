package cspi.ezsmart.common.model;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.model.ITreeVo;
import cspi.ezsmart.common.util.Utilities;
import java.util.ArrayList;
import java.util.List;


public class EzTreeMap extends EzMap implements ITreeVo
{
  private static final long serialVersionUID = 6732971600118045079L;
  private ITreeVo parent;
  
  public EzTreeMap() {
    this.parent = null;
    setLeafIcon("fas fa-book");
    setFolderIcon("far fa-folder");
    setIcon("fas fa-book");
    put("selectedItem", "leafIcon");
//    setState(new State(this));
    put("tags", new ArrayList());
  }

  
  public Object put(String key, Object value) {
    Object ret = super.put(key, value);
    key = Utilities.convert2CamelCase(key);
    if ("lvl".equals(key))
      setInt("level", value); 
    if ("icon".equals(key)) {
      
      setString("selectedIcon", value);
      setString("expandIcon", value);
    } 
    return ret;
  }


  public List<ITreeVo> getNodes() {
    return (List<ITreeVo>)get("nodes");
  }

  
  public void addChild(ITreeVo vo) {
    if (vo == null)
      return; 
    List<ITreeVo> list = getNodes();
    if (list == null)
      list = createNodes(); 
    if (list == null)
      return; 
    list.add(vo);
    vo.setParent(this);
    if (Utilities.isEmpty(getIcon())) {
      put("selectedItem", "fas fa-book");
    }
  }

  
  public int getChildrenCount() {
    List<ITreeVo> list = getNodes();
    int ret = 0;
    for (int i = 0; list != null && i < list.size(); i++) {
      List<ITreeVo> l = ((ITreeVo)list.get(i)).getNodes();
      if (l == null || l.size() == 0) {
        ret++;
      } else {
        ret += ((ITreeVo)list.get(i)).getChildrenCount();
      } 
    }  return ret;
  }
  
  public List<ITreeVo> createNodes() {
    setNodes(new ArrayList<>());
    return getNodes();
  }
  
  public void setNodes(List<ITreeVo> nodes) {
    put("nodes", nodes);
  }

  
  public String getId() {
    return getString("id");
  }

  
  public String getText() {
    return getString("text");
  }

  
  public String getParentId() {
    return getString("parentId");
  }

  
  public ITreeVo parent() {
    return this.parent;
  }

  
  public void setParent(ITreeVo vo) {
    this.parent = vo;
  }

  
  public boolean isNode(String id) {
    if (getId().equals(id))
      return true; 
    if (parent() != null) {
      return parent().isNode(id);
    }
    return false;
  }
  
  public int getLevel() {
    return getInt("lvl");
  }

  
  public String getLeafIcon() {
    return getString("leafIcon");
  }
  
  public void setLeafIcon(String icon) {
    put("leafIcon", icon);
  }

  
  public String getFolderIcon() {
    return getString("folderIcon");
  }
  
  public void setFolderIcon(String icon) {
    put("folderIcon", icon);
  }
  public String getIcon() {
    String icon = getString("icon");
    if (Utilities.isNotEmpty(icon))
      return icon; 
    List<ITreeVo> list = getNodes();
    if (Utilities.isEmpty(list)) {
      return getLeafIcon();
    }
    return getFolderIcon();
  }
  
  public void setIcon(String icon) {
    put("icon", icon);
  }
  public String getSelectedIcon() {
    return getIcon();
  }
  
  public String getExpandIcon() {
    return getIcon();
  }
  
  public String getColor() {
    return getString("color");
  }
  
  public void setColor(String color) {
    put("color", color);
  }
  
  public String getBackColor() {
    return getString("backColor");
  }
  
  public void setBackColor(String backColor) {
    put("backColor", backColor);
  }
  
  public String getHref() {
    return getString("href");
  }
  
  public void setHref(String href) {
    put("href", href);
  }
  
//  public State getState() {
//    return (State)get("state");
//  }
//  
//  public void setState(State state) {
//    put("state", state);
//  }

  
  public List<String> getTags() {
    int cnt = getChildrenCount();
    if (cnt < 1)
      return null; 
    List<String> tags = (List<String>)get("tags");
    
    if (tags == null) {
      
      tags = new ArrayList<>();
      put("tags", tags);
    } 
    tags.clear();
    tags.add((new StringBuilder(String.valueOf(cnt))).toString());
    return tags;
  }
  
  public boolean isSelectable() {
    return getBoolean("selectable");
  }
  
  public void setSelectable(boolean selectable) {
    put("selectable", Boolean.valueOf(selectable));
  }
}
