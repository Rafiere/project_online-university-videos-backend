package dev.piccodev.domain.exception;

/**
 * This will be thrown when a stacktrace is not needed.
 * A stacktrace could decrease performance.
 */

public class NoStacktraceException extends RuntimeException {

    public NoStacktraceException(final String message, final Throwable cause){
        super(message, cause, false, false);
    }

    public NoStacktraceException(final String message) {
        this(message, null);
    }
}
