package com.revshop.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDetailsDTO {
    private Long id;
    private Long buyerId;
    private double totalAmount;
    private String status;
    private LocalDateTime orderDate;
    private String orderNumber;

    // Shipping fields
    private String shippingFullName;
    private String shippingAddress;
    private String shippingCity;
    private String shippingState;
    private String shippingZipCode;
    private String shippingCountry;
    private String shippingPhone;

    private List<OrderItemDTO> items;

    public OrderDetailsDTO(Long id, Long buyerId, double totalAmount, String status, LocalDateTime orderDate, String orderNumber,
                       String shippingFullName, String shippingAddress, String shippingCity, String shippingState, 
                       String shippingZipCode, String shippingCountry, String shippingPhone, List<OrderItemDTO> items) {
        this.id = id;
        this.buyerId = buyerId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate = orderDate;
        this.orderNumber = orderNumber;
        this.shippingFullName = shippingFullName;
        this.shippingAddress = shippingAddress;
        this.shippingCity = shippingCity;
        this.shippingState = shippingState;
        this.shippingZipCode = shippingZipCode;
        this.shippingCountry = shippingCountry;
        this.shippingPhone = shippingPhone;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public double getTotalAmount() {
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

    public String getShippingFullName() {
        return shippingFullName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public String getShippingState() {
        return shippingState;
    }

    public String getShippingZipCode() {
        return shippingZipCode;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public String getShippingPhone() {
        return shippingPhone;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }
}
