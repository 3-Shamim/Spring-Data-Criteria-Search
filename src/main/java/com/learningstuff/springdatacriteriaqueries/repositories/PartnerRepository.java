package com.learningstuff.springdatacriteriaqueries.repositories;

import com.learningstuff.springdatacriteriaqueries.models.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২৩/৫/২০
 * Time: ১০:০৯ AM
 * Email: mdshamim723@gmail.com
 */

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
