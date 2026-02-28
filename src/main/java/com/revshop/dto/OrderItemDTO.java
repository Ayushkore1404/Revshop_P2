package com.revshop.dto;

public class OrderItemDTO {
    private Long id;
    private Long productId;
    private String productName;
    private String imageUrl;
    private int quantity;
    private double price;

    public OrderItemDTO(Long id, Long productId, String productName, String imageUrl, int quantity, double price) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
