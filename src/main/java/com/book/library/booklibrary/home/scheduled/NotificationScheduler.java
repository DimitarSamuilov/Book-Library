package com.book.library.booklibrary.home.scheduled;

import com.book.library.booklibrary.library.service.book.BookService;
import com.book.library.booklibrary.library.service.book.BookServiceInterface;
import com.book.library.booklibrary.order.service.notification.NotificationService;
import com.book.library.booklibrary.order.service.notification.NotificationServiceInterface;
import com.book.library.booklibrary.order.service.order.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationScheduler {


    private NotificationServiceInterface notificationService;
    private BookServiceInterface bookService;
    private OrderServiceInterface orderServiceInterface;

    @Autowired
    public NotificationScheduler(NotificationServiceInterface notificationService, BookServiceInterface bookService, OrderServiceInterface orderServiceInterface) {
        this.notificationService = notificationService;
        this.bookService = bookService;
        this.orderServiceInterface = orderServiceInterface;
    }
//
//    @Scheduled(fixedDelay = 30000)
//    public void deleteOldNotifications() {
//        this.notificationService.getOldNotifications().forEach(notification -> this.notificationService.deleteNotification(notification.getId()));
//    }
}
