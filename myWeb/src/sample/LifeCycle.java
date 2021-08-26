package sample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public LifeCycle() {
        System.out.println("LifeCycle ����");
    }

	@Override
	public void init() throws ServletException {
		System.out.println("init() ȣ���");
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy() ȣ���");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service() ȣ���");
	}

}
