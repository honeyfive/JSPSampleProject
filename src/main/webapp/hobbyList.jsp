<%@page import="db.dao.HobbyDAO"%>
<%@ page import="db.dto.HobbyDTO"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
table, td, th{
border : 1px solid black;
border-collapse: collapse;
text-align: center;
}
</style>
</head>
<body>

<%@ include file="navigation.jsp" %>
	
	<h1>취미 목록</h1>

	<%
	HobbyDAO hobbyDAO = new HobbyDAO();
	List<HobbyDTO> hobbyList = hobbyDAO.selectAllTHobbyList();
	%>

	<table>
		<tr>
			<th>아이디</th>
			<th>순번</th>
			<th>취미</th>
			<th>선호도</th>
		</tr>
		<%
		if(hobbyList != null) {
		for (HobbyDTO hobby : hobbyList) {
		%>
		<tr>
			<td><p><%=hobby.getId()%></p></td>
			<td><p><%=hobby.getNo()%></p></td>
			<td><p><%=hobby.getHobby()%></p></td>
			<td><p><%=hobby.getPrefer()%></p></td>
			<%
			}
		}
			%>
			
		</tr>


	</table>

</body>
</html>