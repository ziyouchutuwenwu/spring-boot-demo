package com.mmc.springbootdemo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    private int code;
    private String message;
}
