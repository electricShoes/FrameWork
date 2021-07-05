package cspi.ezsmart.system.dao;

import cspi.ezsmart.common.dao.BaseH2Dao;
import cspi.ezsmart.common.model.EzMap;
import java.util.List;
import org.springframework.stereotype.Component;



@Component
public class SqlLogDao  extends BaseH2Dao
{
  public List<EzMap> selectList(Object param) throws Exception {
    return getList("ezsmart.sqlLog.selectList", param);
  }
  
  public EzMap selectSqlLog(Object param) throws Exception {
    return (EzMap)getData("ezsmart.sqlLog.selectSqlLog", param);
  }
  
  public int selectListCount(Object param) throws Exception {
    return ((Integer)getData("ezsmart.sqlLog.selectListCount", param)).intValue();
  }
  
  public int insertSqlLog(Object param) throws Exception {
    return insert("ezsmart.zzSqlLogM.insertSqlLog", param);
  }
  
  public int updateSqlLog(Object param) throws Exception {
    return update("ezsmart.zzSqlLogM.updateSqlLog", param);
  }
  
  public int deleteSqlLog(Object param) throws Exception {
    return delete("ezsmart.zzSqlLogM.deleteSqlLog", param);
  }
}
