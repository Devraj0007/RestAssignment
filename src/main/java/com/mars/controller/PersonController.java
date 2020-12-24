package com.mars.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mars.exception.ResourceNotFoundException;
import com.mars.model.Person;
import com.mars.repository.PersonRepository;



@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/persons")
    public List < Person > getListOfPerson() {
        return personRepository.findAll();
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity < Person > getPersonById(@PathVariable(value = "id") Long personId)
    throws ResourceNotFoundException {
        Person person = personRepository.findById(personId)
            .orElseThrow(null);
        return ResponseEntity.ok().body(person);
    }
    
   
    @PostMapping("/persons")
    public Person createPerson(@Valid @RequestBody Person person) {
        return personRepository.save(person);
    }

    @PutMapping("/persons/{id}")
    public  Person  updatePerson(@PathVariable(value = "id") Long personId,
        @Valid @RequestBody Person personDetails) throws ResourceNotFoundException {
        Person person = personRepository.findById(personId)
            .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));

        person.setEmailId(personDetails.getEmailId());
        person.setLastName(personDetails.getLastName());
        person.setFirstName(personDetails.getFirstName());
        Person updatedPerson = personRepository.save(person);
        return updatedPerson;
    }

    
    @DeleteMapping("/persons/{id}")
    public Map < String, Boolean > deletePerson(@PathVariable(value = "id") Long personId)
    throws ResourceNotFoundException {
        Person person = personRepository.findById(personId)
            .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));
        personRepository.delete(person);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
    @GetMapping("/personCount")
    public long countPerson() {
        return personRepository.count();
    }
    
}