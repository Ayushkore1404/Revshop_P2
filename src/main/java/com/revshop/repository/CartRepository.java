package com.revshop.repository;

import com.revshop.model.Cart;
import com.revshop.model.User;
import com.revshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUser(User user);

    @Query("SELECT c FROM Cart c WHERE c.user.id = :userId")
    List<Cart> findByUserId(@Param("userId") Long userId);

    void deleteByUser(User user);

    @Query("DELETE FROM Cart c WHERE c.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);

    List<Cart> findByUserAndProduct(User user, Product product);

    @Query("SELECT c FROM Cart c WHERE c.user.id = :userId AND c.product.id = :productId")
    List<Cart> findByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
}