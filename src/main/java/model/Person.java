package model;

/**
 * Simple Spring bean
 *
 * @author Harrison Mfula
 */
public class Person {

    private String name;
    private int id;
    private String message;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
