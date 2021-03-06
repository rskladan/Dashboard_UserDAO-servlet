package pl.coderslab;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter({"/user/*"})
public class AuthFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();
        boolean isLogged = false;

        for (Cookie c : cookies) {
            if (c.getName().equals("loginCookie")) {
                isLogged = true;
                System.out.println("Logged: " + isLogged);
                chain.doFilter(req, res);
            }
        }

        if (!isLogged) {
            res.sendRedirect("/login");
        }
    }
}