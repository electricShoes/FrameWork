package cspi.ezsmart.manage.service;

import cspi.ezsmart.common.model.EzMap;

public interface LoginService {
  Object updatelogin(EzMap paramEzMap) throws Exception;
  
  Object updatelogout() throws Exception;
}

