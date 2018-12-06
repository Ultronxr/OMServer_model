package action;

import dao.ExtJdbcDao;
import dao.impl.ExtJdbcDaoImpl;
import entity.ExtLoginEntity;
import global.GlobalWebExtTokens;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WebExtLoginAction extends HttpServlet {

    private ExtJdbcDao extJdbcDao = new ExtJdbcDaoImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter pw = response.getWriter();

        String username = request.getParameter("username"),
               password = request.getParameter("password");
        String resData = "";
        //System.out.println("username="+username+"  password="+password);

        ExtLoginEntity extLoginEntity = extJdbcDao.getExtByExtid(username);

        if(extLoginEntity != null && extLoginEntity.getPassword().equals(password)){
            String tokenHashValue =  "dasf797fd9sa65sagdoiost9h79sjk2012";
            resData = "{\"result\":\""+tokenHashValue+"\"}";

            GlobalWebExtTokens.getGlobalWebExtLoginTokensMap().put(tokenHashValue, extLoginEntity);

        }else{
            resData = "{\"result\":\"NULLDATA\"}";
        }

        System.out.println(resData);

        pw.write(resData);
        pw.flush();
        //pw.close();

    }

}
