package by.novik.restbank.service;

import by.novik.restexample.converter.AnimalConverter;
import by.novik.restexample.dto.AnimalRequest;
import by.novik.restexample.dto.AnimalResponse;
import by.novik.restexample.entity.Animal;
import by.novik.restexample.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class AnimalService2 {
    private final AnimalRepository repository;
    private final AnimalConverter converter;

    public AnimalResponse save(AnimalRequest animalRequest) { //request и response посуто шифруем чтобы не все клиентуопказывать
        Animal animal=converter.convert(animalRequest);
       Animal animal2= repository.save(animal); //тот что сохранился
        AnimalResponse animalResponse=converter.convert(animal2);
        return animalResponse; //получили реквест, конвентировали в животное, сохранили, отослали респонс
       // return converter.convert(repository.save(converter.convert(animalRequest))); - все 4 строчки в 1
    }

    //возвр респонс, берет реквест
    public AnimalResponse save(Long id, AnimalRequest animalRequest) { //то что пришло из реквест возвр конвертируем и шлем в респнс
//        Animal animal=converter.convert(id, animalRequest); - здесь отличие от прошлого
//        Animal animal2= repository.save(animal);
//        AnimalResponse animalResponse=converter.convert(animal2);
//        return animalResponse;
        return converter.convert(repository.save(converter.convert(id, animalRequest)));
    }

    public AnimalResponse findById(Long id) {
         Animal animal=repository.findById(id).orElseThrow(() -> new RuntimeException("entity not found"));
    return converter.convert(animal); //это энимал респонс
    }

    public List<AnimalResponse> findAll() {
        return converter.convert(repository.findAll()); //в сервисе логика конвертируем
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
