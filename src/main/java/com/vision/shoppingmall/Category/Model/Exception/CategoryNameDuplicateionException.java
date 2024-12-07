package com.vision.shoppingmall.Category.Model.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CategoryNameDuplicateionException extends RuntimeException {
    public CategoryNameDuplicateionException() {
        super("카테고리 이름이 중복됨");
    }
}

