package com.oxyl.epf.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CorsFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)
            throws IOException, ServletException{
        HttpServletResponse httpResponse=(HttpServletResponse) response;
        HttpServletRequest httpRequest=(HttpServletRequest) request;

        // en-têtes CORS
        httpResponse.setHeader("Access-Control-Allow-Origin","http://localhost:5173");//autorisation pour notre front
        httpResponse.setHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,OPTIONS");//méthodes HTTP autorisées
        httpResponse.setHeader("Access-Control-Allow-Headers","Content-Type,Authorization");//autorisation des en-têtes dans les requêtes
        httpResponse.setHeader("Access-Control-Allow-Credentials","true");

        // Si la méthode est OPTIONS
        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        // Passer la requête à la chaîne suivante
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {
        // Nettoyage si nécessaire
    }
}
