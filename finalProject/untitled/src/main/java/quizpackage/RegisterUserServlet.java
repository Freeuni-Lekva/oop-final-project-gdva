package quizpackage;

import jdk.nashorn.internal.ir.RuntimeNode;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registerUserServlet", urlPatterns = "/registerUserServlet")
public class RegisterUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("nameInput");
        String surname = req.getParameter("surnameInput");
        String username = req.getParameter("usernameInput");
        String password = req.getParameter("passwordInput");
        int age = Integer.parseInt(req.getParameter("ageInput"));

        Account acc = new User(name,surname,username, PasswordHasher.hashPassword(password),age);
        DBHandler handler = (DBHandler) req.getServletContext().getAttribute("handler");
        if(handler.containsUsername(username)){
            req.getSession().setAttribute("validRegister",0);
            RequestDispatcher dispatcher = req.getRequestDispatcher("registerpage.jsp");
            dispatcher.forward(req,resp);
            return;

        }
        handler.addAccount(acc);
        req.getSession().setAttribute("account",acc);
        RequestDispatcher dispatcher = req.getRequestDispatcher("homepage.jsp");
        dispatcher.forward(req,resp);
    }
}
