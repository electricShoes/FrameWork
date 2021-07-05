package cspi.ezsmart.common.interceptor;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ExcludesibleInterceptor
  extends HandlerInterceptorAdapter
{
  private Logger logger = LoggerFactory.getLogger(getClass());
  
  private ArrayList<String> excludeUrlPattern;
  
  private static AntPathMatcher antPathMatcher = null;
  
  public void setExcludeUrlPattern(ArrayList<String> excludeUrlPattern) {
    this.excludeUrlPattern = excludeUrlPattern;
  }






  
  protected boolean isExcludeUrl(String url) {
    if (url == null) return false; 
    String excludeUrl = "";
    if (this.excludeUrlPattern == null || this.excludeUrlPattern.size() == 0) return false; 
    for (int i = 0; i < this.excludeUrlPattern.size(); i++) {
      if (this.excludeUrlPattern.get(i) == null) return false; 
      excludeUrl = this.excludeUrlPattern.get(i);
      if (getPathMatcher().match(excludeUrl, url)) return true; 
    } 
    return false;
  }
  
  private PathMatcher getPathMatcher() {
    if (antPathMatcher == null) {
      antPathMatcher = new AntPathMatcher();
    }
    return (PathMatcher)antPathMatcher;
  }
}