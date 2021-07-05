package cspi.ezsmart.common.aop;

import cspi.ezsmart.common.aop.LogDaoLocal;
import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.util.SessionUtil;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.system.dao.SqlLogDao;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;




public class LogDaoAspect
{
  @Autowired
  SqlLogDao logDao;
  
  public void sqlBeforeController(JoinPoint jp) {
    EzMap sqlInfo = LogDaoLocal.getSqlInfo();
    if (Utilities.isNotEmpty(sqlInfo.getString("skip")))
      return; 
    String method = jp.getStaticPart().toLongString();
    int idx = method.indexOf("cspi.");
    if (idx > 0) {
      method = method.substring(idx);
      idx = method.lastIndexOf("(");
      if (idx > 0)
        method = method.substring(0, idx); 
    } 
    
    
    System.out.println("=======sqlBeforeController================");
    
    sqlInfo.setString("callController", method);
  }

  
  public void sqlBeforeService(JoinPoint jp) {
    EzMap sqlInfo = LogDaoLocal.getSqlInfo();
    if (Utilities.isNotEmpty(sqlInfo.getString("skip")))
      return; 
    String method = jp.getStaticPart().toLongString();
    int idx = method.indexOf("cspi.");
    if (idx > 0) {
      method = method.substring(idx);
      idx = method.lastIndexOf("(");
      if (idx > 0)
        method = method.substring(0, idx); 
    } 
    sqlInfo.setString("callService", method);
  }
  
  public void sqlBeforeMenthod(JoinPoint jp) {
    EzMap sqlInfo = LogDaoLocal.getSqlInfo();
    if (Utilities.isNotEmpty(sqlInfo.getString("skip")))
      return; 
    String method = jp.getStaticPart().toLongString();
    int idx = method.indexOf("cspi.");
    if (idx > 0) {
      method = method.substring(idx);
      idx = method.lastIndexOf("(");
      if (idx > 0)
        method = method.substring(0, idx); 
    } 
    sqlInfo.setString("callMethod", method);
  }


  
  public void sqlBefore(JoinPoint jp) {
    try {
      EzMap sqlInfo = LogDaoLocal.getSqlInfo();
      if (Utilities.isNotEmpty(sqlInfo.getString("skip")))
        return; 
      Date endDt = (Date)sqlInfo.get("endDate");
      if (endDt != null)
        return; 
      String logSql = sqlInfo.getString("logSql");
      if (Utilities.isNotEmpty(logSql))
        return; 
      Object[] params = jp.getArgs();
      if (params != null && params.length > 0) {
        sqlInfo.setString("daoMethod", params[0]);
      } else {
        sqlInfo.setString("daoMethod", jp.getStaticPart().toLongString());
      }  HttpServletRequest request = Utilities.getRequest();
      if (request != null) {
        
        EzMap menu = (EzMap)request.getAttribute("currentMenu");
        if (menu != null)
          sqlInfo.put("currentMenu", menu); 
      } 
      LogDaoLocal.setStart();
    }
    catch (Exception exception) {}
  }




  
  public void sqlAfter(JoinPoint jp) {
    try {
      EzMap sqlInfo = LogDaoLocal.getSqlInfo();
      if (Utilities.isNotEmpty(sqlInfo.getString("skip")))
        return; 
      String logSql = sqlInfo.getString("logSql");
      if (Utilities.isEmpty(logSql))
        return; 
      String strDt = sqlInfo.getString("strDt");
      if (Utilities.isEmpty(strDt))
        return; 
      HttpServletRequest request = Utilities.getRequest();
      if (request == null) {
        return;
      }
      EzMap menu = (EzMap)request.getAttribute("currentMenu");
      if (Utilities.isEmpty(menu))
        return; 
      if (!"Y".equals(menu.getString("sqlLogYn")))
        return; 
      LogDaoLocal.setMenuId(menu.getString("menuId"));
      
      String userId = SessionUtil.getLoginUserId();
      if (Utilities.isEmpty(userId)) {
        return;
      }


      
      LogDaoLocal.setEnd();
      LogDaoLocal.setUserId(userId);
      Date startDt = (Date)sqlInfo.get("startDate");
      Date endDt = (Date)sqlInfo.get("endDate");
      if (startDt == null)
        return; 
      if (!sqlInfo.containsKey("execTime")) {
        long execTime = endDt.getTime() - startDt.getTime();
        sqlInfo.setLong("execTime", Long.valueOf(execTime));
      } 

      
      sqlInfo.setString("logId", String.valueOf(Utilities.getDateString("")) + Utilities.getTimeString("") + Utilities.getUniqNumberID(16));
      sqlInfo.setString("skip", Boolean.valueOf(true));

      
      this.logDao.insertSqlLog(sqlInfo);
      
      LogDaoLocal.clear();
    }
    catch (Exception exception) {}
  }
}
