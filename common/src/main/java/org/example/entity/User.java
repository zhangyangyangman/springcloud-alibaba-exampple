package org.example.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "user")
public class User {
    @Id
    private String id;
    private String name;
    private String age;
}
