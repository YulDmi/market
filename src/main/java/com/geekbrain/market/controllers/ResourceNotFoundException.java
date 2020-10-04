package com.geekbrain.market.controllers;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String text) {
        System.out.println(text);
    }
}
