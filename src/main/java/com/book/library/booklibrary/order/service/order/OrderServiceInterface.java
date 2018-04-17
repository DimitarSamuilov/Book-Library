package com.book.library.booklibrary.order.service.order;

import com.book.library.booklibrary.home.exception.NoSuchResourceException;
import com.book.library.booklibrary.order.model.DTO.AddOrder;

import java.security.Principal;

public interface OrderServiceInterface {

    AddOrder prepareOrder(Long bookId) throws NoSuchResourceException;
    Long makeOrder(AddOrder orderDetails, Principal principal) throws Exception;
}
