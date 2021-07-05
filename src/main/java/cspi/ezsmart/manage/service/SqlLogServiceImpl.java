package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.service.SqlLogService;
import cspi.ezsmart.system.dao.SqlLogDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("sqlLogService")
public class SqlLogServiceImpl
  implements SqlLogService
{
  @Autowired
  SqlLogDao logDao;
  
  public EzMap getLog(EzMap param) throws Exception {
    return this.logDao.selectSqlLog(param);
  }

  
  public List<EzMap> getList(Object param) throws Exception {
    return this.logDao.selectList(param);
  }

  
  public int getListCount(Object param) throws Exception {
    return this.logDao.selectListCount(param);
  }

  
  public EzMap updateLog(EzMap param) throws Exception {
    EzMap ret = Utilities.getUpdateResult(this.logDao.updateSqlLog(param));

    
    return ret;
  }


  
  public EzMap insertLog(EzMap param) throws Exception {
    String pwd = param.getString("pwd");
    
    param.put("pwd", Utilities.passwordEncode(pwd));
    EzMap ret = Utilities.getInsertResult(this.logDao.insertSqlLog(param));
    ret.put("result", this.logDao.selectSqlLog(param));
    return ret;
  }

  
  public EzMap deleteLog(EzMap param) throws Exception {
    return Utilities.getDeleteResult(this.logDao.deleteSqlLog(param));
  }



  
  public EzMap deleteLogList(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(deleteLog(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }

  
  public EzMap saveLog(EzMap param) throws Exception {
    if ("c".equals(param.getString("stat")))
      return insertLog(param); 
    if ("u".equals(param.getString("stat")))
      return updateLog(param); 
    if ("d".equals(param.getString("stat"))) {
      return deleteLog(param);
    }
    return null;
  }

  
  public EzMap saveLog(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(saveLog(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }
}
