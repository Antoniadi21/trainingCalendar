package Filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/training")
public class LoggedUserFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (httpServletRequest.getSession().getAttribute("login") == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/error?errorMessage=notLogged");
        }
        chain.doFilter(request, response);
    }
}
