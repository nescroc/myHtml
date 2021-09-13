package mvcmem.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcmem.control.ActionFoward;

public class DeleteFormAction implements Action {

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return new ActionFoward("/mvcmem/deleteForm.jsp",false);
	}

}
