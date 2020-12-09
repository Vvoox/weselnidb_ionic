package com.db.demo.models;

import com.db.demo.Enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Demand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String location;
    private String destination;
    private LocalDate date;
    private double price;
    private Status status;
    private boolean driverDecision;

    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate modifiedAt;

    @OneToOne
    @JsonIgnoreProperties({"demand","client"})
    private Client client;

    @OneToOne
    @JsonIgnoreProperties({"demand","client"})
    private Driver driver;


}
