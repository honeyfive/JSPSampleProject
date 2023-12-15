<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="db.dao.PersonInfoDAO"%>
	<%@ page import="db.dto.PersonInfoDTO"%>
	<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp 페이지</title>
</head>
<body>

	<%
		PersonInfoDAO personInfoDAO = new PersonInfoDAO();
	
		List<PersonInfoDTO> personList = personInfoDAO.findPersonInfoList();
	%>
	
	<%@ include file="navigation.jsp" %>
	
	<h1>index.jsp 페이지</h1>
	
	<hr/>
	 <%
	 	for(PersonInfoDTO personInfo : personList){ 
	 %>
	 	<p><%=personInfo.getId()%> <a href="./personInfo.jsp?id=<%=personInfo.getId()%>"><%=personInfo.getName() %></a> </p>
	 <%
	 	}
	 %>
</body>
</html>