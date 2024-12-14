package com.vision.shoppingmall.Category.Model.Exception;

public class CategoryHasProductsException extends RuntimeException {
    public CategoryHasProductsException() {
        super("제품 있어, FK 충돌");
    }
}
