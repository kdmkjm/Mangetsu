<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>


<%@ page import="java.io.File"%>

<%@ page import="java.awt.image.BufferedImage"%>
<%@ page import="javax.imageio.ImageIO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style></style>
<script type="text/javascript"></script>
</head>

<body>
	<form id="reupup" action="picRewrite.do" method="post">
		<%
			ServletContext context = getServletContext();
			String saveDir = context.getRealPath("image");
			int maxSize = 1024 * 1024 * 10;
			String encType = "UTF-8";

			String savePath = saveDir.replace('\\', '/'); //구분자 리플레이스

			File targetDir = new File(savePath);

			// 디렉토리가 없을 경우 생성
			if (!targetDir.exists()) {
				targetDir.mkdirs();
			}

			MultipartRequest multipartRequest = new MultipartRequest(request, saveDir, maxSize, encType,
					new DefaultFileRenamePolicy());

			File picfile_re = multipartRequest.getFile("picfile_re");
			String title = multipartRequest.getParameter("title");
			String story = multipartRequest.getParameter("story");
			String manipulation_read = multipartRequest.getParameter("manipulation_read");
			String repost = multipartRequest.getParameter("repost");
		%>

		<input type="hidden" name="picfile_re" value="<%=picfile_re%>">
		<input type="hidden" name="repost" value="<%=repost%>">


	</form>

	<script type="text/javascript">
		this.document.getElementById("reupup").submit();
	</script>

</body>
</html>