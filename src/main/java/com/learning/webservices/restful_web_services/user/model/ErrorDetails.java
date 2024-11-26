package com.learning.webservices.restful_web_services.user.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime timesatmp;
    private String message;
    private String details;

    public ErrorDetails(LocalDateTime timesatmp, String message, String details) {
        this.timesatmp = timesatmp;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimesatmp() {
        return timesatmp;
    }

    public void setTimesatmp(LocalDateTime timesatmp) {
        this.timesatmp = timesatmp;
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

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "timesatmp=" + timesatmp +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
