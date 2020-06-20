package io.javabrains.dao;

import io.javabrains.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao  {

    private static final List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> findAll() {
        return DB;
    }

    @Override
    public Optional<Person> findPersonById(UUID id) {
        Optional<Person> optionalPerson = DB.stream().filter(person -> person.getId().equals(id))
                .findFirst();
        return optionalPerson;
    }

    @Override
    public int updatePerson(UUID id, Person person) {
        Integer rowsAffected = findPersonById(id)
                .map(p -> {
                    int indexOfPersonToDelete = DB.indexOf(p);
                    if (indexOfPersonToDelete >= 0) {
                        DB.set(indexOfPersonToDelete, new Person(id, person.getName()));
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .orElse(0);
        return rowsAffected;

    }

    @Override
    public int deletePerson(UUID id) {
        Optional<Person> optionalPerson = findPersonById(id);
        if (optionalPerson.isEmpty()) {
            return 0;
        }
        DB.remove(optionalPerson.get());
        return 1;
    }


}
