package cspi.ezsmart.common.model;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.model.ITreeVo;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.model.BaseBo;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTreeVo extends BaseBo implements ITreeVo
{
  private static final long serialVersionUID = -3915017122341663294L;
  private List<ITreeVo> nodes = null;
  private String leafIcon;
  private String folderIcon;
  private String color;
  private String backColor;
  private String href;
  private boolean selectable = true;
//  private State state = new State(this);
  private List<String> tags;
  private ITreeVo parent;
  
  public AbstractTreeVo() {
    setLeafIcon("fas fa-book");
    setFolderIcon("far fa-folder");
  }
  
  public static List<ITreeVo> makeHierarchy(List<? extends ITreeVo> list, EzMap itemMap) {
    List<ITreeVo> ret = new ArrayList<>();
    
    if (Utilities.isEmpty(list))
      return ret; 
    if (itemMap == null)
      itemMap = new EzMap();  int i;
    for (i = 0; i < list.size(); i++) {
      ITreeVo vo = list.get(i);
      if (!Utilities.isEmpty(vo.getId()))
      {
        itemMap.put(vo.getId(), vo); } 
    } 
    for (i = 0; i < list.size(); i++) {
      
      ITreeVo vo = list.get(i);

      
      int level = vo.getLevel();
      if (level == 1) {
        
        ret.add(vo);
      
      }
      else if (!itemMap.containsKey(vo.getParentId())) {
        
        ret.add(vo);
      } else {
        
        ITreeVo parent = (ITreeVo)itemMap.get(vo.getParentId());
        if (parent != null)
        {
          parent.addChild(vo); } 
      } 
    }  return ret;
  }
  
  public static List<ITreeVo> makeHierarchy(List<? extends ITreeVo> list) {
    EzMap itemMap = new EzMap();
    return makeHierarchy(list, itemMap);
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
  }
  
  public List<ITreeVo> createNodes() {
    setNodes(new ArrayList<>());
    return this.nodes;
  }

  
  public List<ITreeVo> getNodes() {
    return this.nodes;
  }
  
  public boolean isNode(String id) {
    if (getId().equals(id))
      return true; 
    if (this.parent != null) {
      return this.parent.isNode(id);
    }
    return false;
  }
  public void setNodes(List<ITreeVo> nodes) {
    this.nodes = nodes;
  }
  
  public String getIcon() {
    List<ITreeVo> list = getNodes();
    if (Utilities.isEmpty(list)) {
      return getLeafIcon();
    }
    return getFolderIcon();
  }
  
  public String getSelectedIcon() {
    return getIcon();
  }
  
  public String getExpandIcon() {
    return getIcon();
  }
  
  public String getColor() {
    return this.color;
  }
  
  public void setColor(String color) {
    this.color = color;
  }
  
  public String getBackColor() {
    return this.backColor;
  }
  
  public void setBackColor(String backColor) {
    this.backColor = backColor;
  }
  
  public String getHref() {
    return this.href;
  }

  
  public void setHref(String href) {
    this.href = href;
  }
  
//  public State getState() {
//    return this.state;
//  }
//  
//  public void setState(State state) {
//    this.state = state;
//  }
  
  public List<String> getTags() {
    int cnt = getChildrenCount();
    if (cnt < 1)
      return null; 
    if (this.tags == null)
      this.tags = new ArrayList<>(); 
    this.tags.clear();
    this.tags.add((new StringBuilder(String.valueOf(cnt))).toString());
    return this.tags;
  }
  
  public boolean isSelectable() {
    return this.selectable;
  }
  
  public void setSelectable(boolean selectable) {
    this.selectable = selectable;
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
  
  public String getLeafIcon() {
    return this.leafIcon;
  }
  
  public void setLeafIcon(String leafIcon) {
    this.leafIcon = leafIcon;
  }
  
  public String getFolderIcon() {
    return this.folderIcon;
  }
  
  public void setFolderIcon(String folderIcon) {
    this.folderIcon = folderIcon;
  }
  
  public ITreeVo parent() {
    return this.parent;
  }
  
  public void setParent(ITreeVo parent) {
    this.parent = parent;
  }

  
  public int getLevel() {
    ITreeVo p = parent();
    
    int level = 1;
    while (p != null) {
      level++;
      p = p.parent();
    } 
    return level;
  }
}


/* Location:              D:\OLD_File_2021052\\utils\SI사업부\소스코드\admin.war!\WEB-INF\classes\cspi\ezsmart\common\model\AbstractTreeVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */