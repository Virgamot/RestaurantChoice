package ru.graduation.util.exception;

import org.springframework.http.HttpStatus;

public class TimeExpiredException extends ApplicationException {

    public static final String TIME_EXPIRED_EXCEPTION = "exception.vote.timeExpired";

    public TimeExpiredException(String args) {
        super(ErrorType.TIME_EXPIRED, TIME_EXPIRED_EXCEPTION, HttpStatus.CONFLICT, args);
    }
}
