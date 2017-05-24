
import model.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.PersonService;

/**
 * Demonstrates:
 * This class has a main method which is the entry point into the program
 * 1. Spring bean loading from configuration xml file. In this example,
 * we demonstrate a common 'bean wiring' scenario common in modularized production systems.
 * The system is assembled from modular components described in several Spring module xml files.
 * 2. Bean lookup and method invocation
 *
 * @author Harrison Mfula
 * @since 2017
 */

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-All-Modules.xml");

        PersonService greeterService = (PersonService) context.getBean("greeterBean");
        greeterService.greet(); //Validate service is OK

        greeterService.prepareDb();

        Person person = new Person(); //Created the  model.
        person.setName("Edward"); //Imagine this is input from UI
        person.setMessage("Shani bosi"); //Imagine this is input from UI

        greeterService.createPerson(person); //Ask for example, the service to save this person's details to database

        //Mimic a use case that requires the program to retrieve all persons in the database
        for (Person p : greeterService.getAllPersons()) {
            System.out.println("ID: " + p.getId()+ " Name: "+ p.getName() + " Message: "+ p.getMessage());
        }
        //use case to retrieve and update a given person's details
        Person pr = greeterService.readPerson(1); //Read to see content first before update
        pr.setName("UpdatedName");
        pr.setMessage("Moro! miten menee?");
        greeterService.updatePerson(pr);//Update
        greeterService.readPerson(1);//Validate the update

        greeterService.deletePerson(pr.getId());
        greeterService.readPerson(1); //Validate the update
        System.out.println("TOTAL AFTER DELETE: "+ greeterService.getAllPersons().size());//Validate the delete operation
       // System.out.println(greeterService.getAllPersons().get(0).getMessage());
    }
}