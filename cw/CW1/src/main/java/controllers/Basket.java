package controllers;

import holder.RepositoriesHolder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("/basket")
public class Basket extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] foods = req.getParameterValues("foods");
        if (nonNull(foods)) {
            for (String id : foods) {
                RepositoriesHolder.orderRepository.saveOrder(Long.valueOf(id));
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/basket.jsp").forward(req, resp);
    }
}
