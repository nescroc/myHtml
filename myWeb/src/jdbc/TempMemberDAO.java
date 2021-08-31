package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class TempMemberDAO {
	private ConnectionPool pool = null;

	public TempMemberDAO() {
		pool = ConnectionPool.getInstance();
	}

	public Vector<TempMemberVO> getMemberList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		Vector<TempMemberVO> vecList = new Vector<TempMemberVO>();
		try {
			conn = pool.getConnection();
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
					pool.releasedConnection(conn);
				} catch (SQLException sqle) {
				}
		}

		return vecList;
	}
}
