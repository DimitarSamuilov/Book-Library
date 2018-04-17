package com.book.library.booklibrary.order.service.notification;

import com.book.library.booklibrary.order.model.DTO.BasicNotification;
import com.book.library.booklibrary.order.model.entity.Notification;
import com.book.library.booklibrary.order.repository.NotificationRepository;
import com.book.library.booklibrary.user.model.entity.User;
import com.book.library.booklibrary.user.service.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class NotificationService implements NotificationServiceInterface {

    public static final String BUY_BOOK_MESSAGE = "The order for %s is being processed!";
    public static final String RENT_BOOK_MESSAGE = "The rent book order for %s is being processed";
    public static final String RENT_BOOK_RETURN_MESSAGE = "You need to return the book  %s you rented on %s";

    private UserServiceInterface userService;
    private NotificationRepository notificationRepository;
    private ModelMapper modelMapper;

    public NotificationService(UserServiceInterface userService, NotificationRepository notificationRepository, ModelMapper modelMapper) {
        this.userService = userService;
        this.notificationRepository = notificationRepository;
        this.modelMapper = modelMapper;
    }


    // TODO: 17.4.2018 г. refactor and add to frontend and do chrons for notification return book 
    @Override
    @Async
    public CompletableFuture<Void> asyncAddCurrentUserNotification(String message, Date showDate, Principal principal) {
        Optional<User> currentUser = this.userService.getUserByUsername(principal.getName());
        if (!currentUser.isPresent()) {
            return CompletableFuture.completedFuture(null);
        }
        Notification notification = new Notification();

        notification.setDate(showDate);
        notification.setMessage(message);
        notification.setViewed(false);
        notification.setUser(currentUser.get());

        this.notificationRepository.save(notification);

        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void addNotificationForUser(String message, Date showDate, String username) {

    }

    @Override
    public List<BasicNotification> getLatestNotifications(Principal principal) {
        if (principal == null) {
            return new ArrayList<>();
        }
        return
                this.notificationRepository
                        .findFirst5ByUserUsername(principal.getName()).stream()
                        .map(notification ->
                                this.modelMapper
                                        .map(notification, BasicNotification.class))
                        .collect(Collectors.toList());
    }

    @Override
    public void markNotificationViewed(Long notificationId) {
        Optional<Notification> notificationOptional = this.notificationRepository.findById(notificationId);
        if (notificationOptional.isPresent()){
            Notification notification = notificationOptional.get();
            notification.setViewed(true);
            this.notificationRepository.save(notification);
        }
    }
}
