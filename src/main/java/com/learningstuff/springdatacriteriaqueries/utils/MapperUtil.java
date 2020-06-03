package com.learningstuff.springdatacriteriaqueries.utils;

import com.learningstuff.springdatacriteriaqueries.dto.PersonMediumDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ৩/৬/২০
 * Time: ১২:০৮ PM
 * Email: mdshamim723@gmail.com
 */

@Component
public class MapperUtil {

    public PersonMediumDTO buildPersonMediumDTO(long id, String name, String nickName, String address, LocalDateTime createdAt, int version) {
        return new PersonMediumDTO(id, name, nickName, address, createdAt, version);
    }

}
