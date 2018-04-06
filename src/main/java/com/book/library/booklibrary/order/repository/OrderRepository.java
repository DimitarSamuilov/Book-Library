package com.book.library.booklibrary.order.repository;

import com.book.library.booklibrary.order.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
