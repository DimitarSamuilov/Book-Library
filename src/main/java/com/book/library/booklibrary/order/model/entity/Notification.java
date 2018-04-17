package com.book.library.booklibrary.order.model.entity;

import com.book.library.booklibrary.library.model.entity.Book;
import com.book.library.booklibrary.order.enums.NotificationType;
import com.book.library.booklibrary.user.model.entity.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private Date date;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "is_viewed")
    private boolean viewed;


    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @ManyToMany(targetEntity = Order.class)
    @JoinTable(
            name = "notification_order",
            joinColumns = {@JoinColumn(name = "notification_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")}
    )
    private Set<Order> relatedOrder;

    public Notification() {
    }

    public boolean isViewed() {
        return viewed;
    }


    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Set<Order> getRelatedOrder() {
        return relatedOrder;
    }

    public void setRelatedOrder(Set<Order> relatedOrder) {
        this.relatedOrder = relatedOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
