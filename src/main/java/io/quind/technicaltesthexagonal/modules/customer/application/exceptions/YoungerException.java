package io.quind.technicaltesthexagonal.modules.customer.application.exceptions;

public class YoungerException extends RuntimeException{
    public YoungerException(String message) {
        super(message);
    }
}
