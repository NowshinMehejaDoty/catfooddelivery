package com.bracu.catfood.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "product")
@Data
public class Product {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Food food;

    @Column
    private String name;

    @Column
    private Double price;
}