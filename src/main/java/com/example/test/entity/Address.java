package com.example.test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    private Long id;

    String city;
    @ManyToOne
    @JoinColumn(name = "person_id")
    User user;

}
