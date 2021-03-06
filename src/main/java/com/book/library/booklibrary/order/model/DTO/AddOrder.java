package com.book.library.booklibrary.order.model.DTO;

import com.book.library.booklibrary.order.model.validation.rentPeriod.RentPeriod;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RentPeriod(
        message = "Invalid Rent Period",
        min = 4,
        orderType = "orderType",
        rentTime = "rentTime"
)
public class AddOrder {

    private static final String ADDRESS_SIZE = "Address must be between 10 and 255 strings";
    private static final String NOT_EMPTY_FIELD = "Field cannot be empty";
    private static final String INVALID_PHONE_NUMBER = "Invalid phone number";
    private Long bookId;

    private String bookName;

    @NotNull(message = NOT_EMPTY_FIELD)
    private String orderType;

    private String rentTime;

    @NotEmpty(message = NOT_EMPTY_FIELD)
    @Size(min = 10, max = 255, message = ADDRESS_SIZE)
    private String orderAddress;

    @Pattern(message = INVALID_PHONE_NUMBER, regexp = "^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$")
    @NotEmpty(message = NOT_EMPTY_FIELD)
    private String contactNumber;

    private Double buyPrice;

    private Double rentPrice;

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

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }

    @Override
    public String toString() {
        return "AddOrder{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", orderType='" + orderType + '\'' +
                ", rentTime='" + rentTime + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
