package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.util.SessionUtil;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.dao.UserDao;
import cspi.ezsmart.manage.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
  @Autowired
  UserDao userDao;
  
  @SuppressWarnings("unused")
public Object updatelogin(EzMap param) throws Exception {
    EzMap result = new EzMap();
    EzMap user = this.userDao.selectUser(param);
//    EzMap user = new EzMap();
    
//    user.put("userId", "test");
//    user.put("pwd", "test");
    
    if (user == null) {
      result.put("errorMsg", "사용자 아이디 또는 암호를 확인해 주세요");
      result.put("errorCode", "FAILED LOGIN");
      return result;
    } 
    
    
    if (!Utilities.passwordMatch(param.getString("pwd"), user.getString("pwd"))) {
//      
//      result.put("errorMsg", "사용자 아이디 또는 암호를 확인해 주세요");
//      result.put("errorCode", "FAILED LOGIN");
////      this.userDao.updateLoginFail(param);
//      return result;
    } 
    
    if (user.getString("userId").equals(param.getString("saveId")))
    {
      Utilities.setCookie("saveId", Utilities.encrypt(user.getString("userId")));
    }
    result.put("success", Boolean.valueOf(true));
    processLogin(user);
    return result;
  }
  
  public Object updatelogout() throws Exception {
    EzMap result = new EzMap();
    result.put("success", Boolean.valueOf(true));
    processLogout();
    return result;
  }


  
  private void processLogin(EzMap user) throws Exception {
    System.out.println("user1 ================ " + user);
    this.userDao.updateLogin(user);
    System.out.println("user2 ================ " + user);
    SessionUtil.setLoginUser(user);
  }
  private void processLogout() throws Exception {
    SessionUtil.logOut();
  }
}
