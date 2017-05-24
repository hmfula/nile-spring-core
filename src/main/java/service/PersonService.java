package service;

import model.Person;

import java.util.List;

/**
 * Demo:
 * Simple Spring Service Application Programming Interface (API) . It uses a Spring JdbcTemplate to simplify
 * the implementation of the Data Access Object (DAO) that is used to interact with the persistence layer.
 *
 * @author Harrison Mfula
 */
public interface PersonService {

    void greet();

    void setPerson(Person person);

    Person getPerson();

    List<Person> getAllPersons();

    void createPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(int id);

    Person readPerson(int id);

    void prepareDb();

}
