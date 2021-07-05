package cspi.rest.api;

import cspi.ezsmart.common.util.HandlerUtils;
//import cspi.rest.api.ApiAuthController;
//import cspi.rest.api.ApiAuthService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RestApiIntercepter
  extends HandlerInterceptorAdapter {
//  @Resource(name = "apiAuthService")
//  ApiAuthService apiAuthService;
//  
//  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//    if (HandlerUtils.isInstance(handler, ApiAuthController.class))
//      return true; 
//    return this.apiAuthService.validateToken();
//  }
}
