package com.book.library.booklibrary.order.service.notification;

import com.book.library.booklibrary.order.enums.NotificationType;
import com.book.library.booklibrary.order.model.DTO.BasicNotification;
import com.book.library.booklibrary.order.model.entity.Notification;
import com.book.library.booklibrary.order.model.entity.Order;
import com.book.library.booklibrary.order.repository.NotificationRepository;
import com.book.library.booklibrary.user.model.entity.User;
import com.book.library.booklibrary.user.service.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class NotificationService implements NotificationServiceInterface {

    public static final Integer NOTIFICATION_DEFAULT_PERIOD = 7;
    private static final SimpleDateFormat NOTIFICATION_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyy");
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
    public CompletableFuture<Void> asyncAddCurrentUserNotification(
            Order order,
            NotificationType notificationType,
            Date showDate,
            Principal principal
    ) {
        Optional<User> currentUser = this.userService.getUserByUsername(principal.getName());
        if (!currentUser.isPresent()) {
            return CompletableFuture.completedFuture(null);
        }

        Notification notification = new Notification();
        notification.setDate(showDate);
        notification.setNotificationType(notificationType);
        notification.setViewed(false);
        notification.setRelatedOrder(order);

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
                        .findFirst5ByRelatedOrder_CustomerUsername(principal.getName()).stream()
                        .map(notification -> this.modelMapper.map(notification, BasicNotification.class))
                        .map(basicNotification ->
                                basicNotification.setFormattedMessage
                                        (this.processNotificationMessage
                                                (basicNotification.getNotificationType(),
                                                        basicNotification.getRelatedOrder().getOrderBook().getName(), basicNotification.getDate())))
                        .collect(Collectors.toList())
                ;
    }

    private String processNotificationMessage(NotificationType notificationType, String bookName, Date notificationDate) {

        return String.format(notificationType.getNotificationFormat(), bookName, NotificationService.NOTIFICATION_DATE_FORMAT.format(notificationDate));
    }

    @Override
    public void markNotificationViewed(Long notificationId) {
        Optional<Notification> notificationOptional = this.notificationRepository.findById(notificationId);
        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            notification.setViewed(true);
            this.notificationRepository.save(notification);
        }
    }
}
