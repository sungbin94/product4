package com.kh.demo.dao;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


@Slf4j
@SpringBootTest
class ProductDAOImplTest {

  @Autowired
  private ProductDAO productDAO;

  @Test
  @DisplayName("상품등록")
  void save() {
    Product product = new Product();
    product.setProductId(12L);
    product.setPname("컴퓨터10");
    product.setQuantity(20L);
    product.setPrice(40000L);

    Long affectedRow = productDAO.save(product);

    log.info("affectedRow={}", affectedRow);
    Assertions.assertThat(affectedRow).isEqualTo(8);
  }

  @Test
  @DisplayName("상품목록")
  void findAll() {
    List<Product> list = productDAO.findAll();
    log.info("상품수={}", list.size());

    list.stream().forEach(product -> log.info(product.toString()));
  }

  @Test
  @DisplayName("상품조회")
  void findByProductId() {
    Optional<Product> findProduct = productDAO.findByProductId(6L);
    log.info("product");
    Assertions.assertThat(findProduct.get().getProductId()).isEqualTo(6);

  }

  @Test
  @DisplayName("상품수정")
  void update() {
    Long productId = 6L;
    Product product = new Product();
    product.setPname("마우스");
    product.setQuantity(20L);
    product.setPrice(25000L);
    int update = productDAO.update(productId, product);

    Optional<Product> updatedProduct = productDAO.findByProductId(productId);

    Assertions.assertThat(updatedProduct.get().getPname()).isEqualTo("마우스");
    Assertions.assertThat(updatedProduct.get().getQuantity()).isEqualTo(20);
    Assertions.assertThat(updatedProduct.get().getPrice()).isEqualTo(25000);

  }

  @Test
  @DisplayName("상품삭제")
  void deleteByProductId() {
    Long productId = 7L;
    Product product = new Product();
    product.setPname("컴퓨터10");
    product.setQuantity(20L);
    product.setPrice(40000L);
    int delete = productDAO.deleteByProductId(productId);

    int deleteProduct = productDAO.deleteByProductId(productId);
  }
}