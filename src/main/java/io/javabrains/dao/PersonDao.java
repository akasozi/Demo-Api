package io.javabrains.dao;

import io.javabrains.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> findAll();

    Optional<Person> findPersonById(UUID id);

    int updatePerson(UUID id, Person person);

    int deletePerson(UUID id);

}
