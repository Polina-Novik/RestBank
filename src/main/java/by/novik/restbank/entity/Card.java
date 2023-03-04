package by.novik.restbank.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "card")

public class Card {
    @Id
    private Long cardNumber;
    private String name;
    private String surname;
    private int cvv;
    private String date;
    private int sum;
    private int pin;
    private String password;

}
