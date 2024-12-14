package com.vision.shoppingmall.Product.Service;

import com.vision.shoppingmall.Category.Model.Entity.Category;
import com.vision.shoppingmall.Category.Model.Exception.CategoryNotFoundException;
import com.vision.shoppingmall.Category.Repository.CategoryRepository;
import com.vision.shoppingmall.Product.Model.Entity.Product;
import com.vision.shoppingmall.Product.Model.Entity.ProductStatus;
import com.vision.shoppingmall.Product.Model.Exception.ProductNotFoundException;
import com.vision.shoppingmall.Product.Model.Request.CreateProductRequest;
import com.vision.shoppingmall.Product.Model.Request.ProductUpdateRequest;
import com.vision.shoppingmall.Product.Model.Response.ProductListResponse;
import com.vision.shoppingmall.Product.Model.Response.ProductUpdateResponse;
import com.vision.shoppingmall.Product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository reposit;
    private CategoryRepository CReposit;

    public void removeCategory(List<Product> products) {
        // 카테고리 id → null 변경
        products.forEach(product -> {
            product.setCategory(null);
            reposit.save(product);
        });
    }

    public void createProduct(CreateProductRequest request){
        // 카테고리 실존 확인
        Category category = CReposit.findById(request.getCategoryId()).orElseThrow(CategoryNotFoundException::new);

        // 등록
        Product product = Product.create(request, category);
    }

    public Page<ProductListResponse> getProducts(int page){
        Pageable pageable = PageRequest.of(page, 10);

        Page<Product> products = reposit.findByProductStatus(ProductStatus.Active, pageable);
        return products.map(ProductListResponse::from);
    }

    public ProductUpdateResponse getProduct(Long id){
         Product product = reposit.findById(id).orElseThrow(ProductNotFoundException::new);
         return ProductUpdateResponse.from(product);
    }

    public void updateProduct(Long id, ProductUpdateRequest request){
        // 제품 유무 확인
        Product product = reposit.findById(id).orElseThrow(ProductNotFoundException::new);
        Category category = request.getCategoryId() != null ? CReposit.findById(request.getCategoryId()).orElseThrow(CategoryNotFoundException::new) : null;

        // 제품 수정
        product.update(request, category);
        reposit.save(product);
    }

    public void deleteProduct(Long id){
        Product product = reposit.findById(id).orElseThrow(ProductNotFoundException::new);

        product.delete();
        reposit.save(product);
    }

}