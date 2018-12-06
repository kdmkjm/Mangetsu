<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="assets/css/main.css" />
<title>ComWrite</title>
</head>
<body>

	<form action="friendadd.do" method="post">
	<input type="hidden" name="idme" value="<%=session.getAttribute("sessionID").toString()%>">
		<table
			style="width: 60%; height: 100px; margin: auto; text-align: center;">
		
			<tr>
				<td>추가할 친구 아이디<input type="text" name="addidfriendno">
				</td>
			</tr>
	
			<tr>
				<td><input type="submit" value="추가" onclick="selfclose();">
				<input		type="button" onclick="selfclose();" value="닫기"></td>
			</tr>





		</table>
	</form>

	<script type="text/javascript">
		function selfclose() {
			opener.parent.location.reload();
			window.close();
		}
	</script>

</body>
</html>