package org.chargenet.store.service;

import org.chargenet.store.rest.dto.OrderRequest;
import org.chargenet.store.rest.dto.OrderResponse;

public interface OrderService {
    OrderResponse getOrderById(Long id);
    OrderResponse saveOrder(OrderRequest request);
    OrderResponse createOrder(Long id);
    OrderResponse updateOrder(Long id,OrderRequest request);
}
