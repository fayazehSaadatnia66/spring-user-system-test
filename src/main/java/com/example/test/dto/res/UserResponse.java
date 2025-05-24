package com.example.test.dto.res;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserResponse {
    @Id
    @GeneratedValue
    Long id;
    String username;
    String password;
    String name;
    String familyName;
    String email;
    String phone;
    Boolean enabled;
    ProfileResponse profile;
}
