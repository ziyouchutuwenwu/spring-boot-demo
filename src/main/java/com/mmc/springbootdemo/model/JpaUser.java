package com.mmc.springbootdemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "jpa_users")
public class JpaUser {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32)
    @Getter
    @Setter
    private String name;

    @Column(length = 32)
    @Getter
    @Setter
    private Integer age;

    @Column(length = 64)
    @Getter
    @Setter
    private String pwd;
}