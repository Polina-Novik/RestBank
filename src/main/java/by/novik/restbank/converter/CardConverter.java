package by.novik.restbank.converter;


import by.novik.restbank.dto.CardInformationResponse;
import by.novik.restbank.dto.ClientInformationResponse;
import by.novik.restbank.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardConverter {

    public CardInformationResponse convert(Card card) {
        CardInformationResponse response = new CardInformationResponse();
        response.setCardNumber(card.getCardNumber());
        response.setName(card.getName());
        response.setSurname(card.getSurname());
        response.setDate(card.getDate());
        response.setSum(card.getSum());
        return response;
    }

    public ClientInformationResponse convertClient(Card card) {
        ClientInformationResponse response = new ClientInformationResponse();

        response.setName(card.getName());
        response.setSurname(card.getSurname());

        return response;
    }
}
