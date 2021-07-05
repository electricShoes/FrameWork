package cspi.ezsmart.common.model;

import cspi.ezsmart.common.util.Utilities;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import java.util.LinkedHashMap;


public class EzMap  extends LinkedHashMap<String, Object>
{
  private static final long serialVersionUID = 1235619947606311249L;
  private PaginationInfo page;
  
  public Object put(String key, Object value) {
    if (Utilities.isNotEmpty(key))
      key = Utilities.convert2CamelCase(key); 
    return super.put(key, value);
  }
  
  public String getString(String key) {
    return Utilities.nullCheck(get(key));
  }
  
  public int getInt(String key) {
    return Utilities.parseInt(get(key));
  }
  
  public long getLong(String key) {
    return Utilities.parseLong(get(key));
  }
  
  public boolean getBoolean(String key) {
    return Utilities.parseBoolean(get(key));
  }
  
  public float getFloat(String key) {
    return Utilities.parseFloat(get(key));
  }
  
  public double getDouble(String key) {
    return Utilities.parseDouble(get(key));
  }
  
  public void setString(String key, Object value) {
    put(key, Utilities.nullCheck(value));
  }
  
  public void setInt(String key, Object value) {
    put(key, Integer.valueOf(Utilities.parseInt(value)));
  }
  
  public void setLong(String key, Object value) {
    put(key, Long.valueOf(Utilities.parseLong(value)));
  }
  
  public void setBoolean(String key, Object value) {
    put(key, Boolean.valueOf(Utilities.parseBoolean(value)));
  }
  
  public void setFloat(String key, Object value) {
    put(key, Float.valueOf(Utilities.parseFloat(value)));
  }
  
  public void setDouble(String key, Object value) {
    put(key, Double.valueOf(Utilities.parseDouble(value)));
  }
  
  public PaginationInfo getPaginationInfo() {
    if (this.page == null) {
      int currentPageNo = getInt("currentPageNo");
      int totalRecordCount = getInt("totalRecordCount");
      int pageSize = getInt("pageSize");
      int recordCountPerPage = getInt("recordCountPerPage");
      if (currentPageNo < 1)
        currentPageNo = 1; 
      if (pageSize < 1)
        pageSize = Utilities.getPropertyInt("pageSize"); 
      if (recordCountPerPage < 1)
        recordCountPerPage = Utilities.getPropertyInt("recordCountPerPage"); 
      if (totalRecordCount < 0) {
        totalRecordCount = 0;
      }
      this.page = new PaginationInfo();
      this.page.setCurrentPageNo(currentPageNo);
      this.page.setTotalRecordCount(totalRecordCount);
      this.page.setPageSize(pageSize);
      this.page.setRecordCountPerPage(recordCountPerPage);
      setPaginationInfo(this.page);
    } 
    return this.page;
  }
  
  public void setPaginationInfo(PaginationInfo page) {
    this.page = page;
    putAll(Utilities.beanToMap(page));
  }
}
