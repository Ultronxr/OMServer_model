package action;

import dao.OMTransferDao;
import dao.impl.OMTransferDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class __ConnectAction extends HttpServlet {

    private OMTransferDao omTransferDao = new OMTransferDaoImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String extid1 = request.getParameter("extid1");
        String extid2 = request.getParameter("extid2");

        omTransferDao.connectExtToExt(null, Integer.valueOf(extid1), Integer.valueOf(extid2));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
