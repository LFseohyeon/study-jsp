<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
   <form action="joinAction.member" name="joinForm" method="post"> <!-- joinAction.jsp로 주소 가려서 보낼거임  -->
      <div>
         <input type="text" name="memberIdentification" placeholder="아이디"> <!-- 아이디 입력칸   -->
         <p id="result"></p>
      </div>
      <div>
         <input type="text" name="memberPassword" placeholder="비밀번호"> <!-- 비밀번호 입력칸   -->
      </div>
      <!-- class명 또는 id명은 예약어를 사용하지 말자! -->
      <input id="finish" type="button" value="완료"> <!-- 로그인 완료칸  -->
   </form>      
</body>
<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script> <!-- jQuery -->
<script>
   globalThis.check = false; /* check.. */

   let dom = (function(){ //dom. 
      function changeResult(result){ //결과 바꾸기, result로 나온 값 
         const $p = $("#result"); //안내문구 선언
         result = JSON.parse(result); //검색한 데이터값이 json이니까 문자열로 바꿔줌 
         
         if(!result.check){ //아이디가 db에 없다면
            check = true; //true로 바꾸고 
            $p.css("color", "blue"); //p 출력
            $p.text("사용 가능한 아이디입니다.")
         }else{ //-> result.check값인데... 이게 뭔데? 결과값이지만.. 
            check = false; 
            $p.css("color", "red");
            $p.text("중복된 아이디입니다.")
         }
      }
      
      return {changeResult: changeResult};
   })();
   
   let service = (function(){ //service
      function checkId(callback){ //아이디 확인하는 콜백함수 
         $.ajax({ //ajax 
            url: "${pageContext.request.contextPath}/checkIdAction.member", //${}가 공통 경로/ch~.member로 감
            data: {"memberIdentification": $("input[name='memberIdentification']").val()}, //빈 페이지에 아이디를 보냄. 입력한 아이디의 입력 값을...
            success: function(result){ //완료되면 함수 실행 
               if(callback){
                  callback(result);
               }
            }
         });
      }
      
      return {checkId: checkId};
   })();
   
   $("input[name='memberIdentification']").on("blur", function(){ //id 입력값은 
      globalThis.check = false; //check 값 
      service.checkId(dom.changeResult); //서비스
   });

   $("#finish").on("click", function(){ //finish 버튼을 클릭했을 때 함수 실행 
      const $identification = $("input[name='memberIdentification']"); //id 변수
      const $password = $("input[name='memberPassword']"); //비밀번호 변수 
      
      if(!$identification.val()){ //유효하지 않으면 
         return;
      }
      
      if(!$password.val()){ //유효하지 않으면 
         return;
      }
      
      if(!globalThis.check) { //유효하지 않으면 
         return;
      }
      
      $password.val(btoa($password.val())); //비밀번호 암호화 
      
      joinForm.submit(); //완료함 
   });
</script>
</html>







