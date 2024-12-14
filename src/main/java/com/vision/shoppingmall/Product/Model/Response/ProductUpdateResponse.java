package com.vision.shoppingmall.Product.Model.Response;

import com.vision.shoppingmall.Product.Model.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateResponse {
    private Long id;
    private String productName;
    private String publisherName;
    private String authorName;
    private String translatorName;
    private int purchasePrice;
    private int unitPrice;
    private int discountPrice;
    private int sellingPrice;
    private String description;
    private String thumbnailImageData;
    private String detailImageData;
    private Long categoryId;

    public static ProductUpdateResponse from(Product product){
        return new ProductUpdateResponse(product.getId(), product.getProductName(), product.getPublisherName(), product.getAuthorName(), product.getTranslatorName(), product.getPurchasePrice(), product.getUnitPrice(), product.getDiscountPrice(), product.getSellingPrice(), product.getDescription(), product.getThumbnailImageData(), product.getProductImageData(), product.getCategory() != null ? product.getCategory().getId() : null);
    }
}