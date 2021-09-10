package boardone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	private static BoardDAO instance = null;
	private BoardDAO() {}
	
	public static BoardDAO getInstance() {
			if (instance==null) {
				synchronized (BoardDAO.class) {
					instance = new BoardDAO();
				}
			}
			return instance;
	}
	
	//게시글 db입력 1단계
	public void insertArticle(BoardVO article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int num = article.getNum();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		int number = 0;
		String sql = "";
		try {			
			conn = ConnUtil.getConnection();
			number = maxNum();
			if(num!=0) {//답변글일경우				
				sql="update board set step=step+1 where ref=? and step>?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				pstmt.executeUpdate();
				step = step +1;
				depth = depth +1;				
			}else {//새 글일 경우
				ref=number;
				step = 0;
				depth =0;				
			}//쿼리를 작성
			
			insertQueryAction(article,ref,step,depth);			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try{pstmt.close();}catch(SQLException sqle) {}
			if(conn!=null)try{conn.close();}catch(SQLException sqle) {}
		}
	}
	
	//실제 최종 입력 3단계
	public void insertQueryAction(BoardVO article, int ref,
									int step, int depth) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into board(num,writer,email,subject,"
				+ "pass,regdate,ref,step,"
				+ "depth,content,ip) values(board_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
		
		conn = ConnUtil.getConnection();		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, article.getWriter());
		pstmt.setString(2, article.getEmail());
		pstmt.setString(3, article.getSubject());
		pstmt.setString(4, article.getPass());
		pstmt.setTimestamp(5, article.getRegdate());
		pstmt.setInt(6, ref);
		pstmt.setInt(7, step);
		pstmt.setInt(8, depth);
		pstmt.setString(9, article.getContent());
		pstmt.setString(10, article.getIp());
		pstmt.executeUpdate();
		
		if(pstmt!=null)try{pstmt.close();}catch(SQLException sqle) {}
		if(conn!=null)try{conn.close();}catch(SQLException sqle) {}
		
	}
	
	//2단계 
	public int maxNum() throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int result = 0;
		
		conn = ConnUtil.getConnection();
		pstmt = conn.prepareStatement("select max(num) from board");
		rs = pstmt.executeQuery();
		
		if(rs.next()) 
			result =rs.getInt(1)+1;
		else 
			result =1;
		
		if(rs!=null)rs.close();
		if(pstmt!=null)pstmt.close();
		if(conn!=null)conn.close();
		
		return result;
	}
	
	public int getArticleCount() {
		
		Connection conn= null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;			
		int x =0;
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch (SQLException e) {}
			if(pstmt!=null)try {pstmt.close();}catch (SQLException e) {}
			if(conn!=null)try {conn.close();}catch (SQLException e) {}
		}		
		return x;
	}
	
	public List<BoardVO> getArticles(int start, int end){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> articleList = null;
		try {
			conn = ConnUtil.getConnection();
			/* <수정2> */
			pstmt = conn.prepareStatement("select * from "
					+ "(select rownum rnum, num, writer,"
					+ " email, subject, pass, regdate, "
					+ "readcount, ref, step, depth, content,"
					+ " ip from (select * from board order by ref desc,"
					+ " step asc)) where rnum>=? and rnum<=?");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			//수정 <3>
			rs = pstmt.executeQuery();
			if(rs.next()) {
				articleList = new ArrayList<BoardVO>(end-start+1);
				do {
					BoardVO article = new BoardVO();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPass(rs.getString("pass"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					articleList.add(article);
				}while(rs.next());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try{rs.close();}catch (SQLException sqle) {}
			if(pstmt!=null) try{pstmt.close();}catch (SQLException sqle) {}
			if(conn!=null) try{conn.close();}catch (SQLException sqle) {}
		}
		return articleList;
	}
	
	public BoardVO getArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=  null;
		BoardVO article = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(
					"select * from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new BoardVO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPass(rs.getString("pass"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
			}
		} catch (SQLException sqle) {
				sqle.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(SQLException sqle) {}
			if(pstmt!=null)try {pstmt.close();}catch(SQLException sqle) {}
			if(conn!=null)try {conn.close();}catch(SQLException sqle) {}
		}		
		return article;
	}
	
	public void increaseReadcount(int num) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		conn = ConnUtil.getConnection();
		pstmt = conn.prepareStatement("update board set "
				+ "readcount = readcount+1 where num = ?");
		pstmt.setInt(1, num);
		pstmt.executeUpdate();
		
		if (pstmt!=null)pstmt.close();
		if (conn!=null)conn.close();
	}
	
	public int updateArticle(BoardVO article) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String strQuery = "";
		String dbPass = "";
		int result = -1;
		try {
		
			dbPass = getdbPass(article.getNum());		
			
			if(article.getPass().equals(dbPass)) {
				strQuery="update board set writer=?,email=?,subject=?,content=? where num=?";
				conn = ConnUtil.getConnection();
				pstmt = conn.prepareStatement(strQuery);
				pstmt.setString(1, article.getWriter());
				pstmt.setString(2, article.getEmail());
				pstmt.setString(3, article.getSubject());
				pstmt.setString(4, article.getContent());
				pstmt.setInt(5, article.getNum());
				pstmt.executeUpdate();
				result=1;
			}else {
				result=0;
			}
		
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException sqle) {}
			if(conn	!=null)try {conn.close();}catch(SQLException sqle) {}
		}
		
		
		return result;
	}
	
	public String getdbPass(int num)throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=  null;
		
		String dbPass ="";
		String strQuery = "select pass from board where num = ?";
		
		conn = ConnUtil.getConnection();
		pstmt = conn.prepareStatement(strQuery);
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			dbPass = rs.getString("pass");			
		}
		
		if(rs!=null)rs.close();
		if(pstmt!=null)pstmt.close();
		if(conn!=null)conn.close();
		
		return dbPass;		
	}
	
	public int deleteArticle(int num,String pass) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String dbPass = "";
		try {
			dbPass = getdbPass(num);
			if(dbPass.equals(pass)) {
				  conn = ConnUtil.getConnection(); 
				  pstmt = conn.prepareStatement("delete from board where num = ?");
				  pstmt.setInt(1, num);
				  pstmt.executeUpdate();
				  result = 1; //성공
			}else {
				result = 0;
			}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException sqle) {}
			if(conn	!=null)try {conn.close();}catch(SQLException sqle) {}
		}		
		return result;
	}

}
