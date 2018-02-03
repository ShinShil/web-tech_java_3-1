package presentation.servlets;

import business.configuration.AuthProvider;
import business.configuration.DatabaseProvider;
import persistance.models.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.awt.print.Book;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BookRoom", urlPatterns = { "/BookRoom" })
public class BookRoom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Room[] rooms = DatabaseProvider.roomsDatabase.getAllRooms();
        if(AuthProvider.authManager.getAuthUser() == null) {
            PrintWriter out = resp.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Access denied');");
            out.println("</script>");
            resp.sendRedirect("/");
            return;
        }
        req.setAttribute("rooms", rooms);
        req.getRequestDispatcher("WEB-INF/pages/bookRoom.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int ownerId = Integer.parseInt(req.getParameter("ownerId"));
        if(AuthProvider.authManager.getAuthUser().id != ownerId && ownerId != 0) {
            resp.getWriter().write("You are not the owner");
            return;
        }

        resp.getWriter().write("success");
        DatabaseProvider.roomsDatabase.UpdateRoom(id);
    }
}
