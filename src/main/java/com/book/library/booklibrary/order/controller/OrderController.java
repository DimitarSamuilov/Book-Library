package com.book.library.booklibrary.order.controller;

import com.book.library.booklibrary.library.service.book.BookService;
import com.book.library.booklibrary.order.model.DTO.AddOrder;
import com.book.library.booklibrary.order.model.DTO.OrderListView;
import com.book.library.booklibrary.order.service.order.OrderServiceInterface;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private OrderServiceInterface orderService;
    private BookService bookService;


    public OrderController(OrderServiceInterface orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @PreAuthorize("hasRole('ROLE_READER')")
    @GetMapping("/make/{bookId}")
    public String makeOrder(@PathVariable(name = "bookId") Long bookId, Model model) throws Exception {
        model.addAttribute("bookId", bookId);
        model.addAttribute("order", this.orderService.prepareOrder(bookId));
        return "order/make";
    }

    @PreAuthorize("hasRole('ROLE_READER')")
    @PostMapping("/make/{bookId}")
    public String processOrder(
            @Valid @ModelAttribute(name = "order") AddOrder order,
            BindingResult bindingResult,
            @PathVariable("bookId") Long bookId,
            Model model,
            Principal principal) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("order", order);
            return "order/make";
        }

        Long orderId = this.orderService.makeOrder(order, principal);

        return "redirect:/";
    }

    @PreAuthorize("hasRole('ROLE_LIBRARY')")
    @GetMapping("/notConfirmedList")
    public String notConfirmed(@PageableDefault(size = 2) Pageable pageable, Principal principal, Model model) {

        Slice<OrderListView> notConfirmedOrders = this.orderService.getNotConfirmedOrders(pageable, principal);
        model.addAttribute("orders", notConfirmedOrders);
        model.addAttribute("pageable", pageable);

        return "order/list";
    }

    @PreAuthorize("hasRole('ROLE_LIBRARY')")
    @GetMapping("/confirmOrder/{id}")
    public String confirmOrder(@PathVariable(name = "id") Long id, Principal principal) {

        this.orderService.confirmOrder(id, principal);
        return "redirect:/orders/notConfirmedList";
    }
}
