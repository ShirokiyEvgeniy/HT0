package by.epam.trainingrpa.exceptions;

/**
 * This exception class is needed to throw exceptions connected with problems "too less free space in the room"
 * @author Eugene
 * @version 1.0
 * */
public class SpaceUsageTooMuchException extends Exception {
    public SpaceUsageTooMuchException() {
        super();
    }

    public SpaceUsageTooMuchException(String message) {
        super(message);
    }

    public SpaceUsageTooMuchException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpaceUsageTooMuchException(Throwable cause) {
        super(cause);
    }

    public SpaceUsageTooMuchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
