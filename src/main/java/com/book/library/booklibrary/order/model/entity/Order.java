package com.book.library.booklibrary.order.model.entity;

import com.book.library.booklibrary.library.model.entity.Book;
import com.book.library.booklibrary.order.enums.OrderType;

import javax.persistence.*;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "order_type")
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Column(name = "rent_time",nullable = true)
    private Integer rentTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "book_id", referencedColumnName = "id")
    private Book orderBook;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
