package com.vision.shoppingmall.Category.Model.Entity;

import com.vision.shoppingmall.Category.Model.Request.CreateCategoryRequest;
import com.vision.shoppingmall.Product.Model.Entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Category_ID")
    private Long id;

    @Column(name = "Category_Name", nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products;

    public static Category create(CreateCategoryRequest request){
        return Category.builder().categoryName(request.getCategoryName()).build();
    }

    public void update(String gotCategoryName){
      this.setCategoryName(gotCategoryName);
    }
}
