package com.silab.demo.exception.impl;

import com.silab.demo.exception.MyException;

public class MyEntityAlreadyExists extends MyException {
    public MyEntityAlreadyExists(String message) {
        super(message);
    }
}
