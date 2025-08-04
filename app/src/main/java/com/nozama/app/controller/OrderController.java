package com.nozama.app.controller;

import com.nozama.app.dto.OrderRequest;
import com.nozama.app.dto.OrderResponse;
import com.nozama.app.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public OrderResponse placeOrder (@Valid @RequestBody OrderRequest request) {
        return orderService.placeOrder(request);
    }
}
