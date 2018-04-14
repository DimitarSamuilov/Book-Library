package com.book.library.booklibrary.order.service.notification;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class NotificationService implements NotificationServiceInterface{


    @Override
    @Async
    public CompletableFuture<Void> printAsynchMessage() {
        System.out.println("Asynch task completed");
        return CompletableFuture.completedFuture(null);
    }
}
