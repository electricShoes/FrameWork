package cspi.ezsmart.common.interceptor;

import cspi.ezsmart.common.aop.LogDaoLocal;
import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.model.EzMenuMap;
import cspi.ezsmart.common.model.ITreeVo;
import cspi.ezsmart.common.util.HandlerUtils;
import cspi.ezsmart.common.util.SessionUtil;
import cspi.ezsmart.common.util.Utilities;
import cspi.ezsmart.manage.controller.system.ErrorController;
import cspi.ezsmart.manage.service.MenuService;
import egovframework.rte.fdl.property.impl.EgovPropertyServiceImpl;
import java.util.Enumeration;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class MenuInterceptor extends HandlerInterceptorAdapter
{
  private Logger logger = LoggerFactory.getLogger(cspi.ezsmart.common.interceptor.MenuInterceptor.class);
  
  @Resource(name = "menuService")
  MenuService menuService;
  
  @Resource(name = "propertiesService")
  EgovPropertyServiceImpl propertiesService;
  
  private String setMenuId(HttpServletRequest request) throws Exception {
    this.logger.debug("[setMenuId] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
    
    if (request == null) return null;
    
    String urlSuffix = this.propertiesService.getString("urlSuffix", "");
    request.setAttribute("urlSuffix", urlSuffix);
    
    String cPath = request.getContextPath();
    
    if (this.logger.isDebugEnabled()) {
      this.logger.debug("[setMenuId] urlSuffix :: {} ", urlSuffix);
      this.logger.debug("[setMenuId] cPath :: {} ", cPath);
    } 
    
    if ("/".equals(cPath)) cPath = "";
    
    String menuUrl = null;
    String errorUrl = (String)request.getAttribute("javax.servlet.error.request_uri");
    String rurl = Utilities.isEmpty(errorUrl) ? request.getRequestURI() : errorUrl;
    String sPath = Utilities.isEmpty(errorUrl) ? request.getServletPath() : errorUrl.substring(cPath.length());
    
    if (this.logger.isDebugEnabled()) {
      this.logger.debug("[setMenuId] menuId :: {} ", menuUrl);
      this.logger.debug("[setMenuId] errorUrl :: {} ", errorUrl);
      this.logger.debug("[setMenuId] rurl :: {} ", rurl);
      this.logger.debug("[setMenuId] sPath :: {} ", sPath);
    } 
    
    if (Utilities.isNotEmpty(urlSuffix)) {
      if (sPath.indexOf(urlSuffix) > 0) sPath = sPath.substring(0, sPath.length() - urlSuffix.length()); 
      if (rurl.indexOf(urlSuffix) > 0) rurl = rurl.substring(0, rurl.length() - urlSuffix.length()); 
    } 
    request.setAttribute("urlSuffix", urlSuffix);
    
    if ("/".equals(sPath)) sPath = "";
    
    String manageContext = Utilities.getProperty("context.manage");
//    String rootMenuId = manageContext.equals(cPath) ? "0100000000" : "0200000000";
    
    System.out.println("manageContext :: "+manageContext);
    System.out.println("cPath :: "+cPath);
    
    
    
    String rootMenuId = "0100000000";
    menuUrl = sPath;
    
    if (this.logger.isDebugEnabled()) {
      this.logger.debug("[setMenuId] manageContext :: {} ", manageContext);
      this.logger.debug("[setMenuId] rootMenuId :: {} ", rootMenuId);
      this.logger.debug("[setMenuId] menuId :: {} ", menuUrl);
    } 
    
    if (menuUrl.startsWith("/")) menuUrl = menuUrl.substring(1);
    
    if (Utilities.isEmpty(menuUrl) || "index".equalsIgnoreCase(menuUrl)) {
      request.setAttribute("currentMenuId", Utilities.nullCheck(menuUrl));
      menuUrl = String.valueOf(rootMenuId.substring(0, 2)) + "01010000";
    }
    else {
      
      int idx = menuUrl.indexOf("/");
      if (idx > 0) menuUrl = menuUrl.substring(0, idx);
    
    } 
    
    EzMap so = new EzMap();
    
    so.put("rootMenuId", rootMenuId);
    so.put("userId", Utilities.getLoginUserId());
    
    so.put("langGb", Utilities.getLocale());
    
    EzMap menuMap = new EzMap();
    List<ITreeVo> menuTree = this.menuService.getUserMenuTree(so, menuMap);
//    List<ITreeVo> menuTree = null;
    
    EzMap urlMap = new EzMap();
    for (String key : menuMap.keySet()) {
      EzMenuMap menu = (EzMenuMap)menuMap.get(key);
      String url = menu.getString("menuUrl");
      if (Utilities.isNotEmpty(url)) {
        urlMap.put(url, menu);
      }
    } 
    EzMap currentMenu = (EzMap)urlMap.get(menuUrl);
    String menuId = (currentMenu == null) ? menuUrl : currentMenu.getString("menuUrl");
    request.setAttribute("currentMenuId", menuUrl);
    request.setAttribute("menuMap", urlMap);
    
    this.logger.debug("[setMenuId] menuId :: {} ", menuId);
    this.logger.debug("[setMenuId] menuMap ::11 {} ", urlMap);
    
    if (menuTree != null && menuTree.size() > 0) {
      ITreeVo iTreeVo = null; 
      request.setAttribute("menuTree", menuTree.get(0));
      EzMenuMap menu = (EzMenuMap)urlMap.get(menuId);
      
      request.setAttribute("currentMenu", currentMenu);
      
      EzMenuMap ezMenuMap1 = menu;
      
      System.out.println("ezMenuMap1 ::: "+ezMenuMap1);
      
      while (ezMenuMap1 != null) {
    	  
        request.setAttribute("level" + ezMenuMap1.getLevel() + "Menu", ezMenuMap1);
//        if (ezMenuMap1.getLevel() == 2) {
        if (ezMenuMap1.getLevel() == 4) {
          request.setAttribute("level" + ezMenuMap1.parent().getLevel() + "Menu", ezMenuMap1.parent());
          break;
        } 
        iTreeVo = ezMenuMap1.parent();
      } 
      
      request.setAttribute("topMenu", iTreeVo);
    } 
    
    EzMap sqlInfo = LogDaoLocal.getSqlInfo();
    sqlInfo.put("currentMenu", currentMenu);
    request.setAttribute("currentMenu", currentMenu);
    request.setAttribute("loginUserId", Utilities.getLoginUserId());
    request.setAttribute("urlSuffix", urlSuffix);
    request.setAttribute("systemId", rootMenuId);
    request.setAttribute("contextPath", cPath);
    
    String uniqId = Utilities.getUniqID(20);
    
    request.setAttribute("requestTicket", uniqId);
    
    if (this.logger.isDebugEnabled()) {
      this.logger.debug("[setMenuId] 마지막 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
      this.logger.debug("[setMenuId] currentMenu :: {} ", currentMenu);
      this.logger.debug("[setMenuId] loginUserId :: {} ", Utilities.getLoginUserId());
      this.logger.debug("[setMenuId] urlSuffix :: {} ", urlSuffix);
      this.logger.debug("[setMenuId] rootMenuId :: {} ", rootMenuId);
      this.logger.debug("[setMenuId] cPath :: {} ", cPath);
      this.logger.debug("[setMenuId] uniqId :: {} ", uniqId);
    } 
    
    request.setAttribute("infoMenu", "메뉴는 여기서 나온다.");
    return menuId;
  }



  
  private void setMenu(HttpServletRequest request) {}


  
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    super.postHandle(request, response, handler, modelAndView);
  }








  
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) return true;


    
    this.logger.debug("[preHandle] 요청 페이지 :: " + request.getAttribute("javax.servlet.forward.request_uri"));
    
    String urlSuffix = this.propertiesService.getString("urlSuffix", "");
    request.setAttribute("urlSuffix", urlSuffix);
    
    this.logger.debug("[preHandle] urlSuffix :: {} ", urlSuffix);
    this.logger.debug("[preHandle] 오류페이지야..? :: {} ", Boolean.valueOf(HandlerUtils.isInstance(handler, ErrorController.class)));
    
    if (HandlerUtils.isInstance(handler, ErrorController.class)) {
      for (Enumeration<String> enumeration = request.getAttributeNames(); enumeration.hasMoreElements(); ) {
        String attributeName = enumeration.nextElement();
        Object attribute = request.getAttribute(attributeName);
        
        this.logger.debug("[preHandle] {} :: {}", attributeName, attribute);
      } 
      return true;
    } 

    request.setAttribute("httpServletResponse", response);
    if (!Utilities.isLogin()) return true;
    
    setMenu(request);
    setMenuId(request);

    if (SessionUtil.isAjaxRequest()) return true;
    
    return true;
  }
}

