package action;

import entity.VisitorEntity;
import global.GlobalHoldingQueue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class GetHoldingQueueAction extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<VisitorEntity> holdingQueue = GlobalHoldingQueue.getGlobalHoldingQueue();
        request.setAttribute("HoldingQueue", holdingQueue);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
