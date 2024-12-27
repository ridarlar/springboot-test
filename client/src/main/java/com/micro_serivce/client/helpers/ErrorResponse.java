package com.micro_serivce.client.helpers;

public class ErrorResponse {
    private String message;
    private String details;
    private String errorCode;

    public ErrorResponse(String message, String details, String errorCode) {
        this.message = message;
        this.details = details;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
