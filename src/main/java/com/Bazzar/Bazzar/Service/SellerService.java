package com.Bazzar.Bazzar.Service;


import com.Bazzar.Bazzar.Convertor.SellerConvertor;
import com.Bazzar.Bazzar.Exception.PanCardNotFoundException;
import com.Bazzar.Bazzar.RequestDto.SellerRequestDto;
import com.Bazzar.Bazzar.ResponseDto.SellerResponseDto;
import com.Bazzar.Bazzar.Model.Seller;
import com.Bazzar.Bazzar.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto){

        //setting seller
        Seller seller = SellerConvertor.sellerRequestDtoToSeller(sellerRequestDto);

        //saving in db
        sellerRepository.save(seller);

        //setting responseDTO
        SellerResponseDto sellerResponseDto = new SellerResponseDto();
        sellerResponseDto.setName(seller.getName());
        sellerResponseDto.setEmail(seller.getEmail());

        return sellerResponseDto;

    }

    public List<SellerResponseDto> getAllSellers(){

        List<Seller> sellers = sellerRepository.findAll();
        List<SellerResponseDto> listOfSellers = new ArrayList<>();

        for(Seller seller : sellers){

            SellerResponseDto sellerResponseDto = new SellerResponseDto();
            sellerResponseDto.setName(seller.getName());
            sellerResponseDto.setEmail(seller.getEmail());
            listOfSellers.add(sellerResponseDto);

        }

        return listOfSellers;
    }

    public SellerResponseDto getSellerByPan(String panNo) throws  PanCardNotFoundException{
        SellerResponseDto sellerResponseDto;
        try{
            Seller seller = sellerRepository.findByPanNo(panNo);
           sellerResponseDto = SellerConvertor.sellerToSellerResponseDto(seller);
        }catch (Exception e){
            throw new PanCardNotFoundException("Pan Card Not Found");
        }

        return sellerResponseDto;

    }

}
