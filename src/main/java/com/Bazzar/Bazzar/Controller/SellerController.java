package com.Bazzar.Bazzar.Controller;


import com.Bazzar.Bazzar.Exception.PanCardNotFoundException;
import com.Bazzar.Bazzar.Model.Seller;
import com.Bazzar.Bazzar.RequestDto.SellerRequestDto;
import com.Bazzar.Bazzar.ResponseDto.SellerResponseDto;
import com.Bazzar.Bazzar.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){

        SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);

        return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);

    }

    @GetMapping("/all-sellers")
    public ResponseEntity getAllSellers(){

        List<SellerResponseDto> listOfSellers = sellerService.getAllSellers();
        return new ResponseEntity(listOfSellers,HttpStatus.OK);

    }

    //get seller by Pan Card
    @GetMapping("/get-seller-by-pan/{panNo}")
    public ResponseEntity getSellerByPan(@PathVariable("panNo") String panNo){
        SellerResponseDto sellerResponseDto;
        try{
            sellerResponseDto  = sellerService.getSellerByPan(panNo);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(sellerResponseDto,HttpStatus.OK);

    }

    //delete seller



}
