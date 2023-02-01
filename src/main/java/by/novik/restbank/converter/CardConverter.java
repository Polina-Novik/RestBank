package by.novik.restbank.converter;

import by.novik.restexample.dto.AnimalRequest;
import by.novik.restexample.dto.AnimalResponse;
import by.novik.restexample.entity.Animal;
import org.springframework.stereotype.Component;

import java.util.List;

@Component //говорит что бин не сервис не репозитори, исп чтобы преобразовать 1 тип данных в другой
public class AnimalConverter {
    public List<AnimalResponse> convert(List<Animal> animals) {
        return animals.stream()
                .map(this::convert)
                .toList();
        //берешь лист который тебе пришел и каждый элемент переделывай на энимал респонс. т е для каждого животонго методом что мы ниже написали
    }
    public AnimalResponse convert(Animal animal) {
AnimalResponse response=new AnimalResponse();
response.setId(animal.getId());
response.setName(animal.getName());
response.setLegs(animal.getLegsNumber());
return response; //из объекта из базы данных делает объект, который мы хотим вернуть,тут например спрятали ис флаебле. легс а не легс намбер, тоже можно изм
    }
    public Animal convert(AnimalRequest animalRequest) { //передаем на фронт без айди
        Animal animal=new Animal();
        animal.setName(animalRequest.getName());
        animal.setLegsNumber(animalRequest.getLegs());
        return animal; //здесь все наоборот))
    }
    public Animal convert(Long id, AnimalRequest animalRequest) {
        Animal animal=new Animal();
        animal.setId(id); //на случа когда есть айди
        animal.setName(animalRequest.getName());
        animal.setLegsNumber(animalRequest.getLegs());
        return animal; //здесь все наоборот))
    }
}
