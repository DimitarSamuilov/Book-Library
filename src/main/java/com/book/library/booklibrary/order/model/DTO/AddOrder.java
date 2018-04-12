package com.book.library.booklibrary.order.model.DTO;

import com.book.library.booklibrary.order.enums.OrderType;
import com.book.library.booklibrary.order.model.validation.rentPeriod.RentPeriod;
import org.hibernate.validator.constraints.pl.REGON;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RentPeriod(
        message = "Invalid Rent Period",
        min = 4,
        orderType = "orderType",
        rentTime = "rentTime"
)
public class AddOrder {


    private Long bookId;

    private String bookName;
    @NotNull
    private String orderType;

    private String rentTime;

    @NotNull
    @Size(min = 10, max = 255)
    private String orderAddress;

    @NotNull
    private String contactNumber;

    public AddOrder() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getRentTime() {
        return rentTime;
    }

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
