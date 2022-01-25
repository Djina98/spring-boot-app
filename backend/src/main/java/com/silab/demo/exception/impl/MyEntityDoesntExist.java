package com.silab.demo.exception.impl;

import com.silab.demo.exception.MyException;

public class MyEntityDoesntExist extends MyException {
    public MyEntityDoesntExist(String message) {
        super(message);
    }
}
