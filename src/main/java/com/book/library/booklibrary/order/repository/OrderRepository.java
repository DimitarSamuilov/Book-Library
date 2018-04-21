package com.book.library.booklibrary.order.repository;

import com.book.library.booklibrary.order.model.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{
    Slice<Order> findAllByOrderConfirmedFalseAndOrderBook_Library_User_Username(String lbiName,Pageable pageable);
}
