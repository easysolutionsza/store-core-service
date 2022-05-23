package org.chargenet.store.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.chargenet.store.rest.dto.ProductRequest;
import org.chargenet.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * This controller takes care of the Products
 *
 */
@RestController
@RequestMapping(value = "/api/v1/product",produces = APPLICATION_JSON_VALUE)
@Tag(name = "Product API", description = "Product Management API for Store User")
@Slf4j
public class  ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    @Operation(summary = "Get Product By Id")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    @Operation(summary = "Get all Product")
    public ResponseEntity<?> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("")
    @Operation(summary = "Add Product")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.addProduct(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Product Information")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id,
                                           @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id,request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Product")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
