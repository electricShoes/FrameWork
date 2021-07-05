package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.dao.VarDao;
import cspi.ezsmart.manage.service.VarService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("varService")
public class VarServiceImpl
  implements VarService
{
  @Autowired
  VarDao varDao;
  
  public List<EzMap> getList(Object param) throws Exception {
    return this.varDao.selectList(param);
  }

  
  public int getListCount(Object param) throws Exception {
    return this.varDao.selectListCount(param);
  }

  
  public Object saveVariable(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    System.out.println("list.size() + " + list.size());
    for (int i = 0; i < list.size(); i++) {
      System.out.println("list.get( ) + " + list.get(i));
      if ("c".equals(((EzMap)list.get(i)).getString("stat"))) {
        ((EzMap)list.get(i)).put("cmpyCd", "0000");
        insertVar(list.get(i));
      } else if ("u".equals(((EzMap)list.get(i)).getString("stat"))) {
        ((EzMap)list.get(i)).put("cmpyCd", "0000");
        updateVar(list.get(i));
      } else if ("d".equals(((EzMap)list.get(i)).getString("stat"))) {
        ((EzMap)list.get(i)).put("cmpyCd", "0000");
        deleteVar(list.get(i));
      } 
    } 
    return Utilities.getSaveResult(result);
  }
  
  private Object insertVar(EzMap param) throws Exception {
    EzMap ret = Utilities.getInsertResult(this.varDao.insertVar(param));
    ret.put("result", this.varDao.selectVar(param));
    return ret;
  }
  
  private Object updateVar(EzMap param) throws Exception {
    EzMap ret = Utilities.getUpdateResult(this.varDao.updateVar(param));
    ret.put("result", this.varDao.selectVar(param));
    return ret;
  }
  
  private Object deleteVar(EzMap param) throws Exception {
    return Utilities.getDeleteResult(this.varDao.deleteVar(param));
  }

  
  public Object deleteVariable(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      ((EzMap)list.get(i)).put("cmpyCd", "0000");
      result.add(deleteVariable(list.get(i)));
    } 
    return Utilities.getSaveResult(result);
  }
  
  private EzMap deleteVariable(EzMap param) throws Exception {
    return Utilities.getDeleteResult(this.varDao.deleteVariable(param));
  }
}
