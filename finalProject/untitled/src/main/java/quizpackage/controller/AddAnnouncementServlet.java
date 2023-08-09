package quizpackage.controller;

import quizpackage.model.Account;
import quizpackage.model.Announcement;
import quizpackage.model.DBHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "AddAnnouncementServlet", urlPatterns = "/AddAnnouncementServlet")
public class AddAnnouncementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("titleInput");
        String text = req.getParameter("textInput");
        String image = req.getParameter("imageInput");

        DBHandler handler = (DBHandler) req.getServletContext().getAttribute("handler");
        java.util.Date currentDate = new java.util.Date();

        Announcement announcement = new Announcement(title,text,image,
                new Date(currentDate.getTime()),(Account) req.getSession().getAttribute("account"),
                handler.getMaxIdOFAnnouncements()+1);
        handler.addAnnouncement(announcement);
        req.getSession().setAttribute("announcement",announcement);
        RequestDispatcher dispatcher = req.getRequestDispatcher("adminhomepage.jsp");
        dispatcher.forward(req,resp);
    }

}
