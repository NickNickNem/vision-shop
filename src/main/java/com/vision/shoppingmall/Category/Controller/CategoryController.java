package com.vision.shoppingmall.Category.Controller;

import com.vision.shoppingmall.Category.Model.Request.CategoryResponse;
import com.vision.shoppingmall.Category.Model.Request.CategoryUpdateRequest;
import com.vision.shoppingmall.Category.Model.Request.CreateCategoryRequest;
import com.vision.shoppingmall.Category.Model.Response.CategoryListResponse;
import com.vision.shoppingmall.Category.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping("")
    public String categories(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {

        Page<CategoryListResponse> categories = service.getCategories(page);

        model.addAttribute("categories", categories);

        return "category/list";
    }

    @GetMapping("/new-category")
    public String newCategory(Model model) {
        model.addAttribute("modalTitle", "카테고리 등록하기");
        return "category/category-form";
    }

    @PostMapping("/new-category")
    public String createCategory(@ModelAttribute @Validated CreateCategoryRequest category, BindingResult result){

        if(result.hasErrors()){
            throw new IllegalArgumentException(result.getAllErrors().getFirst().getDefaultMessage());
        }

        service.create(category);

        return "redirect:/categories";
    }

    @GetMapping("/update-category/{id}")
    public String updateCategoryForm(@PathVariable(value = "id") Long ID, Model model) {
        CategoryResponse category = service.findCategory(ID);
        model.addAttribute("category", category);
        return "category/category-form";
    }

    @PostMapping("/update-category/{ID}")
    public String updateCategory(@PathVariable(value = "ID") Long id, @ModelAttribute @Valid CategoryUpdateRequest request, BindingResult result){
        if(result.hasErrors()){
            throw new IllegalArgumentException(result.getAllErrors().getFirst().getDefaultMessage());
        }

        service.update(id, request);

        return "redirect:/categories";
    }
}