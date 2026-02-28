package com.revshop.controller;

import com.revshop.model.User;
import com.revshop.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class WebController {

    private final AuthService authService;

    public WebController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {

        Optional<User> user = authService.login(email, password);

        if (user.isPresent()) {
            if ("SELLER".equalsIgnoreCase(user.get().getRole())) {
                return "redirect:/seller/home";
            } else {
                return "redirect:/buyer/home";
            }
        }

        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        authService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/buyer/home")
    public String buyerHome() {
        return "buyer-home";
    }

    @GetMapping("/seller/home")
    public String sellerHome() {
        return "seller-home";
    }
}
