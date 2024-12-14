package com.vision.shoppingmall.Product.Controller;

import com.vision.shoppingmall.Category.Model.Response.CategoryResponse;
import com.vision.shoppingmall.Category.Service.CategoryService;
import com.vision.shoppingmall.Product.Model.Request.CreateProductRequest;
import com.vision.shoppingmall.Product.Model.Request.ProductUpdateRequest;
import com.vision.shoppingmall.Product.Model.Response.ProductListResponse;
import com.vision.shoppingmall.Product.Model.Response.ProductUpdateResponse;
import com.vision.shoppingmall.Product.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private CategoryService CService;
    private final ProductService PService;


    @GetMapping("")
    public String Products(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<ProductListResponse> products = PService.getProducts(page);
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/new-product")
    public String newProduct(Model model){
        List<CategoryResponse> categories = CService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new CreateProductRequest());

        return "product/product-form";
    }

    @PostMapping("/new-product")
    public String createProduct(@ModelAttribute(name = "product") @Valid CreateProductRequest request, BindingResult result, Model model){
        // 유효성 검사 실패
        if(result.hasErrors()){
            List<CategoryResponse> categories = CService.getAllCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("product", request);

            return "product/product-form";
        }

        // 추가 성공
        PService.createProduct(request);
        return "redirect:/products";
    }

    @GetMapping("/update-product/{ID}")
    public String updateProduct(@PathVariable(value = "ID") Long id, Model model){
        // 뷰 템플릿 리턴
        List<CategoryResponse> categories = CService.getAllCategories();
        model.addAttribute("categories", categories);

        ProductUpdateResponse product = PService.getProduct(id);
        model.addAttribute("product", product);

        return "product/product-form";
    }

    @PostMapping("/update-product/{id}")
    public String updateProduct(@PathVariable(name = "id") Long id, @ModelAttribute(name = "product") @Valid ProductUpdateRequest request, BindingResult result, Model model){
        // 검증 실패
        if(result.hasErrors()){
            List<CategoryResponse> categories = CService.getAllCategories();
            model.addAttribute("categories", categories);

            ProductUpdateResponse product = PService.getProduct(id);
            model.addAttribute("product", product);
            return "product/product-form";
        }

        // 검은사막 후 리다렉
        PService.updateProduct(id, request);
        return "redirect:/products";
    }
}