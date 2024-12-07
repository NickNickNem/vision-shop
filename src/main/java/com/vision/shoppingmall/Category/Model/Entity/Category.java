package com.vision.shoppingmall.Category.Model.Entity;

import com.vision.shoppingmall.Category.Model.Request.CreateCategoryRequest;
import jakarta.persistence.*;
import lombok.*;

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

    public static Category create(CreateCategoryRequest request){
        return Category.builder().categoryName(request.getCategoryName()).build();
    }

    public void update(String gotCategoryName){
      this.setCategoryName(gotCategoryName);
    }
}
