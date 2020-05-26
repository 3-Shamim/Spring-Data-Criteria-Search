package com.learningstuff.springdatacriteriaqueries.dto;

import com.learningstuff.springdatacriteriaqueries.models.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২৩/৫/২০
 * Time: ৫:১৮ PM
 * Email: mdshamim723@gmail.com
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PhoneDTO {

    private Long id;

    private String number;

    private PhoneType type;

    private PersonMediumDTO person;

}
