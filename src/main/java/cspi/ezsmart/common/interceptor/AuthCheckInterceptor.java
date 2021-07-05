package cspi.ezsmart.common.interceptor;

import cspi.ezsmart.common.interceptor.ExcludesibleInterceptor;
import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.util.HandlerUtils;
import cspi.ezsmart.common.util.SessionUtil;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.controller.main.MainController;
import cspi.ezsmart.manage.service.LoginService;
import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;


public class AuthCheckInterceptor extends ExcludesibleInterceptor
{
  private Logger logger = LoggerFactory.getLogger(cspi.ezsmart.common.interceptor.AuthCheckInterceptor.class);

  
//  @Resource(name = "loginService")
//  LoginService loginService;


  
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) return true;


    
    String targetURI = request.getServletPath();
    
    if (this.logger.isDebugEnabled()) {
      StringBuilder sb = new StringBuilder();

      
      sb.append("\n[preHandle] <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
      sb.append("\n#remoteIp   : ").append(request.getRemoteAddr());
      sb.append("\n#targetURI  : ").append(targetURI);
      sb.append("\n#reqUrl     : ").append(request.getRequestURL().toString());
      sb.append("\n#userAgent  : ").append(request.getHeader("User-Agent"));
      sb.append("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
      this.logger.debug(sb.toString());
    } 



    
    boolean isLogin = Utilities.isLogin();
    
    if (this.logger.isDebugEnabled()) {
      this.logger.debug("[preHandle] isLogin :: {}", isLogin ? "로그인되어 있음." : "로그인 되어있지 않음.");
      this.logger.debug("[preHandle] isAjaxRequest :: {}", SessionUtil.isAjaxRequest() ? "화면으로부터" : "Controller부터");
      this.logger.debug("[preHandle] '{}' isExcludeUrl :: {}", targetURI, Boolean.valueOf(isExcludeUrl(targetURI)));
      this.logger.debug("[preHandle] getHeader :: {}", request.getHeader("x-requested-with"));
    } 
    
    if (isLogin) {
      
      if (this.logger.isDebugEnabled())
      {
        this.logger.debug("[preHandle] menuId :: {}", 
            HandlerUtils.isInstance(handler, MainController.class) ? "Main 화면" : 
            "Main 화면 아님.");
      }
      
      if (HandlerUtils.isInstance(handler, MainController.class)) return true;
      
      if (isExcludeUrl(targetURI)) {
        return true;
      }
      String currentMenuId = (String)request.getAttribute("currentMenuId");
      EzMap menuMap = (EzMap)request.getAttribute("menuMap");
      
      if (this.logger.isDebugEnabled()) {
        this.logger.debug("[preHandle] currentMenuId :: {}", currentMenuId);
        this.logger.debug("[preHandle] menuMap :: {}", menuMap);
      } 
      
      if (menuMap.containsKey(currentMenuId)) {
        return true;
      }
      response.sendError(463, "권한이 없습니다.");

    
    }
    else {

      
      if (isExcludeUrl(targetURI)) {
        return true;
      }

      
      if (SessionUtil.isAjaxRequest()) {
        response.sendError(462, "need login");
      } else {
        String url = "/login/index.do";
        request.getRequestDispatcher(url).forward((ServletRequest)request, (ServletResponse)response);
      } 
    } 




    
    return false;
  }


  
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modeAndView) throws Exception {
    if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler)
      return; 
    if (this.logger.isDebugEnabled()) {
      StringBuilder sb = new StringBuilder();

      
      sb.append("\n[postHandle] <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
      if (modeAndView == null) {
        sb.append("\n ModelAndView is null(might be it is x-request).");
      } else {
        
        sb.append("\n#URL                  : ").append(request.getRequestURL());
        sb.append("\n#Handler(Controller)  : ").append(handler.getClass());
        sb.append("\n#View Name            : ").append(modeAndView.getViewName());
      } 

      
      sb.append("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
      this.logger.debug(sb.toString());
    } 
  }
}
