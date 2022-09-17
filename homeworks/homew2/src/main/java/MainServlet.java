import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String generateID = req.getParameter("idGe");
        if (nonNull(generateID)) {
            req.setAttribute("idGe", generateID);
            SetWithIDs.addToSet(req.getParameter("idGe"));
        }

        String id = req.getParameter("id");
        if (nonNull(id)) {
            req.getSession().setAttribute("id", id);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/JSP/MainPage.jsp").forward(req, resp);
    }

}
