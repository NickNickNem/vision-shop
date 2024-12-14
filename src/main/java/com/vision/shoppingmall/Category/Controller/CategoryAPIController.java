package com.vision.shoppingmall.Category.Controller;

import com.vision.shoppingmall.Category.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryAPIController {

    @Autowired
    private CategoryService service;

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable(name = "id") Long ID){
        service.deleteCategory(ID);
    }
}