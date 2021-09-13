package mvcmem.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcmem.control.ActionFoward;

public class IndexAction implements Action {

	@Override
	public ActionFoward execute(
			HttpServletRequest request, HttpServletResponse response) 
					throws IOException {
		System.out.println("IndexAction의 execute() 수행됨");
		return new ActionFoward("/mvcmem/index.jsp",false);
	}

}
