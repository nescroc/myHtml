package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheck extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		System.out.println(id);
		System.out.println(pwd);
		
		String dbPWD = "";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "mytest", "mytest");
			pstmt = con.prepareStatement("select pass from login where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println(2);
				dbPWD = rs.getString("pass");
				if (pwd.equals(dbPWD)) {
					HttpSession session = request.getSession();
					session.setAttribute("user", id);					
				}else {
					System.err.println("잘못된 비밀번호 입니다.");
				}
			}else {
				System.err.println("잘못된 아이디입니다.");				
			}
		} catch (ClassNotFoundException clfe) {
			clfe.printStackTrace();
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			try {if(rs!=null)rs.close();}catch(SQLException sqle) {}
			try {if(pstmt!=null)pstmt.close();}catch(SQLException sqle) {}
			try {if(con!=null)con.close();}catch(SQLException sqle) {}
		}
		response.sendRedirect("Login");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void idCheck(String id) throws Exception {

	}

}
