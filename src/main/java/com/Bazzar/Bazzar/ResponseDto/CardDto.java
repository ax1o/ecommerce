package com.Bazzar.Bazzar.ResponseDto;


import com.Bazzar.Bazzar.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CardDto {

    private String cardNo;
    private CardType cardType;

}
