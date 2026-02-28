package com.revshop.repository;

import com.revshop.model.Product;
import com.revshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /**
     * CORRECT JPQL QUERY: Find products by seller using proper JPA relationship
     * This replaces the incorrect derived query "findBySellerId"
     */
    @Query("SELECT p FROM Product p WHERE p.seller.id = :sellerId")
    List<Product> findBySellerId(@Param("sellerId") Long sellerId);
    
    /**
     * Alternative query using User entity for more explicit relationship handling
     */
    @Query("SELECT p FROM Product p JOIN p.seller s WHERE s.id = :sellerId")
    List<Product> findBySeller(@Param("sellerId") Long sellerId);
    
    /**
     * Find products by seller with seller information loaded
     * Uses JOIN FETCH to prevent lazy loading issues
     */
    @Query("SELECT p FROM Product p JOIN FETCH p.seller WHERE p.seller.id = :sellerId")
    List<Product> findBySellerIdWithSeller(@Param("sellerId") Long sellerId);
    
    /**
     * Find products by category (case-insensitive)
     */
    @Query("SELECT p FROM Product p WHERE LOWER(p.category) LIKE LOWER(CONCAT('%', :category, '%'))")
    List<Product> findByCategoryContainingIgnoreCase(@Param("category") String category);
    
    /**
     * Find products by name (case-insensitive)
     */
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> findByNameContainingIgnoreCase(@Param("keyword") String keyword);
    
    /**
     * Find products with low stock
     */
    @Query("SELECT p FROM Product p WHERE p.stock <= p.lowStockThreshold")
    List<Product> findLowStockProducts();
    
    /**
     * Count products by seller
     */
    @Query("SELECT COUNT(p) FROM Product p WHERE p.seller.id = :sellerId")
    Long countBySellerId(@Param("sellerId") Long sellerId);
}
