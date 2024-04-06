package com.cy.store.service.ex;

public class UsernameNotFindException extends ServiceException{
    public UsernameNotFindException() {
    }

    public UsernameNotFindException(String message) {
        super(message);
    }

    public UsernameNotFindException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotFindException(Throwable cause) {
        super(cause);
    }

    public UsernameNotFindException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
