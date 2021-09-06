package com.german.rest;

import com.german.model.dto.OrderDto;
import com.german.model.entity.Product;
import com.german.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(OrderController.BASE_MAPPING)
public class OrderController {
    protected final static String BASE_MAPPING = "/order/v1";
    private final static String ADD_MAPPING = "/add";
    private final static String GET_ALL_BY_EMAIL_MAPPING = "/getByEmail";
    private final static String GET_ALL_BETWEEN_DATES_MAPPING = "/getByDates";
    private final static String GET_BY_PRODUCT_ARTICLE_MAPPING = "/getByArticle";
    private final static String DELETE_MAPPING = "/delete/{id}";

    @Autowired
    OrderService service;

    @PostMapping(ADD_MAPPING)
    public ResponseEntity<?> add(@RequestBody OrderDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping(GET_ALL_BY_EMAIL_MAPPING)
    public ResponseEntity<List<OrderDto>> findAllByEmail(@RequestParam(name = "email", required = false) String email) {
        return ResponseEntity.ok(service.getAllByEmail(email));
    }

    @GetMapping(GET_BY_PRODUCT_ARTICLE_MAPPING)
    public ResponseEntity<?> findByProductArticle(@RequestParam(name = "article", required = false) Long article) {
        return ResponseEntity.ok(service.findAllByProductArticle(article));
    }

    @GetMapping(GET_ALL_BETWEEN_DATES_MAPPING)
    public ResponseEntity<?> findAllBetweenDates(@RequestParam(name = "sDate", required = false) String sDate,
                                                 @RequestParam(name = "eDate", required = false) String eDate) {
        return ResponseEntity.ok(service.findAllBetweenDates(LocalDateTime.parse(sDate), LocalDateTime.parse(eDate)));
    }

    @DeleteMapping(DELETE_MAPPING)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }


}
