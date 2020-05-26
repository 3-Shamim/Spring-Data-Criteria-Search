package com.learningstuff.springdatacriteriaqueries.repositories;

import com.learningstuff.springdatacriteriaqueries.dao.PersonCriteriaRepository;
import com.learningstuff.springdatacriteriaqueries.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২২/৫/২০
 * Time: ৮:৫২ PM
 * Email: mdshamim723@gmail.com
 */

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, PersonCriteriaRepository {

}
