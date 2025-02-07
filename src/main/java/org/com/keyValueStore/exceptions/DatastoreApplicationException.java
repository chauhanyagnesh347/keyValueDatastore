package org.com.keyValueStore.exceptions;

public class DatastoreApplicationException extends RuntimeException {
    public DatastoreApplicationException(String message) {
        super(message);
    }
}
