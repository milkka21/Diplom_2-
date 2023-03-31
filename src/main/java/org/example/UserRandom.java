package org.example;

import org.apache.commons.lang3.RandomStringUtils;


public class UserRandom {

    public UserCreate generic() {
        return new UserCreate("mikikio", "1234", "qwertyT");

    }
    public UserCreate random() {
        return new UserCreate(RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(6), RandomStringUtils.randomAlphanumeric(8)+ "@mail.ru");
    }
}