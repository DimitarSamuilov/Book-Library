package com.book.library.booklibrary.order.service.notification;

import java.util.concurrent.CompletableFuture;

public interface NotificationServiceInterface {

    public CompletableFuture<Void> printAsynchMessage();
}
