package cspi.ezsmart.manage.dao;

import cspi.ezsmart.common.dao.EzDao;
import cspi.ezsmart.common.model.EzMap;
import java.util.List;
import org.springframework.stereotype.Component;



@Component
public class VarDao
  extends EzDao
{
  public List<EzMap> selectList(Object param) throws Exception {
    return getList("ezsmart.var.selectList", param);
  }
  
  public int selectListCount(Object param) throws Exception {
    return ((Integer)getData("ezsmart.var.selectListCount", param)).intValue();
  }


  
  public Object selectVar(EzMap param) {
    return null;
  }

  
  public int insertVar(EzMap param) throws Exception {
    return insert("ezsmart.var.ZzComVarM.insert", param);
  }

  
  public int updateVar(EzMap param) throws Exception {
    return update("ezsmart.var.ZzComVarM.update", param);
  }

  
  public int deleteVar(EzMap param) throws Exception {
    return delete("ezsmart.var.ZzComVarM.delete", param);
  }

  
  public int deleteVariable(EzMap param) throws Exception {
    return delete("ezsmart.var.ZzComVarM.delete", param);
  }
}
