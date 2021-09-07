package com.german.service;

import com.german.exception.CheckException;
import com.german.model.dto.OrderDto;
import com.german.model.dto.OrderProductDto;
import com.german.model.dto.ProductDto;
import com.german.model.entity.Order;
import com.german.model.entity.OrderProduct;
import com.german.model.entity.Product;
import com.german.model.mapper.OrderMapper;
import com.german.repository.OrderProductRepository;
import com.german.repository.OrderRepository;
import com.german.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.german.exception.CheckExceptionMessages.*;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    OrderMapper mapper;

    @Transactional
    public OrderDto create(OrderDto dto, List<Long> productId) {
        if (dto.getId() != null) {
            throw new CheckException(NOT_NULL_ID);
        }
        Order entity = new Order();
        LocalDateTime now = LocalDateTime.now();
        entity.setDateOfCreation(now);
        entity.setOrderNumber(String.valueOf(now.hashCode()));
        entity.setEmail(dto.getEmail());
        orderRepository.save(entity);
//        createOrderProducts(, productId);
        return mapper.toDto(entity);
    }


    private void createOrderProducts(OrderDto dto, List<Long> productId) {
        for (Long productsId : productId) {
            OrderProduct orderProduct = new OrderProduct();
            Product productEntity = productRepository.findById(productsId)
                    .orElseThrow(() -> new CheckException(NOT_FOUND_PRODUCT));
            Order orderEntity = orderRepository.findById(dto.getId())
                    .orElseThrow(() -> new CheckException(NOT_FOUND_ORDER));
            if (orderProductRepository.findByOrderIdAndProductId(orderEntity.getId(), productEntity.getId()).isEmpty()) {
                throw new CheckException("Связь уже существует.");
            }
            orderProduct.setOrder(orderEntity);
            orderProduct.setProduct(productEntity);
            orderProductRepository.save(orderProduct);
        }
    }

    public List<OrderDto> getAllByEmail(String email) {
        List<Order> orderList = orderRepository.findAllByEmail(email);
        return mapper.toDtoList(orderList);
    }

    public List<OrderDto> findAllBetweenDates(LocalDateTime sDate, LocalDateTime eDate) {
        if (eDate.isBefore(sDate)) {
            throw new CheckException("Неверный запрос");
        }
        List<Order> orderList = orderRepository.findAllByDateOfCreationBetween(sDate, eDate);
        return mapper.toDtoList(orderList);
    }

    public List<OrderDto> findAllByProductArticle(Long article) {
        List<Order> orderList = orderRepository.findAllByArticle(article);
        return mapper.toDtoList(orderList);
    }

    @Transactional
    public OrderDto delete(Long id) {
        Order entity = orderRepository.findById(id)
                .orElseThrow(() -> new CheckException(NOT_FOUND_ORDER));
        orderRepository.delete(entity);
        return mapper.toDto(entity);
    }
}
