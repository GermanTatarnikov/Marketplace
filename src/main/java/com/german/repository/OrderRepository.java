package com.german.repository;

import com.german.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

//    @Query("select o from Order o where o.dateOfCreation >= :sDate and o.dateOfCreation <= :eDate")
//    List<Order> findBetweenTwoDates(LocalDateTime sDate, LocalDateTime eDate);

    @Query("select o from Order o where o.email = :email")
    List<Order> findAllByEmail(String email);

//    List<Order> findAllByEmail(String email);

    Order findByEmail(String email);

    List<Order> findAllByDateOfCreationBetween(LocalDateTime sDate, LocalDateTime eDate);

    @Query("select o from Order o join OrderProduct op on o.id = op.order.id " +
            "join Product p on p.id = op.product.id where p.article = :article")
    List<Order> findAllByArticle(Long article);
}
