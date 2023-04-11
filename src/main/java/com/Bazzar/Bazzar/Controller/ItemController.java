package com.Bazzar.Bazzar.Controller;

import com.Bazzar.Bazzar.ResponseDto.ItemResponseDto;
import com.Bazzar.Bazzar.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/view/{productId}")
    public ResponseEntity viewItem(@PathVariable int productId){

        ItemResponseDto itemResponseDto;

        try{
            itemResponseDto = itemService.viewItem(productId);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(itemResponseDto,HttpStatus.OK);

    }

}
