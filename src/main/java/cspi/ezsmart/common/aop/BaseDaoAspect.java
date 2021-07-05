package cspi.ezsmart.common.aop;

import cspi.ezsmart.common.util.SessionUtil;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.model.BaseBo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.JoinPoint;



public class BaseDaoAspect
{
  public void insertBefore(JoinPoint jp) {
    try {
      String userId = SessionUtil.getLoginUserId();
      if (Utilities.isEmpty(userId)) {
        userId = "SYSTEM";
      }
      String dt = Utilities.getDateTimeString();
      Map<String, String> map = getMapObject(jp.getArgs());
      if (map != null) {
        if (Utilities.isEmpty(map.get("regId")))
          map.put("regId", userId); 
        if (Utilities.isEmpty(map.get("regDt")))
          map.put("regDt", dt); 
        if (Utilities.isEmpty(map.get("modId")))
          map.put("modId", userId); 
        if (Utilities.isEmpty(map.get("modDt")))
          map.put("modDt", dt); 
        return;
      } 
      BaseBo bo = getBaseBo(jp.getArgs());
      if (bo != null) {
        if (Utilities.isEmpty(bo.getRegId()))
          bo.setRegId(userId); 
        if (Utilities.isEmpty(bo.getRegDt()))
          bo.setRegDt(dt); 
        if (Utilities.isEmpty(bo.getModId()))
          bo.setModId(userId); 
        if (Utilities.isEmpty(bo.getModDt())) {
          bo.setModDt(dt);
        }
        return;
      } 
    } catch (Exception ex) {
      Utilities.trace(ex);
    } 
  }
  
  public void updateBefore(JoinPoint jp) {
    try {
      insertBefore(jp);
    } catch (Exception ex) {
      Utilities.trace(ex);
    } 
  }

  
  public void selectBefore(JoinPoint jp) {
    try {
      Map searchObj = getMapObject(jp.getArgs());
      if (searchObj == null)
        return; 
    } catch (Exception ex) {
      Utilities.trace(ex);
    } 
  }


  
  public void selectListBefore(JoinPoint jp) {
    try {
      setPagination(jp);
    } catch (Exception ex) {
      Utilities.trace(ex);
    } 
  }


  
  private void setPagination(JoinPoint jp) {
    Map<String, Object> searchObj = getMapObject(jp.getArgs());
    if (searchObj == null)
      return; 
    Object pinfo = searchObj.get("pagination");
    if (pinfo == null) {
      pinfo = new PaginationInfo();
      searchObj.put("pagination", pinfo);
    } 
    if (pinfo instanceof PaginationInfo) {
      PaginationInfo page = (PaginationInfo)pinfo;
      Utilities.mapToBean(searchObj, page);
      if (page.getCurrentPageNo() < 1)
        page.setCurrentPageNo(1); 
      if (page.getPageSize() < 1)
        page.setPageSize(5); 
      if (page.getRecordCountPerPage() < 1)
        page.setRecordCountPerPage(10); 
      Utilities.beanToMap(searchObj, page);
    } 
    Map<String, Object> param = new HashMap<>();
    for (String key : searchObj.keySet()) {
      Object obj = searchObj.get(key);
      if (obj instanceof String || obj instanceof Number || obj instanceof Boolean || !(obj instanceof Object)) {
        param.put(key.toString(), obj);
      }
    } 
    searchObj.put("searchParam", param);
  }



  
  private Map getMapObject(Object[] args) {
    if (args == null || args.length == 0)
      return null;  byte b; int i;
    Object[] arrayOfObject;
    for (i = (arrayOfObject = args).length, b = 0; b < i; ) { Object arg = arrayOfObject[b];
      if (arg instanceof egovframework.rte.psl.dataaccess.util.EgovMap)
        return (Map)arg; 
      if (arg instanceof Map)
        return (Map)arg; 
      b++; }
    
    return null;
  }

  
  private BaseBo getBaseBo(Object[] args) {
    if (args == null || args.length == 0)
      return null;  byte b;
    int i;
    Object[] arrayOfObject;
    for (i = (arrayOfObject = args).length, b = 0; b < i; ) { Object arg = arrayOfObject[b];
      if (arg instanceof BaseBo) {
        return (BaseBo)arg;
      }
      b++; }
    
    return null;
  }
}
