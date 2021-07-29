package com.example.Projekt;

import org.junit.jupiter.api.Test;

public class UpperCaseTest {

    @Test
    public void upperCaseTest(){
        String hello = "Hello, World!";

        String upperHello = hello.toUpperCase();

        org.assertj.core.api.Assertions.assertThat(upperHello).isEqualTo("HELLO, WORLD!");
    }
}
