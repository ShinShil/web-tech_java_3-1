package presentation.filters;

import business.configuration.AuthProvider;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;


public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(AuthProvider.authManager.getAuthUser() == null) {
            PrintWriter out = servletResponse.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Access denied');");
            out.println("</script>");
            servletRequest.getRequestDispatcher("/").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
