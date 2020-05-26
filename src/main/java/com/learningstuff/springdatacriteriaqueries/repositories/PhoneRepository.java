package com.learningstuff.springdatacriteriaqueries.repositories;

import com.learningstuff.springdatacriteriaqueries.dao.PhoneCriteriaRepository;
import com.learningstuff.springdatacriteriaqueries.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২২/৫/২০
 * Time: ৯:০৩ PM
 * Email: mdshamim723@gmail.com
 */

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>, PhoneCriteriaRepository {

}
