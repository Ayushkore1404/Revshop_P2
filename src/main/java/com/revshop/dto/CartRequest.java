package com.revshop.dto;

/**
 * Data Transfer Object for cart operations.
 * Using a 'record' is the modern, concise way to handle data-only classes in Java.
 */
public record CartRequest(Long userId, Long productId, int quantity) {
}