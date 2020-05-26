package com.learningstuff.springdatacriteriaqueries.controllers;

import com.learningstuff.springdatacriteriaqueries.models.Employee;
import com.learningstuff.springdatacriteriaqueries.repositories.EmployeeRepository;
import com.learningstuff.springdatacriteriaqueries.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২২/৫/২০
 * Time: ৫:০২ PM
 * Email: mdshamim723@gmail.com
 */

@RestController
@RequestMapping(value = "/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @GetMapping(value = "")
    public ResponseEntity<?> getAllEmployeeList() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAllEmployee());
    }

    @GetMapping(value = "/page")
    public ResponseEntity<?> getAllEmployeePage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findEmployeeList(pageRequest));
    }

    @GetMapping(value = "/names")
    public ResponseEntity<?> getAllEmployeeNameList() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findAllNameFromEmployee());
    }

    @GetMapping(value = "/name-and-email-array")
    public ResponseEntity<?> getAllEmployeeNameAndEmailListAsArray() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findAllNameAndEmailFromEmployeeAsArray());
    }

    @GetMapping(value = "/name-and-email")
    public ResponseEntity<?> getAllEmployeeNameAndEmailList() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findAllNameAndEmailFromEmployee());
    }

    @GetMapping(value = "/lite")
    public ResponseEntity<?> getAllLiteEmployeeOfEmployeeList() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findAllLiteEmployeeFromEmployee());
    }

    @GetMapping(value = "/tuple")
    public ResponseEntity<?> getAllTupleOfEmployeeList() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findAllTupleFromEmployee());
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable(value = "id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployeeById(id));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<?> getAllEmployeeListByName(@PathVariable(value = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAllEmployeeByName(name));
    }

    @GetMapping(value = "/doj/{doj}")
    public ResponseEntity<?> getAllEmployeeListByDOJ(@PathVariable(value = "doj") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findEmployeeListByDOJ(date));
    }

    @GetMapping(value = "/name-like/{name}")
    public ResponseEntity<?> getAllEmployeeListByNameLike(@PathVariable(value = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findEmployeeListByNameLike(name));
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveEmployee(@RequestBody @Valid Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(employee));
    }

}
