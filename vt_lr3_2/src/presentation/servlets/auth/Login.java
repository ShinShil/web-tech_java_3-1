package presentation.servlets.auth;

import business.auth.AuthModelState;
import business.configuration.AuthProvider;
import presentation.model.AuthModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "Login", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            AuthModel authModel = new AuthModel();
            authModel.password = password;
            authModel.login = login;
            AuthProvider.authManager.login(authModel);
            AuthModelState authState = AuthProvider.authManager.getUserTryAuthState(authModel);
            resp.getWriter().write(authState.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
