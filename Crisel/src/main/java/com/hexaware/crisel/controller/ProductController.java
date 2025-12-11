package com.hexaware.crisel.controller;

import com.hexaware.crisel.dto.APIResponse;
import com.hexaware.crisel.dto.ProductRequest;
import com.hexaware.crisel.dto.ProductResponse;
import com.hexaware.crisel.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v2/api/products")
public class ProductController {

//    {
//        "name": "macbook",
//            "description": "High performance system hardcore",
//            "price": 155000
//    }


    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }
//http://localhost:8080/v2/api/products
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {

        return ResponseEntity.ok(service.getAll());
    }
//    http://localhost:8080/v2/api/products/1-----get
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    //http://localhost:8080/v2/api/products------post
    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        ProductResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/products/" + created.getId())).body(created);
    }
//    http://localhost:8080/v2/api/products/1----put
//    {
//        "name": "macbook11",
//            "description": "High performance system hardcore",
//            "price": 155000
//    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        ProductResponse updated = service.update(id, request);
        return ResponseEntity.ok(updated);
    }
//http://localhost:8080/v2/api/products/1----------->delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.
        noContent().build();
    }

}

