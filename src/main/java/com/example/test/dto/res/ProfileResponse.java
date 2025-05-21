package com.example.test.dto.res;

import lombok.Data;

@Data
public class ProfileResponse {
    String imageUrl;
    String phoneNumber;
    String address;
    String dateOfBirth;
    String gender;
}
