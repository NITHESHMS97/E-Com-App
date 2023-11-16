package com.nithesh.orderservice.repository;

import com.nithesh.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long > {

}
