package com.book.library.booklibrary.order.service.notification;

import com.book.library.booklibrary.home.exception.NoSuchNotificationException;
import com.book.library.booklibrary.home.exception.NoSuchResourceException;
import com.book.library.booklibrary.order.enums.NotificationType;
import com.book.library.booklibrary.order.model.DTO.BasicNotification;
import com.book.library.booklibrary.order.model.entity.Notification;
import com.book.library.booklibrary.order.model.entity.Order;
import com.book.library.booklibrary.order.repository.NotificationRepository;
import com.book.library.booklibrary.user.model.entity.User;
import com.book.library.booklibrary.user.service.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;
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


    @Override
    @Async
    public CompletableFuture<Void> asyncAddCurrentUserNotification(
            Order order,
            NotificationType notificationType,
            Date showDate,
            Date startDate,
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
        notification.setStartDate(startDate);

        this.notificationRepository.save(notification);

        return CompletableFuture.completedFuture(null);
    }


    @Override
    public List<BasicNotification> getLatestNotifications(Principal principal) {
        if (principal == null) {
            return new ArrayList<>();
        }

        List<Notification> latestNotifications = this.notificationRepository
                .findFirst5ByViewedAndDateAfterAndRelatedOrder_CustomerUsername(false, new Date(), principal.getName());
        return
                latestNotifications.stream()
                        .map(notification -> this.modelMapper.map(notification, BasicNotification.class))
                        .map(basicNotification ->
                                basicNotification.setFormattedMessage
                                        (this.processNotificationMessage
                                                (basicNotification.getNotificationType(),
                                                        basicNotification.getRelatedOrder())))
                        .collect(Collectors.toList())
                ;
    }

    private String processNotificationMessage(NotificationType notificationType, Order order) {
        Date displayDate;
        if (NotificationType.RETURN_BOOK == notificationType) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, order.getRentTime());
            displayDate = c.getTime();
        } else {
            displayDate = order.getOrderDate();
        }
        return String.format(notificationType.getNotificationFormat(), order.getOrderBook().getName(), NotificationService.NOTIFICATION_DATE_FORMAT.format(displayDate));
    }

    @Override
    public void markNotificationViewed(Long notificationId) {
        Optional<Notification> notificationOptional = this.notificationRepository.findById(notificationId);
        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            notification.setViewed(true);
            this.notificationRepository.save(notification);
        } else {
            throw new NoSuchNotificationException("invalid notification Id");
        }
    }

    @Override
    public void deleteNotification(Long notificationId) {
        this.notificationRepository.deleteById(notificationId);
    }

    @Override
    public List<Notification> getOldNotifications() {
        return this.notificationRepository.findAllByDateBeforeOrViewed(new Date(), true);
    }
}
