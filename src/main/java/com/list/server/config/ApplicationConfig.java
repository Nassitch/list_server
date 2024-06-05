package com.list.server.config;

import com.list.server.demo.LoginRepository;
import com.list.server.repositories.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
@ComponentScan
public class ApplicationConfig {

    private final LoginRepository repository;

    // @Bean permet de déclarer un bean.
    // C'est un objet géré par Spring
    // Souvent, lorsqu'on utilise @Bean, c'est que l'objet existe déjà mais avec une implémentation par défaut qu'on souhaite modifier
    // ------
    // UserDetailsService permet de définir la logique de récupération d'un utilisateur dans la BDD
    // On redéfinit la méthode userDetailsService() de l'interface UserDetailsService fournir par Spring Security par défaut
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // Permet de définir la logique d'authentification personnalisée.
    // Ici, on définit que l'authentification sera réalisée en utilisant les données de la BDD
    // On pourrait par exemple, en alternative, utiliser l'Oauth2 (Google, Facebook, etc...)
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Le DaoAuthenticationProvider est une implémentation courante de AuthenticationProvider
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // On utilise notre implémentation de UserDetailsService pour récupérer les données de l'utilisateur
        authProvider.setUserDetailsService(userDetailsService());
        // On utilise notre implémentation de PasswordEncoder pour vérifier le mot de passe
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Permet de définir l'encodeur de mot de passe
    // Ici, on utilise BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // On en aura besoin pour l'authentification
    // L'AuthManager est le conteneur qui gère les AuthProviders
    @Bean
    public AuthenticationManager customAuthenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    // Gère les CORS
    // Attention : ici on autorise tous les URLs à accéder à notre API (avec le *). C'est à éviter en prod.
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}









