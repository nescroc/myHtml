package login;

import java.io.IOException;

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
		System.out.println(id +" "+pwd);
		//db���� ����� ���� ��ȸ... �̺κ��� �ڵ��� �����ؼ� ����� ����
		//db���� ��ȸ�� ������� ���̵� ����� ��ġ�ϴ� ���
		//Ŭ���̾�Ʈ�� ������ HttpSession��ü�� ���� ��Ų��
		String dbId = "admin";
		String dbPWD = "1234";
		if (dbId.equals(id)&&dbPWD.equals(pwd)) {
			//HttpSession��ü ���
			HttpSession session = request.getSession();
			//Ŭ���̾�Ʈ�� ������ HttpSession ��ü�� ����
			session.setAttribute("user", id);
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

}
