package com.lolz.jpa.repository;

import com.lolz.jpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {
  @Autowired
  ProductRepository productRepository;

  @Test
  void saveMethods() {
    // given create
    Product product = new Product();
    product.setName("Product 1");
    product.setDescription("Product 1 Description");
    product.setSku("ABC123");
    product.setPrice(100.00);
    product.setSalePrice(50.00);
    product.setActive(true);
    product.setImageUrl("product1.png");


    // when save
    Product savedProduct = productRepository.save(product);

    // then display
    System.out.println(savedProduct.getId());
    System.out.println(savedProduct);

  }

  @Test
  void saveAllMethod() {
    Product product1 = new Product();
    product1.setName("Product 1");
    product1.setDescription("Product 1 Description");
    product1.setSku("ABC123d");
    product1.setPrice(100.00);
    product1.setSalePrice(50.00);
    product1.setActive(true);
    product1.setImageUrl("product2.png");

    Product product2 = new Product();
    product2.setName("Product 2");
    product2.setDescription("Product 1 Description");
    product2.setSku("ABC123c");
    product2.setPrice(100.00);
    product2.setSalePrice(50.00);
    product2.setActive(true);
    product2.setImageUrl("product3.png");

    productRepository.saveAll(List.of(product1, product2));

  }

  @Test
  void updateUsingSaveMethod() {
    //find or retrive an existing product from the database
    Long id = 2L;
    Product product = productRepository.findById(id).get();

    //update entity information
    product.setName("Product 2 Updated");

    //save the updated product
    productRepository.save(product);
  }

  @Test
  void findByIdMethod() {
    //find or retrive an existing product from the database
    Long id = 1L;
    Product product = productRepository.findById(id).get();
    System.out.println(product);
  }

  @Test
  void findAllMethod() {
    List<Product> products = productRepository.findAll();
    products.forEach((p) -> {
      System.out.println(p.getName());
    });
  }

  @Test
  void deleteByIdMethod() {
    Long id = 2L;

    productRepository.deleteById(id);
  }

  @Test
  void deleteMethod() {
    //find or retrive an existing product from the database
    //id
    Long id = 1L;
    //find product
    Product product = productRepository.findById(id).get();

    //delete product
    productRepository.delete(product);
  }

  @Test
  void deleteAllMethod() {
    productRepository.deleteAll();

  }

  @Test
  void countMethod() {
    long count = productRepository.count();
    System.out.println(count);
  }

  @Test
  void existsByIdMethod() {
    Long id = 1L;
    boolean exists = productRepository.existsById(id);
    System.out.println(exists);
  }

  @Test
  void findByDateCreatedBetweenMethod() {
    LocalDateTime start = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
    LocalDateTime end = LocalDateTime.of(2023, 12, 31, 23, 59, 59);
    List<Product> products = productRepository.findByDateCreatedBetween(start, end);
    products.forEach((p) -> {
      System.out.println(p.getName());
    });
  }

  @Test
  void sortingMethod() {
    String sortBy = "price";
    String sortDir = "desc";

    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    List<Product> products = productRepository.findAll(sort);
    products.forEach((p) -> {
      System.out.println(p.getName());
    });
  }

  @Test
  void sortingByMultipleFieldsMethod() {
    String sortBy = "name";
    String sortDesc = "description";
    String sortDir = "desc";

    Sort sortByName = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    Sort sortByDesc = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortDesc).ascending() : Sort.by(sortDesc).descending();
    Sort groupBySort = sortByName.and(sortByDesc);
    List<Product> products = productRepository.findAll(groupBySort);
    products.forEach((p) -> {
      System.out.println(p.getName());
    });

  }
}