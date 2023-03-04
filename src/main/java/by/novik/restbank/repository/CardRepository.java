package by.novik.restbank.repository;


import by.novik.restbank.entity.Card;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CardRepository {
    private final SessionFactory factory;

    public Card save(Card card) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            if (card.getCardNumber() == null) {
                session.persist(card);
            } else {
                session.merge(card);
            }
            transaction.commit();
        }
        return card;
    }

    public Optional<Card> findCard(Long cardNumber) {
        Card card = null;
        try (Session session = factory.openSession()) {
            card = session.find(Card.class, cardNumber);
        }

        return Optional.ofNullable(card);

    }

}



