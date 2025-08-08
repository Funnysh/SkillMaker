package com.frantisek.herynek.SkillMaker.exceptions;

public class PasswordNotMatchingException extends RuntimeException {
    public PasswordNotMatchingException(String message) {
        super(message);
    }
}
