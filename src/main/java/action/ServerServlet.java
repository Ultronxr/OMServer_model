package action;

import dao.MQOperateDao;
import dao.impl.MQOperateDaoImpl;
import entity.QueryEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerServlet extends HttpServlet {

    private MQOperateDao mqOperateDao = new MQOperateDaoImpl();
    private int t = 10;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String attribute = request.getParameter("attr");
        String ext_id_from = request.getParameter("ext_id_from");
        String ext_id_to = request.getParameter("ext_id_to");
        QueryEntity queryEntity = new QueryEntity(attribute, ext_id_from, ext_id_to);

        System.out.println("[*] "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " Step_01 接收到新的拨号请求："+queryEntity.toString());

        String out_msg = "";
        boolean transferToMQFlag = mqOperateDao.transferToMQ(queryEntity);
        if(transferToMQFlag){
            System.out.println("[*] "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " Step_02 加入消息队列完成："+queryEntity.toString());
            out_msg = "Query Finished .";
        }
        else{
            System.out.println("[x] "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " Step_02 加入消息队列错误："+queryEntity.toString());
            out_msg = "Query Error !";
        }

        PrintWriter out = response.getWriter();
        out.print(out_msg);
        out.flush();
        out.close();
    }
}
