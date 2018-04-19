package com.book.library.booklibrary.order.service.notification;

import com.book.library.booklibrary.order.enums.NotificationType;
import com.book.library.booklibrary.order.model.DTO.BasicNotification;
import com.book.library.booklibrary.order.model.entity.Notification;
import com.book.library.booklibrary.order.model.entity.Order;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface NotificationServiceInterface {

    List<BasicNotification> getLatestNotifications(Principal principal);

    CompletableFuture<Void> asyncAddCurrentUserNotification(Order order,
                                                            NotificationType notificationType,
                                                            Date showDate,
                                                            Date startDate,
                                                            Principal principal);

    void markNotificationViewed(Long notificationId);

    void deleteNotification(Long notificationId);

    List<Notification> getOldNotifications();
}
