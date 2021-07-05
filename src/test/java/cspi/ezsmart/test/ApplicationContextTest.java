package cspi.ezsmart.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring/context-datasource.xml")
public class ApplicationContextTest {
	
	@Autowired
	private DataSource dataSourceH2;
	
	
	@Test
    public void dataSource() {
		
		System.out.println("=======================");
		
	    Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSourceH2.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT CMPY_CD, EMP_NO, USER_ID, PWD, USER_NM FROM ZZ_USER_M");
			while(rs.next()){
				System.out.println("ZZ_USER_M USER_ID="+rs.getString("USER_ID")+", Name="+rs.getString("USER_NM")+", PWD="+rs.getString("PWD"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	    assertNotNull(dataSourceH2);
	    
	    System.out.println("=======================");
    }

	
	public static void main(String[] args) {
		
		System.out.println("테스트 입니다1234.");
	
	}

}
