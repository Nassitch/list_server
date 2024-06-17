//package com.list.server.config;
//
//import com.list.server.util.JwtService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class UserAuthorizationFilter extends OncePerRequestFilter {
//
//    private static final Logger logger = LoggerFactory.getLogger(UserAuthorizationFilter.class);
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String requestURI = request.getRequestURI();
//
//        JwtService jwtService = new JwtService();
//
//        Long idParametter = extractIdFromRequest(request);
//        Long idToken = jwtService.extractIdFromToken(request);
//
//        if (requestURI.startsWith("/api/v1/user") && (idParametter == null || !idParametter.equals(idToken))) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            System.out.println("Access forbidden !");
//            return;
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private Long extractIdFromRequest(HttpServletRequest request) {
//        try {
//            String requestURI = request.getRequestURI();
//            System.out.println("This URI of request is: " + requestURI);
//            String[] uriParam = requestURI.split("/");
//            String idParam = uriParam[uriParam.length - 1];
//            System.out.println("id from request: " + idParam);
//            return Long.valueOf(idParam);
//        } catch (NumberFormatException exception) {
//            return null;
//        }
//    }
//}
