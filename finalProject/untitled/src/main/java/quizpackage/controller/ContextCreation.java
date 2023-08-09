package quizpackage;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextCreation implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DBHandler handler = new DBHandler();
        servletContextEvent.getServletContext().setAttribute("handler",handler);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
