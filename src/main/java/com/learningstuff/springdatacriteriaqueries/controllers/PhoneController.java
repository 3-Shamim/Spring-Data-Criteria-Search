package com.learningstuff.springdatacriteriaqueries.controllers;

import com.learningstuff.springdatacriteriaqueries.repositories.PhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২৩/৫/২০
 * Time: ১১:০৬ AM
 * Email: mdshamim723@gmail.com
 */

@RestController
@RequestMapping(value = "/phones")
@AllArgsConstructor
public class PhoneController {

    private final PhoneRepository phoneRepository;

    @GetMapping(value = "")
    public ResponseEntity<?> getAllPhoneList() {
        return ResponseEntity.status(HttpStatus.OK).body(phoneRepository.findAllPhoneList());
    }

    @GetMapping(value = "/dto")
    public ResponseEntity<?> getAllPhoneDTOList() {
        return ResponseEntity.status(HttpStatus.OK).body(phoneRepository.findAllPhoneDTOList());
    }

    @GetMapping(value = "/dto-projection")
    public ResponseEntity<?> getAllPhoneDTOListProjection() {
        return ResponseEntity.status(HttpStatus.OK).body(phoneRepository.findAllPhoneDTOListProjection());
    }

    @GetMapping(value = "/details-dto")
    public ResponseEntity<?> getAllPhoneDetailsDTOList() {
        return ResponseEntity.status(HttpStatus.OK).body(phoneRepository.findAllPhoneDetailsDTO());
    }

    @GetMapping(value = "/dto-person-name")
    public ResponseEntity<?> getAllPhoneWithPersonNameDTOList() {
        return ResponseEntity.status(HttpStatus.OK).body(phoneRepository.findAllPhoneWithPersonNameDTOList());
    }

}
