package com.list.server.controllers.protecteds;

import com.list.server.auth.Login;
import com.list.server.repositories.LoginRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/login")
@RequiredArgsConstructor
@Component("protectedLoginController")
public class LoginController {

    private final LoginRepository loginRepository;

    @GetMapping("/email/{email}")
    public ResponseEntity<Login> getUserByEmail(@PathVariable String email, HttpServletRequest request) throws AccessDeniedException {
        String username  = SecurityContextHolder.getContext().getAuthentication().getName();
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        if (username.equals(email) || roles.equals("[ROLE_ADMIN]")) {
            return ResponseEntity.ok(loginRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("email " + email +" not found"))
            );
        } else {
            throw new AccessDeniedException("UserApp does not have the correct rights to access to this resource");
        }



    }

    @GetMapping("/all")
    public List<Login> getAll(HttpServletRequest request) throws AccessDeniedException {
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if(roles.equals("[ROLE_ADMIN]")) {
            return loginRepository.findAll();
        } else {
            throw new AccessDeniedException("UserApp does not have the correct rights to access to this resource");

        }
    }




}
