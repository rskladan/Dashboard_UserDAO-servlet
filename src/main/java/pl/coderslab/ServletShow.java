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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/show")
public class ServletShow extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//************************READ USER*****************************
        UserDao userDao = new UserDao();

        int userID = Integer.parseInt(req.getParameter("userID"));
        int count = Integer.parseInt(req.getParameter("count"));
        User user = new UserDao().read(userID);

        req.setAttribute("userName", user.getUserName());
        req.setAttribute("userEmail", user.getEmail());
        req.setAttribute("userID", count);

        getServletContext().getRequestDispatcher("/theme/show.jsp").forward(req, resp);

    }
}
