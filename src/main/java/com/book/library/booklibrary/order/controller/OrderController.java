package com.book.library.booklibrary.order.controller;

import com.book.library.booklibrary.library.service.book.BookService;
import com.book.library.booklibrary.order.model.DTO.AddOrder;
import com.book.library.booklibrary.order.service.order.OrderServiceInterface;
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

    @GetMapping("/make/{bookId}")
    public String makeOrder(@PathVariable(name = "bookId") Long bookId, Model model) throws Exception {
        model.addAttribute("bookId", bookId);
        model.addAttribute("order", this.orderService.prepareOrder(bookId));
        return "order/make";
    }

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
}
