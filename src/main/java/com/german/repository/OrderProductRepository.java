package com.german.repository;

import com.german.model.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    @Query("select op from OrderProduct op where op.product.id = :productId " +
            "and op.order.id = :orderId")
    OrderProduct getByOrderIdAndProductId(Long orderId, Long productId);

}
