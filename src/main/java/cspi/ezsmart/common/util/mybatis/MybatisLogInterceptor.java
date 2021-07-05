package cspi.ezsmart.common.util.mybatis;

import cspi.ezsmart.common.util.EzStringUtils;
import java.lang.reflect.Field;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercepts({@Signature(type = StatementHandler.class, method = "update", args = {Statement.class}), @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})})
public class MybatisLogInterceptor
  implements Interceptor
{
  private Logger logger = LoggerFactory.getLogger(getClass());



  
  public Object intercept(Invocation invocation) throws Throwable {
    Object returnValue = null;
    if (this.logger.isDebugEnabled()) {
      sqlLogging(invocation);
    }
    
    long start = System.currentTimeMillis();
    returnValue = invocation.proceed();
    long end = System.currentTimeMillis();
    long time = end - start;
    if (this.logger.isDebugEnabled()) {
      this.logger.debug(" >> Executing Time: {}ms\n", Long.valueOf(time));
    }
    
    return returnValue;
  }

  
  private void sqlLogging2(Object[] getArgs) {
    MappedStatement mappedStatement = (MappedStatement)getArgs[0];
  }

  
  private void sqlLogging(Invocation invocation) throws Throwable {
    StatementHandler handler = (StatementHandler)invocation.getTarget();
    
    int numberOfParameters = 0;
    String parameterType = null;
    StringBuffer sbParameters = new StringBuffer();
    
    BoundSql boundSql = handler.getBoundSql();

    
    String sql = boundSql.getSql();

    
    Object param = handler.getParameterHandler().getParameterObject();
    
    if (param == null) {
      sql = replaceParameter2Value(sql, "''");
      parameterType = "NULL";
    } else {
      parameterType = param.getClass().getName();
      
      if (param instanceof Integer || param instanceof Long || param instanceof Float || param instanceof Double) {
        sql = replaceParameter2Value(sql, param.toString());
        logParam(sbParameters, numberOfParameters, param, "N/A");
        numberOfParameters++;
      } else if (param instanceof String) {
        
        sql = replaceParameter2Value(sql, "'" + param + "'");
        logParam(sbParameters, numberOfParameters, param, "N/A");
        numberOfParameters++;
      } else if (param instanceof Map) {

        List<ParameterMapping> paramMapping = boundSql.getParameterMappings();
        
        for (ParameterMapping mapping : paramMapping) {
          String propValue = mapping.getProperty();
          Object value = ((Map)param).get(propValue);
          if (value == null) {
            sql = replaceParameter2Value(sql, "NULL");
            logParam(sbParameters, numberOfParameters, null, propValue);
          } else if (value instanceof String) {
            sql = replaceParameter2Value(sql, "'" + value + "'");
            logParam(sbParameters, numberOfParameters, value, propValue);
          } else {
            sql = replaceParameter2Value(sql, "'" + value + "'");
            logParam(sbParameters, numberOfParameters, value, propValue);
          } 
          numberOfParameters++;
        
        }

      
      }
      else {

        
        List<ParameterMapping> paramMapping = boundSql.getParameterMappings();
        
        Class<? extends Object> paramClass = (Class)param.getClass();
        parameterType = paramClass.getName();
        
        for (ParameterMapping mapping : paramMapping) {
          String propValue = mapping.getProperty();
          Field field = paramClass.getDeclaredField(propValue);
          field.setAccessible(true);
          Class<?> javaType = mapping.getJavaType();
          
          Object value = field.get(param);
          if (value == null) {
            sql = replaceParameter2Value(sql, "NULL");
          } else if (String.class == javaType) {
            sql = replaceParameter2Value(sql, "'" + value + "'");
          } else {
            sql = replaceParameter2Value(sql, value.toString());
          } 
          numberOfParameters++;
          logParam(sbParameters, numberOfParameters, value, field.getName());
        } 
      } 
    } 
    
    StringBuilder sb = new StringBuilder();
    sb.append("\n=====================================================================\n");
    sb.append(" >> Method Name: {}\n");
    sb.append(" >> ParameterType: {}\n");
    sb.append(" >> Parameters(Total {} ea) \n{} \n");
    sb.append(" >> Executing SQL: \n");
    sb.append("{} \n ");
    sb.append("=====================================================================");
    this.logger.debug(sb.toString(), new Object[] { invocation.getMethod().getName(), parameterType, Integer.valueOf(numberOfParameters), sbParameters, 
          sql });
  }

  
  private void logParam(StringBuffer sb, int index, Object value, String name) {
    String className = null;
    if (value == null) {
      className = " NULL ";
    } else {
      className = value.getClass().getSimpleName();
    } 
    sb.append("    " + index + "(" + className + ",'" + name + "'): \t'" + value + "'\n");
  }
  
  public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId, long time) {
    String sql = showSql(configuration, boundSql);
    StringBuilder str = new StringBuilder(100);
    str.append(sqlId);
    str.append("\n");
    str.append(sql);
    str.append(":");
    str.append(time);
    str.append("ms");
    return str.toString();
  }
  
  private static String getParameterValue(Object obj) {
    String value = null;
    if (obj instanceof String) {
      value = "'" + obj.toString() + "'";
    } else if (obj instanceof Date) {
      DateFormat formatter = DateFormat.getDateTimeInstance(2, 2);
      value = "'" + formatter.format(new Date()) + "'";
    }
    else if (obj != null) {
      value = obj.toString();
    } else {
      value = "";
    } 
    
    return value;
  }
  
  public static String showSql(Configuration configuration, BoundSql boundSql) {
    Object parameterObject = boundSql.getParameterObject();
    List<ParameterMapping> parameterMappings = boundSql
      .getParameterMappings();
    
    String sql = boundSql.getSql();
    if (parameterMappings.size() > 0 && parameterObject != null) {
      TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
      if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
        sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
      } else {
        MetaObject metaObject = configuration
          .newMetaObject(parameterObject);
        for (ParameterMapping parameterMapping : parameterMappings) {
          String propertyName = parameterMapping.getProperty();
          if (metaObject.hasGetter(propertyName)) {
            Object obj = metaObject.getValue(propertyName);
            sql = sql.replaceFirst("\\?", getParameterValue(obj)); continue;
          }  if (boundSql.hasAdditionalParameter(propertyName)) {
            Object obj = boundSql
              .getAdditionalParameter(propertyName);
            sql = sql.replaceFirst("\\?", getParameterValue(obj));
          } 
        } 
      } 
    } 
    return sql;
  }







  
  private String replaceParameter2Value(String sql, String value) {
    return EzStringUtils.replaceFirst(sql, "?", value);
  }


  
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }
  
  public void setProperties(Properties properties) {}
}
