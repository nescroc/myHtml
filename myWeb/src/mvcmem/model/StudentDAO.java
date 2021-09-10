package mvcmem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StudentDAO {
	private static StudentDAO instance;

	private StudentDAO() {
	}

	public static StudentDAO getInstance() {
		if (instance == null) {
			synchronized (StudentDAO.class) {
				instance = new StudentDAO();
			}
		}
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/myOracle");
			conn = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public boolean idCheck(String id) {
		boolean result =true;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from student where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) 
				result=false;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(SQLException sqle) {}
			if(pstmt!=null)try {pstmt.close();}catch(SQLException sqle) {}
			if(conn!=null)try {conn.close();}catch(SQLException sqle) {}
		}		
		return result;
	}

	public Vector<ZipcodeVO> zipcodeRead(String dong){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<ZipcodeVO> vecList = new Vector<ZipcodeVO>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from zipcode where dong like '"+dong+"%'");
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(SQLException sqle) {}
			if(pstmt!=null)try {pstmt.close();}catch(SQLException sqle) {}
			if(conn!=null)try {conn.close();}catch(SQLException sqle) {}
		}
		return vecList;
	}
}
