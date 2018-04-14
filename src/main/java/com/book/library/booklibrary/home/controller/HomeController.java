package com.book.library.booklibrary.home.controller;

import com.book.library.booklibrary.order.service.notification.NotificationServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private NotificationServiceInterface notificationService;

    public HomeController(NotificationServiceInterface notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/")
    public String home()
    {
        this.notificationService.printAsynchMessage();
        return "home/index";
    }
}
