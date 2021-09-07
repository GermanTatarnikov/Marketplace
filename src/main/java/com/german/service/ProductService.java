package com.german.service;

import com.german.exception.CheckException;
import com.german.model.dto.ProductDto;
import com.german.model.entity.Product;
import com.german.model.mapper.ProductMapper;
import com.german.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.german.exception.CheckExceptionMessages.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductMapper mapper;

    @Transactional
    public ProductDto create(ProductDto dto) {
        if (dto.getId() != null) {
            throw new CheckException(NOT_NULL_ID);
        }
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setArticle(dto.getArticle());
        entity.setPrice(dto.getPrice());
        entity.setDeleted(dto.getDeleted());
        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Transactional
    public ProductDto update(ProductDto dto) {
        if (dto.getId() == null) {
            throw new CheckException(NOT_FOUND_ID);
        }
        Product entity = repository.findById(dto.getId())
                .orElseThrow(() -> new CheckException(NOT_FOUND_PRODUCT));

        boolean updated = false;

        if ((dto.getName() != null && entity.getName() == null)
                || (dto.getName() == null && entity.getName() != null)
                || (!entity.getName().equals(dto.getName()))) {
            updated = true;
            entity.setName(dto.getName());
        }

        if ((dto.getArticle() != null && entity.getArticle() == null)
                || (dto.getArticle() == null && entity.getArticle() != null)
                || (!entity.getArticle().equals(dto.getArticle()))) {
            updated = true;
            entity.setArticle(dto.getArticle());
        }

        if ((dto.getPrice() != null && entity.getPrice() == null)
                || (dto.getPrice() == null && entity.getPrice() != null)
                || (!entity.getPrice().equals(dto.getPrice()))) {
            updated = true;
            entity.setPrice(dto.getPrice());
        }

        if ((dto.getDeleted() != null && entity.getDeleted() == null)
                || (dto.getDeleted() == null && entity.getDeleted() != null)
                || (!entity.getDeleted() == (dto.getDeleted()))) {
            updated = true;
            entity.setDeleted(dto.getDeleted());
        }

        if (updated) {
            repository.save(entity);
            return mapper.toDto(entity);
        }
        return null;
    }

    public ProductDto getProductById(Long id) {
        if (id == null) {
            throw new CheckException(NOT_FOUND_PRODUCT);
        }
        Product entity = repository.findById(id).
                orElseThrow(() -> new CheckException(NOT_FOUND_PRODUCT));
        return mapper.toDto(entity);
    }

    @Transactional
    public ProductDto delete(Long id) {
        Product entity = repository.findById(id)
                .orElseThrow(() -> new CheckException(NOT_FOUND_PRODUCT));
        entity.setDeleted(true);
        repository.save(entity);
        return mapper.toDto(entity);
    }
}
