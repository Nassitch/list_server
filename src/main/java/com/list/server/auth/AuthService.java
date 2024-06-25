package com.list.server.auth;

import com.list.server.repositories.LoginRepository;
import com.list.server.domain.entities.LogDetail;
import com.list.server.domain.entities.User;
import com.list.server.domain.enums.Role;
import com.list.server.domain.enums.Status;
import com.list.server.exceptions.UsernameAlreadyTakenException;
import com.list.server.repositories.LogDetailRepository;
import com.list.server.repositories.UserRepository;
import com.list.server.util.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final LoginRepository loginRepository;
    private final LogDetailRepository logDetailRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Map<String, String> registerLog(RegisterLogRequest request, HttpServletRequest httpRequest) throws UsernameAlreadyTakenException {

        if (!loginRepository.findByEmail(request.getEmail()).isPresent()) {
            var login = new Login();
            login.setPseudo(request.getPseudo());
            login.setEmail(request.getEmail());
            login.setPassword(passwordEncoder.encode(request.getPassword()));
            login.setRole("ROLE_" + Role.USER);

            loginRepository.save(login);

            Map<String, String> body = new HashMap<>();
            body.put("message", "Log information saved with succes.");
            return body;

        } else {
            throw new UsernameAlreadyTakenException("Username already taken");
        }

    }

    public Map<String, String> registerUser(RegisterUserRequest request, HttpServletRequest httpRequest) {
        try {
            var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .createdAt(LocalDateTime.now())
                    .picture(request.getPicture())
                    .address(request.getAddress())
                    .city(request.getCity())
                    .zipCode(request.getZipCode())
                    .status(Status.ACTIVATED)
//                    .status(Status.ACTIVATED)
//                    .loginId(request.getLoginId())
                    .build();
            this.userRepository.save(user);

            Map<String, String> body = new HashMap<>();
            body.put("message", "Account successfully created as user.");
            return body;

        } catch (DataAccessException e) {
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "Failed to save user to the database.");
            return errorBody;
        }
    }

    public AuthResponse authenticate(AuthRequest request, HttpServletRequest httpRequest) {


        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            Login login = loginRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found in DB"));

            User user = userRepository.findByLoginId(login.getId())
                    .orElseThrow(() -> new RuntimeException("The login_id is not found."));


            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("loginId", login.getId());
            extraClaims.put("userId", user.getId());
            extraClaims.put("role", login.getRole());

            this.registerLogTime(request.getEmail());

            String jwtToken = jwtService.generateToken(new HashMap<>(extraClaims), login);
            return AuthResponse.builder()
                    .token(jwtToken)
                    .message("Logged In")
                    .build();

        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Bad credentials");
        }

    }

    public LocalDateTime registerLogTime(String email) {
        Login loginToCheck = loginRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("This user '" + email + "' was not founded."));

        try {
            LocalDateTime currentDate = LocalDateTime.now();
            Long userId = loginToCheck.getId();

            LogDetail currentLog = new LogDetail();
            currentLog.setLoginId(userId);
            currentLog.setLastLog(currentDate);
            logDetailRepository.save(currentLog);
            System.out.println("last_log : " + currentDate);

            return currentDate;
        } catch (BadCredentialsException exception) {
            throw new BadCredentialsException("Bad credentials.");
        }
    }
}

