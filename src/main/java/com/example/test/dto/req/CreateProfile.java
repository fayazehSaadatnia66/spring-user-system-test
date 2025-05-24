package com.example.test.dto.req;

import lombok.Data;

@Data
public class CreateProfile {
    String imageUrl;
    String phoneNumber;
    String address;
    String dateOfBirth;
    String gender;
}
