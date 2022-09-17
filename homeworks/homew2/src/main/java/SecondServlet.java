import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("/chat")
public class SecondServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String dataValue = req.getParameter("value");
        if (nonNull(dataValue) && dataValue.length() > 0) {
            session.setAttribute("value", dataValue);
        }
        req.setAttribute("history", SessionAttributeListener.getHistory(req.getParameter("id")));
        if (req.getParameter("id") != null && req.getParameter("id").length() > 0 && SetWithIDs.getSet().contains(req.getParameter("id"))) {
            req.getRequestDispatcher("/WEB-INF/JSP/2page.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("history", SessionAttributeListener.getHistory(req.getParameter("id")));
        if (req.getParameter("id") != null && req.getParameter("id").length() > 0 && SetWithIDs.getSet().contains(req.getParameter("id"))) {
            req.getRequestDispatcher("/WEB-INF/JSP/2page.jsp").forward(req, resp);
        }
        else {
            req.getRequestDispatcher("/WEB-INF/JSP/noID.jsp").forward(req,resp);
        }

    }
}
