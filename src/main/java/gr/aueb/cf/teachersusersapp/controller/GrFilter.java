package gr.aueb.cf.teachersusersapp.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "GrFilter", value="/*")
public class GrFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
        // Add initialization code here
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    public void destroy() {
        // Add cleanup code here
    }
}
