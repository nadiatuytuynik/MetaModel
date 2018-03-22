package com.netcracker.util.exceptions;

public class DriverNotFoundException extends RuntimeException{
    public DriverNotFoundException(String s, ClassNotFoundException e) {
            super(s,e);
        }
}

