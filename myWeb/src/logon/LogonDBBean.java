package logon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LogonDBBean {
	private static LogonDBBean instance = new LogonDBBean();
	private LogonDBBean() {}
	public static LogonDBBean getInstance() {
		return instance;
	}
	private Connection getConnection()throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/myOracle");		
		return ds.getConnection();
	}
	public int userCheck(String id,String passwd)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs= null;
		String dbpasswd ="";
		int x= -1;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select passwd from TEMPMEMBER where id =?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(passwd)) {
					x=1;//인증성공
				}else {
					x=0;//인증실패 비밀번호 틀림
				}
			}else {
				x = -1;//해당 아이디없음
			}			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
			}finally {
				if(rs!=null)try {rs.close();}catch(SQLException sqle) {}
				if(pstmt!=null)try {pstmt.close();}catch(SQLException sqle) {}
				if(conn!=null)try {conn.close();}catch(SQLException sqle) {}
			}		
		return x;
	}
	
}
