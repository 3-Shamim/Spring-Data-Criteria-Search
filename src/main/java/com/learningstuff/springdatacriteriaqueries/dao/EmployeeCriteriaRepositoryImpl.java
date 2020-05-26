package com.learningstuff.springdatacriteriaqueries.dao;

import com.learningstuff.springdatacriteriaqueries.dto.LiteEmployeeDTO;
import com.learningstuff.springdatacriteriaqueries.models.Book;
import com.learningstuff.springdatacriteriaqueries.models.Employee;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২২/৫/২০
 * Time: ৪:০৩ PM
 * Email: mdshamim723@gmail.com
 */

@AllArgsConstructor
@Repository
public class EmployeeCriteriaRepositoryImpl implements EmployeeCriteriaRepository {

    private final EntityManager em;

    @Override
    public Optional<Employee> findOptionalEmployeeById(long id) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("id"), id));

        TypedQuery<Employee> query = em.createQuery(criteriaQuery);

        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Employee> findEmployeeList() {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root);

        TypedQuery<Employee> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public Page<Employee> findEmployeeList(Pageable pageable) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.select(root);

        TypedQuery<Employee> query = em.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());

        List<Employee> result = query.getResultList();

        CriteriaQuery<Long> countCriteriaQuery = builder.createQuery(Long.class);
        Root<Employee> countRoot = countCriteriaQuery.from(Employee.class);
        countCriteriaQuery.select(builder.count(countRoot));

        TypedQuery<Long> countQuery = em.createQuery(countCriteriaQuery);

        Long totalItems = countQuery.getSingleResult();

        return new PageImpl<>(result, pageable, totalItems);
    }

    @Override
    public List<Employee> findEmployeeListByName(String name) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("name"), name));

        TypedQuery<Employee> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public List<Employee> findEmployeeListByNameLike(String name) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.select(root);

        ParameterExpression<String> nameParameter = builder.parameter(String.class);

//        criteriaQuery.where(builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        criteriaQuery.where(builder.like(builder.lower(root.get("name")), nameParameter));

        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
        query.setParameter(nameParameter, "%" + name.toLowerCase() + "%");

        return query.getResultList();
    }

    @Override
    public List<String> findAllNameFromEmployee() {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root.get("name"));

        TypedQuery<String> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public List<LiteEmployeeDTO> findAllTupleFromEmployee() {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        Path<Object> id = root.get("id");
        Path<Object> name = root.get("name");
        Path<Object> doj = root.get("doj");

        criteriaQuery.multiselect(id, name, doj);

        TypedQuery<Tuple> query = em.createQuery(criteriaQuery);

        List<Tuple> resultList = query.getResultList();

        return resultList.stream().map(tuple -> {
            long _id = (long) tuple.get(id);
            String _name = (String) tuple.get(name);
            LocalDate _doj = (LocalDate) tuple.get(doj);

            System.out.println(_id + " => " + _name + " => " + _doj);
            return new LiteEmployeeDTO(_id, _name, _doj);
        }).collect(Collectors.toList());
    }

    @Override
    public List<Object> findAllNameAndEmailFromEmployee() {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = builder.createQuery(Object.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        Path<Object> name = root.get("name");
        Path<Object> email = root.get("email");

        criteriaQuery.multiselect(name, email);

        TypedQuery<Object> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public List<Object[]> findAllNameAndEmailFromEmployeeAsArray() {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        Path<Object> name = root.get("name");
        Path<Object> email = root.get("email");

        criteriaQuery.select(builder.array(name, email));

        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public List<LiteEmployeeDTO> findAllLiteEmployeeFromEmployee() {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<LiteEmployeeDTO> criteriaQuery = builder.createQuery(LiteEmployeeDTO.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        Path<Object> id = root.get("id");
        Path<Object> name = root.get("name");
        Path<Object> doj = root.get("doj");

        criteriaQuery.select(builder.construct(LiteEmployeeDTO.class, id, name, doj));

        TypedQuery<LiteEmployeeDTO> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public List<Employee> findEmployeeListByDOJ(LocalDate date) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.where(builder.equal(root.get("doj"), date));
//        criteriaQuery.where(builder.equal(root.<LocalDate>get("doj"), date));

        TypedQuery<Employee> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }

}
