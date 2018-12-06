<%@page import="jsp.member.model.MemberBean"%>
<%@page import="jsp.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>실시간 채팅 메인화면</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<script type="text/javascript">
<%

MemberDAO dao = MemberDAO.getInstance();
MemberBean userdata = dao.getUserInfo(session.getAttribute("sessionID").toString());

%>

	var lastID = 0;
	//채팅 제출? 저장?
	function submitFunction() {
		var chatName = $('#chatName').val();
		var chatContent = $('#chatContent').val();
		$.ajax({
			type : "POST",
			url : "./chatSubmitServlet",
			data : {
				chatName : encodeURIComponent(chatName),
				chatContent : encodeURIComponent(chatContent)
			},
			success : function(result) {
				if (result == 1) {
					autoClosingAlert('#successMessage', 5000);
				} else if (result == 0) {
					autoClosingAlert('#dangerMessage', 5000);
				} else {
					autoClosingAlert('#warningMessage', 5000);
				}
			}
		});
		$('#chatContent').val('');
	}
	// 바로 위에 메시지 출력하는거 자동해서 alert.hide() 시켜 줄 수 있는.
	function autoClosingAlert(selector, delay) {
		var alert = $(selector).alert();
		alert.show();
		window.setTimeout(function() {
			alert.hide()
		}, delay);
	}
	//채팅 리스트 불러오기
	function chatListFunction(type) {
		$.ajax({
			type : "POST",
			url : "./chatListServlet",
			data : {
				listType : type,
			},
			success : function(data) {
				if(data == "") return;
				var parsed = JSON.parse(data);
				var result = parsed.result;
				for (var i = 0; i < result.length; i++) {
					addChat(result[i][0].value, result[i][1].value,
							result[i][2].value);
				}
				lastID = Number(parsed.last);
			}
		});
	}
	function addChat(chatName, chatContent, chatTime) {
		$('#chatList').append(
						'<div class="row">'
								+ '<div class="col-lg-12">'
								+ '<div class="media">'
								+ '<a class="pull-left" href="#">'
								+ '<img class="media-object img-circle" src="images/icon1.png" alt="">'
								+ '</a>' + '<div class="media-body">'
								+ '<h4 class="media-heading">' + chatName
								+ '<span class="small pull-right">' +  chatTime
								+ '</span>' + '</h4>' + '<p>' + chatContent
								+ '</p>' + '</div>' + '</div>' + '</div>'
								+ '</div>' + '<hr>');
		
		$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
	}
	// 설정해놓은 시간에 따라 무한정으로 chatListFunction(liastID) 를 실행할거야.. 과부하 엄청나
/*function getInfiniteChat() {
		setInterval(function() {
			chatListFunction(lastID);
		}, 5000);
	}*/
</script>
</head>
<body>
	<div class="container">
		<div class="container bootstrap snippet">
			<div class="row">
				<div class="col-xs-12">
					<div class="portlet portlet-default">
						<div class="portlet-heading">
							<div class="portlet-title">
								<h4>
									<i class="fa fa-circle text-green"></i>실시간채팅방
								</h4>
							</div>
							<div class="clearfix"></div>
						</div>
						<div id="chat" class="panel-collapse collapse in">
							<div id="chatList" class="portlet-body chat-widget"
								style="overflow-y: auto; width: auto; height: 600;">
							</div>

							<div class="portlet-footer">
								<div class="row">
									<div class="form-group col-xs-4">
										<input type="text" style="height: 40px;" id="chatName" value="<%=session.getAttribute("sessionID").toString() %>"
											class="form-control" placeholder="<%=session.getAttribute("sessionID").toString() %>" maxlength="10" disabled="disabled">
									</div>
								</div>
								<div class="row" style="height: 90px">
									<div class="form-group col-xs-10">
										<textarea style="height: 80px;" id="chatContent"
											class="form-control" placeholder="메시지를 입력하세요" maxlength="300"></textarea>
									</div>
									<div class="form-group col-xs-2">
										<button type="button" class="btn btn-default pull-right"
											onclick="submitFunction()">전송</button>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="alert alert-success" id="successMessage"
			style="display: none;">
			<strong>메세지 전송에 성공</strong>
		</div>
		<div class="alert alert-danger" id="dangerMessage"
			style="display: none;">
			<strong>이름과 내용 모두 입력</strong>
		</div>
		<div class="alert alert-warning" id="warningMessage"
			style="display: none;">
			<strong>데이터 베이스 오류 발생</strong>
		</div>
	</div>
		<%--
	<script type="text/javascript">
		$(document).ready(function() {
			chatListFunction('ten');
			getInfiniteChat();
		});
	</script>
	

	
	이 주석은 안봐두 되는데
	채팅 내용 하나하나에 대한 폼을 만들어 놓은 거야.
	
	<button type="button" class="btn btn-default pull-left"
		onclick="chatListFunction('ten')">추가</button>

							<div class="portlet-body chat-widget"
								style="overflow-y: auto; width: auto; height: auto;">
								<div class="row">
									<div class="col-lg-12">
										<p class="text-center text-muted small">2018년 11월 20일</p>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<div class="media">
											<a class="pull-left" href="a"> <img
												class="media-object img-circle" src="images/icon1.png">
											</a>
											<div class="media-body">
												<h4 class="media-heading">
													김동민 <span class="small pull-right">오후 3:41</span>
												</h4>
											</div>
											<p>김동민입니다. 잘부탁드립니다.</p>
										</div>
									</div>
								</div>
							</div>
 --%>




</body>
</html>