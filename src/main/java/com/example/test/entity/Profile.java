package com.example.test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Profile {
    @Id
    @GeneratedValue
    Long id;
    String imageUrl;
    String phoneNumber;
    String address;
    String dateOfBirth;
    String gender;


    @OneToOne(mappedBy = "profile")
    User user;


    public Profile(String imageUrl, String phoneNumber, String address, String dateOfBirth, String gender) {
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

}
