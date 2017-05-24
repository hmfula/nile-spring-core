package model;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Demonstrates Spring JdbcTemplate.
 * JdbcTemplate simplifies JDBC interaction by removing the need for boilerplate JDBC code.
 * @uthor Harrison Mfula
 * @since  16.5.2017
 */
public class PersonDaoImpl implements PersonDao {

    private JdbcTemplate jdbcTemplate;

    public void create(Person person) {
        //Prepared statement Spring substitute
        String SQL = "INSERT INTO TEST (name, message) VALUES (?, ?)";
        jdbcTemplate.update(SQL, new Object[]{person.getName(), person.getMessage()});
        System.out.println("Record created with Name = " + person.getName()  +  " Message = " + person.getMessage());
    }

    public Person getPerson(Integer id) {
        Person person = null;
        String SQL = "SELECT * FROM TEST WHERE id = ?";
        try{
            //Automatic POJO creation from DB ResultSet using PersonMapper
            person = jdbcTemplate.queryForObject(SQL, new Object[]{id}, new PersonMapper());
            System.out.println("Read record ID =  " + person.getId() + " Name = " + person.getName()  +
                     " Message = " + person.getMessage());
        }catch (EmptyResultDataAccessException emptyResultDataAccessException){

            System.out.println("Record not found in database. Record ID =  " + id);
        }
        return person;
    }

    public List<Person> getAllPersons() {
        String SQL = "SELECT * FROM TEST";
        List<Person> persons = jdbcTemplate.query(SQL, new PersonMapper());
        System.out.println("Retrieved  a total of " + persons.size() + " records");
        return persons;
    }

    public void delete(int id) {
        String SQL = "DELETE FROM TEST WHERE id = ?";
        jdbcTemplate.update(SQL, new Object[]{id});
        System.out.println("Deleted record with ID = " + id );
    }

    public void update(Person person) {
        String sql = "update test set name = ? , message = ? where id = ?";
        jdbcTemplate.update(sql, new Object[] {person.getName(), person.getMessage(), person.getId()});
        System.out.println("Updated record with ID = " + person.getId() );
    }

    /**
     * Needed to fulfill Spring dependency injection (DI).
     * See definition in Spring-Connection.xml:
     *
     *<bean id="personDao" class="model.PersonDaoImpl">
     <property name="jdbcTemplate" ref="jdbcTemplate"/>
     </bean>
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Derby has no support for "DROP TABLE IF EXISTS" statement
     * Hacking it here to proceed
     */
    public void  prepareDb(){
        //This is done only for house keeping
        //Comment the next line when you run this code for the first time then leave it uncommented
        String sql ="DROP TABLE TEST " ;
        jdbcTemplate.execute(sql);

        jdbcTemplate.execute(
                "CREATE TABLE TEST " +
                        "(ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                        " Name VARCHAR(20)," +
                        " Message VARCHAR(20))");
    }

    /**
     *Performs Object to Relation Mapping (ORM) the Spring way by implementing the RowMapper interface
     */
    private class PersonMapper implements RowMapper<Person> {
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt("ID"));
            person.setName(rs.getString("Name"));
            person.setMessage(rs.getString("Message"));
            return person;
        }
    }
}
