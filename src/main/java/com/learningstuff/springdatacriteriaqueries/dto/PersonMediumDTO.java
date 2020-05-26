package com.learningstuff.springdatacriteriaqueries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২৩/৫/২০
 * Time: ১১:২১ AM
 * Email: mdshamim723@gmail.com
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonMediumDTO {

    private long id;

    private String name;

    private String nickName;

    private String address;

    private LocalDateTime createdAt;

    private int version;

}
