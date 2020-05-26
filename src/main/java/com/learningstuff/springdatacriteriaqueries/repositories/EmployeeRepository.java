package com.learningstuff.springdatacriteriaqueries.repositories;

import com.learningstuff.springdatacriteriaqueries.dao.EmployeeCriteriaRepository;
import com.learningstuff.springdatacriteriaqueries.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২২/৫/২০
 * Time: ৩:৫২ PM
 * Email: mdshamim723@gmail.com
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeCriteriaRepository {

}
