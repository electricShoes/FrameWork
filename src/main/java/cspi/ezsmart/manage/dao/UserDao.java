package cspi.ezsmart.manage.dao;

import cspi.ezsmart.common.dao.EzDao;
import cspi.ezsmart.common.model.EzMap;
import java.util.List;
import org.springframework.stereotype.Component;



@Component
public class UserDao extends EzDao
{
  public List<EzMap> selectList(Object param) throws Exception {
    return getList("ezsmart.user.selectList", param);
  }
  
  public EzMap selectUser(Object param) throws Exception {
    return (EzMap)getData("ezsmart.user.selectUser", param);
  }
  
  public int selectListCount(Object param) throws Exception {
    return ((Integer)getData("ezsmart.user.selectListCount", param)).intValue();
  }
  
  public int insertUser(Object param) throws Exception {
    return insert("ezsmart.zzUserM.insertUser", param);
  }
  
  public int updateUser(Object param) throws Exception {
    return update("ezsmart.zzUserM.updateUser", param);
  }
  
  public int deleteUser(Object param) throws Exception {
    return delete("ezsmart.zzUserM.deleteUser", param);
  }

  
  public int updatePassword(Object param) throws Exception {
    return update("ezsmart.zzUserM.updatePassword", param);
  }
  
  public int updateLogin(Object param) throws Exception {
    System.out.println("11111111111111111111111111111=====");
    return update("ezsmart.zzUserM.updateLogin", param);
  }

  
  public int updateLoginFail(Object param) throws Exception {
    return update("ezsmart.zzUserM.updateLoginFail", param);
  }
  
  public int updateDelYn(Object param) throws Exception {
    return update("ezsmart.zzUserM.updateDelYn", param);
  }
}

