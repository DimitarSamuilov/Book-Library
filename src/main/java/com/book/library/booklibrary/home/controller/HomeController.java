package com.book.library.booklibrary.home.controller;

import com.book.library.booklibrary.home.exception.NoSuchLibraryException;
import com.book.library.booklibrary.home.exception.NoSuchResourceException;
import com.book.library.booklibrary.order.service.notification.NotificationServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private NotificationServiceInterface notificationService;

    public HomeController(NotificationServiceInterface notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/")
    public String home() {
        return "home/index";
    }
}
