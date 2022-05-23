package org.chargenet.store.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.chargenet.store.exception.DataConflictException;
import org.chargenet.store.exception.DataNotFoundException;
import org.chargenet.store.persistance.entity.Product;
import org.chargenet.store.persistance.entity.ProductRepository;
import org.chargenet.store.rest.dto.ProductRequest;
import org.chargenet.store.rest.dto.ProductResponse;
import org.chargenet.store.service.ProductService;
import org.chargenet.store.util.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repository;

    @Override
    public ProductResponse addProduct(ProductRequest data) {
        Product product = null;
        product = repository.findByNameAndPrice(data.getName(),data.getPrice());
        if(product != null)
                throw new DataConflictException("Product is already exist by name:"+ data.getName());
        product = ObjectMapper.map(data,Product.class);
        product.setActive(true);
        product.setCreatedOn(new Date());
        return ObjectMapper.map(repository.save(product),ProductResponse.class);
    }

    @Override
    public ProductResponse updateProduct(Long id,ProductRequest data) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Product not fount for id:" + id));
        ObjectMapper.map(data,product);
        product.setId(id);
        return ObjectMapper.map(repository.save(product),ProductResponse.class);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = repository.findAll();
        if(products.isEmpty())
        {
           throw new DataNotFoundException("No Products Found In Database ");
        }
        return ObjectMapper.mapAll(products,ProductResponse.class);
    }

    @Override
    public ProductResponse getProductById(Long id){
        Product product = repository.findById(id)
                .orElseThrow(() ->new DataNotFoundException("Product not fount for id:" + id));
        return ObjectMapper.map(product,ProductResponse.class);
    }

    @Override
    public void deleteProduct(Long id){
        repository.deleteById(id);
    }
}
