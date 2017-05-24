package model;

import java.util.List;

/**
 * API for the model. It makes clear what methods can  be called on Person object.
 * @uthor Harrison Mfula
 * @since 16.5.2017.
 */
public interface PersonDao {
    /**
     * Creates the model in db
     * @param person POJO
     */
    void create(Person person);

    /**
     * Returns Person POJO given his id
     * @param id
     * @return Person POJO
     */
    Person getPerson(Integer id);

    /**
     * Method used to retrieve Person objects
     * @return list Person objects
     */
    List<Person> getAllPersons();

    /**
     * Method used to update Person information
     * @param person
     */
    void update(Person person);

    /**
     * Method used to  deletePerson a Person from the system
     * @param person
     */
    void delete(int person);

    /**
     * Temporary helper method. Should be removed from here
     */
    void  prepareDb();
}
