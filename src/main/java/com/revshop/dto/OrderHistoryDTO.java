package com.revshop.dto;

import java.time.LocalDateTime;

public class OrderHistoryDTO {
    private Long id;
    private Long buyerId;
    private Double totalAmount;
    private String status;
    private LocalDateTime orderDate;
    private String orderNumber;

    public OrderHistoryDTO(Long id, Long buyerId, Double totalAmount, String status, LocalDateTime orderDate, String orderNumber) {
        this.id = id;
        this.buyerId = buyerId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate = orderDate;
        this.orderNumber = orderNumber;
    }

    public Long getId() {
        return id;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
}
