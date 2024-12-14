package com.vision.shoppingmall.Category.Service;

import com.vision.shoppingmall.Category.Model.Entity.Category;
import com.vision.shoppingmall.Category.Model.Exception.CategoryHasProductsException;
import com.vision.shoppingmall.Category.Model.Exception.CategoryNameDuplicateionException;
import com.vision.shoppingmall.Category.Model.Exception.CategoryNotFoundException;
import com.vision.shoppingmall.Category.Model.Response.CategoryResponse;
import com.vision.shoppingmall.Category.Model.Request.CategoryUpdateRequest;
import com.vision.shoppingmall.Category.Model.Request.CreateCategoryRequest;
import com.vision.shoppingmall.Category.Model.Response.CategoryCreateResponse;
import com.vision.shoppingmall.Category.Model.Response.CategoryListResponse;
import com.vision.shoppingmall.Category.Repository.CategoryRepository;
import com.vision.shoppingmall.Product.Model.Entity.Product;
import com.vision.shoppingmall.Product.Model.Entity.ProductStatus;
import com.vision.shoppingmall.Product.Service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository reposit;
    private final ProductService service;

    public CategoryCreateResponse create(CreateCategoryRequest request){
        // 카테고리 이름 중복 검사
        if(reposit.existsByCategoryName(request.getCategoryName())){
            throw new CategoryNameDuplicateionException();
        }


        // 카테고리 생성
        Category category = Category.create(request);
        Category created = reposit.save(category);

        return new CategoryCreateResponse(created.getId(), created.getCategoryName());
    }

    public Page<CategoryListResponse> getCategories(int page){
        PageRequest request = PageRequest.of(page, 10);
        Page<Category> categories = reposit.findAll(request);
        //List<Category> categories = reposit.findAll();

        return categories.map(category -> new CategoryListResponse(category.getId(), category.getCategoryName(), category.getProducts().stream().filter(product -> product.getProductStatus() == ProductStatus.Active). count()));
    }

    public List<CategoryResponse> getAllCategories(){
        List<Category> categories = reposit.findAll();

        return categories.stream().map(category -> new CategoryResponse(category.getId(), category.getCategoryName())).toList();
    }

    public CategoryResponse findCategory(Long ID){
        Category category = reposit.findById(ID).orElseThrow(CategoryNotFoundException::new);

        return new CategoryResponse(category.getId(), category.getCategoryName());
    }

    public void update(Long ID, CategoryUpdateRequest request){
        // 카테고리가 존재하는지 검증
        Category category = reposit.findById(ID).orElseThrow(CategoryNotFoundException::new);

        // 카테고리명 중복 검사
        if (reposit.existsByCategoryNameAndIdNot(request.getCategoryName(), ID)){
            throw new CategoryNameDuplicateionException();
        }

        // 카테고리 이름 수정
        category.update(request.getCategoryName());
        reposit.save(category);
    }

    @Transactional
    public void deleteCategory(Long ID){
        // 존재 확인
        Category category = reposit.findById(ID).orElseThrow(CategoryNotFoundException::new);

        boolean hasActiveProducts = category.getProducts().stream().anyMatch(product -> product.getProductStatus() == ProductStatus.Active);
        if(hasActiveProducts){
            throw new CategoryHasProductsException();
        }

        List<Product> inActiveProducts = category.getProducts().stream().filter(product -> product.getProductStatus() == ProductStatus.InActive).toList();
        service.removeCategory(inActiveProducts);

        // 삭제
        reposit.deleteById(ID);
    }
}