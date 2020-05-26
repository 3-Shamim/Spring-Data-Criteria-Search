package com.learningstuff.springdatacriteriaqueries.services;

import com.learningstuff.springdatacriteriaqueries.models.Person;
import com.learningstuff.springdatacriteriaqueries.models.Phone;
import com.learningstuff.springdatacriteriaqueries.repositories.PersonRepository;
import com.learningstuff.springdatacriteriaqueries.repositories.PhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২২/৫/২০
 * Time: ৯:০০ PM
 * Email: mdshamim723@gmail.com
 */

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PhoneRepository phoneRepository;

    public Person savePerson(Person person) {

        List<Phone> phones = person.getPhones();
        person.setPhones(new ArrayList<>());

        Person savePerson = personRepository.save(person);

        List<Phone> savePhones = phones.stream().map(phone -> {
            phone.setPerson(savePerson);
            return phoneRepository.save(phone);
        }).collect(Collectors.toList());

        savePerson.getPhones().addAll(savePhones);

        return savePerson;
    }

    public List<Person> saveAllPerson(List<Person> peoples) {
        return peoples.stream().map(this::savePerson).collect(Collectors.toList());
    }

}
