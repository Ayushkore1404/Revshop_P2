package com.revshop.controller;

import com.revshop.dto.ProductDTO;
import com.revshop.model.Product;
import com.revshop.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testGetAllProducts_Success() {
        // Arrange
        ProductDTO testProduct = new ProductDTO();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setPrice(new BigDecimal("99.99"));
        
        List<ProductDTO> testProducts = Arrays.asList(testProduct);
        when(productService.getAllProducts()).thenReturn(testProducts);

        // Act
        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById_Success() {
        // Arrange
        ProductDTO testProduct = new ProductDTO();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setSellerId(1L);
        
        when(productService.getProductById(1L)).thenReturn(Optional.of(testProduct));

        // Act
        ResponseEntity<ProductDTO> response = productController.getProductById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Product", response.getBody().getName());
        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    public void testAddProduct_Success() {
        // Arrange
        Product newProduct = new Product();
        newProduct.setName("New Product");
        newProduct.setPrice(new BigDecimal("199.99"));
        newProduct.setSellerId(1L);
        
        when(productService.addProduct(any(Product.class))).thenReturn(newProduct);

        // Act
        ResponseEntity<?> response = productController.addProduct(newProduct);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        
        // Cast to Product since we know it succeeds
        Product responseBody = (Product) response.getBody();
        assertEquals("New Product", responseBody.getName());
        verify(productService, times(1)).addProduct(any(Product.class));
    }

    @Test
    public void testGetProductsBySeller_Success() {
        // Arrange
        ProductDTO testProduct = new ProductDTO();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setSellerId(1L);
        
        List<ProductDTO> sellerProducts = Arrays.asList(testProduct);
        when(productService.getProductsBySeller(1L)).thenReturn(sellerProducts);

        // Act
        ResponseEntity<List<ProductDTO>> response = productController.getProductsBySeller(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(productService, times(1)).getProductsBySeller(1L);
    }
}
