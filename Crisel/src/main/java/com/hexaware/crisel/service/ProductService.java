package com.hexaware.crisel.service;

import com.hexaware.crisel.dto.ProductRequest;
import com.hexaware.crisel.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse getById(Long id);
    List<ProductResponse> getAll();
    ProductResponse update(Long id, ProductRequest request);
    void delete(Long id);
}

