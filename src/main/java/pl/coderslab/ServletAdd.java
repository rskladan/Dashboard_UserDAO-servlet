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

@WebServlet("/user/add")
public class ServletAdd extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/theme/add.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();

//************************ADD USER*****************************
        UserDao userDao = new UserDao();
        User user = new User();
        String userName = req.getParameter("userName");
        String userEmail = req.getParameter("userEmail");
        String userPassword = req.getParameter("userPassword");

        user.setUserName(userName);
        user.setEmail(userEmail);
        user.setPassword(userPassword);
        userDao.create(user);

        resp.sendRedirect("/user/list");
    }
}
