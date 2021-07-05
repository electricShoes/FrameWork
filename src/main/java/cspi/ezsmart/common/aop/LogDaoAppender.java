package cspi.ezsmart.common.aop;

import cspi.ezsmart.common.aop.LogDaoLocal;
import cspi.ezsmart.common.model.EzMap;
import cspi.ezsmart.common.util.Utilities;
import java.io.Serializable;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;




@Plugin(name = "LogDaoAppender", category = "Core", elementType = "appender", printObject = false)
public class LogDaoAppender
  extends AbstractAppender
{
  protected LogDaoAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions, Property[] properties) {
    super(name, filter, layout, ignoreExceptions, properties);
  }

  
  public void append(LogEvent ev) {
    EzMap sqlInfo = LogDaoLocal.getSqlInfo();
    if (sqlInfo.containsKey("skip"))
      return; 
    if ("jdbc.sqltiming".equals(ev.getLoggerName())) {
      String sql = ev.getMessage().toString();
      
      int index1 = sql.lastIndexOf("in");
      int index2 = sql.lastIndexOf("msec");
      if (index1 > 0 && index2 > 0) {
        String timing = sql.substring(index1 + 2, index2).trim();
        if (timing.length() > 0) {
          LogDaoLocal.setTiming(timing);
        }
      } 
    } 
    if ("jdbc.sqlonly".equals(ev.getLoggerName()) && 
      Utilities.isEmpty(LogDaoLocal.getSqlInfo().getString("logSql"))) {
      String sql = ev.getMessage().toString();
      int index = sql.indexOf("SQL:");
      if (index > -1) {
        sql = sql.substring(index + 5);
      }
      LogDaoLocal.setSql(sql);
    } 
  }

  
  @PluginFactory
  public static cspi.ezsmart.common.aop.LogDaoAppender createAppender(@PluginAttribute("name") String name, @PluginElement("Layout") Layout<? extends Serializable> layout, @PluginElement("Filter") Filter filter, @PluginElement("Property") Property[] properties, @PluginAttribute("otherAttribute") String otherAttribute) {
    PatternLayout patternLayout = null;
    if (name == null) {
      LOGGER.error("No name provided for LogDaoAppender");
      return null;
    } 
    if (layout == null) {
      patternLayout = PatternLayout.createDefaultLayout();
    }
//    return new cspi.ezsmart.common.aop.LogDaoAppender(name, filter, (Layout<? extends Serializable>)patternLayout, true, properties);
    return new cspi.ezsmart.common.aop.LogDaoAppender(name, filter, (Layout<? extends Serializable>)patternLayout, true, properties);
  }
}

