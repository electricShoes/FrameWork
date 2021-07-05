 package cspi.ezsmart.common.util;
 
 import cspi.ezsmart.common.aop.LogDaoLocal;
 import cspi.ezsmart.common.model.EzMap;
 import cspi.ezsmart.common.util.Utilities;
 import net.sf.log4jdbc.Slf4jSpyLogDelegator;
 import net.sf.log4jdbc.Spy;
 import net.sf.log4jdbc.tools.LoggingType;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 public class Log4JdbcFormatter extends Slf4jSpyLogDelegator
 {
   private LoggingType loggingType = LoggingType.DISABLED;
   private String margin = "";
   private String sqlPrefix = "SQL:";
   private Logger logger = LoggerFactory.getLogger(cspi.ezsmart.common.util.Log4JdbcFormatter.class);
   
   public void setMargin(int n) {
     this.margin = String.format("%1$" + n + "s", new Object[] { "" });
   }
 
   
   public String sqlOccured(Spy spy, String methodCall, String rawSql) {
     EzMap sqlInfo = LogDaoLocal.getSqlInfo();
     if (sqlInfo.containsKey("skip"))
       return ""; 
     if (this.loggingType == LoggingType.DISABLED) {
       return "";
     }
     
     if (this.loggingType != LoggingType.MULTI_LINE) {
       rawSql = rawSql.replaceAll("\r", "");
       rawSql = rawSql.replaceAll("\n", "");
     } 
     
     rawSql = rawSql.trim();
     
     String fromClause = " FROM ";
     String sql = rawSql;
     if (this.loggingType == LoggingType.MULTI_LINE) {
       
       sql = sql.replace("\r", "");
       String[] arr = sql.split("\n");
       StringBuffer bf = new StringBuffer();
       for (int i = 0; i < arr.length; i++) {
         if (arr[i] != null && arr[i].trim().length() > 0)
           bf.append(String.valueOf(arr[i]) + "\n"); 
       } 
       sql = bf.toString();
       if ("SELECT 1\n".equals(sql)) {
         return "";
       }
     } 
 
   if (this.loggingType == LoggingType.SINGLE_LINE_TWO_COLUMNS && 
       sql.startsWith("select")) {
       String from = sql.substring(sql.indexOf(" FROM ") + " FROM ".length());
       sql = String.valueOf(from) + "\t" + sql;
     } 
     
     if (this.logger.isInfoEnabled()) {
       EzMap menu = (EzMap)sqlInfo.get("currentMenu");
       String daoMethod = sqlInfo.getString("daoMethod");
       String log = "";
       String callController = sqlInfo.getString("callController");
       String callService = sqlInfo.getString("callService");
       String callMethod = sqlInfo.getString("callMethod");
       String reqUrl = (Utilities.getRequest() == null) ? null : Utilities.getRequest().getRequestURI();
       if (Utilities.isNotEmpty(Utilities.getLoginUserId())) {
         log = String.valueOf(log) + "로그인사용자      ====>      " + Utilities.getLoginUserId() + "\n";
       }
       if (Utilities.isNotEmpty(reqUrl)) {
         log = String.valueOf(log) + "호출 URL          ====>      " + reqUrl + "\n";
       }
       if (menu != null) {
         log = String.valueOf(log) + "실행 메뉴         ====>      " + menu.getString("menuUrl") + "(" + menu.getString("menuId") + ")\n";
       }
       if (Utilities.isNotEmpty(callController)) {
         log = String.valueOf(log) + "호출 Controller   ====>      " + callController + "\n";
       }
       if (Utilities.isNotEmpty(callService)) {
         log = String.valueOf(log) + "실행 Service      ====>      " + callService + "\n";
       }
       if (Utilities.isNotEmpty(callMethod)) {
         log = String.valueOf(log) + "실행 Dao          ====>      " + callMethod + "\n";
       }
       if (Utilities.isNotEmpty(daoMethod)) {
         log = String.valueOf(log) + "실행 매퍼         ====>      " + daoMethod + "\n";
       }
       if (Utilities.isNotEmpty(log)) {
         log = "\n==========================================================================================\n" + 
           log + 
           "==========================================================================================";
         this.logger.info(log);
       } 
     } 
     
     getSqlOnlyLogger().info(String.valueOf(this.sqlPrefix) + "\n" + this.margin + sql);
     
     if (!sqlInfo.containsKey("skip")) {
       LogDaoLocal.setSql(String.valueOf(this.margin) + sql);
     }
 
     
     return sql;
   }
 
   
   public String sqlOccured(Spy spy, String methodCall, String[] sqls) {
     String s = "";
     for (int i = 0; i < sqls.length; i++) {
       s = String.valueOf(s) + sqlOccured(spy, methodCall, sqls[i]) + String.format("%n", new Object[0]);
     }
     return s;
   }
   
   public LoggingType getLoggingType() {
     return this.loggingType;
   }
   
   public void setLoggingType(LoggingType loggingType) {
     this.loggingType = loggingType;
   }
   
   public String getSqlPrefix() {
     return this.sqlPrefix;
   }
   
   public void setSqlPrefix(String sqlPrefix) {
     this.sqlPrefix = sqlPrefix;
   }
 }
