package org.common.core.exception;

public class ServiceErrorException extends RuntimeException{
    public ServiceErrorException(String message) {
        super(message);
    }
}
