package com.german.rest;


import com.german.model.dto.ProductDto;
import com.german.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(ProductController.BASE_MAPPING)
public class ProductController {
    protected final static String BASE_MAPPING = "/product/v1";
    private final static String ADD_MAPPING = "/add";
    private final static String UPDATE_MAPPING = "/update";
    private final static String GET_MAPPING = "/{id}";
    private final static String DELETE_MAPPING = "/delete/{id}";

    @Autowired
    ProductService service;

    @PostMapping(ADD_MAPPING)
    public ResponseEntity<?> add(@RequestBody ProductDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping(UPDATE_MAPPING)
    public ResponseEntity<?> update(@RequestBody ProductDto dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @GetMapping(GET_MAPPING)
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @DeleteMapping(DELETE_MAPPING)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
