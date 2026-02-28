package com.revshop.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "product_id")
    private Product product;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Constructors
    public Cart() {}

    public Cart(User user, Product product, String productName, Integer quantity, Double price) {
        this.user = user;
        this.product = product;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = price * quantity;
        this.createdAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    // Convenience methods for backward compatibility
    public Long getUserId() { return user != null ? user.getId() : null; }
    public void setUserId(Long userId) { 
        if (userId != null) {
            this.user = new User();
            this.user.setId(userId);
        }
    }

    public Long getProductId() { return product != null ? product.getId() : null; }
    public void setProductId(Long productId) { 
        if (productId != null) {
            this.product = new Product();
            this.product.setId(productId);
        }
    }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (totalPrice == null && price != null && quantity != null) {
            totalPrice = price * quantity;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        if (totalPrice == null && price != null && quantity != null) {
            totalPrice = price * quantity;
        }
    }
}
