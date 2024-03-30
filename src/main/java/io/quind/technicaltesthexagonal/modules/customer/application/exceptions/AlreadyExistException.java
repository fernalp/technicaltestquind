package io.quind.technicaltesthexagonal.modules.customer.application.exceptions;

public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException(String message) {
        super(message);
    }


}
