package common;

import model.Person;

/**
 * TODO
 *Typically you need some helper logic common for all kept in one place
 *
 * @author Harrison Mfula
 */
public class PersonServiceUtil {

    private Person person;

    public PersonServiceUtil(Person person) {
        setPerson(person);
    }


    public void greet() {
        System.out.println("Hello " + getPerson().getName() + "!");
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
