package com.Bazzar.Bazzar.Service;


import com.Bazzar.Bazzar.Exception.ProductNotFoundException;
import com.Bazzar.Bazzar.Model.Item;
import com.Bazzar.Bazzar.Model.Product;
import com.Bazzar.Bazzar.Repository.ProductRepository;
import com.Bazzar.Bazzar.ResponseDto.ItemResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ProductRepository productRepository;
    public ItemResponseDto viewItem(int productId) throws ProductNotFoundException {

        Product product;
        try{
            product = productRepository.findById(productId).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Product Not Found");
        }

        //setting item
        Item item = new Item();
        item.setProduct(product);
        item.setRequiredQuantity(0);

        //set item in product
        product.setItem(item);

        productRepository.save(product);


        //use builder 
        //convert item to itemResponseDto
        ItemResponseDto itemResponseDto = new ItemResponseDto();
        itemResponseDto.setCategory(product.getCategory());
        itemResponseDto.setPrice(product.getPrice());
        itemResponseDto.setProductName(product.getName());
        itemResponseDto.setProductStatus(product.getProductStatus());

        return itemResponseDto;

    }

}
