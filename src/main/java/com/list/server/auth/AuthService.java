package com.list.server.auth;

import com.list.server.demo.LoginRepository;
import com.list.server.domain.entities.User;
import com.list.server.exceptions.GlobalExceptionHandler;
import com.list.server.exceptions.UsernameAlreadyTakenException;
import com.list.server.repositories.UserRepository;
import com.list.server.util.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final LoginRepository loginRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Map<String, String> registerLog(RegisterRequest request, HttpServletRequest httpRequest) throws UsernameAlreadyTakenException {

        if (!loginRepository.findByEmail(request.getEmail()).isPresent()) {
            var login = Login.builder()
                    .pseudo(request.getPseudo())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role("ROLE_" + Role.USER)
                    .build();

            loginRepository.save(login);

            Map<String, String> body = new HashMap<>();
            body.put("message", "Log information saved with succes.");
            return body;

        } else {
            throw new UsernameAlreadyTakenException("Username already taken");
        }

    }

    public Map<String, String> registerUser(RegisterRequest request, HttpServletRequest httpRequest) {
        try {
            var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .createdAt(new Date())
                    .picture(request.getPicture())
                    .address(request.getAddress())
                    .city(request.getCity())
                    .zipCode(request.getZipCode())
                    .build();
            this.userRepository.save(user);

            Map<String, String> body = new HashMap<>();
            body.put("message", "Account successfully created as user.");
            return body;

        } catch (GlobalExceptionHandler exceptionHandler) {
            throw new GlobalExceptionHandler().handleMethodeNotAllowedException()
        }
    }

    public AuthResponse authenticate(AuthRequest request, HttpServletRequest httpRequest) {


        /* Permet de comparer le pwd reçu de la request reçue avec le pwd haché de la BDD.
         * La méthode authenticate() permet surtout de garantir que les informations d'identification sont exactes
         * Permet de transmettre au contexte de Spring l'utilisateur qui a été trouvé.
         *  Cela permet de l'utiliser pour autoriser/refuser l'accès aux ressources protégées
         * S'il n'est pas trouvé, une erreur est levée et la méthode s'arrête.
         */
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            /* Si tout va bien et que les informations sont OK, on peut récupérer l'utilisateur */
            /* La méthode findByEmail retourne un type Optionnel. Il faut donc ajouter une gestion d'exception avec "orElseThrow" */
            Login login = loginRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found in DB"));

            /* On extrait le rôle de l'utilisateur */
            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("role", login.getRole());

            /* On génère le token avec le rôle */
            String jwtToken = jwtService.generateToken(new HashMap<>(extraClaims), login);
            return AuthResponse.builder()
                    .token(jwtToken)
                    .message("Logged In")
                    .build();

        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Bad credentials");
        }

    }
}
