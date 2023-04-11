package com.Bazzar.Bazzar.Convertor;

import com.Bazzar.Bazzar.Model.Card;
import com.Bazzar.Bazzar.Model.Customer;
import com.Bazzar.Bazzar.RequestDto.CardRequestDto;
import com.Bazzar.Bazzar.ResponseDto.CardDto;
import com.Bazzar.Bazzar.ResponseDto.CardResponseDto;

import java.util.ArrayList;
import java.util.List;

public class CardConverter {

    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){

        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .build();

    }

    public static CardResponseDto cardToCardResponseDto(Card card, Customer customer){

        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setName(customer.getName());
        List<CardDto> cardDtos = new ArrayList<>();

        for(Card card1 : customer.getCards()){

            CardDto cardDto = CardDto
                    .builder()
                    .cardNo(card1.getCardNo())
                    .cardType(card1.getCardType())
                    .build();

            cardDtos.add(cardDto);

        }

        cardResponseDto.setCardDtos(cardDtos);

        return cardResponseDto;

    }

}
