package service;

import model.Person;
import model.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Demo:
 * PersonService API implementation. Allows the service to perform Create, Read, Update and Delete (CRUD) operation
 * on the Domain Objects (DO). This is the core of any application.
 * @author Harrison Mfula
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired //Performs injection using  @Autowired annotation
    private PersonDao personDao;

    private Person person;//Injection performed using xml config file.  Added here for Demo completeness
    /**
     Note:This is for demo purpose only, in production, choose and stick with one of the two methods for consistency
    */

    public PersonServiceImpl(Person person) {
        setPerson(person);
    }

    /**
     * CRUD methods
     * Person is the Domain Object (DO). Represents an example business domain model
     * PersonDao is the Data Access Object (DAO). Hides details  of database interactions
     */
    public void createPerson(Person person) {
        personDao.create(person);
    }
    public Person readPerson(int id) {
        return personDao.getPerson(id);
    }
    public List<Person> getAllPersons() {
        return personDao.getAllPersons();
    }
    public void updatePerson(Person person) {
        personDao.update(person);
    }
    public void deletePerson(int id) {
    personDao.delete(id);
    }

    /**
     * These methods are added only for testing other Spring features
     */
    public void greet() {
        //Validate that name property was indeed set by Spring in xml configuration as 'Nile'
        System.out.println("Hello " + getPerson().getName() + "!");
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public Person getPerson() {
        return person;
    }

    /**
     * Helper method
     */
    public void prepareDb() {
        personDao.prepareDb();
    }
}
