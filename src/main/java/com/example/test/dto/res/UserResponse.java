package com.example.test.dto.res;

import lombok.Data;

@Data
public class UserResponse {
    String username;
    String password;
    String name;
    String familyName;
    String email;
    String phone;
    Boolean enabled;
    ProfileResponse profile;
}

