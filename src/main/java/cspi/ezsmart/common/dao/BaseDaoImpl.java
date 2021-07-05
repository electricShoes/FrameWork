package cspi.ezsmart.common.dao;

import cspi.ezsmart.common.dao.BaseDao;
import java.util.List;

public abstract class BaseDaoImpl  implements BaseDao {
  public <T> T getData(String statement, Object parameter) {
    return (T)getTemplate().selectOne(statement, parameter);
  }
  
  public <T> T getData(String statementId) {
    return (T)getTemplate().selectOne(statementId);
  }


  
  public <T> List<T> getList(String statement, Object parameter) {
    return getTemplate().selectList(statement, parameter);
  }
  
  public <T> List<T> getList(String statement) {
    return getTemplate().selectList(statement);
  }


  
  public int insert(String statement) {
    return getTemplate().insert(statement);
  }

  
  public int insert(String statement, Object parameter) {
    return getTemplate().insert(statement, parameter);
  }

  
  public int update(String statement) {
    return getTemplate().update(statement);
  }

  
  public int update(String statement, Object parameter) {
    return getTemplate().update(statement, parameter);
  }

  
  public int delete(String statement) {
    return getTemplate().update(statement);
  }

  
  public int delete(String statement, Object parameter) {
    return getTemplate().update(statement, parameter);
  }
}

