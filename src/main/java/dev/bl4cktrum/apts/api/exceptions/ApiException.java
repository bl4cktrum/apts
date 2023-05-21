package dev.bl4cktrum.apts.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }

}
