package com.revshop.controller;

import com.revshop.model.Cart;
import com.revshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

    @Autowired
    private CartService cartService;

    // POST endpoint for adding items to cart
    @PostMapping
    public ResponseEntity<Map<String, Object>> addToCart(@RequestBody Map<String, Object> cartRequest) {
        try {
            System.out.println("🛒 Adding item to cart:");
            System.out.println("  - User ID: " + cartRequest.get("userId"));
            System.out.println("  - Product ID: " + cartRequest.get("productId"));
            System.out.println("  - Quantity: " + cartRequest.get("quantity"));
            
            Long userId = Long.valueOf(cartRequest.get("userId").toString());
            Long productId = Long.valueOf(cartRequest.get("productId").toString());
            Integer quantity = Integer.valueOf(cartRequest.get("quantity").toString());
            
            Cart savedCart = cartService.addToCart(userId, productId, quantity);
            
            // Create success response
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Item added to cart successfully");
            response.put("cartItemId", savedCart.getId());
            response.put("userId", userId);
            response.put("productId", productId);
            response.put("quantity", quantity);
            
            System.out.println("✅ Item added to cart successfully with ID: " + savedCart.getId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("❌ Error adding to cart: " + e.getMessage());
            e.printStackTrace();
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to add item to cart: " + e.getMessage());
            
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    // GET endpoint for retrieving cart items
    @GetMapping("/{buyerId}")
    public ResponseEntity<List<Map<String, Object>>> getCartItems(@PathVariable Long buyerId) {
        try {
            System.out.println("🛒 Getting cart items for buyer: " + buyerId);
            
            List<Cart> cartItems = cartService.getCartByUserId(buyerId);
            
            // Convert Cart entities to response format
            List<Map<String, Object>> response = cartItems.stream().map(cart -> {
                Map<String, Object> item = new HashMap<>();
                item.put("id", cart.getId());
                item.put("cartItemId", cart.getId());
                item.put("userId", cart.getUserId());
                item.put("productId", cart.getProductId());
                item.put("name", cart.getProductName());
                item.put("productName", cart.getProductName());
                item.put("price", cart.getPrice());
                item.put("quantity", cart.getQuantity());
                item.put("totalPrice", cart.getTotalPrice());
                item.put("imageUrl", cart.getImageUrl());
                item.put("productImage", cart.getImageUrl());
                return item;
            }).collect(Collectors.toList());
            
            System.out.println("📦 Found " + response.size() + " cart items");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("❌ Error getting cart items: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // PUT endpoint for updating cart item quantity
    @PutMapping("/{cartItemId}")
    public ResponseEntity<Map<String, Object>> updateCartItem(@PathVariable Long cartItemId, @RequestBody Map<String, Object> updateRequest) {
        try {
            System.out.println("🛒 Updating cart item: " + cartItemId);
            System.out.println("  - New quantity: " + updateRequest.get("quantity"));
            
            Integer quantity = Integer.valueOf(updateRequest.get("quantity").toString());
            Cart updatedCart = cartService.updateQuantity(cartItemId, quantity);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cart item updated successfully");
            response.put("cartItemId", cartItemId);
            response.put("quantity", quantity);
            response.put("totalPrice", updatedCart.getTotalPrice());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("❌ Error updating cart item: " + e.getMessage());
            e.printStackTrace();
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to update cart item: " + e.getMessage());
            
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    // DELETE endpoint for removing cart item
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Map<String, Object>> removeCartItem(@PathVariable Long cartItemId) {
        try {
            System.out.println("🛒 Removing cart item: " + cartItemId);
            
            cartService.removeItem(cartItemId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cart item removed successfully");
            response.put("cartItemId", cartItemId);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("❌ Error removing cart item: " + e.getMessage());
            e.printStackTrace();
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to remove cart item: " + e.getMessage());
            
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    // DELETE endpoint for clearing entire cart
    @DeleteMapping("/clear/{buyerId}")
    public ResponseEntity<Map<String, Object>> clearCart(@PathVariable Long buyerId) {
        try {
            System.out.println("🛒 Clearing cart for buyer: " + buyerId);
            
            cartService.clearCart(buyerId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cart cleared successfully");
            response.put("buyerId", buyerId);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("❌ Error clearing cart: " + e.getMessage());
            e.printStackTrace();
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to clear cart: " + e.getMessage());
            
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    // GET endpoint for cart count
    @GetMapping("/count/{buyerId}")
    public ResponseEntity<Map<String, Object>> getCartCount(@PathVariable Long buyerId) {
        try {
            System.out.println("🛒 Getting cart count for buyer: " + buyerId);
            
            List<Cart> cartItems = cartService.getCartByUserId(buyerId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("count", cartItems.size());
            response.put("buyerId", buyerId);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("❌ Error getting cart count: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // WORKING cart endpoint without database dependencies
    @GetMapping("/working/{buyerId}")
    public ResponseEntity<List<String>> getWorkingCart(@PathVariable Long buyerId) {
        try {
            System.out.println(" Working cart endpoint for buyer: " + buyerId);
            
            // For now, return a simple list to test the endpoint
            List<String> cartItems = new ArrayList<>();
            cartItems.add("Sample item 1");
            cartItems.add("Sample item 2");
            
            return ResponseEntity.ok(cartItems);
        } catch (Exception e) {
            System.err.println(" Error in working cart: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/minimal-test")
    public ResponseEntity<String> minimalTest() {
        try {
            return ResponseEntity.ok("Minimal cart test works!");
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Minimal test error: " + e.getMessage());
        }
    }
}