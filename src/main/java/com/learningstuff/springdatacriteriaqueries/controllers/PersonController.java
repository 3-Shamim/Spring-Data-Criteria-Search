package com.learningstuff.springdatacriteriaqueries.controllers;

import com.learningstuff.springdatacriteriaqueries.models.Person;
import com.learningstuff.springdatacriteriaqueries.models.Phone;
import com.learningstuff.springdatacriteriaqueries.repositories.PersonRepository;
import com.learningstuff.springdatacriteriaqueries.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২২/৫/২০
 * Time: ৮:৫৫ PM
 * Email: mdshamim723@gmail.com
 */

@RestController
@RequestMapping(value = "/persons")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final PersonRepository personRepository;

    @GetMapping(value = "")
    public ResponseEntity<?> getAllPerson() {
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.findAll());
    }

    @GetMapping(value = "/criteria")
    public ResponseEntity<?> getAllPersonCriteria() {
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.findAllPersonList());
    }

    @GetMapping(value = "/criteria-dto")
    public ResponseEntity<?> getAllPersonDTOCriteria() {
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.findAllPersonDTOListFromPerson());
    }

    @GetMapping(value = "/multi-root")
    public ResponseEntity<?> getAllMultiRoot() {
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.findDataFromMultipleRoot());
    }

    @PostMapping(value = "/save")
    private ResponseEntity<?> savePerson(@RequestBody @Valid List<Person> persons) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.saveAllPerson(persons));
    }


}
