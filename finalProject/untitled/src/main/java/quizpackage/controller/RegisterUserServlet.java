package quizpackage.controller;

import quizpackage.model.Account;
import quizpackage.model.DBHandler;
import quizpackage.model.PasswordHasher;
import quizpackage.model.User;

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
        String image = req.getParameter("imageInput");
        String ageStr = req.getParameter("ageInput");
        int age = 0;

        try {
            age = Integer.parseInt(ageStr);
        }catch (NumberFormatException e) {
            req.getSession().setAttribute("validRegister",0);
            req.getSession().setAttribute("NumberFormatException",0);
            RequestDispatcher dispatcher = req.getRequestDispatcher("registerpage.jsp");
            dispatcher.forward(req,resp);
            e.printStackTrace();
            return;
        }

        DBHandler handler = (DBHandler) req.getServletContext().getAttribute("handler");
        Account acc = new User(name,surname,username, PasswordHasher.hashPassword(password),age,handler.getMaxUserId()+1,image);
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
