package cspi.ezsmart.common.dao;

import cspi.ezsmart.common.dao.BaseDaoImpl;
import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;

public class BaseH2Dao extends BaseDaoImpl {
  @Resource(name = "sqlSessionTemplateH2")
  SqlSessionTemplate sqlSessionTemplate;
  
  public SqlSessionTemplate getTemplate() {
    return this.sqlSessionTemplate;
  }
}
