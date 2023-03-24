package com.Bazzar.Bazzar.Convertor;

import com.Bazzar.Bazzar.RequestDto.SellerRequestDto;
import com.Bazzar.Bazzar.Model.Seller;
import com.Bazzar.Bazzar.ResponseDto.SellerResponseDto;
import lombok.experimental.UtilityClass;


@UtilityClass
public class SellerConvertor {

    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){

        //set seller
        Seller seller = Seller.builder()
                .name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .mobNo(sellerRequestDto.getMobNo())
                .panNo(sellerRequestDto.getPanNo())
                .build();

        return seller;

    }

    public static SellerResponseDto sellerToSellerResponseDto(Seller seller){
       return SellerResponseDto.builder()
                .name(seller.getName())
                .email(seller.getEmail())
                .build();
    }

}
