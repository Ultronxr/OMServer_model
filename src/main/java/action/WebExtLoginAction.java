package action;

import dao.ExtJdbcDao;
import dao.impl.ExtJdbcDaoImpl;
import entity.ExtLoginEntity;
import global.GlobalWebExtTokens;
import utils.MyMD5Hash;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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

            //extid+authcode然后进行MD5 hash作为token
            String tokenTemp = extLoginEntity.getExtid()+extLoginEntity.getAuthcode();
            String tokenMd5 = MyMD5Hash.stringToMd5LowerCase(tokenTemp);

            if(GlobalWebExtTokens.getOnlineTokensMap().containsKey(tokenMd5)){
                resData = "{\"result\":\"HAVELOGINED\"}";
            }
            else{
                GlobalWebExtTokens.getLoginTokensMap().put(tokenMd5, extLoginEntity);
                resData = "{\"result\":\""+tokenMd5+"\"}";
            }


        }else{
            resData = "{\"result\":\"ERROR\"}";
        }

        System.out.println(resData);

        pw.write(resData);
        pw.flush();
        //pw.close();

    }

}
