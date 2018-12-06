<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>회원가입 화면</title>
<style type="text/css">
table {
	margin-left: auto;
	margin-right: auto;
	border: 3px solid skyblue;
}

td {
	border: 1px solid skyblue
}

#title {
	background-color: skyblue
}
</style>
<!-- jQuery와 Postcodify를 로딩한다 -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
 
<script> 
$(function() { $("#postcodify_search_button").postcodifyPopUp(); }); 
</script>
<script type="text/javascript">
	function checkValue() {
		if (!document.userInfo.id.value) {
			alert("아이디 입력");
			return false;
		}

		if(form.idDuplication.value != "idCheck"){
			alert("중복체크 눌러라");
			return false;
		}
		
		if (!document.userInfo.password.value) {
			alert("비밀번호를 입력");
			return false;
		}

		if (document.userInfo.password.value != document.userInfo.passwordcheck.value) {
			alert("다른 비번 치셨어요....");
			return false;
		}
	}

	function goFirstForm() {
		location.href = "mainForm.do";
	}
	
	function openIdChk(){
		window.name = "parentForm";
		window.open("member/idCheckForm.jsp",
				"chkForm", "width=500, height=300, resizeable=no, scrollbars=no");
	}
	
	function inputIdChk(){
		document.userInfo.idDuplication.value="idUncheck";
	}
	
	
</script>
<%! public int getRandom(){
	int random = 0;
	random = (int)Math.floor((Math.random()*(99999-10000+1)))+10000;
	return random;
	}
%>

</head>
<body>
	<br>
	<br>
	<b><font size="6" color="gray">회원가입</font></b>
	<br>
	<br>

	<form method="post"
		action="MemberJoinAction.do"
		name="userInfo" onsubmit="return checkValue()">
		<table>

			<tr>
				<td id="title">아이디</td>
				<td><input type="text" name="id" maxlength="50" onkeydown="inputIdChk()"> 
				<input type="button" 
				value="중복확인" onclick="openIdChk()">
				<input type="hidden" name="idDuplication" value="idUncheck">
				</td>
			</tr>

			<tr>
				<td id="title">비밀번호</td>
				<td><input type="password" name="password" maxlength="50">
				</td>
			</tr>

			<tr>
				<td id="title">비밀번호 확인</td>
				<td><input type="password" name="passwordcheck" maxlength="50">
				</td>
			</tr>

			<tr>
				<td id="title">이름</td>
				<td><input type="text" name="name" maxlength="50"></td>
			</tr>

			<tr>
				<td id="title">이메일</td>
				<td><input type="text" name="mail1" maxlength="50">@ 
				<select name="mail2">
						<option>naver.com</option>
						<option>daum.net</option>
						<option>gmail.com</option>
						<option>nate.com</option>
						<option>hanmail.net</option>
						<option>shutup.com</option>
				</select></td>
				<td>
				<input id="mailchk" type="button" value="이메일 인증">
				</td>
			</tr>

			<tr>
				<td id="title">휴대전화</td>
				<td><input type="text" name="phone" /></td>
			</tr>
			<tr>
				<td id="title" rowspan="4">주소</td>
				<td><input type="text" name="address1" class="postcodify_postcode5" value=""/> 
				<button id="postcodify_search_button">검색</button></td>
			</tr>
			<tr>
				<td>
				<input type="text" name="address2" class="postcodify_address" value="" />
				</td>
			</tr>
			<tr>
				<td>
				<input type="text" name="address3" class="postcodify_details" value="" />
				</td>
			</tr>
			<tr>
				<td>
				<input type="text" name="address4" class="postcodify_extra_info" value="" />
				</td>
			</tr>
		</table>
		<br> <input type="submit" value="가입" /> 
		<input type="button"
			value="취소" onclick="goFirstForm()">
	</form>
	<hr><hr>
	
</body>
</html>


<!--    id varchar2(45) not null primary KEY
  ,password varchar2(45) not null
  ,name varchar2(45)
  ,mail varchar2(45)
  ,phone varchar2(45)
  ,address varchar2(45)
  ,reg date default sysdate -->