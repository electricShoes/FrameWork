package cspi.ezsmart.common.util.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.core.io.Resource;

public class RefreshableSqlSessionFactoryBean  extends SqlSessionFactoryBean  implements DisposableBean
{
  private static final Log log = LogFactory.getLog(cspi.ezsmart.common.util.mybatis.RefreshableSqlSessionFactoryBean.class);
  private SqlSessionFactory proxy;
  private int interval = 500;

  
  private Timer timer;

  
  private TimerTask task;
  
  private Resource configLocation;
  
  private Resource[] mapperLocations;
  
  private Properties configurationProperties;

  
  public void setConfigurationProperties(Properties sqlSessionFactoryProperties) {
    super.setConfigurationProperties(sqlSessionFactoryProperties);
    this.configurationProperties = sqlSessionFactoryProperties;
  }


  
  private boolean running = false;

  
  private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
  private final Lock r = this.rwl.readLock();
  private final Lock w = this.rwl.writeLock();
  
  public void setConfigLocation(Resource configLocation) {
    super.setConfigLocation(configLocation);
    this.configLocation = configLocation;
  }
  
  public void setMapperLocations(Resource[] mapperLocations) {
    super.setMapperLocations(mapperLocations);
    this.mapperLocations = mapperLocations;
  }
  
  public void setInterval(int interval) {
    this.interval = interval;
  }
  
  public void refresh() throws Exception {
    if (log.isInfoEnabled()) {
      log.info("refreshing SqlSessionFactory.");
    }
    
    this.w.lock();
    
    try {
      super.afterPropertiesSet();
    } finally {
      this.w.unlock();
    } 
  }

  
  public void afterPropertiesSet() throws Exception {
    super.afterPropertiesSet();
    setRefreshable();
  }
  
  private void setRefreshable() {
    this.proxy = (SqlSessionFactory)Proxy.newProxyInstance(
        SqlSessionFactory.class.getClassLoader(), 
        new Class[] { SqlSessionFactory.class
//        }, (InvocationHandler)new Object(this));
        }, (InvocationHandler)new Object());
    
//    this.task = (TimerTask)new Object(this);
    this.task = (TimerTask)new Object();


    this.timer = new Timer(true);
    
    resetInterval();
  }
  
  private Object getParentObject() throws Exception {
    this.r.lock();
    
    try {
      return super.getObject();
    } finally {
      this.r.unlock();
    } 
  }
  
  public SqlSessionFactory getObject() {
    return this.proxy;
  }
  
  public Class<? extends SqlSessionFactory> getObjectType() {
    return (this.proxy != null) ? (Class)this.proxy.getClass() : SqlSessionFactory.class;
  }
  
  public boolean isSingleton() {
    return true;
  }
  
  public void setCheckInterval(int ms) {
    this.interval = ms;
    
    if (this.timer != null) {
      resetInterval();
    }
  }
  
  private void resetInterval() {
    if (this.running) {
      this.timer.cancel();
      this.running = false;
    } 
    
    if (this.interval > 0) {
      this.timer.schedule(this.task, 0L, this.interval);
      this.running = true;
    } 
  }
  
  public void destroy() throws Exception {
    this.timer.cancel();
  }
}
