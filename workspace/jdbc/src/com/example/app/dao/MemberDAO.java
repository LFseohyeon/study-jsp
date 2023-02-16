package com.example.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.app.domain.MemberVO;

public class MemberDAO {
   Connection connection; //커넥션
   PreparedStatement preparedStatement;
   ResultSet resultSet; //결과값 담아줘야 함 
   
//   회원가입
   public void join(MemberVO memberVO) { //회원가입 해야하니까 memberVO 받아오기
      String query = "INSERT INTO TBL_MEMBER(MEMBER_ID, MEMBER_IDENTIFICATION, MEMBER_PASSWORD) " //모든 정보값을
            + "VALUES(SEQ_MEMBER.NEXTVAL, ?, ?)"; //아이디 일련번호 부여하고 입력받기
      try {
         connection = DBConnecter.getConnection(); //db 연결 
         preparedStatement = connection.prepareStatement(query); //쿼리에 넣기
         preparedStatement.setString(1, memberVO.getMemberIdentification()); //멤버VO의 id셋
         preparedStatement.setString(2, memberVO.getMemberPassword()); //멤버VO의 pw셋
         preparedStatement.executeUpdate(); //넣기
      } catch (SQLException e) {
         System.out.println("join(MemberVO) SQL문 오류"); //오류
         e.printStackTrace();
      } catch (Exception e) {
         System.out.println("join(MemberVO) 오류"); //올 
         e.printStackTrace();
      } finally { //예외처리 오류
         try {
            if(preparedStatement != null) {
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
         }
      }
   }
//   로그인
   public Long login(String memberIdentification, String memberPassword) { //세션범위에서 userId 반환해줘야 함. long 리턴. 아이디 비번 매개변수
      Long memberId = null; //id 담을 값 선언 
      String query = "SELECT MEMBER_ID FROM TBL_MEMBER WHERE MEMBER_IDENTIFICATION = ? AND MEMBER_PASSWORD = ?"; //아이디번호 가져오는 쿼리(id, pw 입력)
      try {
         connection = DBConnecter.getConnection(); //db 연결
         preparedStatement = connection.prepareStatement(query); //쿼리 넣기
         preparedStatement.setString(1, memberIdentification); //아이디 세팅
         preparedStatement.setString(2, memberPassword); //패스워드 세팅
         resultSet = preparedStatement.executeQuery(); //아이디, 비밀번호값을 resultSet에 넣기
         
         if(resultSet.next()) { //만약 결과로 받은 값이 문제 없다면(로그인 성공이라면)
            memberId = resultSet.getLong(1); //1번값(userId, 일련번호) 가져와서 memeberId에 넣기 
         }
         
      } catch (SQLException e) {
         System.out.println("login(String) SQL문 오류");
         e.printStackTrace();
      } catch (Exception e) {
         System.out.println("login(String) 오류");
         e.printStackTrace();
      } finally {
         try {
            if(resultSet != null) {
               resultSet.close();
            }
            if(preparedStatement != null) {
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
         }
      }
            
      return memberId; //memberId 로 리턴하기
   }
   
//   마이페이지
   public MemberVO select(Long memberId) { //마이페이지이니까 memberVO로 반환하기. 매개변수는.. 일련번호(로그인 상태라고 봐야하지 않나?) 로 받기
      MemberVO memberVO = null; //memberVO 타입의 memberVO 
      String query = "SELECT MEMBER_ID, MEMBER_IDENTIFICATION, MEMBER_PASSWORD FROM TBL_MEMBER WHERE MEMBER_ID = ?"; //memberID번호로 정보 조회
      try {
         connection = DBConnecter.getConnection(); //db 연결
         preparedStatement = connection.prepareStatement(query); //쿼리 연결
         preparedStatement.setLong(1, memberId); //번호 넣기 
         resultSet = preparedStatement.executeQuery(); //쿼리 resultSet에 넣기
         
         if(resultSet.next()) { //결과값이 true 이면, 로그인 상태가 정상이라면 
            memberVO = new MemberVO(); //memberVO 초기화 
            memberVO.setMemberId(resultSet.getLong(1)); //memberVO에다가 memberId와 일치하는 회원의 일련번호를 가져와서 세팅
            memberVO.setMemberIdentification(resultSet.getString(2));//memberVO에다가 memberIdenti~와 일치하는 회원의 아이디를 가져와서 세팅
            memberVO.setMemberPassword(resultSet.getString(3)); //memberVO에다가 memberIdenti~와 일치하는 회원의 비밀번호를 가져와서 세팅
         }
         
      } catch (SQLException e) {
         System.out.println("join(MemberVO) SQL문 오류");
         e.printStackTrace();
      } catch (Exception e) {
         System.out.println("join(MemberVO) 오류");
         e.printStackTrace();
      } finally {
         try {
            if(resultSet != null) {
               resultSet.close();
            }
            if(preparedStatement != null) {
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
         }
      }
      return memberVO;
   }
   
//   아이디 중복검사
   public boolean checkId(String memberIdentification) { //중복검사, id 넣기 
      boolean check = false; //T/F 타입이니까 논리형 변수 선언 
      String query = "SELECT MEMBER_ID FROM TBL_MEMBER WHERE MEMBER_IDENTIFICATION = ?"; //멤버 아이디 넣거어서 일련번호 조회 
      try {
         connection = DBConnecter.getConnection(); //연결
         preparedStatement = connection.prepareStatement(query); //쿼리에 넣기
         preparedStatement.setString(1, memberIdentification); //아이디 넣기
         resultSet = preparedStatement.executeQuery(); //resultSet에 넣기
         
         check = resultSet.next(); //이 check에 결과값을... 넣기....(뭐야?) 다음 줄애.... 뭐야:? 결과가 트루면!!! 
         
      } catch (SQLException e) {
         System.out.println("join(MemberVO) SQL문 오류");
         e.printStackTrace();
      } catch (Exception e) {
         System.out.println("join(MemberVO) 오류");
         e.printStackTrace();
      } finally {
         try {
            if(resultSet != null) {
               resultSet.close();
            }
            if(preparedStatement != null) {
               preparedStatement.close();
            }
            if(connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
         }
      }
      
      return check; //check 반환 
   }
}











