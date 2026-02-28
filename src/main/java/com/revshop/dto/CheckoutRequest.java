package com.revshop.dto;

import com.revshop.model.OrderItem;
import java.util.List;

public class CheckoutRequest {
    private Long buyerId;
    private double totalAmount;
    private List<OrderItem> items;
    private ShippingAddress shippingAddress;

    public Long getBuyerId() { return buyerId; }
    public void setBuyerId(Long buyerId) { this.buyerId = buyerId; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public ShippingAddress getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(ShippingAddress shippingAddress) { this.shippingAddress = shippingAddress; }
}
