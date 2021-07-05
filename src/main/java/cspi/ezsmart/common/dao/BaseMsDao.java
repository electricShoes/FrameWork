package cspi.ezsmart.common.dao;

import cspi.ezsmart.common.dao.BaseDaoImpl;
import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;

public class BaseMsDao extends BaseDaoImpl {
  @Resource(name = "sqlSessionTemplateMs")
  SqlSessionTemplate sqlSessionTemplate;
  
  public SqlSessionTemplate getTemplate() {
    return this.sqlSessionTemplate;
  }
}
