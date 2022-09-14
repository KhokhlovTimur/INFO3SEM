package com.example.ee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@ServletAnnot(servletURL = "/serv3_2",someInfo = "Мартин Лютер Кинг \"I have a dream\"",pathToResources = "resources3/King.jpg")
@WebServlet("/serv3_2")
public class Servlet3_2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/jsp3_2.jsp").forward(req, resp);
    }
}
