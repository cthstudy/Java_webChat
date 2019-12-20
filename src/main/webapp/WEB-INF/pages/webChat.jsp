<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聊天页面</title>
<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<script type="text/javascript" src="js/jquery-3.0.0.js"></script> 
<script type="text/javascript" src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
	//建立连接	
	var ws = null;
	if(WebSocket){
		ws = new WebSocket("ws://localhost:8080/chat_web/socket_server");
	}else{
		alert("浏览器不支持WebSocket")
	}
	
	/* 客户端向服务器端发消息 */
	ws.onopen=function(){
		ws.send("hello,已连接");
	}
	/* 服务器接收客户端的消息并显示 */
	ws.onmessage = function(e){
		showMessage(event.data);
	}
	
</script>
</head>
<body>
</body>
</html>