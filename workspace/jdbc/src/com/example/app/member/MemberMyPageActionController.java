package com.example.app.member;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Action;
import com.example.app.Result;
import com.example.app.dao.MemberDAO;
import com.example.app.domain.MemberVO;

public class MemberMyPageActionController implements Action { //마이페이지 액션, 인터페이스 

   @Override
   public Result execute(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException {
      //Result 타입으로 서블릿 req, resp 받음 
      MemberDAO memberDAO = new MemberDAO(); //memberDAO 쓸거임
      Result result = new Result(); //Result 쓸거임 
      
      req.setAttribute("memberVO", memberDAO.select((Long)req.getSession().getAttribute("memberId"))); 
      //setAttribute memberVO 뭐야 이거 
      //memberId의 속성값을 가져오는데 이게 세션의 것인데.. long타입이 아니라서 형변환을 했고..
      //memberDAO의 select(userId)를 가져오는 거 아니야????????? 
      //session에 담은 건 초기화 안 됨, 
      
      result.setPath("myPage.jsp"); //마이페이지로 설정함
      //redirect=false라서 안 적음(기본값이 false니까) 
      return result; //Result 반환 
   }

}