package com.Bazzar.Bazzar.ResponseDto;

import com.Bazzar.Bazzar.Enum.Category;
import com.Bazzar.Bazzar.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemResponseDto {

    private String productName;
    private int price;
    private Category category;

    private ProductStatus productStatus;

}
