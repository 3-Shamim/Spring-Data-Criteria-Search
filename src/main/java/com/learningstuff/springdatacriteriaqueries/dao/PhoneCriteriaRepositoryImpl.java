package com.learningstuff.springdatacriteriaqueries.dao;

import com.learningstuff.springdatacriteriaqueries.dto.PersonMediumDTO;
import com.learningstuff.springdatacriteriaqueries.dto.PhoneDTO;
import com.learningstuff.springdatacriteriaqueries.dto.PhoneWithPersonNameDTO;
import com.learningstuff.springdatacriteriaqueries.models.Person;
import com.learningstuff.springdatacriteriaqueries.models.Phone;
import com.learningstuff.springdatacriteriaqueries.models.PhoneType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২৩/৫/২০
 * Time: ১১:০৩ AM
 * Email: mdshamim723@gmail.com
 */

@Repository
@AllArgsConstructor
public class PhoneCriteriaRepositoryImpl implements PhoneCriteriaRepository {

    private final EntityManager em;

    @Override
    public List<Phone> findAllPhoneList() {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Phone> criteriaQuery = builder.createQuery(Phone.class);
        Root<Phone> root = criteriaQuery.from(Phone.class);

//        root.join("person"); // Join
        root.fetch("person"); // Eager

        criteriaQuery.select(root);

        TypedQuery<Phone> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public List<PhoneDTO> findAllPhoneDTOList() {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
        Root<Phone> root = criteriaQuery.from(Phone.class);
        Join<Phone, Person> person = root.join("person");

        Path<Object> id = root.get("id");
        Path<Object> number = root.get("number");
        Path<Object> type = root.get("type");

        Path<Object> personId = person.get("id");
        Path<Object> name = person.get("name");
        Path<Object> nickName = person.get("nickName");
        Path<Object> address = person.get("address");
        Path<Object> createdAt = person.get("createdAt");
        Path<Object> version = person.get("version");

//        criteriaQuery.select(
//                builder.construct(PhoneDTO.class, id, number, type,
//                        builder.construct(PersonMediumDTO.class, personId, name, nickName, address, createdAt, version)
//                )
//        );

        criteriaQuery.multiselect(id, number, type, personId, name, nickName, address, createdAt, version);


        TypedQuery<Tuple> query = em.createQuery(criteriaQuery);

        List<Tuple> resultList = query.getResultList();

        return resultList.stream().map(tuple -> new PhoneDTO(
                (long) tuple.get(id),
                (String) tuple.get(number),
                (PhoneType) tuple.get(type),
                new PersonMediumDTO(
                        (long) tuple.get(personId),
                        (String) tuple.get(name),
                        (String) tuple.get(nickName),
                        (String) tuple.get(address),
                        (LocalDateTime) tuple.get(createdAt),
                        (int) tuple.get(version)
                )
        )).collect(Collectors.toList());
    }

    @Override
    public List<PhoneDTO> findAllPhoneDTOListProjection() {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
        Root<Phone> root = criteriaQuery.from(Phone.class);
        Join<Phone, Person> person = root.join("person");

        Path<Object> id = root.get("id");
        Path<Object> number = root.get("number");
        Path<Object> type = root.get("type");

        Path<Object> personId = person.get("id");
        Path<Object> name = person.get("name");
        Path<Object> nickName = person.get("nickName");
        Path<Object> address = person.get("address");
        Path<Object> createdAt = person.get("createdAt");
        Path<Object> version = person.get("version");

//        CompoundSelection<PersonMediumDTO> personDTO = builder.construct(PersonMediumDTO.class, personId, name, nickName, address, createdAt, version);

//        criteriaQuery.select(builder.construct(PhoneDTO.class, id, number, type, builder.construct(PersonMediumDTO.class, personId, name, nickName, address, createdAt, version)));


        criteriaQuery.multiselect(id, number, type, personId, name, nickName, address, createdAt, version);


//        criteriaQuery.multiselect(personDTO.alias("person"));


        TypedQuery<Tuple> query = em.createQuery(criteriaQuery);

        List<Tuple> resultList = query.getResultList();

        resultList.forEach(tuple -> {
            System.out.println(tuple.get(id));
            System.out.println(tuple.get(number));
            System.out.println(tuple.get(type));
//            System.out.println(tuple.get("person"));
        });

        return null;
    }

    @Override
    public List<PhoneWithPersonNameDTO> findAllPhoneWithPersonNameDTOList() {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<PhoneWithPersonNameDTO> criteriaQuery = builder.createQuery(PhoneWithPersonNameDTO.class);
        Root<Phone> root = criteriaQuery.from(Phone.class);
        Join<Phone, Person> person = root.join("person");

        Path<Object> id = root.get("id");
        Path<Object> number = root.get("number");
        Path<Object> type = root.get("type");

        criteriaQuery.select(builder.construct(PhoneWithPersonNameDTO.class, id, number, type,
                builder.concat(builder.concat(person.get("name"), " ("), builder.concat(person.get("nickName"), ")")).alias("personName")));

        TypedQuery<PhoneWithPersonNameDTO> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
