package com.learningstuff.springdatacriteriaqueries.dao;

import com.learningstuff.springdatacriteriaqueries.dto.LiteEmployeeDTO;
import com.learningstuff.springdatacriteriaqueries.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২২/৫/২০
 * Time: ৪:০২ PM
 * Email: mdshamim723@gmail.com
 */

public interface EmployeeCriteriaRepository {

    public Optional<Employee> findOptionalEmployeeById(long id);

    public List<Employee> findEmployeeList();

    public Page<Employee> findEmployeeList(Pageable pageable);

    public List<Employee> findEmployeeListByName(String name);

    public List<Employee> findEmployeeListByNameLike(String name);

    public List<String> findAllNameFromEmployee();

    public List<Object[]> findAllNameAndEmailFromEmployeeAsArray();

    public List<Object> findAllNameAndEmailFromEmployee();

    public List<LiteEmployeeDTO> findAllLiteEmployeeFromEmployee();

    public List<LiteEmployeeDTO> findAllTupleFromEmployee();

    public List<Employee> findEmployeeListByDOJ(LocalDate date);

}
