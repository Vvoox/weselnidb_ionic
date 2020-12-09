package com.db.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    private double location;
    private LocalDate date;

    @OneToOne(mappedBy = "client", cascade = {CascadeType.REMOVE})
    @JsonIgnoreProperties({"client","driver"})
    private Demand demand;


    @OneToOne
    @JsonIgnoreProperties({"user","driver"})
    private User user;



}
