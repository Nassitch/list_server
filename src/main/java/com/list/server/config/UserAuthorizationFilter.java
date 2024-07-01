package com.list.server.config;

import com.list.server.util.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UserAuthorizationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(UserAuthorizationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        if (requestURI.contains("auth") || requestURI.contains("public") || requestURI.startsWith("/api/v1/admin")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!requestURI.startsWith("/api/v1/user") && !requestURI.startsWith("/api/v1/admin")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            System.out.println("Route not correct.");
            return;
        }

        String token = jwtService.getTokenFromRequest(request);
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            logger.warn("JWT token is null or empty");
            return;
        }

        String role = jwtService.extractRole(token);
        if (role.equals("ROLE_ADMIN")) {
            filterChain.doFilter(request, response);
            return;
        }

        Long idParametter = extractIdFromRequest(request);
        Long extractedIdFromToken = jwtService.extractUserId(token);

        if (requestURI.startsWith("/api/v1/user")) {
            if (requestURI.endsWith("/create") || !requestURI.startsWith("/api/v1/user/this")) {
                filterChain.doFilter(request, response);
                return;
            } else if (idParametter == null || !idParametter.equals(extractedIdFromToken)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                System.out.println("Access unauthorized !");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private Long extractIdFromRequest(HttpServletRequest request) {
        try {
            String requestURI = request.getRequestURI();
            System.out.println("This URI of request is: " + requestURI);
            String[] uriParam = requestURI.split("/");
            String idParam = uriParam[uriParam.length - 1];
            System.out.println("id from request: " + idParam);
            return Long.valueOf(idParam);
        } catch (NumberFormatException exception) {
            return null;
        }
    }

}
