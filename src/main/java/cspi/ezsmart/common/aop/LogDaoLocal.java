package cspi.ezsmart.common.aop;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.util.Utilities;
import java.util.Date;


public class LogDaoLocal
{
  public static ThreadLocal<EzMap> local = new ThreadLocal<>();
  
  public static void setStart() {
    EzMap info = getSqlInfo();
    info.put("startDate", new Date());
    info.setString("strDt", String.valueOf(Utilities.getDateString("")) + Utilities.getTimeString(""));
  }
  
  public static void setEnd() {
    EzMap info = getSqlInfo();
    info.put("endDate", new Date());
    info.setString("endDt", String.valueOf(Utilities.getDateString("")) + Utilities.getTimeString(""));
  }
  
  public static void setSql(String sql) {
    EzMap info = getSqlInfo();
    info.setString("logSql", sql);
  }
  
  public static void setMenuId(String menuId) {
    EzMap info = getSqlInfo();
    info.setString("menuId", menuId);
  }
  
  public static void setUserId(String userId) {
    EzMap info = getSqlInfo();
    info.setString("userId", userId);
  }
  
  public static EzMap getSqlInfo() {
    EzMap sqlInfo = local.get();
    if (sqlInfo == null) {
      sqlInfo = new EzMap();
      local.set(sqlInfo);
    } 
    return sqlInfo;
  }
  
  public static void clear() {
    local.set(null);
  }

  
  public static void setTiming(String timing) {
    EzMap info = getSqlInfo();
    info.setLong("execTime", timing);
  }
}
