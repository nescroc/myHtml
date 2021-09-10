<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.fileupload.FileUpload" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="java.io.*" %>
<html>
	<head>
		<title>파일 업로드 처리</title>
	</head>
	<body>
<%
	DiskFileItemFactory diskFileItemFactory=new DiskFileItemFactory();
	String temporaryDir="C://temp";
	File repositoryPath=new File(temporaryDir);
	diskFileItemFactory.setRepository(repositoryPath);
	//100K까지는 메모리에 저장
	diskFileItemFactory.setSizeThreshold(1024*100);
	ServletFileUpload fileUpload=new ServletFileUpload(diskFileItemFactory);
	if(ServletFileUpload.isMultipartContent(request)){
		/*FileItem의 리스트*/
		java.util.List fileItemList=fileUpload.parseRequest(request);
		for(int i=0;i<fileItemList.size();i++){
			FileItem fileItem=(FileItem)fileItemList.get(i);
			if(fileItem.isFormField()){//파일 이외의 파라미터
%>
		폼 파라미터 : <%=fileItem.getFieldName() %>=<%=fileItem.getString() %><br/>
<%		}else{//파일인 경우%>
		파일 : <%=fileItem.getFieldName() %>=<%=fileItem.getName() %>(<%=fileItem.getSize() %>bytes)<br/>
<%			if(fileItem.isInMemory()){ %>
				메모리에 저장<br/>
<%			}else{ %>
				디스크에 저장<br/>
<%			} %>
<%
				String filePath=application.getRealPath("upload");
				//업로드한 파일이 존재하는 경우
				if(fileItem.getSize()>0){
					int idx=fileItem.getName().lastIndexOf("\\");
					if(idx==-1){
						idx=fileItem.getName().lastIndexOf("/");
					}
					String fileName=fileItem.getName().substring(idx+1);
					try{
						File uploadFile=new File(filePath,fileName);
						fileItem.write(uploadFile);
					}catch(IOException ex){
						//예외처리
					}finally{
						fileItem.delete();
					}
				}
			}
		}
	}else{
%>
		인코딩 타입이 multipart/form-data가 아님.
<%}%>
	</body>
</html>