package com.example.test.dto.res;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ProfileResponse {
    @Id
    @GeneratedValue
    Long id;
    String imageUrl;
    String phoneNumber;
    String address;
    String dateOfBirth;
    String gender;
}
