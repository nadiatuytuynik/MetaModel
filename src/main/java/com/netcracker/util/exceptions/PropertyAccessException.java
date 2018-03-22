package com.netcracker.util.exceptions;

import java.io.IOException;

public class PropertyAccessException extends RuntimeException{
    public PropertyAccessException(String s, IOException e) {
        super(s, e);
    }
}
