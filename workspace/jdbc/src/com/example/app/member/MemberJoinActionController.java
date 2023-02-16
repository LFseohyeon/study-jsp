package com.example.app.member;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Action;
import com.example.app.Result;
import com.example.app.dao.MemberDAO;
import com.example.app.domain.MemberVO;

public class MemberJoinActionController implements Action{ //action 인터페이스, 가입 액션 컨트롤러 
   @Override
   public Result execute(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException {
      //result 타입을 반환, execute 실행, 서블릿의 req, resp의 값을 받아옴 
      
      MemberVO memberVO = new MemberVO(); //memberVO 써야함 
      MemberDAO memberDAO = new MemberDAO();  //memberDAO 써야함
      Result result = new Result(); //Result 써야함 
      
      memberVO.setMemberIdentification(req.getParameter("memberIdentification")); //memberVO에 아이디 받아온 값을 세팅함 
      memberVO.setMemberPassword(new String(Base64.getEncoder().encode(req.getParameter("memberPassword").getBytes())));
      //memberVO의 비밀번호 자리에 암호화 ㄱㄱ한 비밀번호를 넣음  
      memberDAO.join(memberVO); //dao의 join 메소드 실행 
      
      result.setPath(req.getContextPath() + "/login.member"); //결과: req의 공통 경로+ 회원가입 후 login 페이지로 이동함 
      result.setRedirect(true); //Redirect 실행
      
      return result; //결과값 반환 
   }
}











