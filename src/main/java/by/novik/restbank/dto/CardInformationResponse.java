package by.novik.restbank.dto;

import lombok.Data;

@Data
public class CardInformationResponse {


    private Long cardNumber;
    private String name;
    private String surname;
    private String date;
    private int sum;

}
