package com.book.library.booklibrary.order.enums;

import com.book.library.booklibrary.library.model.entity.Category;

public enum NotificationType {

    BUY_BOOK("The book %s was successfully ordered!"),
    RENT_BOOK("The book %s was successfully rented until %s days"),
    RETURN_BOOK("You must return the book %s by %s");

    private String notificationFormat;

    NotificationType(String notificationFormat) {
        this.notificationFormat = notificationFormat;
    }

    public String getNotificationFormat(){
        return  this.notificationFormat;
    }

}
