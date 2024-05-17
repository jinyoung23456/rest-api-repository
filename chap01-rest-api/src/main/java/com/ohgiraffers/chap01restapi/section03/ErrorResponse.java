package com.ohgiraffers.chap01restapi.section03;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private String code;
    private String description;
    private String detail;

}
