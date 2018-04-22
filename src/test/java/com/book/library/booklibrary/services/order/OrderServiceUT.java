package com.book.library.booklibrary.services.order;

import com.book.library.booklibrary.library.model.entity.Book;
import com.book.library.booklibrary.library.service.book.BookService;
import com.book.library.booklibrary.library.service.book.BookServiceInterface;
import com.book.library.booklibrary.order.enums.OrderType;
import com.book.library.booklibrary.order.model.DTO.AddOrder;
import com.book.library.booklibrary.order.repository.NotificationRepository;
import com.book.library.booklibrary.order.repository.OrderRepository;
import com.book.library.booklibrary.order.service.notification.NotificationService;
import com.book.library.booklibrary.order.service.notification.NotificationServiceInterface;
import com.book.library.booklibrary.order.service.order.OrderService;
import com.book.library.booklibrary.user.repository.RoleRepository;
import com.book.library.booklibrary.user.repository.UserRepository;
import com.book.library.booklibrary.user.service.UserService;
import com.book.library.booklibrary.user.service.UserServiceInterface;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrderServiceUT {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @InjectMocks
    private ModelMapper modelMapper = new ModelMapper();

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private NotificationRepository notificationRepository;


    @InjectMocks
    private BookService bookService;

    @InjectMocks
    private UserService userService;
//    private UserService userService = new UserService(this.userRepository, this.roleRepository, this.passwordEncoder, this.modelMapper);


    @InjectMocks
    private OrderService orderService;


    @InjectMocks
    private NotificationService notificationService;
//    private NotificationService notificationService = new NotificationService(this.userService, this.notificationRepository, this.modelMapper);


    private AddOrder addOrder;

    private Book book;

    @Before
    public void setUp() {

        this.addOrder = new AddOrder();
        addOrder.setBookName("firstBook");


        this.addOrder.setRentTime("4");
        this.addOrder.setOrderAddress("address");

        this.addOrder.setContactNumber("0895545963");

        this.addOrder.setOrderType(OrderType.RENT.toString());

        this.addOrder.setBuyPrice(10.0);

        this.addOrder.setRentPrice(5.0);
    }

}
