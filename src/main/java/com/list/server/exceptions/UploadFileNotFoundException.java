package com.list.server.exceptions;

public class UploadFileNotFoundException extends UploadException {

    public UploadFileNotFoundException(String message) {
        super(message);
    }

    public UploadFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
