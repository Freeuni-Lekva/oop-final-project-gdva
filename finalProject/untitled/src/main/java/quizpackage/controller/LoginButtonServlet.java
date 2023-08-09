package quizpackage;


import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginButtonServlet", urlPatterns = "/loginButtonServlet")
public class LoginButtonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = req.getParameter("loginInput");
        String password = req.getParameter("passwordInput");
        DBHandler handler =(DBHandler) req.getServletContext().getAttribute("handler");
        Account acc = handler.getAccount(input,password);
        if(acc == null){
            req.getSession().setAttribute("validInput",0);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req,resp);
            return;
        }
        req.getSession().setAttribute("validInput",1);
        if(!handler.isAdmin(acc.getId())){
            req.getSession().setAttribute("account",acc);
            RequestDispatcher dispatcher  = req.getRequestDispatcher("homepage.jsp");
            dispatcher.forward(req,resp);
        } else {
            req.getSession().setAttribute("account",acc);
            RequestDispatcher dispatcher  = req.getRequestDispatcher("adminhomepage.jsp");
            dispatcher.forward(req,resp);
        }
    }
}
