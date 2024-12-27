package com.example.cartservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cartservice.entity.CartItem;
import com.example.catalogservice.dto.ApplicationDTO;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> { 
	List<CartItem> findByCustomerId(Long customerId);

	//ApplicationDTO saveAll(ApplicationDTO applicationDTO);
}
