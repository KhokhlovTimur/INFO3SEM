package controllers;

import holder.UserRepHolder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("/login_mac")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(nonNull(login) && nonNull(password)){
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("password", password);
            UserRepHolder.usersRepository.save(User.builder().login(login).password(password).status(true).build());
        }
        getServletContext().getRequestDispatcher("/menu").forward(req, resp);
    }
}
