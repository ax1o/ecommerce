package com.Bazzar.Bazzar.Service;


import com.Bazzar.Bazzar.Enum.ProductStatus;
import com.Bazzar.Bazzar.Exception.CustomerNotFoundException;
import com.Bazzar.Bazzar.Exception.ProductNotFoundException;
import com.Bazzar.Bazzar.Model.*;
import com.Bazzar.Bazzar.Repository.CustomerRepository;
import com.Bazzar.Bazzar.Repository.ProductRepository;
import com.Bazzar.Bazzar.RequestDto.OrderRequestDto;

import com.Bazzar.Bazzar.ResponseDto.OrderResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemService itemService;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {

        Customer customer;

        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("customer not found");
        }

        Product product;

        try {
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Invalid Product id");
        }

        if(product.getQuantity() < orderRequestDto.getRequiredQuantity()){

            throw new Exception("Sorry, Required quantity not available");

        }


        Ordered order = new Ordered();
        order.setTotalCost(orderRequestDto.getRequiredQuantity()* product.getPrice());
        order.setDeliveryCharge(40);
        Card card = customer.getCards().get(0);
        String cardNo = "";
        for(int i=0;i<card.getCardNo().length()-4;i++)
            cardNo += 'X';
        cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
        order.setCardUsedForPayment(cardNo);

        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setOrder(order);
        order.getOrderedItems().add(item);
        order.setCustomer(customer);

        int leftQuantity = product.getQuantity()-orderRequestDto.getRequiredQuantity();
        if(leftQuantity<=0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        product.setQuantity(leftQuantity);

        customer.getOrders().add(order);
        Customer savedCustomer = customerRepository.save(customer);
        Ordered savedOrder = savedCustomer.getOrders().get(savedCustomer.getOrders().size()-1);

        //prepare response DTO
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .productName(product.getName())
                .orderDate(savedOrder.getOrderDate())
                .quantityOrdered(orderRequestDto.getRequiredQuantity())
                .cardUsedForPayment(cardNo)
                .itemPrice(product.getPrice())
                .totalCost(order.getTotalCost())
                .deliveryCharge(40)
                .build();

        return orderResponseDto;

    }

}
