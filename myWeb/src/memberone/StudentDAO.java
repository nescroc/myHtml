package memberone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jdbc.TempMemberVO;

public class StudentDAO {

	private Connection getConnection() {
		Connection conn = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/myOracle");
			conn = ds.getConnection();
		} catch (Exception E) {
			System.err.println("Connection 생성 실패");
		}
		return conn;
	}	
	
	public Vector<ZipcodeVO> zipcodeRead(String dong){
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		Vector<ZipcodeVO> vecList = new Vector<ZipcodeVO>();
		try {
			conn = getConnection();
			String strQuery = 
					"select * from zipcode where dong like'"+dong+"%'";
			pstmt = conn.prepareStatement(strQuery);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ZipcodeVO tempZipcode = new ZipcodeVO();
				tempZipcode.setZipcode(rs.getString("zipcode"));
				tempZipcode.setSido(rs.getString("sido"));
				tempZipcode.setGugun(rs.getString("gugun"));
				tempZipcode.setDong(rs.getString("dong"));
				tempZipcode.setRi(rs.getString("ri"));
				tempZipcode.setBunji(rs.getString("bunji"));
				vecList.addElement(tempZipcode);				
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			if (rs != null)try {rs.close();} catch (SQLException sqle) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException sqle) {}
			if (conn != null)try {conn.close();} catch (SQLException sqle) {}
		}
		return vecList;
	}
	
	public boolean idCheck(String id) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from student where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (!rs.next())
				result = false;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (rs != null)try {rs.close();} catch (SQLException sqle) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException sqle) {}
			if (conn != null)try {conn.close();} catch (SQLException sqle) {}
		}
		return result;
	}

	public Vector<TempMemberVO> getMemberList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		Vector<TempMemberVO> vecList = new Vector<TempMemberVO>();
		try {
			conn = getConnection();
			String strQuery = "select * from tempMember";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strQuery);
			while (rs.next()) {
				TempMemberVO vo = new TempMemberVO();
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setName(rs.getString("name"));
				vo.setMem_num1(rs.getString("mem_num1"));
				vo.setMem_num2(rs.getString("mem_num2"));
				vo.setE_mail(rs.getString("e_mail"));
				vo.setPhone(rs.getString("phone"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddress(rs.getString("address"));
				vo.setJob(rs.getString("job"));
				vecList.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}

		return vecList;
	}

	public boolean memberInsert(StudentVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag =false;
		String strQuery="insert into student values(?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(strQuery);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return true;
	}
}
