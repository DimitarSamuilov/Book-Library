package com.book.library.booklibrary.order.repository;

import com.book.library.booklibrary.order.model.entity.Notification;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long>{

    List<Notification> findFirst5ByRelatedOrder_CustomerUsername(String username);
}
