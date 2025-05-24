package com.example.test.dto.req;

import lombok.Data;

@Data
public class CreateUser {
    String username;
    String password;
    String name;
    String familyName;
    String email;
    String phone;
    Boolean enabled;
    CreateProfile profile;
}
