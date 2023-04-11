package com.Bazzar.Bazzar.Controller;

import com.Bazzar.Bazzar.RequestDto.OrderRequestDto;
import com.Bazzar.Bazzar.ResponseDto.OrderResponseDto;
import com.Bazzar.Bazzar.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;
    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody OrderRequestDto orderRequestDto){

        String response = "";
        try{
            response = cartService.addToCart(orderRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(response,HttpStatus.OK);

    }

    @PostMapping("/checkout/{customerId}")
    public ResponseEntity checkout(@PathVariable int customerId){
        List<OrderResponseDto> orderResponseDtoList;

        try{
            orderResponseDtoList = cartService.checkout(customerId);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(orderResponseDtoList,HttpStatus.OK);

    }

}
