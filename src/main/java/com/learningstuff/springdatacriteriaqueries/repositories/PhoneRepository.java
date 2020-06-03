package com.learningstuff.springdatacriteriaqueries.repositories;

import com.learningstuff.springdatacriteriaqueries.dao.PhoneCriteriaRepository;
import com.learningstuff.springdatacriteriaqueries.dto.PhoneDetailsDTO;
import com.learningstuff.springdatacriteriaqueries.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২২/৫/২০
 * Time: ৯:০৩ PM
 * Email: mdshamim723@gmail.com
 */

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>, PhoneCriteriaRepository {

    @Query(value = "SELECT p.id AS id, p.number AS number, p.type AS type, per.id AS personId, per.name AS name, per.nickName AS nickName, per.address AS address, per.createdAt AS createdAt, per.version AS version FROM Phone p LEFT JOIN Person per ON per.id = p.id")
    public List<PhoneDetailsDTO> findAllPhoneDetailsDTO();

}
