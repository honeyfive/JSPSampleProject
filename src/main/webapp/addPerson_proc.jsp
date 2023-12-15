<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="db.dao.PersonInfoDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addPerson_proc 페이지</title>
</head>
<body>
	<h1>addPerson_proc 페이지</h1>
	
	<%
		request.setCharacterEncoding("UTF-8"); //문자인코딩 설정
		String name = request.getParameter("name");
        System.out.println(name);
		
        PersonInfoDAO personInfoDAO = new PersonInfoDAO();
        
        int result = personInfoDAO.savePersonInfo(name);
        
        if(result > 0) {
    %>
    	<script>
    		alert('저장 성공');
    		location.href = 'index.jsp'; // 해당 경로로 페이지 이동
    	</script>
    <% 	
        } else {
    %>
    	<script>
    		alert('저장 실패');
    		//location.href = 'addPerson.jsp'; 
    		history.back(); //뒤로가기
    	</script>
    <%	
        }
	%>
</body>
</html>