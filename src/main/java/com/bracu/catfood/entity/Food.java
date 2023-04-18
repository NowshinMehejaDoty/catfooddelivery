package com.bracu.catfood.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "food")
@Data
public class Food {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer type;
}