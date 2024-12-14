package com.vision.shoppingmall.Product.Repository;

import com.vision.shoppingmall.Product.Model.Entity.Product;
import com.vision.shoppingmall.Product.Model.Entity.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByProductStatus(ProductStatus productStatus, Pageable pageable);
}