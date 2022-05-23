package org.chargenet.store.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.chargenet.store.rest.dto.OrderRequest;
import org.chargenet.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller takes care of the Orders
 *
 */
@RestController
@RequestMapping(value = "/api/v1/order", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Order API", description = "Order Management API")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/{id}")
    @Operation(summary = "Get Order By Id")
    public ResponseEntity<?> getOrder(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    @Operation(summary = "Save Order")
    public ResponseEntity<?> saveOrders(@RequestBody OrderRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.saveOrder(request));
    }

    @PutMapping
    @Operation(summary = "Update Order")
    public ResponseEntity<?> updateOrder(@PathVariable("id") Long id,
                                         @RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.updateOrder(id,request));
    }

    @PatchMapping
    @Operation(summary = "Create Order")
    public ResponseEntity<?> createOrders(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(id));
    }

}
