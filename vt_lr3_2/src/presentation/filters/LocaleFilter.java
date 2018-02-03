package presentation.filters;

import business.configuration.AuthProvider;
import persistance.models.User;
import presentation.servlets.settings.Locale;
import resources.locale.strings.LocaleProvider;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "LocaleFilter", urlPatterns = {  "/*" })
public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setAttribute("home_locale", LocaleProvider.locale.getHome());
        servletRequest.setAttribute("book_locale", LocaleProvider.locale.getBook());
        User user = AuthProvider.authManager.getAuthUser();
        if (user != null) {
            servletRequest.setAttribute("userId", AuthProvider.authManager.getAuthUser().id);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
