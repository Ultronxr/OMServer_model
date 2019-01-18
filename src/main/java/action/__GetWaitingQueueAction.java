package action;

import entity.VisitorEntity;
import global.__GlobalWaitingQueue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class __GetWaitingQueueAction extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<VisitorEntity> waitingQueue = __GlobalWaitingQueue.getGlobalWaitingQueue();
        request.setAttribute("WaitingQueue", waitingQueue);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
