package com.mmc.springbootdemo.model;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {

    private Integer id;

    private String name;
    private Integer age;
}