package pl.coderslab;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.Cookie;
import java.io.IOException;

@WebServlet("/login")
public class ServletLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/theme/login.jsp").forward(req ,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Cookie[] cookies = req.getCookies();
        cookies = cookies != null ? cookies : new Cookie[] {};

        if ("admin".equals(login) && "coderslab".equals(password)) {
            session.setAttribute("loggedUser", login);
            Cookie loginCookie = new Cookie("loginCookie", login);
            resp.addCookie(loginCookie);
            resp.sendRedirect("/user/list");
        } else {
            req.setAttribute("error", "Błędne dane logowania");
            getServletContext().getRequestDispatcher("/theme/login.jsp").forward(req, resp);
        }
    }
}
