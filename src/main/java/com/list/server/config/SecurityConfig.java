package com.list.server.config;

import com.list.server.filter.JwtAuthenticationFilter;
import com.list.server.domain.enums.Role;
import com.list.server.util.Routes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationErrors jwtAuthenticationErrors;
    private final AccessDeniedHandler accessDeniedHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // On configure les règles de sécurité de Spring Security
        http
                // On délègue la configuration de CORS à notre propre implémentation
                .cors(cors -> cors.configure(http))
                // On désactive la gestion des sessions par Spring Security : pas utile avec un JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Il n'y a pas de sessions car l'application est en STATELESS : pas besoin de CSRF
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).ignoringRequestMatchers("/**").disable())

                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/v1/auth/**", "/api/v1/public/**", "/api/v1/user/**").permitAll()
//                        .requestMatchers("/api/v1/user/**").authenticated()
                        .requestMatchers(Routes.USERS_ONLY.getRoute()).hasAnyRole(Role.USER.name()) /* ROLE_USER */
                        .requestMatchers(Routes.ADMIN_ONLY.getRoute()).hasAnyRole(Role.ADMIN.name()) /* ROLE_ADMIN */
                        .anyRequest().authenticated()
                )

                // On configure les erreurs d'authentification
                .exceptionHandling((exception) -> exception
                        .authenticationEntryPoint(jwtAuthenticationErrors)
                        .accessDeniedHandler(accessDeniedHandler)
                )

                // On précise quel Provider d'authentification utiliser
                .authenticationProvider(authenticationProvider)

                // On ajoute notre filtre de vérification du JWT avant le filtre de vérification des identifiants
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }
}
