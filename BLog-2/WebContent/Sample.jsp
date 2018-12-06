<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>주소검색 접속 샘플</title>
<script>

function checkCode(){
	var v1 = form2.code_check.value;
	var v2 = form2.code.value;
	if(v1!=v2){
		document.getElementById('checkCode').style.color = "red";
		document.getElementById('checkCode').innerHTML = "잘못된 인증키";
		makeNull();
	} else {
		document.getElementById('checkCode').style.color = "blue";
		document.getElementById('checkCode').innerHTML = "인증되었습니다";
		makeReal();
	}
}
function makeReal(){
	var hi = document.getElementById("hi");
	hi.type = "submit";
}

function makeNull(){
	var hi = document.getElementById("hi");
	hi.type = "hidden";
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

	
	<form action="send.java" method="post" id="form1">
		<table>
			<tr>
				<td><input type='text' id="receiver" name='receiver' placeholder="이메일 입력하쇼."/></td>
				<td><input id="submit" type="submit" value="인증번호발송"/></td>
				<td><input type="hidden" readonly="readonly" name="code_check"
				id="code_check" value="<%=getRandom()%>"/>
				</td>
			</tr>
		</table>
	</form>
	<form id="form2" action="javascript:getPassword()">
		<table>
		<tr>
		<td><span>인증번호</span></td>
		<td><input type="text" name="code"
		onkeyup="checkCode()" placeholder="인증번호를 입력해"/>
		<div id="checkCode"></div></td>
		<td><input type="hidden" readonly="readonly" name="code_check"
		id="code_check" value="<%=request.getAttribute("code")%>">
		</td>
		</tr>
		</table>
		<input id="hi" type="hidden" value='인증하기'/>
	</form>
</body>
</html>