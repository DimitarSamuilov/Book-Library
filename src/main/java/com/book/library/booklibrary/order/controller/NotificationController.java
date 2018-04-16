package com.book.library.booklibrary.order.controller;

import com.book.library.booklibrary.order.model.DTO.BasicNotification;
import com.book.library.booklibrary.order.service.notification.NotificationService;
import com.book.library.booklibrary.order.service.notification.NotificationServiceInterface;
import com.book.library.booklibrary.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.BasicPermission;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private UserService userService;
    private NotificationServiceInterface notificationServiceInterface;


    public NotificationController(UserService userService, NotificationServiceInterface notificationServiceInterface) {
        this.userService = userService;
        this.notificationServiceInterface = notificationServiceInterface;
    }

    @GetMapping("/latestNotifications")
    public List<BasicNotification> latestNotifications(Principal principal) {
           return this.notificationServiceInterface.getLatestNotifications(principal);
    }

    @PostMapping("/markViewed/{id}")
    public String markViewedNotification(@PathVariable(name = "id") String id){
        try{
            Long notificationId= Long.valueOf(id);

        }catch (NumberFormatException nfe){
            System.out.println("invalid convert");
        }
        return "{success:1}";
    }


}
