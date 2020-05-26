package com.learningstuff.springdatacriteriaqueries.dao;

import com.learningstuff.springdatacriteriaqueries.dto.PersonDTO;
import com.learningstuff.springdatacriteriaqueries.models.Person;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২২/৫/২০
 * Time: ৮:৫৭ PM
 * Email: mdshamim723@gmail.com
 */

public interface PersonCriteriaRepository {

    public List<Person> findAllPersonList();

    public List<PersonDTO> findAllPersonDTOListFromPerson();

    public List<Map<String, Object>> findDataFromMultipleRoot();

}
