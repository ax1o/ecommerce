package com.Bazzar.Bazzar.Convertor;

import com.Bazzar.Bazzar.Model.Customer;
import com.Bazzar.Bazzar.RequestDto.CustomerRequestDto;

public class CustomerConverter {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){

        return Customer.builder().
         name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .email(customerRequestDto.getEmail())
                .mobNo(customerRequestDto.getMobNo())
        .build();

    }

}
