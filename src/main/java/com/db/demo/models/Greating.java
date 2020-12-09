package com.db.demo.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


public class Greating {

    private String content;

    public Greating() {}

    public Greating(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }


}
