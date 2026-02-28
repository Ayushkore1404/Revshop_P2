package com.revshop.service;

import com.revshop.model.Cart;
import com.revshop.model.Product;
import com.revshop.repository.CartRepository;
import com.revshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart addToCart(Long userId, Long productId, int quantity) {
        // Check if product exists
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if item already exists in cart
        List<Cart> existingItems = cartRepository.findByUserIdAndProductId(userId, productId);
        
        if (!existingItems.isEmpty()) {
            // Update quantity if item exists
            Cart cartItem = existingItems.get(0);
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setTotalPrice(cartItem.getPrice() * cartItem.getQuantity());
            return cartRepository.save(cartItem);
        } else {
            // Create new cart item
            Cart cartItem = new Cart();
            cartItem.setUserId(userId);
            cartItem.setProductId(productId);
            cartItem.setProductName(product.getName());
            cartItem.setQuantity(quantity);
            cartItem.setPrice(product.getPrice().doubleValue());
            cartItem.setTotalPrice(product.getPrice().doubleValue() * quantity);
            
            // Set high-quality image URL based on product name
            String productName = product.getName().toLowerCase();
            String imageUrl;
            if (productName.contains("macbook") || productName.contains("laptop")) {
                imageUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/refurb-cosmetically-flawless-macbook-air-midnight-2023?wid=1144&hei=1144&fmt=jpeg&qlt=90&.v=1693078356000";
            } else if (productName.contains("iphone") || productName.contains("phone")) {
                imageUrl = "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-pro-finish-select-202309-6-1inch-naturaltitanium?wid=5120&hei=2880&fmt=webp&qlt=95&.v=1692923519600";
            } else if (productName.contains("nike") || productName.contains("shoes")) {
                imageUrl = "https://static.nike.com/a/images/t_PDP_864x963/f_auto,q_auto:eco/b5222c8e-5e6d-4173-a44e-4d4a8c1c9b0b/air-max-270-shoes.png";
            } else if (productName.contains("sony") || productName.contains("headphone")) {
                imageUrl = "https://www.sony.com/image/dam/global/wh-1000xm5/product-gallery/gallery1.png";
            } else {
                imageUrl = "https://images.unsplash.com/photo-1560472354-b33ff4b4c40?w=80&h=80&fit=crop&auto=format";
            }
            cartItem.setImageUrl(imageUrl);
            
            return cartRepository.save(cartItem);
        }
    }

    public List<Cart> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public void removeItem(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }

    public Cart updateQuantity(Long cartId, int quantity) {
        Cart cartItem = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getPrice() * quantity);
        
        return cartRepository.save(cartItem);
    }
}