package com.example.notes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("/note")
public class MyNote extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        if (nonNull(message)) {
            req.getSession().setAttribute("message", message);
        }
        req.setAttribute("history", AttributeListener.get(req.getParameter("login")));
//        if (req.getParameter("login") != null && req.getParameter("login").length() > 0) {
//            System.out.println(req.getParameter("login"));
//        req.setAttribute("history", MessagesHolder.get(req.getParameter("login")));
            getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(req, resp);
//        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("history", AttributeListener.get(req.getParameter("login")));
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(req, resp);
    }
}
