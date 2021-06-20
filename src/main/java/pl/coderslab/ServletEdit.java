package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/user/edit")
public class ServletEdit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userID = Integer.parseInt(req.getParameter("userID"));

        User user = new UserDao().read(userID);

//        To jest do odczytania przez JSP i wpisania domyslnych wartosci

        req.setAttribute("userName", user.getUserName());
        req.setAttribute("userEmail", user.getEmail());
        req.setAttribute("userID", userID);

        getServletContext().getRequestDispatcher("/theme/edit.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userID = Integer.parseInt(req.getParameter("userID"));

        UserDao userDao = new UserDao();
        User userToUpdate = new UserDao().read(userID);

//        *******************EDIT USER***********************

        String userName = req.getParameter("userName");
        String userEmail = req.getParameter("userEmail");
        String userPassword = req.getParameter("userPassword");

        userToUpdate.setUserName(userName);
        userToUpdate.setEmail(userEmail);
        userToUpdate.setPassword(userPassword);

        userDao.update(userToUpdate);

        resp.sendRedirect("/user/list");



    }
}

