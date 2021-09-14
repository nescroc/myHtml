package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFormAction implements CommandAction {

	@Override
	public String requestPro(
			HttpServletRequest request,
			HttpServletResponse response) throws Throwable {//글삭제 폼
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		//해당 뷰에서 사용할 속성
		request.setAttribute("num", Integer.valueOf(num));
		request.setAttribute("pageNum", Integer.valueOf(pageNum));
		return "/board/deleteForm.jsp";//해당 뷰
	}

}
