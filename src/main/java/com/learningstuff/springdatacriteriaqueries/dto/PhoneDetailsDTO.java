package com.learningstuff.springdatacriteriaqueries.dto;

import com.learningstuff.springdatacriteriaqueries.models.PhoneType;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২৩/৫/২০
 * Time: ৫:১৮ PM
 * Email: mdshamim723@gmail.com
 */

public interface PhoneDetailsDTO {

    long getId();

    String getNumber();

    @Value(value = "#{target.id + ' -> ' + target.number}")
    String getNumberAndId();

    PhoneType getType();

    @Value(value = "#{@mapperUtil.buildPersonMediumDTO(target.personId, target.name, target.nickName, target.address, target.createdAt, target.version)}")
    PersonMediumDTO getPerson();

}
