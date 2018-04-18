package com.book.library.booklibrary.order.service.order;

import com.book.library.booklibrary.home.exception.NoSuchResourceException;
import com.book.library.booklibrary.library.model.entity.Book;
import com.book.library.booklibrary.library.service.book.BookServiceInterface;
import com.book.library.booklibrary.order.enums.*;
import com.book.library.booklibrary.order.model.DTO.AddOrder;
import com.book.library.booklibrary.order.model.entity.Order;
import com.book.library.booklibrary.order.repository.OrderRepository;
import com.book.library.booklibrary.order.service.notification.*;
import com.book.library.booklibrary.user.model.entity.User;
import com.book.library.booklibrary.user.service.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;


@Service
public class OrderService implements OrderServiceInterface {

    private NotificationServiceInterface notificationService;
    private OrderRepository orderRepository;
    private BookServiceInterface bookService;
    private UserServiceInterface userService;
    private ModelMapper modelMapper;

    public OrderService(
            NotificationServiceInterface notificationService,
            OrderRepository orderRepository,
            BookServiceInterface bookService,
            UserServiceInterface userService,
            ModelMapper modelMapper
    ) {
        this.notificationService = notificationService;
        this.orderRepository = orderRepository;
        this.bookService = bookService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddOrder prepareOrder(Long bookId) throws NoSuchResourceException {

        Optional<Book> bookOptional = this.bookService.getBookById(bookId);

        if (!bookOptional.isPresent()) {
            throw new NoSuchResourceException("No such book!");
        }

        AddOrder bookOrder = new AddOrder();
        bookOrder.setBookId(bookId);
        bookOrder.setBookName(bookOptional.get().getName());
        bookOrder.setRentPrice(bookOptional.get().getRentPrice());
        bookOrder.setBuyPrice(bookOptional.get().getBuyPrice());

        return bookOrder;
    }

    @Override
    public Long makeOrder(AddOrder orderDetails, Principal principal) throws Exception {
        Order finalOrder;
        Optional<Book> bookOptional = this.bookService.getBookById(orderDetails.getBookId());
        if (!bookOptional.isPresent()) {
            throw new NoSuchResourceException("invalid book");
        }
        Optional<User> userOptional = this.userService.getUserByUsername(principal.getName());

        if (!userOptional.isPresent()) {
            throw new Exception("User not logged");
        }

        finalOrder = this.modelMapper.map(orderDetails, Order.class);
        finalOrder.setOrderBook(bookOptional.get());
        finalOrder.setCustomer(userOptional.get());

        NotificationType notificationType;
        if (finalOrder.getOrderType().equals(OrderType.BUY)) {
            notificationType = NotificationType.BUY_BOOK;
        } else {
            notificationType = NotificationType.RENT_BOOK;
        }
        Order order = this.orderRepository.save(finalOrder);


        this.notificationService.asyncAddCurrentUserNotification(order, notificationType, this.notificationDate(), principal);

        return order.getId();
    }

    private Date notificationDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, NotificationService.NOTIFICATION_DEFAULT_PERIOD);
        return c.getTime();
    }
}
