package org.chargenet.store.service.impl;

import io.swagger.v3.oas.annotations.security.OAuthFlow;
import lombok.extern.slf4j.Slf4j;
import org.chargenet.store.constant.Status;
import org.chargenet.store.exception.DataConflictException;
import org.chargenet.store.exception.DataNotFoundException;
import org.chargenet.store.model.OrderItem;
import org.chargenet.store.persistance.entity.*;
import org.chargenet.store.rest.dto.OrderRequest;
import org.chargenet.store.rest.dto.OrderResponse;
import org.chargenet.store.rest.dto.ProductRequest;
import org.chargenet.store.rest.dto.ProductResponse;
import org.chargenet.store.service.OrderService;
import org.chargenet.store.service.ProductService;
import org.chargenet.store.util.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository repository;
    @Autowired
    ItemRepository itemRepository;

    @Override
    public OrderResponse getOrderById(Long id){
        return ObjectMapper.map(repository.findById(id),OrderResponse.class);
    }

    @Override
    public OrderResponse saveOrder(OrderRequest data) {
        Order order = ObjectMapper.map(data,Order.class);
        order.setStatus(Status.SAVED);
        List<Item> items = ObjectMapper.mapAll(data.getItems(),Item.class);
        items.forEach(order::addItem);
        return ObjectMapper.map(repository.save(order),OrderResponse.class);
    }

    @Override
    public OrderResponse createOrder(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() ->new DataNotFoundException("Order not fount for id:" + id));
        order.setStatus(Status.CREATED);
        order.setCreatedOn(new Date());
        return ObjectMapper.map(repository.save(order),OrderResponse.class);
    }

    @Override
    public OrderResponse updateOrder(Long id,OrderRequest data){
        Order order = repository.findById(id)
                .orElseThrow(() ->new DataNotFoundException("Order not fount for id:" + id));
        //repository.save(order);
        return ObjectMapper.map(order,OrderResponse.class);
    }

    @Transactional
    private Order addItemToCart(Long orderId, Item item){
        Order order = repository.findById(orderId)
                .orElseThrow(()-> new DataNotFoundException("Order not found for ID:" + orderId));
        order.addItem(item);
        item.setOrder(order);
        return order;
    }

    @Transactional
    private Order removeItemToCart(Long orderId, Item item){
        Order order = repository.findById(orderId)
                .orElseThrow(()-> new DataNotFoundException("Order not found for ID:" + orderId));
        order.removeItem(item);
        item.setOrder(order);
        return order;
    }

}
