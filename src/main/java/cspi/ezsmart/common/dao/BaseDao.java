package cspi.ezsmart.common.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;

public interface BaseDao {
  SqlSessionTemplate getTemplate();
  
  <T> T getData(String paramString);
  
  <T> T getData(String paramString, Object paramObject);
  
  <T> List<T> getList(String paramString, Object paramObject);
  
  <T> List<T> getList(String paramString);
  
  int insert(String paramString);
  
  int insert(String paramString, Object paramObject);
  
  int update(String paramString);
  
  int update(String paramString, Object paramObject);
  
  int delete(String paramString);
  
  int delete(String paramString, Object paramObject);
}
