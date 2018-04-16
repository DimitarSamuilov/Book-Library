package com.book.library.booklibrary.order.service.notification;

import com.book.library.booklibrary.order.model.DTO.BasicNotification;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface NotificationServiceInterface {

    List<BasicNotification> getLatestNotifications(Principal principal);

    CompletableFuture<Void> asyncAddCurrentUserNotification(String message, Date showDate, Principal principal);

    void addNotificationForUser(String message, Date showDate, String username);

    void markNotificationViewed(Long notificationId);
}
