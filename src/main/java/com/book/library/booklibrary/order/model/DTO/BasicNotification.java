package com.book.library.booklibrary.order.model.DTO;

import com.book.library.booklibrary.order.enums.NotificationType;
import com.book.library.booklibrary.order.model.entity.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class BasicNotification {

    private Long id;

    @JsonIgnore
    private NotificationType notificationType;

    private String message;

    @JsonIgnore
    private Order relatedOrder;

    private Date date;

    public BasicNotification() {
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BasicNotification setFormattedMessage(String message){
        this.setMessage(message);
        return  this;
    }
    public Order getRelatedOrder() {
        return relatedOrder;
    }

    public void setRelatedOrder(Order relatedOrder) {
        this.relatedOrder = relatedOrder;
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
