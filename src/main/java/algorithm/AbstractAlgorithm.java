package algorithm;

import model.Person;

/**
 *  TODO
 * Demonstrates:
 * #1: Typical algorithm design. All common code in abstract classes
 * #2: Uses the template method design patten to fill the implementation specific gaps
 *
 * @author Harrison Mfula
 * @since
 */
public abstract class AbstractAlgorithm  implements  Algorithm{

    private Person person;

    public AbstractAlgorithm(Person person) {
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
