package com.list.server.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.list.server.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Date createdAt;
    private String picture;
    private String address;
    private String city;
    @Column(length = 5)
    private String zipCode;
    private Status status = Status.ACTIVATED;
    @Column(name = "login_id")
    private Long loginId;
}
