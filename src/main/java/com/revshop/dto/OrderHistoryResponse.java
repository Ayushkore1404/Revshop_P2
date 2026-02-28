package com.revshop.dto;

import com.revshop.model.Order;
import com.revshop.model.OrderItem;
import java.time.LocalDateTime;
import java.util.List;

public class OrderHistoryResponse {
    private Long id;
    private Long buyerId;
    private Double totalAmount;
    private String status;
    private LocalDateTime orderDate;
    private List<OrderItem> orderItems;

    public OrderHistoryResponse(Order order, List<OrderItem> orderItems) {
        this.id = order.getId();
        this.buyerId = order.getBuyerId();
        this.totalAmount = order.getTotalAmount();
        this.status = order.getStatus();
        this.orderDate = order.getOrderDate();
        this.orderItems = orderItems;
    }

    // Getters
    public Long getId() { return id; }
    public Long getBuyerId() { return buyerId; }
    public Double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public List<OrderItem> getOrderItems() { return orderItems; }
}
