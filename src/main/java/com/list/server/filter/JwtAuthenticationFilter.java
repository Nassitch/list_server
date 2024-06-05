package com.list.server.filter;

import com.list.server.util.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        /* Extraire la partie "authorization" du header de la requête entrante */
        final String authHeader = request.getHeader("Authorization");
        /* Mon authHeader ressemble à : {"Authorization": "Bearer KAOJCZNCIZA192EN?.C_QCIENAC?QC"}*/
        final String jwt;
        final String userEmail;

        /* On vérifie si authHeader n'est pas null ET si la valeur de la clé "Authorization" commence par "Bearer " */
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            request.setAttribute("no_jwt_provided", "No JWT provided");
            filterChain.doFilter(request, response);


            return;
        }

        try {
            /* J'implémente la logique du filtre JWT*/
            jwt = authHeader.substring(7); /* j'extraie la valeur du token après "Bearer "*/
            userEmail = jwtService.extractUsername(jwt);

            /* Si le user n'est pas null & qu'il n'est pas déjà connecté */
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                /* Je récupère en BDD l'utilisateur dont l'email correspond à "userEmail" */
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

                /* Si le token est valide */
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    /* Crée un nouvel objet "Authentication" pour dialoguer avec SecurityContextHolder */
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null, /* L'étape d'auth a déjà été faite, on utilise ici juste pour update le security context holder*/
                            userDetails.getAuthorities() /* Permet de récupérer le rôle de notre User */
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    /*Mettre à jour le SecurityContextHolder pour le notifier de cette nouvelle authentification */
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

        } catch (Exception error) {
            /* S'il y a eu une erreur durant le processus, "j'attrape l'erreur" pour la remonter au client */
            if (error instanceof ExpiredJwtException) {
                request.setAttribute("expired_exception", error.getMessage());
            }
            if (error instanceof MalformedJwtException) {
                request.setAttribute("malformed_exception", error.getMessage());
            }
        }

        /* Une fois le traitement terminé, je passe au filtre suivant */
        filterChain.doFilter(request, response);

    }
}




