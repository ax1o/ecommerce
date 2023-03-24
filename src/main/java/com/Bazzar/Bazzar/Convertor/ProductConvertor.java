package com.Bazzar.Bazzar.Convertor;

import com.Bazzar.Bazzar.Enum.ProductStatus;
import com.Bazzar.Bazzar.Model.Product;
import com.Bazzar.Bazzar.RequestDto.ProductRequestDto;
import com.Bazzar.Bazzar.ResponseDto.ProductResponseDto;

public class ProductConvertor {

    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto) {
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .category(productRequestDto.getCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();

        return product;
    }

    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .productStatus(product.getProductStatus())
                .build();
    }

}
