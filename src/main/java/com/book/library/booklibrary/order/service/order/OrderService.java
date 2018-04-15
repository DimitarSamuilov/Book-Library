package com.book.library.booklibrary.order.service.order;

import com.book.library.booklibrary.library.model.entity.Book;
import com.book.library.booklibrary.library.service.book.BookService;
import com.book.library.booklibrary.library.service.book.BookServiceInterface;
import com.book.library.booklibrary.order.enums.OrderType;
import com.book.library.booklibrary.order.model.DTO.AddOrder;
import com.book.library.booklibrary.order.model.entity.Order;
import com.book.library.booklibrary.order.repository.OrderRepository;
import com.book.library.booklibrary.order.service.notification.NotificationService;
import com.book.library.booklibrary.order.service.notification.NotificationServiceInterface;
import com.book.library.booklibrary.user.model.entity.User;
import com.book.library.booklibrary.user.service.UserService;
import com.book.library.booklibrary.user.service.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

import static com.book.library.booklibrary.order.service.notification.NotificationService.BUY_BOOK_MESSAGE;

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
    public AddOrder prepareOrder(Long bookId) throws Exception {
        Optional<Book> bookOptional = this.bookService.getBookById(bookId);
        if (!bookOptional.isPresent()) {
            throw new Exception("No such book!");
        }
        AddOrder bookOrder = new AddOrder();
        bookOrder.setBookId(bookId);
        bookOrder.setBookName(bookOptional.get().getName());

        return bookOrder;
    }

    @Override
    public Long makeOrder(AddOrder orderDetails, Principal principal) throws Exception {
        Order finalOrder;
        Optional<Book> bookOptional = this.bookService.getBookById(orderDetails.getBookId());
        if (!bookOptional.isPresent()) {
            throw new Exception("invalid book");
        }
        Optional<User> userOptional = this.userService.getUserByUsername(principal.getName());

        if (!userOptional.isPresent()) {
            throw new Exception("User not logged");
        }

        finalOrder = this.modelMapper.map(orderDetails, Order.class);
        finalOrder.setOrderBook(bookOptional.get());
        finalOrder.setCustomer(userOptional.get());

        String message = "";
        if (finalOrder.getOrderType().equals(OrderType.BUY)) {
            message = NotificationService.BUY_BOOK_MESSAGE;
        } else {
            message = NotificationService.RENT_BOOK_MESSAGE;
        }

        this.notificationService.asyncAddCurrentUserNotification(String.format(message, orderDetails.getBookName()), new Date(), principal);

        return this.orderRepository.save(finalOrder).getId();
    }
}
