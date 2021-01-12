package com.example.Server.exceptions;

import java.util.Date;

public class ExceptionDetail {
    private Date date;
    private String message;
    private String errorDetail;

    public ExceptionDetail(Date date, String message, String errorDetail) {
        this.date = date;
        this.message = message;
        this.errorDetail = errorDetail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
