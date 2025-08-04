package com.nozama.app.service.implementation;

import com.nozama.app.dto.OrderItemRequest;
import com.nozama.app.dto.OrderItemResponse;
import com.nozama.app.dto.OrderRequest;
import com.nozama.app.dto.OrderResponse;
import com.nozama.app.model.Order;
import com.nozama.app.model.OrderItem;
import com.nozama.app.model.OrderStatus;
import com.nozama.app.model.Product;
import com.nozama.app.model.User;
import com.nozama.app.repository.OrderRepository;
import com.nozama.app.repository.ProductRepository;
import com.nozama.app.repository.UserRepository;
import com.nozama.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {
        User user = userRepository.findById((request.getUserId()))
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if(product.getStockQuantity()< itemRequest.getQuantity()){
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }
            product.setStockQuantity(product.getStockQuantity() - itemRequest.getQuantity());
            productRepository.save(product);

            BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(product.getStockQuantity())
                    .price(product.getPrice())
                    .build();

            orderItems.add(orderItem);
            total = total.add(itemTotal);
        }
        Order order = Order.builder()
                .user(user)
                .paymentMethod(request.getPaymentMethod())
                .status(OrderStatus.PENDING)
                .CreatedAt(LocalDateTime.now())
                .totalAmount(total)
                .items(new ArrayList<>())
                .build();

        for (OrderItem item : orderItems) {
            item.setOrder(order);
        }
        order.setItems(orderItems);
        Order saveOrder = orderRepository.save(order);

        return toResponse(saveOrder);
    }
    private OrderResponse toResponse(Order order) {
        List<OrderItemResponse> itemResponses = order.getItems().stream().map(item -> {
            OrderItemResponse response = new OrderItemResponse();
            response.setProductName(item.getProduct().getName());
            response.setQuantity(item.getQuantity());
            response.setPrice(item.getPrice());
            return response;
        }).toList();

        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setTotalAmount(order.getTotalAmount());
        response.setCreatedAt(order.getCreatedAt());
        response.setStatus(order.getStatus().toString());
        response.setPaymentMethod(order.getPaymentMethod());
        response.setItems(itemResponses);

        return response;
    }
}
