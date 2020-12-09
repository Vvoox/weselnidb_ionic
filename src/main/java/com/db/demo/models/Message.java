package com.db.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<Conversation> conversations ;

    private LocalDate date;

    @OneToOne
    @JsonIgnoreProperties({"message","user"})
    private User source;

    @OneToOne
    @JsonIgnoreProperties({"message","user"})
    private User destination;

}

