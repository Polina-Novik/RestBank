package by.novik.restbank.service;


import by.novik.restbank.converter.CardConverter;
import by.novik.restbank.dto.CardInformationResponse;
import by.novik.restbank.dto.ClientInformationResponse;
import by.novik.restbank.entity.Card;

import by.novik.restbank.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class CardService {
    private final CardRepository repository;
    private final CardConverter converter;


    public ClientInformationResponse findClientInformation(Long cardNumber, String password) {
        Card card = repository.findCard(cardNumber).orElseThrow(() -> new RuntimeException("entity not found"));
        if (card.getPassword().equals(password)) {
            return converter.convertClient(card);
        } else {
            throw new RuntimeException("Wrong password");
        }

    }

    public CardInformationResponse findCardInformation(Long cardNumber, String password) {
        Card card = repository.findCard(cardNumber).orElseThrow(() -> new RuntimeException("entity not found"));
        if (card.getPassword().equals(password)) {
            return converter.convert(card);
        } else {
            throw new RuntimeException("Wrong password");
        }
    }

    public CardInformationResponse payByCard(Long cardNumber, String password, int price) {
        Card card = repository.findCard(cardNumber).orElseThrow(() -> new RuntimeException("entity not found"));
        if (price < card.getSum() && card.getPassword().equals(password)) {
            card.setSum(card.getSum() - price);
            return converter.convert(repository.save(card));
        } else {
            throw new RuntimeException("mistake");
        }

    }

    public CardInformationResponse remittance(Long cardNumber1, String password1, int price, Long cardNumber2) {
        Card card1 = repository.findCard(cardNumber1).orElseThrow(() -> new RuntimeException("entity not found"));
        Card card2 = repository.findCard(cardNumber2).orElseThrow(() -> new RuntimeException("entity not found"));
        if (price < card1.getSum() && card1.getPassword().equals(password1)) {
            card1.setSum(card1.getSum() - price);
            card2.setSum(card2.getSum() + price);
            converter.convert(repository.save(card2));
            return converter.convert(repository.save(card1));
        } else {
            throw new RuntimeException("mistake");
        }

    }

}
