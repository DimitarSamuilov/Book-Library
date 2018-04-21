package com.book.library.booklibrary.order.controller;

import com.book.library.booklibrary.home.exception.NoSuchResourceException;
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

    @RequestMapping(method = RequestMethod.PATCH, path = "/markViewed/{id}")
    public String markViewedNotification(@PathVariable(name = "id") String id) {
        Long notificationId;
        try {
            notificationId = Long.valueOf(id);

        } catch (NumberFormatException nfe) {
            throw new NoSuchResourceException("invalid notification id");
        }
        this.notificationServiceInterface.markNotificationViewed(notificationId);
        return "{id:" + notificationId + "}";
    }

    // TODO: 21.4.2018 г. json exception handling

}
