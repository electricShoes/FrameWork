package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.dao.UserDao;
import cspi.ezsmart.manage.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl
  implements UserService
{
  @Autowired
  UserDao userDao;
  
  public EzMap getUser(EzMap param) throws Exception {
    return this.userDao.selectUser(param);
  }

  
  public List<EzMap> getList(Object param) throws Exception {
    return this.userDao.selectList(param);
  }

  
  public int getListCount(Object param) throws Exception {
    return this.userDao.selectListCount(param);
  }

  
  public EzMap updateUser(EzMap param) throws Exception {
    EzMap ret = Utilities.getUpdateResult(this.userDao.updateUser(param));

    
    return ret;
  }



  
  public EzMap insertUser(EzMap param) throws Exception {
    if ("".equals(param.getString("empNo")) || param.getString("empNo") == null) {
      System.out.println("사용자 신규 등록 ================ UserServiceImpl.java - insertUser");
      String pwd = param.getString("pwd");
      param.put("pwd", Utilities.passwordEncode(pwd));
      EzMap ret = Utilities.getInsertResult(this.userDao.insertUser(param));
      ret.put("result", this.userDao.selectUser(param));
      return ret;
    } 
    System.out.println("사용자 수정 ================ UserServiceImpl.java - insertUser");
    return updateUser(param);
  }


  
  public EzMap deleteUser(EzMap param) throws Exception {
    return Utilities.getDeleteResult(this.userDao.deleteUser(param));
  }

  
  public EzMap updateResetPassword(EzMap param) throws Exception {
    param.put("pwd", Utilities.passwordEncode("1qa@WS"));
    return Utilities.getUpdateResult(this.userDao.updatePassword(param));
  }

  
  public EzMap deleteUserList(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(deleteUser(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }

  
  public EzMap saveUser(EzMap param) throws Exception {
    if ("c".equals(param.getString("stat")))
      return insertUser(param); 
    if ("u".equals(param.getString("stat")))
      return updateUser(param); 
    if ("d".equals(param.getString("stat"))) {
      return deleteUser(param);
    }
    return null;
  }

  
  public EzMap saveUser(List<EzMap> list) throws Exception {
    List<EzMap> result = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      result.add(saveUser(list.get(i)));
    }
    return Utilities.getSaveResult(result);
  }
}