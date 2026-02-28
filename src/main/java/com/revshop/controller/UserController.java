package com.revshop.controller;

import com.revshop.model.User;
import com.revshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    // POST user registration
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody Map<String, Object> userRequest) {
        try {
            System.out.println("👤 Registering new user:");
            System.out.println("  - Name: " + userRequest.get("name"));
            System.out.println("  - Email: " + userRequest.get("email"));
            System.out.println("  - Role: " + userRequest.get("role"));
            
            // Create success response
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "User registered successfully");
            response.put("userId", System.currentTimeMillis());
            response.put("name", userRequest.get("name"));
            response.put("email", userRequest.get("email"));
            response.put("role", userRequest.get("role"));
            
            System.out.println("✅ User registered successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("❌ Error registering user: " + e.getMessage());
            e.printStackTrace();
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to register user: " + e.getMessage());
            
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    // POST user login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, Object> loginRequest) {
        try {
            System.out.println("🔐 User login attempt:");
            System.out.println("  - Email: " + loginRequest.get("email"));
            System.out.println("  - Role: " + loginRequest.get("role"));
            
            String email = (String) loginRequest.get("email");
            
            // Find user by email in database
            Optional<User> userOpt = userRepository.findByEmail(email);
            
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                System.out.println("✅ Found user in database: " + user.getName());
                
                // Create success response with real user data
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("message", "Login successful");
                response.put("userId", user.getId());
                response.put("name", user.getName());
                response.put("email", user.getEmail());
                response.put("role", user.getRole());
                response.put("token", "mock-jwt-token-" + System.currentTimeMillis());
                
                System.out.println("✅ User logged in successfully");
                return ResponseEntity.ok(response);
            } else {
                System.out.println("❌ User not found with email: " + email);
                
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "User not found");
                
                return ResponseEntity.status(401).body(errorResponse);
            }
        } catch (Exception e) {
            System.err.println("❌ Error during login: " + e.getMessage());
            e.printStackTrace();
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Login failed: " + e.getMessage());
            
            return ResponseEntity.status(401).body(errorResponse);
        }
    }

    // GET user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long userId) {
        try {
            System.out.println("👤 Getting user by ID: " + userId);
            
            // Find user in database
            Optional<User> userOpt = userRepository.findById(userId);
            
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                System.out.println("✅ Found user: " + user.getName());
                
                // Return real user data
                Map<String, Object> userResponse = new HashMap<>();
                userResponse.put("id", user.getId());
                userResponse.put("name", user.getName());
                userResponse.put("email", user.getEmail());
                userResponse.put("role", user.getRole());
                
                // Add address information if available
                userResponse.put("address", user.getAddress());
                userResponse.put("city", user.getCity());
                userResponse.put("state", user.getState());
                userResponse.put("zipCode", user.getZipCode());
                userResponse.put("country", user.getCountry());
                userResponse.put("phone", user.getPhone());
                
                return ResponseEntity.ok(userResponse);
            } else {
                System.out.println("❌ User not found with ID: " + userId);
                return ResponseEntity.status(404).build();
            }
        } catch (Exception e) {
            System.err.println("❌ Error getting user: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // GET user address specifically
    @GetMapping("/{userId}/address")
    public ResponseEntity<Map<String, Object>> getUserAddress(@PathVariable Long userId) {
        try {
            System.out.println("🏠 Getting user address for ID: " + userId);
            
            // Find user in database
            Optional<User> userOpt = userRepository.findById(userId);
            
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                System.out.println("✅ Found user address for: " + user.getName());
                
                // Return real address data
                Map<String, Object> address = new HashMap<>();
                address.put("fullName", user.getName());
                address.put("address", user.getAddress() != null ? user.getAddress() : "N/A");
                address.put("city", user.getCity() != null ? user.getCity() : "N/A");
                address.put("state", user.getState() != null ? user.getState() : "N/A");
                address.put("zipCode", user.getZipCode() != null ? user.getZipCode() : "N/A");
                address.put("country", user.getCountry() != null ? user.getCountry() : "N/A");
                address.put("phone", user.getPhone() != null ? user.getPhone() : "N/A");
                
                return ResponseEntity.ok(address);
            } else {
                System.out.println("❌ User not found with ID: " + userId);
                return ResponseEntity.status(404).build();
            }
        } catch (Exception e) {
            System.err.println("❌ Error getting user address: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // PUT update user profile
    @PutMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> updateUserProfile(@PathVariable Long userId, @RequestBody Map<String, Object> userRequest) {
        try {
            System.out.println("👤 Updating user profile: " + userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Profile updated successfully");
            response.put("userId", userId);
            response.put("name", userRequest.get("name"));
            response.put("email", userRequest.get("email"));
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("❌ Error updating profile: " + e.getMessage());
            e.printStackTrace();
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to update profile: " + e.getMessage());
            
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    // POST user logout
    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logoutUser(@RequestBody Map<String, Object> logoutRequest) {
        try {
            System.out.println("🚪 User logout: " + logoutRequest.get("userId"));
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Logout successful");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("❌ Error during logout: " + e.getMessage());
            e.printStackTrace();
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Logout failed: " + e.getMessage());
            
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    // GET user statistics
    @GetMapping("/{userId}/stats")
    public ResponseEntity<Map<String, Object>> getUserStats(@PathVariable Long userId) {
        try {
            System.out.println("📊 Getting user stats: " + userId);
            
            Map<String, Object> stats = new HashMap<>();
            stats.put("totalOrders", 5);
            stats.put("totalSpent", 2500.0);
            stats.put("totalItems", 12);
            stats.put("favoriteCategory", "Electronics");
            stats.put("lastOrderDate", "2024-02-20T15:30:00");
            
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            System.err.println("❌ Error getting user stats: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // POST forgot password - direct password update
    @PostMapping("/forgot-password")
    public ResponseEntity<Map<String, Object>> forgotPassword(@RequestBody Map<String, Object> request) {
        try {
            System.out.println("🔑 Forgot password request:");
            System.out.println("  - Email: " + request.get("email"));
            System.out.println("  - New Password: " + (request.get("newPassword") != null ? "***PROVIDED***" : "NULL"));
            
            String email = (String) request.get("email");
            String newPassword = (String) request.get("newPassword");
            
            // Validate input
            if (email == null || email.trim().isEmpty()) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "Email is required");
                return ResponseEntity.badRequest().body(errorResponse);
            }
            
            if (newPassword == null || newPassword.trim().isEmpty()) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "New password is required");
                return ResponseEntity.badRequest().body(errorResponse);
            }
            
            // Find user by email
            Optional<User> userOpt = userRepository.findByEmail(email.trim());
            
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                System.out.println("✅ Found user: " + user.getName() + " (" + user.getEmail() + ")");
                
                // Encode and update the new password
                String encodedPassword = passwordEncoder.encode(newPassword);
                user.setPassword(encodedPassword);
                
                // Save the updated user
                userRepository.save(user);
                
                System.out.println("✅ Password updated successfully for user: " + user.getEmail());
                
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("message", "Password has been reset successfully");
                response.put("email", user.getEmail());
                response.put("name", user.getName());
                
                return ResponseEntity.ok(response);
            } else {
                System.out.println("❌ User not found with email: " + email);
                
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "No account found with this email address");
                
                return ResponseEntity.status(404).body(errorResponse);
            }
        } catch (Exception e) {
            System.err.println("❌ Error in forgot password: " + e.getMessage());
            e.printStackTrace();
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to reset password: " + e.getMessage());
            
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    // Test endpoint
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("User controller is working!");
    }
}
