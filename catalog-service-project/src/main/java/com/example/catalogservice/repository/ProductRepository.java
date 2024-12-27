package com.example.catalogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.catalogservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

}
