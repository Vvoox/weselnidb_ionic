package com.db.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean available;
//    private double longitude;
//    private double latitude;

    @OneToOne
    @JsonIgnoreProperties({"driver","client"})
    private User user;

    @OneToOne(mappedBy = "driver", cascade = {CascadeType.REMOVE})
    @JsonIgnoreProperties({"driver","client"})
    private Demand demand;



}
