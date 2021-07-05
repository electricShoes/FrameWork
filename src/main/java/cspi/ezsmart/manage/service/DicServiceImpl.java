package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.dao.DicDao;
import cspi.ezsmart.manage.service.DicService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dicService")
public class DicServiceImpl
  implements DicService
{
  @Autowired
  DicDao dicDao;
  
  public List<EzMap> getList(Object param) throws Exception {
    return this.dicDao.selectList(param);
  }

  
  public int getListCount(Object param) throws Exception {
    return this.dicDao.selectListCount(param);
  }

  
  public Object saveVariable(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      
      if ("c".equals(((EzMap)list.get(i)).getString("stat"))) {
        insertVar(list.get(i));
      } else if ("u".equals(((EzMap)list.get(i)).getString("stat"))) {
        updateVar(list.get(i));
      } else if ("d".equals(((EzMap)list.get(i)).getString("stat"))) {
        deleteVar(list.get(i));
      } 
    }  return Utilities.getSaveResult(result);
  }
  
  private Object insertVar(EzMap param) throws Exception {
    EzMap ret = Utilities.getInsertResult(this.dicDao.insertVar(param));
    ret.put("result", this.dicDao.selectVar(param));
    return ret;
  }
  
  private Object updateVar(EzMap param) throws Exception {
    EzMap ret = Utilities.getUpdateResult(this.dicDao.updateVar(param));
    ret.put("result", this.dicDao.selectVar(param));
    return ret;
  }
  
  private Object deleteVar(EzMap param) throws Exception {
    return Utilities.getDeleteResult(this.dicDao.deleteVar(param));
  }

  
  public Object deleteVariable(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(deleteVariable(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }
  
  private EzMap deleteVariable(EzMap param) throws Exception {
    return Utilities.getDeleteResult(this.dicDao.deleteVariable(param));
  }
}

