package com.book.library.booklibrary.order.model.entity;

import com.book.library.booklibrary.library.model.entity.Book;
import com.book.library.booklibrary.order.enums.OrderType;
import com.book.library.booklibrary.user.model.entity.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "order_type")
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Column(name = "rent_time", nullable = true)
    private Integer rentTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book orderBook;

    @Column(name = "order_address")
    private String orderAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private User customer;

    @Column(name = "order_confirmed")
    private boolean orderConfirmed;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "order_date")
    private Date orderDate;

    @ManyToMany(targetEntity = Notification.class)
    @JoinTable(
            name = "notification_order",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "notification_id", referencedColumnName = "id")}
    )
    private Set<Notification> notifications;

    public Order() {
        this.orderDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public User getCustomer() {
        return customer;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public boolean isOrderConfirmed() {
        return orderConfirmed;
    }

    public void setOrderConfirmed(boolean orderConfirmed) {
        this.orderConfirmed = orderConfirmed;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Integer getRentTime() {
        return rentTime;
    }

    public void setRentTime(Integer rentTime) {
        this.rentTime = rentTime;
    }

    public Book getOrderBook() {
        return orderBook;
    }

    public void setOrderBook(Book orderBook) {
        this.orderBook = orderBook;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderType=" + orderType +
                ", rentTime=" + rentTime +
                ", orderBook=" + orderBook +
                ", orderAddress='" + orderAddress + '\'' +
                ", customer=" + customer +
                ", orderConfirmed=" + orderConfirmed +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
