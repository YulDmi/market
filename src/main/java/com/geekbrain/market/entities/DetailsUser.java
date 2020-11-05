package com.geekbrain.market.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "details")
public class DetailsUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "address")
    private String address ;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "phone")
    private String phone ;

    @Column(name = "year")
    private Integer year;

    @Column(name = "sex")
    private String sex ;

    @OneToOne(mappedBy = "details")
    User user;

    }
