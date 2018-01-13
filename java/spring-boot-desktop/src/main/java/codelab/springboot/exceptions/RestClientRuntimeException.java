package codelab.springboot.exceptions;

/**
 * REST service invocation failed
 */
public class RestClientRuntimeException extends RuntimeException {
    public RestClientRuntimeException() {
        super();
    }

    public RestClientRuntimeException(String message) {
        super(message);
    }

    public RestClientRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestClientRuntimeException(Throwable cause) {
        super(cause);
    }

    protected RestClientRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
