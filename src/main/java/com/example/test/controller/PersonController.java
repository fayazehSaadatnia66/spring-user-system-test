package com.example.test.controller;

import com.example.test.entity.Person;
import com.example.test.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    final
    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Person savePerson(@RequestParam String name, @RequestParam String family, @RequestParam int age, @RequestParam long cardId) {
        Person newP = new Person(name, family, age, cardId);
        return personService.save(newP);
    }

    @GetMapping
    public List<Person> getAllPersonList() {
        return personService.getAllPersons();

    }

    @DeleteMapping
    public String deletePerson(@RequestParam Long id) {
        return personService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personService.getById(id);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person) {

        return personService.update(id, person);
    }
}
