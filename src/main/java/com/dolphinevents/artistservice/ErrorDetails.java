package com.dolphinevents.artistservice;

import java.time.LocalDate;

import org.springframework.http.HttpStatusCode;

public class ErrorDetails {
    
    private HttpStatusCode status;

    private LocalDate timestamp;

    private String message;

    private String details;

    public ErrorDetails(HttpStatusCode status, LocalDate timestamp, String message, String details) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public HttpStatusCode getStatus() {
        return status;
    }

    public void setStatus(HttpStatusCode status) {
        this.status = status;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
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

    
}
