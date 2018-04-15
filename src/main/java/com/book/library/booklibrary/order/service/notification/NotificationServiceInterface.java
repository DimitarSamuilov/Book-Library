package com.book.library.booklibrary.order.service.notification;

import java.security.Principal;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

public interface NotificationServiceInterface {

    CompletableFuture<Void> printAsynchMessage();

    CompletableFuture<Void> asyncAddCurrentUserNotification(String message, Date showDate, Principal principal);

    void addNotificationForUser(String message, Date showDate, String username);
}
