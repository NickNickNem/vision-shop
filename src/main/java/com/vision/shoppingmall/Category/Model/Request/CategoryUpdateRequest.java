package com.vision.shoppingmall.Category.Model.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryUpdateRequest {
    @NotBlank(message = "이름이 beer 있으면 안돼")
    private String categoryName;
}
