<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="/OMServer_model/jquery3.3.1-jqueryui1.12.1/jquery-3.3.1.js" charset="utf-8"></script>

    <script type="text/javascript">

        function loginSubmit() {
            var input_username = $("#usn").val();
            var input_password = $("#pwd").val();
            var ajax_data = {username:input_username,password:input_password};
            $.ajax({
                type:"POST",
                url:"/OMServer_model/webExtLogin.jhtml",
                data:ajax_data,
                dataType:'json',
                success:function(msg){
                    if(msg.token != null){
                        alert(msg.token);
                    }
                    connectWebSocket(msg.token);
                }
            });
        }



        function connectWebSocket(token) {
            //var websocket = null;
            //_websocket = new WebSocket("ws://localhost:8100/OMServer_model/websocket/"+token)
            if('WebSocket' in window){
                websocket = new WebSocket("ws://localhost:8100/OMServer_model/websocket/"+token);
                console.log("link success");
            }else{
                alert('Not support websocket')
            }
        }

        // if('WebSocket' in window){
        //     websocket = new WebSocket("ws://localhost:8100/OMServer_model/websocket/"+token);
        //     console.log("link success");
        // }else{
        //     alert('Not support websocket')
        // }

        //连接发生错误的回调方法
        websocket.onerror = function(){
            setMessageInnerHTML("error");
        };

        //连接成功建立的回调方法
        websocket.onopen = function(event){
            setMessageInnerHTML("open");
        }
        console.log("-----")
        //接收到消息的回调方法
        websocket.onmessage = function(event){
            setMessageInnerHTML(event.data);
        }

        //连接关闭的回调方法
        websocket.onclose = function(){
            setMessageInnerHTML("close");
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function(){
            websocket.close();
        }

        //将消息显示在网页上
        function setMessageInnerHTML(innerHTML){
            document.getElementById('message').innerHTML += innerHTML + '<br/>';
        }

        //关闭连接
        function closeWebSocket(){
            websocket.close();
        }

        //发送消息
        function send(){
            var message = document.getElementById('text').value;
            websocket.send(message);
        }

    </script>
</head>
<body>

    <strong><p>websocket demo</p></strong><br/>
    <div>
        <form name="loginForm" id="loginForm">
            <ul>
                <li>
                    <input type="text" name="" id="usn">
                </li>
                <li>
                    <input type="password" name="" id="pwd">
                </li>
                <li>
                    <button name="" id="3" onclick="loginSubmit()">登录</button>
                </li>
            </ul>
        </form>
    </div>

    <br/><br/><br/>

    <input id="text" type="text" />
    <button onclick="send()"> Send </button>
    <button   onclick="closeWebSocket()"> Close </button>
    <div id="message">   </div>







    <%--<h2>Use form below to send test query:</h2>--%>
    <%--<form id="uploadForm" method="get" action="/OMServer_model/ServerServlet">--%>
        <%--<ul style="">--%>
            <%--<li>--%>
                <%--<input type="text" id="attr" name="attr">--%>
                <%--<br/>--%>
                <%--<input type="text" id="ext_id_from" name="ext_id_from">--%>
                <%--<br/>--%>
                <%--<input type="text" id="ext_id_to" name="ext_id_to">--%>
                <%--<br/>--%>
                <%--<input type="submit" id="upload" value="提交">--%>
                <%--<br/>--%>
            <%--</li>--%>
        <%--</ul>--%>
    <%--</form>--%>




</body>
</html>
