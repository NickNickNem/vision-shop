package com.vision.shoppingmall.Product.Model.Exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("하아못찾겠다");
    }
}
