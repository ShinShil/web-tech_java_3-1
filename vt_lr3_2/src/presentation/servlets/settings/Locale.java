package presentation.servlets.settings;

import business.configuration.PhysicalDatabaseProvider;
import resources.locale.strings.LocalMap;
import resources.locale.strings.LocaleProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Locale", urlPatterns = "/Locale")
public class Locale extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String locale = req.getParameter("locale");
        PhysicalDatabaseProvider.settingsDatabase.SetLocale(locale);
        LocaleProvider.locale = LocalMap.getMap().get(locale);
    }
}
