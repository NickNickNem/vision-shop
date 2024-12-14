package com.vision.shoppingmall.Product.Controller;

import com.vision.shoppingmall.Product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductAPIController {
    @Autowired
    private ProductService productService;

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") Long ID) {
        productService.deleteProduct(ID);
    }
}
