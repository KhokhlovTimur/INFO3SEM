package controllers;

import holder.UserRepHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/admin")
public class FilterPs extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (UserRepHolder.usersRepository.findEntity(req.getParameter("login"), req.getParameter("password")).isPresent() && req.getParameter("login").length() > 1) {
//            && ) {

            UserRepHolder.usersRepository.findEntity("login", "password").get().setStatus(true);
            req.getServletContext().getRequestDispatcher("/admin").forward(req, res);
        }
        req.getServletContext().getRequestDispatcher("/login_mac").forward(req, res);
        chain.doFilter(req, res);
    }
}
