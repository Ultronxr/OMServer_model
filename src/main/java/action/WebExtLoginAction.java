package action;

import entity.ExtLoginEntity;
import global.GlobalWebExtTokens;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WebExtLoginAction extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("username="+username+"  password="+password);

        if(username.equals("231") && password.equals("123456")){
            PrintWriter pw = response.getWriter();
            String token = "1jkldsafio59932lkjk234jkln34bn56n23";
            ExtLoginEntity extLoginEntity = new ExtLoginEntity();
            extLoginEntity.setLineid("Line 10");
            extLoginEntity.setExtid("231");
            extLoginEntity.setGroupid("1");
            extLoginEntity.setPassword("123456");
            extLoginEntity.setAuthcode("123231");

            GlobalWebExtTokens.getGlobalWebExtLoginTokensMap().put(token, extLoginEntity);

            String json_data = "{\"token\":\""+token+"\"}";
            pw.write(json_data);
            pw.flush();
            pw.close();
        }

    }

}
