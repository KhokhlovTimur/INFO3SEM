package com.example.notes;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        if(nonNull(login)){
            req.getSession().setAttribute("login", login);
        }
        String password = req.getParameter("password");
        if(nonNull(password)){
            req.setAttribute("password", password);
        }
//        req.setAttribute("history", MessagesHolder.get(req.getParameter("login")));
        getServletContext().getRequestDispatcher("/WEB-INF/mainPage.jsp").forward(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String password = req.getParameter("password");
//
//        if(nonNull(password)){
//            req.setAttribute("password", password);
//        }
//        req.getRequestDispatcher("/WEB-INF/mainPage.jsp").forward(req, resp);
//    }
}
