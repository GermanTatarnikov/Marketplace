package com.german.model.mapper;

import com.german.model.dto.OrderDto;
import com.german.model.dto.ProductDto;
import com.german.model.entity.Order;
import com.german.model.entity.OrderProduct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setEmail(order.getEmail());
        dto.setDateOfCreation(order.getDateOfCreation());
        if (order.getOrderNumber().contains("-")) {
            dto.setOrderNumber(order.getOrderNumber().substring(1));
        } else {
            dto.setOrderNumber(order.getOrderNumber());
        }
        if (order.getOrderProducts() != null) {
            dto.setProducts(order.getOrderProducts().stream().map(OrderProduct::getProduct).map(p -> {
                ProductDto productDto = new ProductDto();
                productDto.setId(p.getId());
                productDto.setArticle(p.getArticle());
                productDto.setDeleted(p.getDeleted());
                productDto.setName(p.getName());
                productDto.setPrice(p.getPrice());
                return productDto;
            }).collect(Collectors.toList()));
        }
        return dto;
    }

    public List<OrderDto> toDtoList(List<Order> orderList) {
        return orderList.stream().map(this::toDto).collect(Collectors.toList());
    }
}

