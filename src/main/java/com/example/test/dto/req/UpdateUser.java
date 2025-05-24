package com.example.test.dto.req;

import lombok.Data;

@Data
public class UpdateUser {
    private String username;
    private String password;
    private String name;
    private String familyName;
    private String email;
    private String phone;
    private Boolean enabled;
    private CreateProfile profile;
}