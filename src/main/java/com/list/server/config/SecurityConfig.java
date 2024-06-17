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
    private final UserAuthorizationFilter userAuthorizationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configure(http))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).ignoringRequestMatchers("/**").disable())

                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/v1/auth/**", "/api/v1/public/**").permitAll()
                        .requestMatchers("/api/v1/user/**").authenticated()
                        .requestMatchers(Routes.USERS_ONLY.getRoute()).hasAnyRole(Role.USER.name()) /* ROLE_USER */
                        .requestMatchers(Routes.ADMIN_ONLY.getRoute()).hasAnyRole(Role.ADMIN.name()) /* ROLE_ADMIN */
                        .anyRequest().authenticated()
                )

                .exceptionHandling((exception) -> exception
                        .authenticationEntryPoint(jwtAuthenticationErrors)
                        .accessDeniedHandler(accessDeniedHandler)
                )

                .authenticationProvider(authenticationProvider)

                // On ajoute notre filtre de vérification du JWT avant le filtre de vérification des identifiants
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(userAuthorizationFilter, JwtAuthenticationFilter.class);

        return http.build();

    }
}
