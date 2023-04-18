package com.bracu.catfood.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "cart")
@Data
public class Cart {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer isPlaced;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private User user;
}