package com.Bazzar.Bazzar.Service;


import com.Bazzar.Bazzar.Convertor.ProductConvertor;
import com.Bazzar.Bazzar.Enum.ProductStatus;
import com.Bazzar.Bazzar.Exception.SellerNotFoundException;
import com.Bazzar.Bazzar.RequestDto.ProductRequestDto;
import com.Bazzar.Bazzar.ResponseDto.ProductResponseDto;
import com.Bazzar.Bazzar.Enum.Category;
import com.Bazzar.Bazzar.Model.Item;
import com.Bazzar.Bazzar.Model.Product;
import com.Bazzar.Bazzar.Model.Seller;
import com.Bazzar.Bazzar.Repository.ProductRepository;
import com.Bazzar.Bazzar.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException{

        Seller seller;
        //find seller
        try{
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }catch (Exception e){
            throw new SellerNotFoundException("Not a valid seller");
        }

        //setting product
        Product product = ProductConvertor.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);


        //add product to seller
        seller.getProducts().add(product);

        sellerRepository.save(seller);

        //setting response and returning
        return ProductConvertor.productToProductResponseDto(product);


    }

    public List<ProductResponseDto> getProductByCategory(Category category){
        List<Product> products =  productRepository.findByCategory(category);
        List<ProductResponseDto> listOfProducts = new ArrayList<>();

        for(Product product : products){

            ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);
            listOfProducts.add(productResponseDto);

        }

        return listOfProducts;

    }

    public List<ProductResponseDto> getFiveCheapestProduct(){

        //gets top 5 cheapest product
        List<Product> products = productRepository.findCheapestProduct();
        List<ProductResponseDto> listOfResponseDto = new ArrayList<>();

        for(Product product : products){

            ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);
            listOfResponseDto.add(productResponseDto);


        }

        return listOfResponseDto;

    }

}
