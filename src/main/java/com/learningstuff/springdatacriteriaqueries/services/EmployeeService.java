package com.learningstuff.springdatacriteriaqueries.services;

import com.learningstuff.springdatacriteriaqueries.models.Employee;
import com.learningstuff.springdatacriteriaqueries.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২২/৫/২০
 * Time: ৫:০০ PM
 * Email: mdshamim723@gmail.com
 */

@AllArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployee() {
        return employeeRepository.findEmployeeList();
    }

    public List<Employee> findAllEmployeeByName(String name) {
        return employeeRepository.findEmployeeListByName(name);
    }

    public Employee findEmployeeById(long id) {
        return employeeRepository.findOptionalEmployeeById(id).orElse(null);
    }

}
