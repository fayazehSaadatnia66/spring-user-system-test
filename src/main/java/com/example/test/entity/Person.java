package com.example.test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue
    Long id;
    String name;
    String family;
    Integer age;
    Long cardId;

    public Person(String name, String family, Integer age, Long cardId) {
        this.name = name;
        this.family = family;
        this.age = age;
        this.cardId = cardId;
    }

}
