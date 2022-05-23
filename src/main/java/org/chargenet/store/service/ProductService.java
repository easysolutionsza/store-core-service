package org.chargenet.store.service;

import org.chargenet.store.persistance.entity.Product;
import org.chargenet.store.rest.dto.ProductRequest;
import org.chargenet.store.rest.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest request);
    ProductResponse updateProduct(Long id,ProductRequest request);
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(Long id);
    void deleteProduct(Long id);

}
