package com.revshop.controller;

import com.revshop.dto.ForgotPasswordRequest;
import com.revshop.model.User;
import com.revshop.service.AuthService;
import com.revshop.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ========================= REGISTER =========================

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        
        // Validate name with specific error
        if (user.getName() == null || user.getName().trim().isEmpty())
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Name is required. Please enter your full name."));
        
        if (user.getName().trim().length() < 2)
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Name must be at least 2 characters long."));
        
        if (user.getName().trim().length() > 50)
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Name is too long. Maximum 50 characters allowed."));

        // Validate email with specific error
        if (user.getEmail() == null || user.getEmail().trim().isEmpty())
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Email is required. Please enter your email address."));
        
        if (!user.getEmail().contains("@") || !user.getEmail().contains("."))
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Invalid email format. Please enter a valid email address (e.g., name@example.com)."));
        
        if (user.getEmail().trim().length() > 100)
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Email is too long. Maximum 100 characters allowed."));

        // Validate password with specific error
        if (user.getPassword() == null || user.getPassword().trim().isEmpty())
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Password is required. Please enter a password."));
        
        if (user.getPassword().length() < 6)
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Password must be at least 6 characters long for security."));
        
        if (user.getPassword().length() > 100)
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Password is too long. Maximum 100 characters allowed."));
        
        if (!user.getPassword().matches(".*[A-Z].*"))
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Password must contain at least one uppercase letter."));
        
        if (!user.getPassword().matches(".*[a-z].*"))
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Password must contain at least one lowercase letter."));
        
        if (!user.getPassword().matches(".*[0-9].*"))
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Password must contain at least one number."));

        // Validate role with specific error
        if (user.getRole() == null || user.getRole().trim().isEmpty())
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Role is required. Please select BUYER or SELLER."));

        String role = user.getRole().toUpperCase().trim();
        if (!role.equals("BUYER") && !role.equals("SELLER"))
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Invalid role. Please select either BUYER or SELLER."));

        try {
            user.setRole(role);
            User savedUser = authService.register(user);

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "success", true,
                    "message", "Registration successful",
                    "userId", savedUser.getId(),
                    "name", savedUser.getName(),
                    "email", savedUser.getEmail(),
                    "role", savedUser.getRole()
            ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "success", false,
                    "message", "Email already exists"
            ));
        }
    }

    // ========================= LOGIN =========================

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return validateAndLoginUser(user);
    }

    // ========================= VALIDATE TOKEN =========================

    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        
        if (token == null || token.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                "valid", false,
                "message", "Token is required"
            ));
        }
        
        try {
            if (jwtUtil.validateToken(token)) {
                return ResponseEntity.ok(Map.of(
                    "valid", true,
                    "message", "Token is valid",
                    "userId", jwtUtil.extractUserId(token),
                    "email", jwtUtil.extractEmail(token),
                    "role", jwtUtil.extractRole(token),
                    "name", jwtUtil.extractName(token),
                    "expiresAt", jwtUtil.getExpirationTime(token)
                ));
            } else {
                return ResponseEntity.ok(Map.of(
                    "valid", false,
                    "message", "Token is invalid or expired"
                ));
            }
        } catch (Exception e) {
            System.err.println("❌ Token validation error: " + e.getMessage());
            return ResponseEntity.ok(Map.of(
                "valid", false,
                "message", "Token validation failed: " + e.getMessage()
            ));
        }
    }

    // ========================= FORGOT PASSWORD =========================

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        System.out.println("🔐 Forgot password request:");
        System.out.println("  - Email: " + request.getEmail());
        System.out.println("  - Role: " + request.getRole());

        // Validate input with specific error messages
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            return ResponseEntity.status(400).body(Map.of(
                    "success", false,
                    "message", "Email is required. Please enter the email address associated with your account."
            ));
        }

        if (request.getEmail().trim().length() > 100) {
            return ResponseEntity.status(400).body(Map.of(
                    "success", false,
                    "message", "Email is too long. Maximum 100 characters allowed."
            ));
        }

        if (!request.getEmail().contains("@") || !request.getEmail().contains(".")) {
            return ResponseEntity.status(400).body(Map.of(
                    "success", false,
                    "message", "Invalid email format. Please enter a valid email address (e.g., name@example.com)."
            ));
        }

        if (request.getRole() == null || request.getRole().trim().isEmpty()) {
            return ResponseEntity.status(400).body(Map.of(
                    "success", false,
                    "message", "Role is required. Please select your account role (BUYER or SELLER)."
            ));
        }

        String role = request.getRole().toUpperCase().trim();
        if (!role.equals("BUYER") && !role.equals("SELLER")) {
            return ResponseEntity.status(400).body(Map.of(
                    "success", false,
                    "message", "Invalid role. Please select either BUYER or SELLER."
            ));
        }

        if (request.getNewPassword() == null || request.getNewPassword().trim().isEmpty()) {
            return ResponseEntity.status(400).body(Map.of(
                    "success", false,
                    "message", "New password is required. Please enter your new password."
            ));
        }

        if (request.getNewPassword().length() < 6) {
            return ResponseEntity.status(400).body(Map.of(
                    "success", false,
                    "message", "New password must be at least 6 characters long for security."
            ));
        }

        if (request.getNewPassword().length() > 100) {
            return ResponseEntity.status(400).body(Map.of(
                    "success", false,
                    "message", "New password is too long. Maximum 100 characters allowed."
            ));
        }

        if (!request.getNewPassword().matches(".*[A-Z].*")) {
            return ResponseEntity.status(400).body(Map.of(
                    "success", false,
                    "message", "New password must contain at least one uppercase letter."
            ));
        }

        if (!request.getNewPassword().matches(".*[a-z].*")) {
            return ResponseEntity.status(400).body(Map.of(
                    "success", false,
                    "message", "New password must contain at least one lowercase letter."
            ));
        }

        if (!request.getNewPassword().matches(".*[0-9].*")) {
            return ResponseEntity.status(400).body(Map.of(
                    "success", false,
                    "message", "New password must contain at least one number."
            ));
        }

        try {
            // Find user and update password directly
            Optional<User> userOptional = authService.findByEmail(request.getEmail());
            if (!userOptional.isPresent()) {
                return ResponseEntity.status(404).body(Map.of(
                    "success", false,
                    "message", "User not found"
                ));
            }

            User user = userOptional.get();

            // Validate user role matches request role
            if (!user.getRole().toUpperCase().equals(role)) {
                return ResponseEntity.status(400).body(Map.of(
                    "success", false,
                    "message", "Role mismatch. You are registered as " + user.getRole() + " but tried to reset as " + role
                ));
            }

            // Update password directly
            user.setPassword(request.getNewPassword());
            authService.updateUser(user);

            System.out.println("✅ Password reset successfully for: " + request.getEmail());

            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Password reset successfully",
                "email", request.getEmail(),
                "role", role
            ));

        } catch (Exception e) {
            System.err.println("❌ Forgot password error: " + e.getMessage());
            return ResponseEntity.status(500).body(Map.of(
                "success", false,
                "message", "Failed to reset password: " + e.getMessage()
            ));
        }
    }

    // ========================= HELPER METHODS =========================

    private ResponseEntity<?> validateAndLoginUser(User user) {
        if (user.getEmail() == null || user.getEmail().trim().isEmpty())
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Email is required"));

        if (user.getPassword() == null || user.getPassword().trim().isEmpty())
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Password is required"));

        if (user.getRole() == null || user.getRole().trim().isEmpty())
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Role is required"));

        String requestedRole = user.getRole().toUpperCase().trim();

        // First check if user exists by email
        Optional<User> userByEmail = authService.findByEmail(user.getEmail());
        
        if (userByEmail.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "success", false,
                    "message", "Email not found. Please check your email or register for an account."
            ));
        }

        User dbUser = userByEmail.get();
        
        // Check if password matches
        if (!authService.validatePassword(user.getPassword(), dbUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "success", false,
                    "message", "Incorrect password. Please check your password and try again."
            ));
        }

        // Check role mismatch
        if (!dbUser.getRole().toUpperCase().equals(requestedRole)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "success", false,
                    "message", "Role mismatch. You are registered as " + dbUser.getRole() + " but tried to login as " + requestedRole + "."
            ));
        }

        // Generate token and return success
        String token = jwtUtil.generateToken(
                dbUser.getEmail(),
                dbUser.getId(),
                dbUser.getRole(),
                dbUser.getName()
        );

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Login successful",
                "userId", dbUser.getId(),
                "name", dbUser.getName(),
                "email", dbUser.getEmail(),
                "role", dbUser.getRole(),
                "token", token
        ));
    }
}