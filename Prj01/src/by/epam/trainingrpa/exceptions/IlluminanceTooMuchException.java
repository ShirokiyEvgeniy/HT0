package by.epam.trainingrpa.exceptions;

/**
 * This exception class is needed to throw exceptions connected with problems "too much light in the room"
 * @author Eugene
 * @version 1.0
 * */
public class IlluminanceTooMuchException extends Exception {
    public IlluminanceTooMuchException() {
        super();
    }

    public IlluminanceTooMuchException(String message) {
        super(message);
    }

    public IlluminanceTooMuchException(String message, Throwable cause) {
        super(message, cause);
    }

    public IlluminanceTooMuchException(Throwable cause) {
        super(cause);
    }

    public IlluminanceTooMuchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
