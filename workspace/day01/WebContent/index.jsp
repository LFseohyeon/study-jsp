<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 디렉티브 태그  directive -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
   <h1>JSP로 만든 페이지 </h1>
   <!-- <a href="/MyServlet">Servlet 페이지로 이동</a> -->
   <!--/절대경로 /안붙히면 상대(현재에서 이 뒤에 붙여라)  -->
   <form action = "MyServlet">
      <label>
         이름 <input type="text" name="name">
      </label>
      <input type="submit" value="완료">
   </form>
</body>
</html>