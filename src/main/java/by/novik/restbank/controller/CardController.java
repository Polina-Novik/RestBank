package by.novik.restbank.controller;

import by.novik.restexample.dto.AnimalRequest;
import by.novik.restexample.dto.AnimalResponse;
import by.novik.restexample.service.AnimalService2;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@Hidden //больше третий не показывается
@Tag(name="Third controller",description = "this is my third controller with transfer object pattern")
@RequiredArgsConstructor
@RestController //отличие в ресте контроллера! тут проще типо.спринг пон что не надо ни на какую страницу отпр вас
@RequestMapping("animals3")
public class ThirdAnimalController {
    private final AnimalService2 animalService2;
    @GetMapping //раньше возвр строчку, теперь лист
    public List<AnimalResponse> findAll() {
        return animalService2.findAll(); //никакой логики и тд
    }

    @GetMapping("{id}")
    public AnimalResponse findById(@PathVariable Long id) {
        return animalService2.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //иначе можно сказать не сохранило. не 200 ок а 201
    public AnimalResponse save(@RequestBody AnimalRequest animal) {

        return animalService2.save(animal);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse update(@PathVariable Long id, AnimalRequest animal) {

        return animalService2.save(id, animal);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //чтоб показыало что все нормально но я ничего не возвращал
    public void delete(@PathVariable Long id) {
        animalService2.delete(id);
    }
    //http://localhost:8080/swagger-ui/index.html
}
