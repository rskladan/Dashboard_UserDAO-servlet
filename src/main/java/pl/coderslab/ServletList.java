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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user/list")
public class ServletList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();

//************************READ ALL USERS*****************************
        UserDao userDao = new UserDao();
        List<User> allUsers = userDao.findAll();

        for (int i = 0; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            writer.println("name: " + user.getUserName() + ", email: " + user.getEmail());
            req.setAttribute("userName", user.getUserName());
            req.setAttribute("userEmail", user.getEmail());
            req.setAttribute("userID", user.getId());
        }

        req.setAttribute("usersAmount", allUsers.size());
        req.setAttribute("allUsers", allUsers);

        getServletContext().getRequestDispatcher("/theme/index.jsp").forward(req, resp);

    }
}
