package com.andregpereira.tests.springboottestcases.infra.exceptions;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3863700555653485145L;

    public UserNotFoundException(String msg) {
        super(msg);
    }

}
