package com.example.ee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@ServletAnnot(servletURL = "/serv3_3", pathToResources = "resources3/JFK.jpg",someInfo = "11.22.63")
@WebServlet("/serv3_3")
public class Servlet3_3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/jsp3_3.jsp").forward(req, resp);
    }
}
