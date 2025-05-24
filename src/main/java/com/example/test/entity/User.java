package com.example.test.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_")
public class User {
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

//    @OneToMany
//    @JoinTable(name = "person_address")
//    List<Address> address;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
//    @JsonManagedReference
    Profile profile;

    public User(Profile profile, String username, String password, String name, String familyName, String email, String phone, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.familyName = familyName;
        this.email = email;
        this.phone = phone;
        this.enabled = enabled;
        this.profile = profile;
        if (profile != null) {
            profile.setUser(this);
        }
    }


}
