package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/menu")
public class Menu extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/menu.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String[] foods = req.getParameterValues("foods");
//        if (nonNull(foods)) {
//            for (String id : foods) {
//                LoginsHolder.orderRepository.saveOrder(Long.valueOf(id));
//            }
//        }
//       getServletContext().getRequestDispatcher("WEB-INF/jsp/basket.jsp").forward(req,resp);
    }
}
