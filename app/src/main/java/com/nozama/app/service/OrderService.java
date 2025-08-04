package com.nozama.app.service;

import com.nozama.app.dto.OrderRequest;
import com.nozama.app.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest request);
}
