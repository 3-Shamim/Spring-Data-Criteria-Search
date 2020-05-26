package com.learningstuff.springdatacriteriaqueries.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.*;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২২/৫/২০
 * Time: ৮:৩৮ PM
 * Email: mdshamim723@gmail.com
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String nickName;

    private String address;

    private LocalDateTime createdAt;

    @Version
    @JsonProperty(access = READ_ONLY)
    private int version;

//    @JsonIgnore
    @JsonProperty(access = WRITE_ONLY)
    @OneToMany(mappedBy = "person", orphanRemoval = true, cascade = CascadeType.ALL)
    @NotNull(message = "Phone's must not be null")
    private List<Phone> phones;

    @PrePersist
    public void perPersist() {
        this.createdAt = LocalDateTime.now();
    }

}
