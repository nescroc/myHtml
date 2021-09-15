<%@page import="java.util.Collections"%>
<%@ page  contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%! 
	String[]keywords={
		"AJAX",
		"AJAX 실전 프로그래밍",
		"자바",
		"자바프로그래밍",
		"자바 서버 페이지",
		"자바스터디",
		"자바서비스",
		"자바캔"
	};
	public List<String> search(String keyword){
		if(keyword==null||keyword.equals(""))
			return Collections.emptyList();
		keyword = keyword.toUpperCase();
		List<String> result = new java.util.ArrayList<String>(8);
		for(int i=0;i<keywords.length;i++){
			if((keywords[i]).startsWith(keyword)){
				result.add(keywords[i]);
			}			
		}
		return result;
	}
%>
<%
	request.setCharacterEncoding("utf-8");
	String keyword = request.getParameter("keyword");
	List<String> keywordList = search(keyword);
	out.print(keywordList.size());//제시어 개수 출력
	out.print("|");
	for(int i =0;i<keywordList.size(); i++){
		String key = keywordList.get(i);
		out.print(key);
		if(i<keywordList.size()-1){
			out.print(",");
		}
	}
%>