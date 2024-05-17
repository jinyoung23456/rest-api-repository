package com.ohgiraffers.chap01restapi.section03;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
