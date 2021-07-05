package cspi.ezsmart.manage.model;

import cspi.ezsmart.common.model.AbstractTreeVo;
import cspi.ezsmart.common.util.Utilities;




public class GroupVo
  extends AbstractTreeVo
{
  private static final long serialVersionUID = 3603332642225037884L;
  private String id;
  private String name;
  private String parentId;
  
  public String getId() {
    return this.id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  
  public String getParentId() {
    return this.parentId;
  }
  
  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  
  public String getText() {
    return getName();
  }
  
  public cspi.ezsmart.manage.model.GroupVo findGroupByName(String group1Name, String group2Name) {
    if (Utilities.isEmpty(group1Name))
      return null; 
    if (group1Name.equals(this.name)) {
      if (Utilities.isEmpty(group2Name))
        return this; 
      for (int j = 0; getNodes() != null && j < getNodes().size(); j++) {
        cspi.ezsmart.manage.model.GroupVo vo = ((cspi.ezsmart.manage.model.GroupVo)getNodes().get(j)).findGroupByName(group2Name);
        if (vo != null)
          return vo; 
      } 
      return null;
    } 
    for (int i = 0; getNodes() != null && i < getNodes().size(); i++) {
      cspi.ezsmart.manage.model.GroupVo vo = ((cspi.ezsmart.manage.model.GroupVo)getNodes().get(i)).findGroupByName(group1Name, group2Name);
      if (vo != null)
        return vo; 
    } 
    return null;
  }


  
  public cspi.ezsmart.manage.model.GroupVo findGroupByName(String grpNm) {
    if (Utilities.isEmpty(grpNm))
      return null; 
    if (grpNm.equals(this.name))
      return this; 
    for (int i = 0; getNodes() != null && i < getNodes().size(); i++) {
      cspi.ezsmart.manage.model.GroupVo vo = ((cspi.ezsmart.manage.model.GroupVo)getNodes().get(i)).findGroupByName(grpNm);
      if (vo != null)
        return vo; 
    } 
    return null;
  }
}
