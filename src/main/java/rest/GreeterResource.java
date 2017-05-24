package rest;

import model.Person;

/**
 * Simple Spring bean
 *
 * @author Harrison Mfula
 */
public class GreeterResource {

    private Person person;

    public GreeterResource(Person person) {
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
