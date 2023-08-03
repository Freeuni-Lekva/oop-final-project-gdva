package quizpackage;

import javax.ejb.SessionContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionCreation implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("validInput",1);
        httpSessionEvent.getSession().setAttribute("validRegister",1);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("validInput",1);
        httpSessionEvent.getSession().setAttribute("validRegister",1);
    }
}
