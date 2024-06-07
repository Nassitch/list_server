package com.list.server.auth;

import com.list.server.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    private String firstName;
    private String lastName;
    private String picture;
    private String address;
    private String city;
    private String zipCode;
    private Status status;
    private Long loginId;
}
