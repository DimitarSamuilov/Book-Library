package com.book.library.booklibrary.order.model.DTO;

import java.util.Date;

public class BasicNotification {

    private Long id;

    private String message;

    private Date date;

    public BasicNotification() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
