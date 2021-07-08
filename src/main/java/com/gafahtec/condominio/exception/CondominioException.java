package com.gafahtec.condominio.exception;

public class CondominioException extends RuntimeException{
    public CondominioException() {
    }

    public CondominioException(String message) {
        super(message);
    }

    public CondominioException(String message, Throwable cause) {
        super(message, cause);
    }

    public CondominioException(Throwable cause) {
        super(cause);
    }

    public CondominioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
