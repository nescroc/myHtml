package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;

//글수정 폼
public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(
			HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		BoardDAO dbPro = BoardDAO.getInstance();
		BoardVO article = dbPro.getArticle(num);
				//해당 뷰에서 사용할 속성
		request.setAttribute("pageNum", Integer.valueOf(pageNum));
		request.setAttribute("article", article);
		return "/board/udpateForm.jsp";
	}
}
