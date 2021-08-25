package sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out = response.getWriter();
		try{
			out.println("<html><head><title>MyServlet File</title></head><body>");
			out.println("<h2 align='center'>My First Servlet File</h2><br>");
			out.println("<hr color='red'><br><p align='center'>now ");
			out.println(new java.util.Date());
			out.println("¿‘¥œ¥Ÿ..</p></body></html>");
		}finally{
			out.close();
		}
	}


}
