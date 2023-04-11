package com.Bazzar.Bazzar.Controller;

import com.Bazzar.Bazzar.RequestDto.CustomerRequestDto;
import com.Bazzar.Bazzar.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){

        String response = customerService.addCustomer(customerRequestDto);

        return new ResponseEntity(response,HttpStatus.CREATED);

    }

    //get customer by id
    //view all customer (can add pagination)
    //delete customer by id
    //get a particular customer by email
    //update customer
    //remove card
    //remove all cards


}
