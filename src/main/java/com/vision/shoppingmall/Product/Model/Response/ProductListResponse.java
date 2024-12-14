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
public class ProductListResponse {
    private Long id;
    private String productName;
    private String publisherName;
    private String authorName;
    private String translatorName;
    private int purchasePrice;
    private int unitPrice;
    private int discountPrice;
    private int sellingPrice;

    public static ProductListResponse from(Product product){
        return new ProductListResponse(product.getId(), product.getProductName(), product.getPublisherName(), product.getAuthorName(), product.getTranslatorName(), product.getPurchasePrice(), product.getUnitPrice(), product.getDiscountPrice(), product.getSellingPrice());
    }
}
