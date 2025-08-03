package com.nozama.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Table (name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    private User user;

    private BigDecimal totalAmount;
    private LocalDateTime CreatedAt;
    private OrderStatus status;
    private String paymentMethod;
    @OneToMany
    private List<OrderItem> items;

}
