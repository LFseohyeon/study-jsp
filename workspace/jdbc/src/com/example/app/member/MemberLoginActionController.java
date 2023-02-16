package com.example.app.member;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Action;
import com.example.app.Result;
import com.example.app.dao.MemberDAO;

public class MemberLoginActionController implements Action { //로그인 눌렀을 때 컨트롤러, 액션 인터페이스 
   @Override
   public Result execute(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException { 
      //Result 타입 반환, execute 는 서블릿 req와 resp를 매개변수로 함
      MemberDAO memberDAO = new MemberDAO(); //memberDAO 쓸거임
      Result result = new Result(); //Result 쓸거임
      String path = null; //경로 기니까 선언
      Long memberId = null; //로그인(아이디, 비밀번호 겟 파라미터로 받아온 값을 매개변수로 하는 거 너무 기니까 여기다 담으려고 선언) 
      
      memberId = memberDAO.login(req.getParameter("memberIdentification"), req.getParameter("memberPassword"));
      //이 아이디는 memberDAO에서 로그인한 결과임(아이디, 비밀번호는 입력값 받아온거)
      if(memberId != null) {  //위 memeberId 값이 null이 아닌 경우(로그인 성공한 경우임. null이면 로그인 실패)
         path = "/index.main"; //그럼 메인페이지로 이동함 
         req.getSession().setAttribute("memberId", memberId); //여기서 세션에 담음. 로그인 상태로 가야해서..? 속성을 memberId 상태로 담아줌.. 로그인 상태 유지를 위해서
      }else {
         path = "/login.member?login=false"; //로그인 실패했을 때
      }
      
      result.setPath(req.getContextPath() + path); //결과에 따라 위에서 설정한 경로를 다르게 함. 
      result.setRedirect(true); //setRedirect는 true.. 초기화시킴.. 
      
      return result;
   }
}










