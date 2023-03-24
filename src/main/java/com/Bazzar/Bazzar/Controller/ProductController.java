package com.Bazzar.Bazzar.Controller;


import com.Bazzar.Bazzar.RequestDto.ProductRequestDto;
import com.Bazzar.Bazzar.ResponseDto.ProductResponseDto;
import com.Bazzar.Bazzar.Enum.Category;
import com.Bazzar.Bazzar.Model.Product;
import com.Bazzar.Bazzar.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){

        ProductResponseDto productResponseDto;
        try{
             productResponseDto = productService.addProduct(productRequestDto);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(productResponseDto,HttpStatus.CREATED);

    }

    @GetMapping("/get-product-by-category")
    public ResponseEntity getProductByCategory(@RequestParam Category category){

        List<ProductResponseDto> listOfProductDto = productService.getProductByCategory(category);

        return new ResponseEntity(listOfProductDto,HttpStatus.OK);

    }

    //least 5 products in terms of price
    @GetMapping("/five-cheapest-product")
    public ResponseEntity getFiveCheapestProduct(){

        List<ProductResponseDto> listOfProductResponseDto = productService.getFiveCheapestProduct();


        return new ResponseEntity(listOfProductResponseDto,HttpStatus.OK);

    }


}
