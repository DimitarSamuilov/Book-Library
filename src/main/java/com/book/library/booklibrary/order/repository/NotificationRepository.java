package com.book.library.booklibrary.order.repository;

import com.book.library.booklibrary.order.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long>{
}
