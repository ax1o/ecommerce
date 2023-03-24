package com.Bazzar.Bazzar.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerRequestDto {

    private String name;
    private String mobNo;
    private String panNo;

    private String email;

}
