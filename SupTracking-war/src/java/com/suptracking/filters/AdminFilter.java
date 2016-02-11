package com.suptracking.filters;

import com.suptracking.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = ((HttpServletRequest) request).getSession(false);
            User user = (session != null) ? (User) session.getAttribute("user") : null;
            if (user == null || !user.isAdminBool()) {
                res.sendRedirect(req.getContextPath() + "/accessRefused.xhtml");
            } else {
                chain.doFilter(req, res);
            }
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
