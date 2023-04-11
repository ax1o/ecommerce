package com.Bazzar.Bazzar.Service;

import com.Bazzar.Bazzar.Convertor.CustomerConverter;
import com.Bazzar.Bazzar.Model.Cart;
import com.Bazzar.Bazzar.Model.Customer;
import com.Bazzar.Bazzar.Repository.CustomerRepository;
import com.Bazzar.Bazzar.RequestDto.CustomerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    public String addCustomer(CustomerRequestDto customerRequestDto){

        Customer customer = CustomerConverter.CustomerRequestDtoToCustomer(customerRequestDto);

        //creating and setting cart
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        //set cart in customer
        customer.setCart(cart);

        //save
        customerRepository.save(customer);


        return "Welcome " + customer.getName()  + " Account created and ready to use";


    }

}
