package com.Bazzar.Bazzar.Service;


import com.Bazzar.Bazzar.Convertor.CardConverter;
import com.Bazzar.Bazzar.Exception.CustomerNotFoundException;
import com.Bazzar.Bazzar.Model.Card;
import com.Bazzar.Bazzar.Model.Customer;
import com.Bazzar.Bazzar.Repository.CardRespository;
import com.Bazzar.Bazzar.Repository.CustomerRepository;
import com.Bazzar.Bazzar.RequestDto.CardRequestDto;
import com.Bazzar.Bazzar.ResponseDto.CardResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    CardRespository cardRespository;

    @Autowired
    CustomerRepository customerRepository;


    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {

        Customer customer;

        try{
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Invalid Customer Id");
        }

        //make a card Object
        Card card = CardConverter.cardRequestDtoToCard(cardRequestDto);

        //setting customer
        card.setCustomer(customer);

        //add card to current card list of customer
        customer.getCards().add(card);

        //save customer
        customerRepository.save(customer);

        //prepare Response dto
        return CardConverter.cardToCardResponseDto(card,customer);


    }

}
