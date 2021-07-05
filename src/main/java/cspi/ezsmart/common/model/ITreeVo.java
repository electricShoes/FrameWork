package cspi.ezsmart.common.model;

import java.io.Serializable;
import java.util.List;

public interface ITreeVo extends Serializable {
  String getId();
  
  String getText();
  
  String getParentId();
  
  cspi.ezsmart.common.model.ITreeVo parent();
  
  void setParent(cspi.ezsmart.common.model.ITreeVo paramITreeVo);
  
  int getLevel();
  
  List<cspi.ezsmart.common.model.ITreeVo> getNodes();
  
  void addChild(cspi.ezsmart.common.model.ITreeVo paramITreeVo);
  
  int getChildrenCount();
  
  boolean isNode(String paramString);
}

