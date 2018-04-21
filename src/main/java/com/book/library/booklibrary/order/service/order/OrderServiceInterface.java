package com.book.library.booklibrary.order.service.order;

import com.book.library.booklibrary.home.exception.NoSuchResourceException;
import com.book.library.booklibrary.order.model.DTO.AddOrder;
import com.book.library.booklibrary.order.model.DTO.OrderListView;
import com.book.library.booklibrary.order.model.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.security.Principal;

public interface OrderServiceInterface {

    AddOrder prepareOrder(Long bookId) throws NoSuchResourceException;

    Long makeOrder(AddOrder orderDetails, Principal principal) throws Exception;

    Slice<OrderListView> getNotConfirmedOrders(Pageable pageable,Principal principal);

    void confirmOrder(Long id,Principal principal);
}
