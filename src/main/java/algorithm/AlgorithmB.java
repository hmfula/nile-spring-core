package algorithm;

import model.Person;

/**
 *  TODO
 *Demo
 * Algorithm implementation
 * @author Harrison Mfula
 */
public class AlgorithmB extends  AbstractAlgorithm{

    private Person person;

    public AlgorithmB(Person person) {
        super(person);
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
