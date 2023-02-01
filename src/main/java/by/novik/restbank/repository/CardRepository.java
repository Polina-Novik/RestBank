package by.novik.restbank.repository;



import by.novik.restexample.entity.Animal;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AnimalRepository {
    private final SessionFactory factory;

    public Animal save(Animal animal) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            if (animal.getId() == null) {
                session.persist(animal);
            } else {
                session.merge(animal);
            }
            transaction.commit();
        }
        return animal;
    }

    public List<Animal> findAll() {
        List<Animal> animals;
        try (Session session = factory.openSession()) {
            animals = session.createQuery("from Animal", Animal.class).getResultList();
        }
        return animals;
    }

    public Optional<Animal> findById(Long id) {
        Animal animal = null;
        try (Session session = factory.openSession()) {
            animal = session.find(Animal.class, id);
        }
        return Optional.ofNullable(animal);
    }


    public void delete(Long id) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            findById(id).ifPresent(session::remove);
            transaction.commit();
        }
    }
}
