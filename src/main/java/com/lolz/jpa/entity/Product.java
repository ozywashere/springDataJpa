package com.lolz.jpa.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "products",
    schema = "ecommerce",
    uniqueConstraints = {
    @UniqueConstraint(
        name = "sku_unique",
        columnNames = "stock_keeping_unit"),
})
@SequenceGenerator(
    name = "product_generator",
    sequenceName = "product_sequence_name",
    allocationSize = 1
)
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  @Column(name="product_id")
  private Long id;

  @Column(name="stock_keeping_unit")
  private String sku;


  @Column(name="product_name")
  private String name;


  @Column(name="product_description")
  private String description;

  @Column(name="product_image_url")
  private String imageUrl;

  @Column(name="unit_price")
  private Double price;

  @Column(name="unit_sale_price")
  private Double salePrice;

  @Column(name="active")
  private Boolean active;

  @CreationTimestamp
  private LocalDateTime dateCreated;

  @UpdateTimestamp
  private LocalDateTime lastUpdated;





}
