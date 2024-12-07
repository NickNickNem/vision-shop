package com.vision.shoppingmall.Category.Repository;

import com.vision.shoppingmall.Category.Model.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCategoryName(String categoryName);
    boolean existsByCategoryNameAndIdNot(String categoryName, Long id);

    Page<Category> findAll(Pageable pageable);
}
