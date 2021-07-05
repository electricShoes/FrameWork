package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.dao.CodeDao;
import cspi.ezsmart.manage.service.CodeService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service("codeService")
public class CodeServiceImpl
  implements CodeService
{
  @Autowired
  CodeDao codeDao;
  
  public List<EzMap> getComboCode(Object param) throws Exception {
    return this.codeDao.selectCodeCombo(param);
  }
  
  public EzMap getCode(EzMap param) throws Exception {
    return this.codeDao.selectCode(param);
  }

  
  public List<EzMap> getList(Object param) throws Exception {
    return this.codeDao.selectList(param);
  }


  
  public List<EzMap> getListDetail(Object param) throws Exception {
    return this.codeDao.getListDetail(param);
  }

  
  public int getListCount(Object param) throws Exception {
    return this.codeDao.selectListCount(param);
  }

  
  public EzMap updateCode(EzMap param) throws Exception {
    return Utilities.getUpdateResult(this.codeDao.updateCode(param));
  }

  
  public EzMap updateCodeDetail(EzMap param) throws Exception {
    return Utilities.getUpdateResult(this.codeDao.updateCodeDetail(param));
  }

  
  public EzMap insertCode(EzMap param) throws Exception {
    EzMap ret = Utilities.getInsertResult(this.codeDao.insertCode(param));
    ret.put("result", this.codeDao.selectCode(param));
    return ret;
  }

  
  public EzMap insertCodeDetail(EzMap param) throws Exception {
    EzMap ret = Utilities.getInsertResult(this.codeDao.insertCodeDetail(param));
    ret.put("result", this.codeDao.selectCode(param));
    return ret;
  }

  
  public EzMap deleteCode(EzMap param) throws Exception {
    return Utilities.getDeleteResult(this.codeDao.deleteCode(param));
  }

  
  public EzMap deleteCodeDetail(EzMap param) throws Exception {
    return Utilities.getDeleteResult(this.codeDao.deleteCodeDetail(param));
  }

  
  public EzMap deleteCodeList(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(deleteCode(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }
  
  public EzMap deleteCodeDetailList(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(deleteCodeDetail(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }
  
  public EzMap saveCode(EzMap param) throws Exception {
    if ("c".equals(param.getString("stat")))
      return insertCode(param); 
    if ("u".equals(param.getString("stat")))
      return updateCode(param); 
    if ("d".equals(param.getString("stat"))) {
      return deleteCode(param);
    }
    return null;
  }

  
  public EzMap saveCodeDetail(EzMap param) throws Exception {
    if ("c".equals(param.getString("stat")))
      return insertCodeDetail(param); 
    if ("u".equals(param.getString("stat")))
      return updateCodeDetail(param); 
    if ("d".equals(param.getString("stat"))) {
      return deleteCodeDetail(param);
    }
    return null;
  }

  
  public EzMap saveCode(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(saveCode(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }
  
  public EzMap saveDetail(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(saveCodeDetail(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }

  
  public void langDown(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++)
      result.add(deleteCodeDetail(list.get(i))); 
  }
}
