package com.vision.shoppingmall.Product.Model.Entity;

import com.vision.shoppingmall.Category.Model.Entity.Category;
import com.vision.shoppingmall.Product.Model.Request.CreateProductRequest;
import com.vision.shoppingmall.Product.Model.Request.ProductUpdateRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false,unique = true)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "publisher_name", nullable = false, length = 50)
    private String publisherName;

    @Column(name = "author_name", nullable = false, length = 50)
    private String authorName;

    @Column(name = "translator_name", length = 50)
    private String translatorName;

    @Column(name = "purchase_price", nullable = false)
    private int purchasePrice;

    @Column(name = "unit_price", nullable = false)
    private int unitPrice;

    @Column(name = "discount_price")
    private int discountPrice;

    @Column(name = "selling_price", nullable = false)
    private int sellingPrice;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "thumbnail_image_data", nullable = false, columnDefinition = "LONGTEXT")
    private String thumbnailImageData;

    @Column(name = "product_image_data", nullable = false, columnDefinition = "LONGTEXT")
    private String productImageData;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status",nullable = false, length = 10)
    private ProductStatus productStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",  nullable = true, foreignKey = @ForeignKey(name = "fk_product_category"))
    private Category category;

    public static Product create(CreateProductRequest request, Category category){
        return Product.builder()
                .productName(request.getProductName())
                .publisherName(request.getPublisherName())
                .authorName(request.getAuthorName())
                .translatorName(request.getTranslatorName())
                .purchasePrice(request.getPurchasePrice())
                .unitPrice(request.getUnitPrice())
                .discountPrice(request.getDiscountPrice())
                .sellingPrice(request.getSellingPrice())
                .description(request.getDescription())
                .thumbnailImageData(request.getThumbnailImageData())
                .productImageData(request.getDetailImageData())
                .category(category)
                .build();
    }

    public void update(ProductUpdateRequest request, Category category){
        this.productName = request.getProductName();
        this.publisherName = request.getPublisherName();
        this.authorName = request.getAuthorName();
        this.translatorName = request.getTranslatorName();
        this.purchasePrice = request.getPurchasePrice();
        this.unitPrice = request.getUnitPrice();
        this.discountPrice = request.getDiscountPrice();
        this.sellingPrice = request.getSellingPrice();
        this.description = request.getDescription();
        this.thumbnailImageData = request.getThumbnailImageData();
        this.productImageData = request.getDetailImageData();
        this.category = category;
    }

    public void delete(){
        this.productStatus = ProductStatus.InActive;
    }
}