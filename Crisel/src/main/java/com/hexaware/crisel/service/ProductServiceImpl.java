package com.hexaware.crisel.service;

import com.hexaware.crisel.dto.ProductRequest;
import com.hexaware.crisel.dto.ProductResponse;
import com.hexaware.crisel.exception.NotFoundException;
import com.hexaware.crisel.model.Product;
import com.hexaware.crisel.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {

        this.repository = repository;
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        Product p = new Product();
        p.setName(request.getName());
        p.setDescription(request.getDescription());
        p.setPrice(request.getPrice());
        p.setCreatedAt(LocalDateTime.now());
        Product saved = repository.save(p);
        return mapToResponse(saved);
    }

    @Override
    public ProductResponse getById(Long id) {
        Product p = repository.findById(id).orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
        return mapToResponse(p);
    }

    @Override
    public List<ProductResponse> getAll() {
        return repository.
                findAll().
                stream().
                map(this::mapToResponse).
                collect(Collectors.toList());
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product existing = repository.findById(id).orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());
        Product saved = repository.save(existing);
        return mapToResponse(saved);
    }

    @Override
    public void delete(Long id) {
        Product existing = repository.
                findById(id).
                orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
        repository.delete(existing);
    }

    private ProductResponse mapToResponse(Product p) {
        return new ProductResponse(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getCreatedAt());
    }
}

