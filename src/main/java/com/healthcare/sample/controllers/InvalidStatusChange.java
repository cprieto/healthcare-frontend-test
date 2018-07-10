package com.healthcare.sample.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
class InvalidStatusChange extends RuntimeException {
    InvalidStatusChange(String message) {
        super(message);
    }
}
