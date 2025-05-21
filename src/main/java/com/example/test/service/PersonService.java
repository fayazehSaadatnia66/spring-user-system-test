package com.example.test.service;

import com.example.test.entity.Person;
import com.example.test.repo.PersonRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    final
    PersonRepo personRepo;

    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public Person save(Person person) {
        return saveToDb(person);
    }

    public List<Person> getAllPersons() {
        return personRepo.findAll();
    }

    public String deleteById(Long id) {
        personRepo.deleteById(id);
        return "Person deleted";
    }
    public Person getById(Long id) {
        return personRepo.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
    }

    public Person update(Long id, Person person) {
        // get entity
        Person dbPerson = getPersonOrElseThrow(id);

        // set person in dbPerson
        mapFromTo(person, dbPerson);


        // save in database
        return saveToDb(dbPerson);


    }

    private Person saveToDb(Person dbPerson) {
        return personRepo.save(dbPerson);
    }

    private void mapFromTo(Person person, Person dbPerson) {
        dbPerson.setName(person.getName());
        dbPerson.setFamily(person.getFamily());
    }

    private Person getPersonOrElseThrow(Long id) {
        return personRepo.findById(id).orElseThrow();
    }

}
