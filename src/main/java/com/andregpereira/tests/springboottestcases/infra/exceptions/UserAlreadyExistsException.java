package com.andregpereira.tests.springboottestcases.infra.exceptions;

import java.io.Serial;

public class UserAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3863700555653485145L;

    public UserAlreadyExistsException(String msg) {
        super(msg);
    }

}
