package org.oregami.util.exception;

public class OregamiRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 2186326024270318546L;

    public OregamiRuntimeException() {
        super();
    }

    public OregamiRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OregamiRuntimeException(String message) {
        super(message);
    }

    public OregamiRuntimeException(Throwable cause) {
        super(cause);
    }

}
