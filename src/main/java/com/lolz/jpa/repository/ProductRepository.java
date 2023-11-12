package com.lolz.jpa.repository;

import com.lolz.jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findByDateCreatedBetween(LocalDateTime start, LocalDateTime end);
}
