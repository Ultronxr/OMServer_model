<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
    <h2>Use form below to send test query:</h2>
    <form id="uploadForm" method="get" action="/OMServer_model/ServerServlet">
        <ul style="">
            <li>
                <input type="text" id="attr" name="attr">
                <br/>
                <input type="text" id="ext_id_from" name="ext_id_from">
                <br/>
                <input type="text" id="ext_id_to" name="ext_id_to">
                <br/>
                <input type="submit" id="upload" value="提交">
                <br/>
            </li>
        </ul>
    </form>
</body>
</html>
