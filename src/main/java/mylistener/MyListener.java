package mylistener;

import mainmethod.MainMethod;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        MainMethod.main();
    }

//    @Override
//    public void contextDestroyed(ServletContextEvent arg0) {
//
//    }

}
