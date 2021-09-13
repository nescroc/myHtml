package mvcmem.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcmem.control.ActionFoward;
import mvcmem.model.StudentDAO;

public class DeleteProcAction implements Action {

	@Override
	public ActionFoward execute(
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		StudentDAO dao = StudentDAO.getInstance();
		HttpSession session = request.getSession();
		String loginID = (String) session.getAttribute("loginID");
		String pass = request.getParameter("pass");
		int result = dao.deleteMember(loginID, pass);
		if(result!=0) {
			session.invalidate();
		}
		request.setAttribute("result", result);
		return new ActionFoward("/mvcmem/deleteProc.jsp",false);
	}

}
