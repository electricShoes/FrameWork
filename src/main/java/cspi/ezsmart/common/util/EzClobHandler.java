 package cspi.ezsmart.common.util;
 
 import java.io.StringReader;
 import java.sql.CallableStatement;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import org.apache.ibatis.type.JdbcType;
 import org.apache.ibatis.type.TypeHandler;
 
 
 
 public class EzClobHandler
   implements TypeHandler<Object>
 {
   public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
     String s = (String)parameter;
     StringReader reader = new StringReader(s);
     ps.setCharacterStream(i, reader, s.length());
   }
 
   
   public Object getResult(ResultSet rs, String columnName) throws SQLException {
     return rs.getString(columnName);
   }
 
   
   public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
     return cs.getString(columnIndex);
   }
 
   
   public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
     return rs.getString(columnIndex);
   }
 }

