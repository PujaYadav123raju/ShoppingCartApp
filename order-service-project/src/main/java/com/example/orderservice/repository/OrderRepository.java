package com.example.orderservice.repository;

//import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.example.orderservice.dto.ApplicationDTO;
import com.example.orderservice.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

	//Order save(ApplicationDTO applicationDTO);

	//Object saves(ApplicationDTO applicationDTO);

	//Object saveAll(com.example.inventoryservice.dto.ApplicationDTO applicationDTO);

}
