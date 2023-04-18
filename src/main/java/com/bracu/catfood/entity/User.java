package com.bracu.catfood.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "user")
@Data
public class User {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Integer type;
}
