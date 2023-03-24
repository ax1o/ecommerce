package com.Bazzar.Bazzar.ResponseDto;


import com.Bazzar.Bazzar.Enum.Category;
import com.Bazzar.Bazzar.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

    private String name;
    private int price;
    private Category category;

    private ProductStatus productStatus;

}
