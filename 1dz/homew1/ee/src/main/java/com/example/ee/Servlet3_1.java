package com.example.ee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@ServletAnnot(servletURL = "/serv3_1",pathToResources = "resources3/tobi.jpg", someInfo = "Человек-паук")
@WebServlet("/serv3_1")
public class Servlet3_1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/jsp3_1.jsp").forward(req, resp);

    }
}
