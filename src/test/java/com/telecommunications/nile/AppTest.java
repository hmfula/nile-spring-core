package com.telecommunications.nile;

import com.google.common.collect.Lists;
import junit.framework.TestCase;
import model.Person;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
    private static final List<String> columns = Lists.newArrayList();
    static {
        columns.add("id");
        columns.add("name");
        columns.add("message");
    }

    @Test
    public void test() throws Exception {
        assertNotNull(jdbcTemplate);
        jdbcTemplate.execute("DROP TABLE test");
        jdbcTemplate.execute(
                "CREATE TABLE TEST (ID INT , Name VARCHAR(20),"
                        + " Message VARCHAR(20))");
        insertIntoDb(1,"Harry","Terve");
        //insertIntoDb(2,"Gregory","Privet");

//        String SQL = "select * from TEST";
        String SQL = "select * from TEST  where id  = ?";
//        Person person = (Person) jdbcTemplate.queryForObject(SQL, new Object[]{"Francis"}, new PersonMapper());
        //SELECT: I will adopt this approach because it is the most generic can query or one or more + filter objects
        List<Person> persons = jdbcTemplate.query(SQL, new Object[]{2}, new PersonMapper());
        for (Person person: persons) {
            System.out.println(person.getId() + " " + person.getName() + " " +person.getMessage());
        }
    }

    public void insertIntoDb(Integer id, String name, String message) {
        final SimpleJdbcInsert inserter = new SimpleJdbcInsert(getDataSource())
                .withTableName("test")
                .usingGeneratedKeyColumns("id");
        Map parameters = new HashMap();
        parameters.put("name", name);
        parameters.put("message", message);

        inserter.execute(parameters);
        Person person = new Person();
        person.setId(2);
        person.setName("Francis");
        person.setMessage("Bon zhou");

        insert( person);

        update(person);
        delete(person);
        System.out.println("Hola! " + read().get(0).getMessage());
    }

//CREATE
    public void insert(Person person){
        String sql = "INSERT INTO TEST " +
                "(id, name, message) VALUES (?, ?, ?) ";
        jdbcTemplate.update(sql, new Object[] { person.getId(),
                person.getName(),person.getMessage()
        });

    }

//READ
   public List<Person> read(){
        String SQL = "select * from TEST where name  = ?";
        List<Person> persons = jdbcTemplate.query(SQL, new Object[]{"Harry"}, new PersonMapper());
        return persons;
    }

  //UPDATE
    public void update(Person person){
        String sql = "update test set name = ? where id = ?";//SET NAME GIVEN ID OF PERSON
        jdbcTemplate.update(sql, new Object[] {   "Tillerson", person.getId() });

    }

    //DELETE
    public void delete(Person person){
        String sql = "delete from  test where id = ?";//SET NAME GIVEN ID OF PERSON
        jdbcTemplate.update(sql, new Object[] {person.getId()});

    }


    public class PersonMapper implements RowMapper<Person> {
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setMessage(rs.getString("message"));
            return person;
        }
    }

    /**
     * Using embedded Derby driver.
     */
    private DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        dataSource.setUrl("jdbc:derby:KPIStorageService_PU;create=true");
        /* Connecting Derby or something else, use something like following:
        dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        dataSource.setUrl("jdbc:derby://localhost:1527/db1;create=true");
        */

        dataSource.setUsername("APP");
        dataSource.setPassword("APP");
        return dataSource;
    }

//    private void recreateTable() {
//        try {
//            jdbcTemplate.execute("DROP TABLE test");
//        } catch (DataAccessException e) {
//            System.out.println("drop table error ignored");
//        }
//
//        StringBuilder sb = new StringBuilder("CREATE TABLE test (id integer not null");
//        for (int i = 0; i < columns.size(); i++) {
//            sb.append(String.format(", %1$s real", columns.get(i)));
//        }
//        sb.append(")");
//        jdbcTemplate.execute(sb.toString());
//    }
}
