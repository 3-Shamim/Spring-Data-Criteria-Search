package com.learningstuff.springdatacriteriaqueries.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২৪/৪/২০
 * Time: ১০:২৭ AM
 * Email: mdshamim723@gmail.com
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Book {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String author;

}
