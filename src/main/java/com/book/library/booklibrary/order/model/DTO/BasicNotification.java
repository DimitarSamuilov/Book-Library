package com.book.library.booklibrary.order.model.DTO;

import com.book.library.booklibrary.order.enums.NotificationType;
import com.book.library.booklibrary.order.model.entity.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

public class BasicNotification {

    private Long id;

    @JsonIgnore
    private NotificationType notificationType;

    private Date startDate;

    private boolean viewed;

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

    public BasicNotification setFormattedMessage(String message) {
        this.setMessage(message);
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
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

    @Override
    public String toString() {
        return "BasicNotification{" +
                "id=" + id +
                ", notificationType=" + notificationType +
                ", startDate=" + startDate +
                ", is_viewed=" + viewed +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
