package com.learningstuff.springdatacriteriaqueries.dao;

import com.learningstuff.springdatacriteriaqueries.dto.PersonDTO;
import com.learningstuff.springdatacriteriaqueries.dto.PhoneLiteDTO;
import com.learningstuff.springdatacriteriaqueries.models.Partner;
import com.learningstuff.springdatacriteriaqueries.models.Person;
import com.learningstuff.springdatacriteriaqueries.models.Phone;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২২/৫/২০
 * Time: ৮:৫৭ PM
 * Email: mdshamim723@gmail.com
 */

@Repository
@AllArgsConstructor
public class PersonCriteriaRepositoryImpl implements PersonCriteriaRepository {

    private final EntityManager em;

    @Override
    public List<Person> findAllPersonList() {

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);

        Root<Person> root = criteriaQuery.from(Person.class);

        criteriaQuery.select(root);

        TypedQuery<Person> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public List<PersonDTO> findAllPersonDTOListFromPerson() {

        // Persons dto query
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
        Root<Person> personRoot = criteriaQuery.from(Person.class);

        Path<Object> id = personRoot.get("id");
        Path<Object> name = personRoot.get("name");
        Path<Object> nickName = personRoot.get("nickName");
        Path<Object> address = personRoot.get("address");
        Path<Object> createdAt = personRoot.get("createdAt");
        Path<Object> version = personRoot.get("version");

        criteriaQuery.multiselect(id, name, nickName, address, createdAt, version);

        TypedQuery<Tuple> query = em.createQuery(criteriaQuery);

        // Phone dto query by person
        CriteriaQuery<PhoneLiteDTO> phoneCriteriaQuery = builder.createQuery(PhoneLiteDTO.class);
        Root<Phone> phoneRoot = phoneCriteriaQuery.from(Phone.class);

        Path<Object> phn_id = phoneRoot.get("id");
        Path<Object> phn_number = phoneRoot.get("number");
        Path<Object> phone_type = phoneRoot.get("type");

        ParameterExpression<Person> personParameterExpression = builder.parameter(Person.class);

        phoneCriteriaQuery.select(builder.construct(PhoneLiteDTO.class, phn_id, phn_number, phone_type));
        phoneCriteriaQuery.where(builder.equal(phoneRoot.get("person"), personParameterExpression));

        return query.getResultList().stream().map(tuple -> {

            long _id = (long) tuple.get(id);
            Person person = new Person();
            person.setId(_id);

            TypedQuery<PhoneLiteDTO> phoneQuery = em.createQuery(phoneCriteriaQuery).setParameter(personParameterExpression, person);
            List<PhoneLiteDTO> phoneLiteDTOS = phoneQuery.getResultList();

            return new PersonDTO(
                    _id,
                    (String) tuple.get(name),
                    (String) tuple.get(nickName),
                    (String) tuple.get(address),
                    (LocalDateTime) tuple.get(createdAt),
                    (int) tuple.get(version),
                    phoneLiteDTOS
            );
        }).collect(Collectors.toList());
    }

//    @Override
//    public List<PersonDTO> findAllPersonDTOListFromPerson() {
//
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<PersonDTO> criteriaQuery = builder.createQuery(PersonDTO.class);
//        Root<Person> root = criteriaQuery.from(Person.class);
//
//        Path<Object> id = root.get("id");
//        Path<Object> name = root.get("name");
//        Path<Object> nickName = root.get("nickName");
//        Path<Object> address = root.get("address");
//        Path<Object> createdAt = root.get("createdAt");
//        Path<Object> version = root.get("version");
////        Path<Object> phones = root.get("phones");
//
//        criteriaQuery.select(builder.construct(PersonDTO.class, id, name, nickName, address, createdAt, version));
//
//        TypedQuery<PersonDTO> query = em.createQuery(criteriaQuery);
//
//        return query.getResultList();
//    }

    @Override
    public List<Map<String, Object>> findDataFromMultipleRoot() {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);

        Root<Person> personRoot = criteriaQuery.from(Person.class);
        Root<Partner> partnerRoot = criteriaQuery.from(Partner.class);

        criteriaQuery.multiselect(personRoot, partnerRoot);

        Predicate personRestriction = builder.and(
                builder.equal(personRoot.get("name"), "Md. Mehedi"),
                builder.isNotEmpty(personRoot.get("phones"))
        );
        Predicate partnerRestriction = builder.and(
                builder.like(partnerRoot.get("name"), "%Md%"),
                builder.equal(partnerRoot.get("version"), 0)
        );

        criteriaQuery.where(builder.and(personRestriction, partnerRestriction));

        TypedQuery<Tuple> query = em.createQuery(criteriaQuery);

        List<Tuple> tuples = query.getResultList();

        List<Map<String, Object>> result = new ArrayList<>();

        tuples.forEach(tuple -> {
            Map<String, Object> map = new HashMap<>();

            map.put("person", tuple.get(0));
            map.put("partner", tuple.get(1));

            result.add(map);
        });


//        for (Tuple tuple : tuples) {
//            Person person = (Person)tuple.get(0);
//            if(person != null){
//                System.out.println(person);
//                List<Phone> phones = person.getPhones();
//                for (Phone phone : phones) {
//                    System.out.println(phone.getId()+"\t"+phone.getNumber()+"\t"+phone.getType().toString());
//                }
//            }
//
//            Partner partner = (Partner)tuple.get(1);
//            System.out.println(partner);
//        }


        return result;
    }
}
