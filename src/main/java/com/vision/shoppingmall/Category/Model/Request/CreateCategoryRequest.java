package com.vision.shoppingmall.Category.Model.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCategoryRequest {

    @NotBlank(message = "카테고리 이름은 빌 수 없어")
    private String categoryName;

}
