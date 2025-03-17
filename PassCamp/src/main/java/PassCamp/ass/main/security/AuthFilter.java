/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PassCamp.ass.main.security;

import PassCamp.ass.main.entity.Account;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthFilter implements Filter {

    private static final List<String> PUBLIC_ENDPOINTS = Arrays.asList(
            "/logout","/session", "/login", "/account/register", "/account/verify", "/categories",
            "/item"
    );
    
    private static final List<String> USER_ENDPOINTS = Arrays.asList(
            "/cart","/cart/add"
    );
    
    private static final List<String> ADMIN_ENDPOINTS = Arrays.asList(
            "/account/all"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        // Allow public endpoints
        if (isPublicEndpoint(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        // Check session for authentication
        String loggedInUser = httpRequest.getHeader("loggedInUser");
        String role = (String) httpRequest.getHeader("role");

        System.out.println("role: "+ role);
        System.out.println("user: "+ loggedInUser);
        
        if (loggedInUser == null) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Unauthorized: Please log in.");
            return;
        }

        if (isUserEndpoint(requestURI) && (role == null && !role.equals("USER"))) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.getWriter().write("Forbidden: User access required.");
            return;
        }
        
        // Check admin-only access
        if (isAdminEndpoint(requestURI) && (role == null || !role.equals("ADMIN"))) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.getWriter().write("Forbidden: Admin access required.");
            return;
        }

        // Default: allow request to proceed
        chain.doFilter(request, response);
    }

    private boolean isPublicEndpoint(String requestURI) {
        return PUBLIC_ENDPOINTS.stream().anyMatch(requestURI::startsWith);
    }
    
    private boolean isUserEndpoint(String requestURI) {
        return USER_ENDPOINTS.stream().anyMatch(requestURI::startsWith);
    }

    private boolean isAdminEndpoint(String requestURI) {
        return ADMIN_ENDPOINTS.stream().anyMatch(requestURI::startsWith);
    }
}
