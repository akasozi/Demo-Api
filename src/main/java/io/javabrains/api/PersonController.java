package io.javabrains.api;

import io.javabrains.model.Person;
import io.javabrains.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody @Valid @NonNull Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAll();
    }

    @GetMapping(path = "{id}")
    public Person getPerson(@PathVariable("id") UUID id) {

        return personService.getPerson(UUID.randomUUID())
                .orElseThrow(() -> new IllegalStateException("Student with " + id + " not found!"));
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id,
                              @RequestBody Person person) {

        personService.updatePerson(id, person);
    }

    @DeleteMapping(path = "{id}")
    public void deletePerson(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
    }

}
