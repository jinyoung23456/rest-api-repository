package com.ohgiraffers.chap01restapi.section01.respone;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Message {

    private int httpStatusCode;
    private String message;
}
