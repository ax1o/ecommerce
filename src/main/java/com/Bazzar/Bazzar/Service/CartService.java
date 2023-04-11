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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    JavaMailSender emailSender;

    public String addToCart(OrderRequestDto orderRequestDto) throws Exception {

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
//
        Cart cart = customer.getCart();

        int newCost = cart.getCartTotal() + orderRequestDto.getRequiredQuantity()*product.getPrice();
        cart.setCartTotal(newCost);

        // Add item to current cart
        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);
        cart.getItems().add(item);

        customerRepository.save(customer);

        return "Item has been added to your Cart!!";

    }

    public List<OrderResponseDto> checkout(int customerId) throws Exception {

        Customer customer;

        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("customer not found");
        }

        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        Cart cart = customer.getCart();
        int totalCost = cart.getCartTotal();

        for(Item item: cart.getItems()){
            Ordered order = new Ordered();
            order.setTotalCost(item.getRequiredQuantity()*item.getProduct().getPrice());
            order.setDeliveryCharge(0);
            order.setCustomer(customer);
            order.getOrderedItems().add(item);


            Card card = customer.getCards().get(0);
            String cardNo = "";
            for(int i=0;i<card.getCardNo().length()-4;i++)
                cardNo += 'X';
            cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
            order.setCardUsedForPayment(cardNo);

            int leftQuantity = item.getProduct().getQuantity()-item.getRequiredQuantity();
            if(leftQuantity<=0)
                item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
            item.getProduct().setQuantity(leftQuantity);

            customer.getOrders().add(order);

            // prepare response dto
            OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                    .productName(item.getProduct().getName())
                    .orderDate(order.getOrderDate())
                    .quantityOrdered(item.getRequiredQuantity())
                    .cardUsedForPayment(cardNo)
                    .itemPrice(item.getProduct().getPrice())
                    .totalCost(order.getTotalCost())
                    .deliveryCharge(0)
                    .build();

            orderResponseDtos.add(orderResponseDto);
        }

        cart.setItems(new ArrayList<Item>());
        cart.setCartTotal(0);
        customerRepository.save(customer);

//        String text = "Congrats your order with total value "+totalCost+" has been placed";
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        //set email
//        message.setFrom("siddhant");
//        message.setTo(customer.getEmail());
//        message.setSubject("Order Placed from Bazaar");
//        message.setText(text);
//        emailSender.send(message);

        return orderResponseDtos;



    }

}
