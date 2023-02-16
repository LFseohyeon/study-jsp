package com.example.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.member.MemberJoinActionController;
import com.example.app.member.MemberLoginActionController;
import com.example.app.member.MemberMyPageActionController;

public class MemberFrontController extends HttpServlet{ //fc, 서블릿
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //doget 메소드, req와 resp 를 받음 
      String uri = req.getRequestURI(); //uri: 경로 받아와야 해서 선언 
      String target = uri.replaceAll(req.getContextPath() + "/", "").split("\\.")[0]; //target에서 마지막 경로 빼내기. /로 구분해서 공백으로 대체, 마지막 .도 제거
      Result result = null; //result 쓸거임 
      
      if(target.equals("join")) { //만약 경로가 join 이라면 
         result = new Result(); //result 
         result.setPath("/join.jsp"); //join 페이지로 이동하기
         result.setRedirect(false); //이거 왜 false임? 
         
      }else if(target.equals("checkIdAction")) { //아이디 중복검사라면
         new MemberCheckIdActionController().execute(req, resp); //아이디 중복검사 액션으로
         
      }else if(target.equals("joinAction")) { //회원가입 이라면 
         result = new MemberJoinActionController().execute(req, resp);
         
      }else if(target.equals("login")) { //로그인이라면
         result = new Result(); //결과 
         result.setPath(req.getContextPath() + "/login.jsp"); //로그인 페이지로 이동하기 
         result.setRedirect(true);
         
      }else if(target.equals("loginAction")) { //로그인 액션이라면 
         result = new MemberLoginActionController().execute(req, resp); //로그인 액션 연산해서 리턴
      }else if(target.equals("myPageAction")) {
         result = new MemberMyPageActionController().execute(req, resp);
      }else {
         
      }
      
      if(result != null) { //만약 결과가 null이 아니라면 ....
         if(result.isRedirect()) { //result의 redirect가 true라면
            resp.sendRedirect(result.getPath()); //result의 Path가...뭐죠?
         }else { //null이라면 
            req.getRequestDispatcher(result.getPath()).forward(req, resp); //
         }
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
   
}




