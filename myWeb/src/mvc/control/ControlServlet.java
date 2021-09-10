package mvc.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.action.Action;

public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd = req.getParameter("cmd");
		if(cmd!=null) {
			ActionFactory factory = ActionFactory.getInstance();
			Action action = factory.getAction(cmd);
			ActionFoward af = action.execute(req, resp);
			if(af.isRedircet()) {
				resp.sendRedirect(af.getUrl());
			}else {
				RequestDispatcher rd = req.getRequestDispatcher(af.getUrl());
				rd.forward(req, resp);
			}
		}else {
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head><title>Error</title></head>");
			out.println("<body>");
			out.println("<h4>옳바른 요청이 아닙니다</h4>");
			out.println("<h4>http://localhost:8080/mvc/test.do?cmd=요청키워드</h4>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
