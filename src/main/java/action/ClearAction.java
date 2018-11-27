package action;

import dao.OMTransferDao;
import dao.impl.OMTransferDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClearAction extends HttpServlet {

    private OMTransferDao omTransferDao = new OMTransferDaoImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int extid = Integer.valueOf(request.getParameter("extid"));

        omTransferDao.Clear(null, extid);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
