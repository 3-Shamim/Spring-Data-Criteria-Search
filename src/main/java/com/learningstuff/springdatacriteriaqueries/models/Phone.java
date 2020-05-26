package com.learningstuff.springdatacriteriaqueries.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২২/৫/২০
 * Time: ৮:৪৪ PM
 * Email: mdshamim723@gmail.com
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"person"})
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @Enumerated(EnumType.STRING)
    private PhoneType type;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

}
