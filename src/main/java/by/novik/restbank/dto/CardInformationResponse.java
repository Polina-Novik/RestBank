package by.novik.restbank.dto;

import lombok.Data;

@Data
public class CardResponse {


    private String cardNumber;
    private String name;
    private String surname;
    private String date;
    private int sum;

}
