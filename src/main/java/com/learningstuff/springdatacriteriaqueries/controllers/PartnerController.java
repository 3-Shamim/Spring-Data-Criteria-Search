package com.learningstuff.springdatacriteriaqueries.controllers;

import com.learningstuff.springdatacriteriaqueries.models.Partner;
import com.learningstuff.springdatacriteriaqueries.repositories.PartnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২৩/৫/২০
 * Time: ১০:১৯ AM
 * Email: mdshamim723@gmail.com
 */

@RestController
@RequestMapping(value = "/partners")
@AllArgsConstructor
public class PartnerController {

    private final PartnerRepository partnerRepository;

    @GetMapping(value = "")
    public ResponseEntity<?> getAllPartner() {
        return ResponseEntity.status(HttpStatus.OK).body(partnerRepository.findAll());
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> savePartner(@RequestBody @Valid Partner partner) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partnerRepository.save(partner));
    }

}
