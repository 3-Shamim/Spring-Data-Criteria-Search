package com.learningstuff.springdatacriteriaqueries.dao;

import com.learningstuff.springdatacriteriaqueries.dto.PhoneDTO;
import com.learningstuff.springdatacriteriaqueries.dto.PhoneWithPersonNameDTO;
import com.learningstuff.springdatacriteriaqueries.models.Phone;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২৩/৫/২০
 * Time: ১১:০২ AM
 * Email: mdshamim723@gmail.com
 */

public interface PhoneCriteriaRepository {

    public List<Phone> findAllPhoneList();

    public List<PhoneDTO> findAllPhoneDTOList();

    public List<PhoneDTO> findAllPhoneDTOListProjection();

    public List<PhoneWithPersonNameDTO> findAllPhoneWithPersonNameDTOList();


}
