package com.example.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.app.dao.MemberDAO;

public class MemberCheckIdActionController implements Action { //액션컨트롤러, 아이디 중복확인, 인터페이스 

   @Override
   public Result execute(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException { 
      MemberDAO memberDAO = new MemberDAO(); //memberDAO 초기화
      PrintWriter out = resp.getWriter(); //resp 출력 필요함 초기화
      
      JSONObject jsonObject = new JSONObject(); //받아온 아이디값을 jsonObject 에 넣기 위하여 초기화 
      try {
         jsonObject.put("check", memberDAO.checkId(req.getParameter("memberIdentification"))); 
         //jsonObjectdp 넣기. check(논리형 값), memberDAO의 중복검사 메소드를 실행함(여기서 받아오는 값은 사용자가 입력한 meember 아이디를 겟파라미터로 가져옴) 
      } catch (JSONException e) {
         e.printStackTrace();
      }
      
      out.print(jsonObject.toString()); //그래서 이 jsonObject의 값을 tostring하고..
      out.close();
      
      return null;
   }

}











