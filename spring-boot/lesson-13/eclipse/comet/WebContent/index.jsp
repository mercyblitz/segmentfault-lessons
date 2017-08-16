<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var xhr = null;
		$('#btn_send').click(function(){
			var msg = $('#ta_chat').val();
			xhr = $.post('/chat',msg,function(resp,status){
				$('#div_resp').append(resp+'<br/>');
			},'text');
		$('#btn_close').click(function(){
			
			if(xhr) {
				xhr.abort();
			}
			
		});	
		});
	});
</script>
</head>
<body>

	<textarea rows="5" cols="100" id="ta_chat"></textarea>

	<button id="btn_send">发送</button>
	<button id="btn_close">关闭</button>
	
	<p />
	
	相应内容:
	
	<div id="div_resp"></div>
</body>

</html>