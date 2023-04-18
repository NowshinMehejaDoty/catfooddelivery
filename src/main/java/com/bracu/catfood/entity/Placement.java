package com.bracu.catfood.entity;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;

@Entity(name = "placement")
@Data
public class Placement {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Double totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @Column
    private Integer paymentMethod;

    @Column
    private String houseNo;

    @Column
    private String roadNo;

    @Column
    private String district;

    @Column
    private String custName;

    @Column
    private String custMobile;

    @Column
    private Integer cartId;

}