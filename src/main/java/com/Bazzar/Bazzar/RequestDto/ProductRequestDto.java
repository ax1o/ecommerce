package com.Bazzar.Bazzar.RequestDto;


import com.Bazzar.Bazzar.Enum.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    private String name;
    private int price;
    private int quantity;
    Category category;
    private int sellerId;

}
